package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import BookingManagement.DBConnection;
import DataModels.TheatreOwner;

public class TheatreAdminManager {

	

	  Connection conn =  null ; 

		
	  PreparedStatement  preparedStatement = null;
	  
	public TheatreAdminManager(   Connection conn )
	{
		this.conn = conn;
	   
	}

	
	//========================================================================================
	
	public int addTheatreAdmin(String username,String  email,String password, long contact,String documentProofId, String dateOfBirth, int choiceForUserType) throws SQLException {
		
		java.sql.Date date = new java.sql.Date(0000-00-00);

		String s = "insert into users(name,email,password,contact,date_of_birth,document_proof_id,role) values(?,?,?,?,?,?,?)";
			
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,username);
		preparedStatement.setString(2,email);
		preparedStatement.setString(3,password);
		preparedStatement.setLong(4,contact);
		preparedStatement.setDate(5, date.valueOf(dateOfBirth) );
		preparedStatement.setString(6,documentProofId);
		preparedStatement.setInt(7,choiceForUserType);
		
		
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
	
	
	
	public TheatreOwner isExistTheatreAdmin(String email, String password) throws SQLException {

		String s = "select * from users where email = ? and password = ? and role = ?";
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,email);
		preparedStatement.setString(2,password);
		preparedStatement.setInt(3,2);
		
		ResultSet theatreAdminResult = preparedStatement.executeQuery(); 

		int c=0; 
		TheatreOwner theatreOwner = null;
		while(theatreAdminResult.next())
		
		{
			c++;
			
			theatreOwner = new TheatreOwner(
					
					theatreAdminResult.getInt(1),
					theatreAdminResult.getString(2),
					theatreAdminResult.getString(3),
					theatreAdminResult.getString(4),
					theatreAdminResult.getString(5),
					theatreAdminResult.getString(6),
					theatreAdminResult.getString(7)
				
					);
		
		}
		
		if ( c==1 ) { return theatreOwner; }
		
		return theatreOwner;
	}

	
	//========================================================================================
	
	public void displayAll() {
		
		
	}


	public int getCount() throws SQLException {
		
			String s = "select count(*) from users where role=?";
			
			preparedStatement = conn.prepareStatement(s);
		
			preparedStatement.setInt(1,2);
					
			ResultSet res = preparedStatement.executeQuery();
			
			int val =0;
			while( res.next() )
			{
				val=	res.getInt(1);
			}
			
			return val;
		}



	
}
