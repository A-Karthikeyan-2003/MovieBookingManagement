package Manager;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.sql.Types;
import java.time.Instant;
import java.time.LocalTime;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Date;

import BookingManagement.DBConnection;

public class ShowManager {
	

	  Connection conn =  null ; 

		
	  PreparedStatement  preparedStatement = null;
	  
	public ShowManager(   Connection conn )
	{


		  this.conn = conn;

		
	}
	
	//====================================================================
	
	
	public int addShows( int theatreId, int screenId, String showName, String timeStart, int movieId, String datesForShow, int rate, String rowCol ) throws SQLException {
		
		java.sql.Date date = new java.sql.Date(0000-00-00);
		
		
		
		String[] st = datesForShow.split(",");
	
		
		Date[] arr = new Date[st.length];
		
		String g = rowCol.substring(1,rowCol.length()-1);
		
		String[] sts = g.split(",");
		
		int row = Integer.parseInt(  sts[0]  );
		
		int col = Integer.parseInt(  sts[1]  );
		
		boolean[][] seats = new boolean[row][col];
		
		Array seat_new = conn.createArrayOf( "boolean", seats );
		
		for(int i=0; i<st.length; i++)
		{
			
			String s = "insert into show(name,theatre_id,screen_id,movie_id,start_time,rate, show_date, seat_arrange , total_ticket ) values(?,?,?,?,?,?,?,?,?)";
			
			preparedStatement = conn.prepareStatement(s);
			
			preparedStatement.setString(1,showName);
			preparedStatement.setInt(2,theatreId);
			preparedStatement.setInt(3,screenId  );
			preparedStatement.setInt(4, movieId );
			
		
			
			LocalTime lt = LocalTime.parse(timeStart);
			preparedStatement.setObject(5, lt);
			preparedStatement.setInt(6,rate );
			
			int tot_tic = row*col;
			preparedStatement.setInt(9,tot_tic );
			
			
			
			arr[i] = date.valueOf(st[i]);
			
			preparedStatement.setArray( 8 , seat_new );
			
			preparedStatement.setObject( 7, arr[i] , Types.OTHER );
			
			preparedStatement.executeUpdate();  
			
		}
		
	

	
		
	//	int res = preparedStatement.executeUpdate();  
		
	
		
		return 1;	
		
		
	}
	
	//====================================================================
	
	
public int getShowRateById(int showId) throws SQLException {

	String s = "select rate from show where id = ?";
	
	
	preparedStatement = conn.prepareStatement(s);
	
	preparedStatement.setInt(1,showId);
	
	 int rate = 0 ;
			
	ResultSet res = preparedStatement.executeQuery();

	while(res.next())
	{   
		rate = res.getInt("rate");
		
	}
	
	return rate;
	
	
	}


//====================================

	
public int edit(String showName, int showId) throws SQLException {
	String s = "update show set name = ? where id = ? ";

	preparedStatement = conn.prepareStatement(s);
	
	preparedStatement.setString(1,showName);
	preparedStatement.setInt(2,showId);

	
	
	int res = preparedStatement.executeUpdate();  
	
	return res;	
}


public int delete(int showId) throws SQLException {
	String s = "delete from show where id = ? ";
	
	preparedStatement = conn.prepareStatement(s);

	preparedStatement.setInt(1,showId);

	
	
	int res = preparedStatement.executeUpdate();  
	
	return res;	
}
	
	public boolean isExist() {
	
		return false;
	}

	
	public void displayAll() {
		
		
	}

	//====================================================================
	
	public int updateSeat(int showId, ArrayList<String> seatsDetails, int totalNoOfBookedSeat, int status) throws SQLException {
		
		String s = "select seat_arrange, total_ticket from show where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,showId);
		
		Array seat_arrange = null ;
		
		int tt = 0;
				
		ResultSet res = preparedStatement.executeQuery();

		
		while(res.next())
		{   
			seat_arrange = res.getArray("seat_arrange");
			
			tt = res.getInt("total_ticket");
			
		}
		
		if( seat_arrange == null || tt == 0 ) { return 0; }
		
		if( status == 1 ) {
			
		tt = tt-totalNoOfBookedSeat;
		
			Object[][] seat_arrange_new = ( Object[][] )seat_arrange.getArray() ;
			
		
	
			for(String seat : seatsDetails )
			{
				String[] st = seat.split("-");
				
				int row = ( (int) st[0].charAt(0) ) - 65;
				
				int col = Integer.parseInt( st[1] ) - 1 ;
				
				seat_arrange_new[row][col] = true;
				
			}
			
			 Array afterUpdateArray = conn.createArrayOf("boolean", seat_arrange_new );
	
			 String h = "UPDATE show SET seat_arrange = ?, total_ticket = ?  where id = ?";
			 
			 preparedStatement = conn.prepareStatement(h);
				
			 preparedStatement.setArray(1,afterUpdateArray );
			 preparedStatement.setInt(2, tt);
			 preparedStatement.setInt(3,showId);
		
			 int r = preparedStatement.executeUpdate();
			 return r;
			 
		}
		else {
			
			
			tt = tt+totalNoOfBookedSeat;
			
			Object[][] seat_arrange_new = ( Object[][] )seat_arrange.getArray() ;
			
		
	
			for(String seat : seatsDetails )
			{
				String[] st = seat.split("-");
				
				int row = ( (int) st[0].charAt(0) ) - 65;
				
				int col = Integer.parseInt( st[1] ) - 1 ;
				
				seat_arrange_new[row][col] = false;
				
			}
			
			 Array afterUpdateArray = conn.createArrayOf("boolean", seat_arrange_new );
	
			 String h = "UPDATE show SET seat_arrange = ?, total_ticket = ?  where id = ?";
			 
			 preparedStatement = conn.prepareStatement(h);
				
			 preparedStatement.setArray(1,afterUpdateArray );
			 preparedStatement.setInt(2, tt);
			 preparedStatement.setInt(3,showId);
		
			 int r = preparedStatement.executeUpdate();
			 return r;
			
			
		}
		
	}

	
	//======================================================================
	

	public ResultSet getShowByTheatreId(int theatreId) throws SQLException {
		

		String s = "select * from show where theatre_id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,theatreId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();
		
		return res;
	}


	
	//====================================================================
	
	public Object[][] getSeatById(int showId) throws SQLException {
		
	String s = "select seat_arrange from show where id = ?";
		
	
	
	Array seat_arrange = null ;

	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,showId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();

		while(res.next())
		{   seat_arrange = res.getArray("seat_arrange");
			
			
		}
		
		if( seat_arrange == null  ) { return null; }
		
		
		Object[][] seat_arrange_new = ( Object[][] )seat_arrange.getArray() ;
		
		return seat_arrange_new;
	}

	
	//====================================================================

	public ResultSet getTheatreIdByMovieId(int movieId) throws SQLException {
		
		String s = "Select DISTINCT theatre_id from show where movie_id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,movieId);
		
		int tid=0;
				
		ResultSet res = preparedStatement.executeQuery();


		return res;
		
	}

	//====================================================================
	

	public boolean isValidBooking(int showId, int noOfSeatForThisShow, ArrayList<String> seatsDetails) throws SQLException {
		
		
		String s = "Select total_ticket, seat_arrange from show where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1, showId);
		
		int noOfAvailSeat=0; 
		Array seat_arrange = null ;
				
		ResultSet res = preparedStatement.executeQuery();
		
		while( res.next() )
		{
			noOfAvailSeat  = res.getInt("total_ticket");
			
			seat_arrange = res.getArray("seat_arrange");
		}
		
		if( seat_arrange == null ||  noOfAvailSeat == 0 ||  ( noOfAvailSeat - noOfSeatForThisShow ) <=0 ) { return false; }
		
		
		Object[][] seat_arrange_new = ( Object[][] )seat_arrange.getArray() ;
		
		boolean isAvailable = true;
		
		for( String seat : seatsDetails )
		{
			 String[] st = seat.split("-");
			
			int row = ( (int) st[0].charAt(0) ) - 65;
			
			int col = Integer.parseInt( st[1] ) - 1 ;
			
//			if( !( ( row <= seat_arrange_new.length && row >=1 ) && ( col <= seat_arrange_new.length && col >=1 ) ) ) { 
//			
//			isAvailable = false;
//			break; 
//			
//			}
			
			if( (boolean) seat_arrange_new[row][col] == true) {
				isAvailable = false;
				break;
			}
		}
		
		return isAvailable;
	}

	
	//====================================================================
	
	

	public void diplsayAllForThisScreen(int screenId) throws SQLException {
		
		String s = "select * from show where screen_id = ?";
			
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,screenId);
		
		ResultSet res = preparedStatement.executeQuery();
		
		System.out.println("\n==============================================\n");
		System.out.println("\nAll Your Screen List To Book Ticket\n");
		System.out.println("\n==============================================\n");
		while(res.next())
		{   
			System.out.println("\nShow Id : " + res.getInt("id") + "\nShow Name : " + res.getString("name"));
			System.out.println("\n==============================================\n");
		}
		
		System.out.println("\n==============================================\n");
	}
	
	//===========================================================================
	
	

	public boolean isExistId(int showId, int screenId) throws SQLException {
		
		String s = "select id from show where screen_id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,screenId);
		
		int idd = -1;
		
		ResultSet res = preparedStatement.executeQuery();
		
		ArrayList<Integer> allTh  = new ArrayList<Integer>();
		
		while( res.next()  )
		{
			idd = res.getInt("id");
			
			allTh.add(idd);
		}
			if( idd >=0 && allTh.contains(showId) ) { return true; } else {	
		
		return false;
		
			}
	}

//==========================================================================================
	

	public int getCount() throws SQLException {
		
			String s = "select count(*) from show ";
			
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
