package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	

    private static final String URL = "jdbc:mysql://localhost:3306/userdb";
    private static final String USER = "user";
    private static final String PASS = "pass";
    
    /**
     * Get a connection to database
     * @return Connection object
     */
    public static Connection getConnection() {
      try {
          return DriverManager.getConnection(URL, USER, PASS);
      } catch (SQLException ex) {
          throw new RuntimeException("Error connecting to the database", ex);
      }
    }
    
}
    