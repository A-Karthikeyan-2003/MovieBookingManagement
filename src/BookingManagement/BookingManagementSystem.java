package BookingManagement;


import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Scanner;
import AccessService.MainAdminAccess;
import AccessService.NormalAccess;
import AccessService.TheatreOwnerAccess;
import AccessService.UserAccessService;
import DataModels.Roles;
import DataModels.TheatreOwner;
import DataModels.User;
import Manager.BookingTransactionManager;
import Manager.CityManager;

import Manager.MovieManager;
import Manager.ScreenManager;
import Manager.ShowManager;
import Manager.TheatreAdminManager;
import Manager.TheatreManager;
import Manager.UserManager;



public class BookingManagementSystem implements UserAccessService, TheatreOwnerAccess, NormalAccess, MainAdminAccess {

	

    
    TheatreManager theatreManager = null;
   
    BookingTransactionManager bookingTransactionManager = null;
    
    ShowManager showManager = null;
    
    UserManager userManager = null;
    
    CityManager enumManager = null;
    
    ScreenManager screenManager = null;
    
    TheatreAdminManager theatreAdminManager = null;
    
    Connection conn = null;
    
    MovieManager movieManager = null;	
	
	private HashMap<Integer,String> languageMap = null;
	
	private HashMap<Integer,String> categoryMap = null;
	
	public BookingManagementSystem() 
	{
	
		
		DBConnection  app = DBConnection.getInstance();
		   
		 conn = app.connect();

	   userManager = new UserManager(conn);	
	  
	   theatreAdminManager  = new TheatreAdminManager(conn);	
	  
	   enumManager = new CityManager( conn);
	   
	   theatreManager = new TheatreManager( conn );
	   
	   screenManager = new ScreenManager(conn);
	   
	   movieManager = new MovieManager(conn);
	   
	   showManager = new ShowManager(conn);
	   
	   bookingTransactionManager = new BookingTransactionManager(conn);
	   
	   languageMap = new HashMap<Integer,String>();
		
	   categoryMap = new HashMap<Integer,String>();
	}
	

public int addToUser( String username, String email, String password, long contact, String dateOfBirth,int choiceForUserType ) throws SQLException {
	

	int res = userManager.addUser( username,  email,  password,  contact,  dateOfBirth, choiceForUserType);
	return res;
}



public int addToTheatreOwner(String username,String  email,String password, long contact,String documentProofId, String dateOfBirth, int choiceForUserType) throws SQLException
{
	
	int res = theatreAdminManager.addTheatreAdmin( username,  email,  password,  contact, documentProofId,  dateOfBirth, choiceForUserType);
	return res;
	
}

	public Roles isExistInAccount(String email, String password) throws SQLException {
		
		Roles res = null; 
		
	
		res = theatreAdminManager.isExistTheatreAdmin(email,  password);
		if( res != null ) { return res; }
		
		
		
		res = userManager.isExistUser(email,  password);
		if( res != null ) { return res; }
		
	
		return  res;
	

	}


	//===========================  VALIDATION TASKS ============================		
	
	
//	boolean isExistTheatre( int theatreId )
//	{
//		boolean valid = false;
//		for(int i=0; i<theatreList.size(); i++)
//		{
//			//if(  )
//		}
//	}
	
	
	// ===========================  THEATRE OWNER TASKS ==================================
	

	
	public void setCategory() throws SQLException 
	{

		categoryMap =	enumManager.setCategory(categoryMap);

	}
	
	
	public void setLanguage() throws SQLException 
	{

		languageMap =	enumManager.setLanguage(languageMap);

	}
	
	public void viewLanguage() throws SQLException  {
		
		setLanguage();
		
		 enumManager.setLanguage(languageMap);
		if( !enumManager.getLanguage(languageMap) ) { return;  }
		

	}
	
	public void viewCategory() throws SQLException  {
		
		setCategory();
		
		 enumManager.setCategory(categoryMap);
		if( !enumManager.getCategory(categoryMap) ) { return;  }
		

	}
	
	
	public void viewCity() throws SQLException  {
//		
//		setCity();
//		
//		 enumManager.setCity(cityMap);
		
	ResultSet res =	enumManager.getCity();
	
	System.out.println("\n==========================\n");
	System.out.println("\nALL CITY LIST\n");
	System.out.println("\n==========================\n");
	int c=0;
	while(res.next()) {
	c++;
	System.out.println("\nCity Id : " + res.getInt("id")+"\nCity Name : " + res.getString("name") + "\nCity District : " + res.getString("district") + "\nCity Zipcode : " + res.getLong("zipcode") );
		

	
	}
	
	if( c==0 ) { System.out.println("City Not Found"); }
	
	System.out.println("\n==========================\n");
	
	}
	
	//===================================================================
	
	
	

	
	//========================================================================================================
	
	
	
	public void addTheatre( String theatreName,  int cityId , int ownerId ) throws SQLException {
		
		 String name = enumManager.getCityNameById(cityId);
	
		
		int res = theatreManager.addTheatre( theatreName, ownerId, cityId );	
		
		if(res != 0 ) {
		System.out.println( theatreName + " theatre was Added Successfully in  the " + name + " city ");
		}
		else {
			System.out.println("Theatre Added Failed");
		}
		
	}

	
	
	//=======================================================================================================
	
	
	
	public void addScreens(int theatreId, String screenName, int row, int col) throws SQLException {

	
		int res = screenManager.addScreen(theatreId,screenName,row , col );
		
		
		if(res != 0 ) {
			System.out.println( screenName + " screen was Added Successfully");
			}
			else {
				System.out.println("Screen Added Failed");
			}
	
	}

//====================================================================================================================================================
	
	public void addShows(int theatreId, int screenId, String showName, String timeStart, int movieId, String datesForShow, int rate) throws SQLException  {
		
		String rowCol = screenManager.getRowColById(screenId);
		
		if( rowCol == null ) { System.out.println("Row Col is empty..Pls Try Again.."); return; }
		
		int res = showManager.addShows( theatreId,  screenId,  showName,  timeStart,  movieId,  datesForShow,  rate , rowCol );
		
		
		if(res != 0 ) {
			System.out.println( showName + " show was Added Successfully");
			}
			else {
				System.out.println("Show Added Failed");
			}
		

		
	}


	//===================================================================================================================================
	
	
	
	public void viewMovies() throws SQLException {
		
		

		int res = movieManager.viewMovies();
		
		
		if( res == 0 ) {
			System.out.println("No Movie Shown Here..");
			}
		
		
	}

	
	public void viewAllMovieInTheatre(int theatreId) throws SQLException {
		
		ResultSet rs = showManager.getShowByTheatreId(theatreId);
		
		String theatreName =  theatreManager.getTheatreNameById(theatreId);
		
		System.out.println("\n============================\n");
		System.out.println("\nTheatre Name : " + theatreName );
		
		System.out.println("\n============================\n");

		int c=0;
		while(rs.next())
		{ c++;
					int screenId = rs.getInt("screen_id");
					
					String screen_name = screenManager.getScreenNameById(screenId);
					
					System.out.println("\nScreen Name : " + screen_name + "\n" );
					
					int movieId = rs.getInt("movie_id");
			
					String movieName = movieManager.getMovieNameById(movieId);
					
					System.out.println("\nMovie Name : " + movieName  + "\n" );
					
					System.out.println("\nShow date : " + rs.getDate("show_date"));
					
					System.out.println("\nTotal Tickets : " + rs.getInt("total_ticket"));
					
					System.out.println("\nAmount : " +  rs.getInt("rate"));
					
					Object[][] seat = showManager.getSeatById( rs.getInt("id") );
					
					System.out.println("\nSeat Deatils : \n");
					
					for(int j=0; j<seat.length; j++)
					{ 
						System.out.print( "\t" + (j+1));
						
					}
					System.out.println("\n");
					for(int i=0; i<seat.length; i++)
					{ 
					
					
						for(int j=0; j<seat[i].length; j++)
						{
							
							
							if(j==0) {
								int v = 65+i;
								System.out.print( (char) v +"\t");
							}
							String seatN = "";
							
							
							
							char rr = (char) ( i + 65) ;
							
							seatN += String.valueOf(rr);
							seatN += "-";
							seatN += String.valueOf( (j+1) );
							
							System.out.print( ( ( ( (boolean) seat[i][j] == true ) ? "\u001B[41m " + seatN + "\t" + "\u001B[0m" : seatN  ) + "\t"  ) );
							
						}
						
						System.out.println();
					}
					
					System.out.println("\n\n");
			
					System.out.println("\n============================\n");
		}
		
		if( c==0 ) { System.out.println("Not Found"); }
		
		System.out.println("\n============================\n");
		
	
	
	}
	
	
	public boolean existCategoryId(int id)
	{
		return categoryMap.containsKey(id);
	}
	
	public boolean existLanguageId(int id)
	{
		return languageMap.containsKey(id);
	}
	
	//====================================================================================
	
	
	public void viewAllTheatreForMovie(int movieId) throws SQLException { 
		
		
		ResultSet rs = showManager.getTheatreIdByMovieId(movieId);
		System.out.println("\n============================\n");
		System.out.println("\nTheatre Name List For This Movie ");
		
		System.out.println("\n============================\n");
		int c=0;
		while(rs.next())
		{
			c++;
			
			String theatreName =  theatreManager.getTheatreNameById(rs.getInt("theatre_id"));
			
			System.out.println(theatreName);
		}
		
		if( c==0 ) { System.out.println("Not Found"); return; }
		
		System.out.println("\n============================\n");
		

		
	}
	
	
	//===========================================================================
	
	
	public void viewAllTheatreForCity(int cityId) throws SQLException { 
		
		String cityName = enumManager.getCityNameById(cityId);
		ResultSet rs = theatreManager.getIdByCity( cityId );
		
		System.out.println("\n============================\n");
		System.out.println("\n All Theatre List For This City ");
		
		System.out.println("\n============================\n");
		
		int c=0;
		while( rs.next() )
		{ c++;
			int idd =  rs.getInt("id"); 
			viewAllMovieInTheatre(idd);
		}
		
		if( c==0 ) { System.out.println("Not Found"); }
		
		System.out.println("\n============================\n");

		
	}


	//=====================================================================================
	
	
	public void bookTicket(int theatreId, String dateForMovie, int screenId , int showId, int noOfSeatForThisShow ,int userId, ArrayList<String> seatsDetails) throws SQLException {
		
			int totalRate = showManager.getShowRateById(showId);
			
			boolean isValidForBooking = showManager.isValidBooking(showId, noOfSeatForThisShow , seatsDetails );
			
				if(	isValidForBooking  ) {
					
			int res1 =showManager.updateSeat(showId,seatsDetails,noOfSeatForThisShow,1);
			
			if( res1 == 0 ) { System.out.println( "Ticket Booking Failed .." ); return; }
		 
		   int res = bookingTransactionManager.bookTicket(  theatreId,  dateForMovie,  screenId ,  showId,  noOfSeatForThisShow , userId, seatsDetails, ( totalRate*noOfSeatForThisShow ) );
		
		   
			
			if(res != 0 ) {
			System.out.println( "Ticket Booking Successfully.." );
			}
			else {
				System.out.println( "Ticket Booking Failed.. " );
			}
			
				}
				else {
					System.out.println("Your Specify Tickets seats are Not Available For this show");
				}
		
			
	}

	//=================================================================================
	
	

	
	public void cancelTickets(int bookingTicketTransactionId) throws SQLException {
		
		int showId = bookingTransactionManager.getShowIdByTransactionId(bookingTicketTransactionId);
		ArrayList<String> seatsDetails = bookingTransactionManager.getSeatsDetailsByTransactionId(bookingTicketTransactionId)  ;
		int noOfSeatForThisShow = bookingTransactionManager.getNoOfSeatForThisShowByTransactionId(bookingTicketTransactionId) ;
		
		int res1 =showManager.updateSeat(showId,seatsDetails,noOfSeatForThisShow,0);
		
		if( res1 == 0 ) { System.out.println( "Ticket Cancelling Failed For update seat failed.." ); return; }
	 
	   int res = bookingTransactionManager.cancelTickets( bookingTicketTransactionId  );
	
	   
		
		if(res != 0 ) {
		System.out.println( "Ticket Cancelling Successfully.." );
		}
		else {
			System.out.println( "Ticket Cancelling Failed.. Totally" );
		}
		
		
		}
	
	//=====================================================================================================

	
	public void historyViewForUser( int userId ) throws SQLException {
		
		
		ResultSet res = bookingTransactionManager.viewAllByUserId(userId);
		
		System.out.println("\n============================\n");
		System.out.println("\n\t MY HISTORY \t");
		
		System.out.println("\n============================\n");
		
		int r = this.viewResultSetFromTransaction(res);
		
		if( r==0 ) { System.out.println("No History Available"); }
		
		System.out.println("\n============================\n");

	}
	
	//=======================================================================================================


	
	public void viewAllBookingTickets(int theatreId) throws SQLException  {
	
		ResultSet res = bookingTransactionManager.viewAllByTheatreId(theatreId);
		
		
		
		System.out.println("\n============================\n");
		System.out.println("\n\t VIEW bOOKING TICKETS FOR THIS THEATRE \t");
		
		System.out.println("\n============================\n");
		
		int r = this.viewResultSetFromTransaction(res);
		
		if( r==0 ) { System.out.println("No Ticked Are Booked Yet"); }
		
		System.out.println("\n============================\n");
	}


	//=======================================================================================================

	
public int viewResultSetFromTransaction(ResultSet res) throws SQLException
{
	int c=0;
	while( res.next() ) {
		c++;
		 String[]  st  ;
			Array as = null;
			as = res.getArray("seat_list");
			
		String[] seat_detail_new = ( String[] )as.getArray() ;
		
		System.out.println( "Ticket Id : " + res.getInt("id") + "\nNo of Booked Seat : " + res.getInt("no_of_booked_seat") + "\nTotal Rate : " +  res.getInt("total_rate") + "\nShow Date : " + res.getDate("show_date") + "\nStatus : " +  ( ( res.getInt("status") == 1 ) ? "Booked" : "Cancelled" ) + "\nBook/Cancel Date : " + res.getDate("apply_date_and_time")  );
		System.out.println("\nBooked Seat : ");
		for(int i=0; i<seat_detail_new.length; i++) { System.out.print(seat_detail_new[i] + " "); } 
		System.out.println("\n\n============================\n\n");
		
	}
	
	return c;
}

//=================================================================================================================

						// VALIDATION PURPOSE IT WILL DISPLAY DETAIL AND CHECK EXIST OR NOT	

//=================================================================================================================


public boolean getCity(int cityId) throws SQLException {
	
	return enumManager.isCityExist(cityId);
	
	
	//return cityMap.containsKey(cityId) ;
	
}


public void viewTheatreForOwner(int id) throws SQLException {
	
	theatreManager.getIdAndNameByOwnerId(id);
	
}



public boolean isValidToAdd(int userId, int theatreId) throws SQLException {
	
	return theatreManager.getExistData(userId, theatreId);
}



public void viewScreenForOwner(int theatreId) throws SQLException {
	
	screenManager.getIdAndNameOfScreenByOwnerId(theatreId);
}



public boolean isValidToAddScreen(int screenId, int theatreId) throws SQLException {
	
	return screenManager.getExistData(screenId, theatreId);

}


public boolean isValidMovieId(int movieId) throws SQLException {

	return movieManager.getExistData(movieId);
}



public boolean isValidForBookingId(int bookingTicketTransactionId, int userId) throws SQLException {
	
	return bookingTransactionManager.getExistData( bookingTicketTransactionId, userId );
}



//=================================================================================================================

// VALIDATION PURPOSE IT WILL DISPLAY DETAIL AND CHECK EXIST OR NOT	

//=================================================================================================================

public void viewTheatreForUser() throws SQLException 
{
	theatreManager.getIdAndNameForUser();
}


public boolean isExistId(int theatreId) throws SQLException {
	
	return theatreManager.isExistId(theatreId);
}


public void displayScreenIdNameForThisTheatreId(int theatreId) throws SQLException {
	
	screenManager.getIdAndNameOfScreenByOwnerId(theatreId);
	
}



public boolean isValidToScreen(int screenId, int theatreId) throws SQLException {

	return screenManager.getExistData(screenId, theatreId);
}


public void displayShowIdAndNameForThisScreen(int screenId) throws SQLException {
	
	showManager.diplsayAllForThisScreen(screenId);
}



public boolean isValidToShow(int showId, int screenId) throws SQLException {
	
	return showManager.isExistId(showId, screenId);
}

//============================= EDIT AND DELETE TASKS ===========================


public void editTheatre( String theatreName , int theatreId ) throws SQLException
{
	int res = theatreManager.edit(theatreName, theatreId);
	
	if ( res != 0 ) { System.out.println("Theatre Edited Successfully");  }  else { System.out.println("Theatre Edited Failed"); }
}

public void deleteTheatre(int  theatreId) throws SQLException
{
int res = theatreManager.delete( theatreId);
	
if ( res != 0 ) { System.out.println("Theatre Deleted Successfully");  }  else { System.out.println("Theatre Deleted Failed"); }
}


public void editScreen( String screenName , int screenId ) throws SQLException
{
	int res = screenManager.edit(screenName, screenId);
	if ( res != 0 ) { System.out.println("Screen Edited Successfully");  }  else { System.out.println("Screen Edited Failed"); }
	
}


//=================================== MAIN ADMIN TASK ============================


public void addMovie(String moviename, int categorys, int languages, String filmCertifications, String duration)
		throws SQLException {

	int res = movieManager.add( moviename, categoryMap.get(categorys) ,  languageMap.get(languages) ,  filmCertifications,  duration);
	
	if ( res != 0 ) { System.out.println("Movie Added Successfully"); } else { System.out.println("Movie added Failed"); }
	
}

public void editMovie(int movieId, int languageId,  String duration)
		throws SQLException {

	int res = movieManager.editMovie(movieId, languageMap.get(languageId), duration); 
	
	if ( res != 0 ) { System.out.println("Movie Edited Successfully"); } else { System.out.println("Movie Edited Failed"); }
	
}




public void getReport() throws SQLException {
	
	int theatreCount = theatreManager.getCount();
	
	int screenCount = screenManager.getCount();
	
	int showCount = showManager.getCount();
	
	int movieCount = movieManager.getCount();
	
	
	int cityCount = enumManager.getCount();
	
	int usercount = userManager.getCount();
	
	int theatreAdmin = theatreAdminManager.getCount();
	
	int bookingTransactionCount = bookingTransactionManager.getCount();
	
	System.out.println("\n============================\n");
	System.out.println("\n TOTAL OVERALL REPORT \t");
	
	System.out.println("\n============================\n");
	
	System.out.println("\nTotal no of Theatre Count : " + theatreCount ); 
	System.out.println("\nTotal no of Screen Count : " + screenCount ); 
	System.out.println("\nTotal no of show Count : " + showCount ); 
	System.out.println("\nTotal no of movie Count : " + movieCount ); 
	System.out.println("\nTotal no of city Count : " + cityCount ); 
	System.out.println("\nTotal no of Booking Transaction Count : " + bookingTransactionCount ); 
	System.out.println("\nTotal no of User Count : " + usercount ); 
	System.out.println("\nTotal no of Theatre Admin Count : " + theatreAdmin ); 
	
	
	System.out.println("\n============================\n");
}


	//=================================================================================

public void addCategoryEnum( String category ) throws SQLException {
	

	int res = movieManager.addCategoryEnum( category  ); 
	
	System.out.println("Category Edited Successfully");
	
}




public void addLanguageEnum( String language ) throws SQLException {
	

	int res = movieManager.addLanguageEnum( language  ); 
	
	System.out.println("Language Edited Successfully"); 
	
}


public void addCityEnum( String city, String district, long zipcode) throws SQLException {
	
	int res = enumManager.addCityEnum(city, district, zipcode);

	if ( res != 0 ) { System.out.println("City added Successfully");  }  else { System.out.println("City added Failed"); }
	
	
//	int res = enumManager.addCityEnum( city  , district , zipcode ); 
//	
//	System.out.println("City Edited Successfully"); 
	
}




}
