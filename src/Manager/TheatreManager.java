package Manager;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;
import BookingManagement.DBConnection;

public class TheatreManager  {
	
	

	 Connection conn =  null ; 

		
	 PreparedStatement  preparedStatement = null;
	  
	public TheatreManager(  Connection conn  )
	{
		this.conn = conn;
	
	}

	
	public int addTheatre( String theatreName,  int ownerId,  int cityId) throws SQLException {
		String s = "insert into theatre(name,theatre_admin_id,city_id) values(?,?,?)";
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,theatreName);
		preparedStatement.setInt(2,ownerId);
		preparedStatement.setInt(3, cityId );
		
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
	}

	
	public int edit(String theatreName, int theatreId) throws SQLException {
		String s = "update theatre set name = ? where id = ? ";
	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,theatreName);
		preparedStatement.setInt(2,theatreId);
	
		
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
	}

	
	public int delete(int theatreId) throws SQLException {
		String s = "delete from theatre where id = ? ";
		
		preparedStatement = conn.prepareStatement(s);
	
		preparedStatement.setInt(1,theatreId);
	
		
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
	}

	
	public boolean isExist() {
	
		return false;
	}

	
	public void displayAll() {
		
		
	}


	public String getTheatreNameById(int theatreId) throws SQLException {
		
		String s = "select name from theatre where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,theatreId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();

		while(res.next())
		{   
			name = res.getString("name");
			
		}
		
		return name;
		
		
		
	}


	public void displayAllMovieInTheatre(int theatreId) throws SQLException {
	
		
			
		String s = "select * from movie where id in ( select movie_id from show where theatre_id = ? )";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,theatreId);
		
		
				
		ResultSet result = preparedStatement.executeQuery();

		while(result.next())
		{   
			 System.out.println( "\nMovie Id :	" + result.getString(1) +  "\nMovie Name : " + result.getString(2) + "\nMovie Category : " + result.getObject(3) + "\nLanguage : " + result.getObject(4) + "\nFilm Certification : " + result.getObject(5) + "\nDuration : " + result.getObject(6)  );

		}
		
	
		
		
	}


	public ResultSet getIdByCity(int cityId) throws SQLException {
		
		String s = "select id from theatre where city_id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,cityId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();

		return res;
	
	}


	public void getIdAndNameByOwnerId(int id) throws SQLException {
	
		String s = "select * from theatre where theatre_admin_id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,id);
		
		String name = null ;
		
		ResultSet res = preparedStatement.executeQuery();
		
		CityManager enumManager = new CityManager(this.conn);
		
		System.out.println("\n==============================================\n");
		System.out.println("\nAll Your Theatre List \n");
		System.out.println("\n==============================================\n");
		while(res.next())
		{   
			System.out.println("\nTheare Id : " + res.getInt("id") + "\nTheatre Name : " + res.getString("name") + "\nCity Name : " + enumManager.getCityNameById( res.getInt("city_id") ) );
			System.out.println("\n==============================================\n");
		}
		
		System.out.println("\n==============================================\n");
	}


	public boolean getExistData(int userId, int theatreId) throws SQLException {
	
		String s = "select id from theatre where theatre_admin_id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,userId);
		
		int idd = -1;
		
		ResultSet res = preparedStatement.executeQuery();
		
		ArrayList<Integer> allTh  = new ArrayList<Integer>();
		
		while( res.next()  )
		{
			idd = res.getInt("id");
			
			allTh.add(idd);
		}
			if( idd >=0 && allTh.contains(theatreId) ) { return true; } else {	
		
		return false;
		
			}
			
	}


	public void getIdAndNameForUser() throws SQLException {
		
		String s = "select * from theatre ";
			
		preparedStatement = conn.prepareStatement(s);
	
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();
		
		System.out.println("\n==============================================\n");
		System.out.println("\nTheatre List To Book Ticket\n");
		System.out.println("\n==============================================\n");
		CityManager enumManager = new CityManager(this.conn);
		while(res.next())
		{   
			System.out.println("\nTheare Id : " + res.getInt("id") + "\nTheatre Name : " + res.getString("name") + "\nCity Name : " +  enumManager.getCityNameById( res.getInt("city_id") ) );
			System.out.println("\n==============================================\n");
		}
		
		System.out.println("\n==============================================\n");
		
	}


	public boolean isExistId(int theatreId) throws SQLException {
		
		String s = "select id from theatre where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,theatreId);
		
		int idd = -1;
		
		ResultSet res = preparedStatement.executeQuery();
		
		ArrayList<Integer> allTh  = new ArrayList<Integer>();
		
		while( res.next()  )
		{
			idd = res.getInt("id");
			
			allTh.add(idd);
		}
			if( idd >=0 && allTh.contains(theatreId) ) { return true; } else {	
		
		return false;
		
			}
	}


	public int getCount() throws SQLException {
		String s = "select count(*) from theatre ";
		
		preparedStatement = conn.prepareStatement(s);
	
		
				
		ResultSet res = preparedStatement.executeQuery();
		int val =0;
		while( res.next() )
		{
			val=	res.getInt(1);
		}
		
		return val;
	}

}
