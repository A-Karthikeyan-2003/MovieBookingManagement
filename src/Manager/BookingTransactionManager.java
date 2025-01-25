package Manager;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import BookingManagement.DBConnection;

public class BookingTransactionManager  {

	  Connection conn =  null ; 
	  
	  PreparedStatement  preparedStatement = null;
	  
	  
	public BookingTransactionManager(  Connection conn  )
	{

		this.conn = conn;
	}

	
	


	public int bookTicket(int theatreId, String dateForMovie, int screenId, int showId, int noOfSeatForThisShow,
			int userId, ArrayList<String> seatsDetails, int totalRate) throws SQLException {
		
		java.sql.Date date = new java.sql.Date(0000-00-00);
		
		 java.util.Date today = new java.util.Date();
		 
		 String[] st = new String[seatsDetails.size()];
				 
				 st =  seatsDetails.toArray(st);
		
		Array seatListForMe = conn.createArrayOf( "text", st );
		
		String s = "insert into booking_transaction( user_id, theatre_id, screen_id, show_id, no_of_booked_seat, total_rate, show_date , status , seat_list, apply_date_and_time ) values(?,?,?,?,?,?,?,?,?,?)";
				 	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,userId);
		preparedStatement.setInt(2,theatreId);
		preparedStatement.setInt(3,screenId);
		preparedStatement.setInt(4,showId);
		preparedStatement.setInt(5,noOfSeatForThisShow);
		preparedStatement.setInt(6, totalRate );
		preparedStatement.setDate(7, date.valueOf(dateForMovie) );
		
		preparedStatement.setInt(8, 1 );
		preparedStatement.setArray(9, seatListForMe );
		preparedStatement.setDate(10, new java.sql.Date(today.getTime())   );
		
	
		
		// status : 1 -> booked, 2-> cancelled
		
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
		
	}


	public int getShowIdByTransactionId(int bookingTicketTransactionId) throws SQLException {
		
		String s = "select show_id from booking_transaction where id = ?";
		 	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,bookingTicketTransactionId);
		 int showId = 0 ;
			
			ResultSet res = preparedStatement.executeQuery();

			while(res.next())
			{   
				showId = res.getInt("show_id");
				
			}
			
			return showId;
	}


	public ArrayList<String> getSeatsDetailsByTransactionId(int bookingTicketTransactionId) throws SQLException {
		String s = "select seat_list from booking_transaction where id = ?";
	 	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,bookingTicketTransactionId);
		 String[]  st  ;
			Array as = null;
			ResultSet res = preparedStatement.executeQuery();

			while(res.next())
			{   
				as = res.getArray("seat_list");
				
			}
			
			String[] seat_detail_new = ( String[] )as.getArray() ;
			
			ArrayList<String> stt =new ArrayList<String>();
			
			for(int i=0; i<seat_detail_new.length; i++)
			{
				stt.add( seat_detail_new[i]  );
			}
			
			return stt;
	}


	public int getNoOfSeatForThisShowByTransactionId(int bookingTicketTransactionId) throws SQLException {

		String s = "select no_of_booked_seat from booking_transaction where id = ?";
		 	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,bookingTicketTransactionId);
		 int bookedSeat = 0 ;
			
			ResultSet res = preparedStatement.executeQuery();

			while(res.next())
			{   
				bookedSeat = res.getInt("no_of_booked_seat");
				
			}
			
			return bookedSeat;
	}


	public int cancelTickets(int bookingTicketTransactionId) throws SQLException {

		 String h = "UPDATE booking_transaction SET status = ? , apply_date_and_time = ?  where id = ?";
		 
		 java.util.Date today = new java.util.Date();
		 
		 preparedStatement = conn.prepareStatement(h);
		 preparedStatement.setInt(1,0 );
		 preparedStatement.setDate(2, new java.sql.Date(today.getTime())   );
		 preparedStatement.setInt(3,bookingTicketTransactionId);
	
		 int r = preparedStatement.executeUpdate();
		 return r;
	}


	public ResultSet viewAllByUserId(int userId) throws SQLException {
		
		String s = "select * from booking_transaction where user_id = ?";
	 	
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,userId);
		
			
			ResultSet res = preparedStatement.executeQuery();

		
			return res;
	}


	public ResultSet viewAllByTheatreId(int theatreId) throws SQLException {
	
		String s = "select * from booking_transaction where theatre_id = ?";
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,theatreId);
		
			
			ResultSet res = preparedStatement.executeQuery();

			
			return res;
		
	}


	public boolean getExistData(int bookingTicketTransactionId, int userId) throws SQLException {
		
		String s = "select id from booking_transaction where user_id = ?";
		
		
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
			if( idd >=0 && allTh.contains( bookingTicketTransactionId) ) { return true; } else {	
		
		return false;
		
			}
	}
	
	public int getCount() throws SQLException {
		
		String s = "select count(*) from booking_transaction";
		
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
