package application;

import java.io.IOException;
import java.sql.Connection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private PasswordField passwordText;
    @FXML
    private TextField userText;
    @FXML
    private Button loginButton;
    @FXML
    private Text backToCreate;
    @FXML
    private TextField newUserText;
    @FXML
    private PasswordField newPasswordText;
    @FXML
    private Text backToLogin;
    @FXML
    private Button createButton;
    @FXML
    private Text displayCreateAccount;

    UserDAO userEdit = new UserDAO();
    
    Connection connection = null;
	Stage stage;
	Parent root;
	User user;

    public LoginController() {
        connection = ConnectionUtil.getConnection();
    }
    
    @FXML
	void handleClickAction(MouseEvent event) throws IOException {
		if (event.getSource() == backToCreate) {
			// get reference to the button's stage
			stage = (Stage) backToCreate.getScene().getWindow();
			// load up OTHER FXML document
			root = FXMLLoader.load(getClass().getResource("/application/NewAccount.fxml"));
		} else {
			stage = (Stage) backToLogin.getScene().getWindow();
			root = FXMLLoader.load(getClass().getResource("/application/Login.fxml"));
		}
		// create a new scene with root and set the stage
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
    }
    

    @FXML
    void loginButtonPressed(ActionEvent event) {
        String username = userText.getText().toString();
        String password = passwordText.getText().toString();
       
        try {
        	if (userEdit.executeLogin(username, password) != null) {
        		user = userEdit.executeLogin(username, password).get();
    			stage = (Stage) loginButton.getScene().getWindow();
    			root = FXMLLoader.load(getClass().getResource("/application/HomePage.fxml"));
    			Scene scene = new Scene(root);
    			stage.setScene(scene);
    			stage.show();
        	}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @FXML
    void forgotPasswordPressed(MouseEvent event) {

    }
    

    @FXML
    void createAccount(ActionEvent event) {
    	if (newUserText.getText().length() <= 3 && newUserText.getText().length() <= 3) {
    		System.out.println("INVALID");
    		displayCreateAccount.setText("Invalid username or password. Please enter more than 3 characters.");
    	} else {
    		user = new User(newUserText.getText(),newPasswordText.getText(), 0);
    		userEdit.createNewUser(user);
    		displayCreateAccount.setText("Account created!");
    	}
    	
    }

}
