package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BookingManagement.DBConnection;
import DataModels.User;

public class UserManager  {
	
	  Connection conn =  null ; 

		
	  PreparedStatement  preparedStatement = null;
	  
	public UserManager( Connection conn   )
	{
			this.conn = conn;
	}

	
	public int addUser(String username, String email, String password, long contact, String dateOfBirth, int choiceForUserType) throws SQLException {

		java.sql.Date date = new java.sql.Date(0000-00-00);
		
		
		String s = "insert into users(name,email,password,contact,date_of_birth,role) values(?,?,?,?,?,?)";
			
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,username);
		preparedStatement.setString(2,email);
		preparedStatement.setString(3,password);
		preparedStatement.setLong(4,contact);
		preparedStatement.setDate(5, date.valueOf(dateOfBirth) );
		preparedStatement.setInt(6, choiceForUserType);
	
		int res = preparedStatement.executeUpdate();  
		
		return res;		
	}
	
	//========================================================================================

	
	public int edit() {
		
		return 0;
	}

	
	public int delete() {
	
		return 0;
	}

	//========================================================================================
	
	
	public User isExistUser(String email, String password) throws SQLException {
	
		String s1 = "select * from users where email = ? and password = ? and role = ?";
		
		preparedStatement = conn.prepareStatement(s1);
		
		preparedStatement.setString(1,email);
		preparedStatement.setString(2,password);
		preparedStatement.setInt(3,1);
		
		ResultSet userResult = preparedStatement.executeQuery();
		
		int c1=0; 
		User user = null;
		while( userResult.next() ) {
			c1++;
			
			 user = new User(
					
					userResult.getString(2),
					userResult.getString(3),
					userResult.getString(4),
					userResult.getString(5),
					userResult.getString(6),
					userResult.getInt(1)
					
					);
			
			}
		
		if ( c1 == 1 ) { return user; }
		
		return user;
		
	}
	
	//========================================================================================

	
	public User displayAll(String email, String password) throws SQLException {
		
		String s1 = "select * from users where email = ? and password = ? ";
		
		preparedStatement = conn.prepareStatement(s1);
		
		preparedStatement.setString(1,email);
		preparedStatement.setString(2,password);
		
		ResultSet userResult = preparedStatement.executeQuery();
		
		int c1=0; 
		User user = null;
		while( userResult.next() ) {
			c1++;
			
			 user = new User(
					
					userResult.getString(2),
					userResult.getString(3),
					userResult.getString(4),
					userResult.getString(5),
					userResult.getString(6),
					userResult.getInt(1)
					
					);
			
			}
		
		if ( c1 == 1 ) { return user; }
		
		return user;
		
	}


//	public boolean containAccount(String email) {
//	
//		return false;
//	}
	
	
	public int getCount() throws SQLException {
		
		String s = "select count(*) from users where role = ?";
		
		preparedStatement = conn.prepareStatement(s);
		preparedStatement.setInt(1,1);
		
				
		ResultSet res = preparedStatement.executeQuery();
		
		int val =0;
		while( res.next() )
		{
			val=	res.getInt(1);
		}
		
		return val;
	}
	

}
