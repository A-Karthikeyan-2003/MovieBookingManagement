package BookingManagement;

import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	
	  private final String url = "jdbc:postgresql://localhost:5432/BookingManagement";
	  private final String user = "postgres";
	  private final String password = "user";
	  
	  private static DBConnection dbConnectionObj = null;
	  
	  private DBConnection() {
		  
	  }
	  
	  public static  DBConnection getInstance() {
		  
		  if( dbConnectionObj == null )
		  {
			  dbConnectionObj = new DBConnection();
		  }
		  
		  return dbConnectionObj;
	  }
	    
	    public Connection connect() {
	        Connection conn = null;
	        try {
	        
	            conn = DriverManager.getConnection(url, user, password);
	            
	        } catch (SQLException e) {
	            System.out.println(e.getMessage());
	        }

	        return conn;
	    }

}
