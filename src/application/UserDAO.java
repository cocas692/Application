package application;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Optional;
import javafx.scene.Parent;
import javafx.stage.Stage;

public class UserDAO {
	
    Connection connection = null;
    PreparedStatement preparedStatement = null;
    ResultSet resultSet = null;
	Stage stage;
	Parent root;
    
    public UserDAO() {
    	connection = ConnectionUtil.getConnection();
    }
	 
	public void createNewUser(User user) {
        String sql = "INSERT INTO users VALUES (" + "'"+user.getUsername()+"'" + "," + "'" + user.getPassword()+"'" + "," + "'"+ user.isAdmin() +"'" + ")";
        
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.executeUpdate();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
	}
	
	public Optional<User> executeLogin(String username, String password) {
		Optional<User> user = null;
		User temp = new User();
		String sql = "SELECT * FROM users WHERE username = ? and pass = ?";

        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                System.out.println("Failed to login");
                return null;
            } else {
            	System.out.println("Login successfull");
            	temp.setUsername(username);;
            	temp.setPassword(password);
            	temp.setAdmin(resultSet.getInt(3));
            	user = Optional.ofNullable(temp);
            	return user;
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
	}

}
