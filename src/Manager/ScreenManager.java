package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import BookingManagement.DBConnection;

public class ScreenManager   {


	  Connection conn =  null ; 

		
	  PreparedStatement  preparedStatement = null;
	  
	public ScreenManager( Connection conn  )
	{
		
		   
		  this.conn = conn;
		
	}

	
	public int edit(String screenName, int screenId) throws SQLException {
		String s = "update screen set name = ? where id = ? ";
	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,screenName);
		preparedStatement.setInt(2,screenId);
	
		
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
	}

	
	public int delete(int screenId) throws SQLException {
		String s = "delete from screen where id = ? ";
		
		preparedStatement = conn.prepareStatement(s);
	
		preparedStatement.setInt(1,screenId);
	
		
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
	}

	
	public boolean isExist() {
	
		return false;
	}

	
	public String getRowColById(int screenId) throws SQLException
	{
		String s = "select row_col from screen where id = ?";
		

		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,screenId);
		
		String rowCol = null;
				
		ResultSet res = preparedStatement.executeQuery();

		while(res.next())
		{   System.out.println( res.getObject("row_col") );
			rowCol = String.valueOf( res.getObject("row_col") );
			
		}
		
		return rowCol;
		
	}
	
	public void displayAll() {
		
		
	}


	public int addScreen(int theatreId, String screenName, int row ,  int col) throws SQLException {
	
		String s = "insert into screen(name,theatre_id,row_col) values(?,?,?)";
		
		String v = "(";
		v+=String.valueOf(row);
		v+=",";
		v+=String.valueOf(col);
		v+=")";
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,screenName);
		preparedStatement.setInt(2,theatreId);
		preparedStatement.setObject(3, v , Types.OTHER  );
		
		
		int res = preparedStatement.executeUpdate();  
		
	
		
		return res;	
	}



	public String getScreenNameById(int screenId) throws SQLException {
	
		String s = "select name from screen where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,screenId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();

		while(res.next())
		{   
			name = res.getString("name");
			
		}
		
		return name;
		
		
	}



	public void getIdAndNameOfScreenByOwnerId(int theatreId) throws SQLException {
		
		String s = "select * from screen where theatre_id = ?";
			
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,theatreId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();
		
		System.out.println("\n==============================================\n");
		System.out.println("\nAll Your Screen List \n");
		System.out.println("\n==============================================\n");
		while(res.next())
		{   
			System.out.println("\nScreen Id : " + res.getInt("id") + "\nScreen Name : " + res.getString("name"));
			System.out.println("\n==============================================\n");
		}
		
		System.out.println("\n==============================================\n");
		
	}



	public boolean getExistData(int screenId, int theatreId) throws SQLException {
		
		String s = "select id from screen where theatre_id = ?";
		
		
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
			if( idd >=0 && allTh.contains(screenId) ) { return true; } else {	
		
		return false;
		
			}
	}


	public int getCount() throws SQLException {
		
			String s = "select count(*) from screen ";
			
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
