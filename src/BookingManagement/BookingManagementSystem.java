package BookingManagement;

import java.time.LocalDate;



import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import AccessService.CommonAccess;
import AccessService.TheatreOwnerAccess;
import AccessService.UserAccessService;
import DataModels.BookingTransaction;
import DataModels.City;
import DataModels.Movie;
import DataModels.Roles;
import DataModels.Screen;
import DataModels.Show;
import DataModels.Theatre;
import DataModels.TheatreOwner;
import DataModels.User;
import Helper.MovieManagement;
import Helper.ScreenManagement;
import Helper.SearchManagement;
import Helper.ShowAndDateManagement;
import Helper.ShowManagement;
import Helper.Status;
import Helper.TheatreManagement;

public class BookingManagementSystem implements UserAccessService, TheatreOwnerAccess, CommonAccess {

	

	
	private static int userId=0,theatreOwnerId=0, theatreId=0, screenId=0, showId=0, movieId=0, cityId=0, bookingTransactionId=0;
	
	private ArrayList<Theatre> theatreList = new ArrayList<Theatre>();
	
	private ArrayList<User> userList = new ArrayList<User>();
	
	private ArrayList<BookingTransaction> bookingTransactionList = new ArrayList<BookingTransaction>();
	
	private ArrayList<TheatreOwner> theatreOwnerList = new ArrayList<TheatreOwner>();
	
	private ArrayList<City> cityList = new ArrayList<City>();
	
	private HashMap<String,ArrayList<Show>> hashmap = new HashMap<String,ArrayList<Show>>();
	
	

	
	public BookingManagementSystem(){
		
		
	
		
		Movie movie1 = new Movie(movieId++, "Bagubali", "Action", "Pan India Movie", "Tamil", "U", " 2 hour 30 min");
		Movie movie2 = new Movie(movieId++, "Bagubali - 2", "Action", "Pan India Movie", "Tamil", "U", " 2 hour 45 min");
		Movie movie3 = new Movie(movieId++, "GOAT", "Action", "Pan India Movie", "Tamil", "U", " 2 hour 30 min");
		Movie movie4 = new Movie(movieId++, "VidaMuyarchi", "Action Thriller", "Pan India Movie", "Tamil", "U", " 2 hour 35 min");

		MovieManagement.movieList.add(movie1); 
		MovieManagement.movieList.add(movie2); 
		MovieManagement.movieList.add(movie3); 
		MovieManagement.movieList.add(movie4);
		
		City city1 = new City(cityId++, "Sivakasi", "626121", "Virudhunagar");
		City city2 = new City(cityId++, "Srivilliputtur", "626125", "Virudhunagar");
		City city3 = new City(cityId++, "Rajapalayam", "626122", "Virudhunagar");
		City city4 = new City(cityId++, "Aruppukottai", "626123", "Virudhunagar");
		
		cityList.add(city1);
		cityList.add(city2);
		cityList.add(city3);
		cityList.add(city4);
		
		
		
	}
	

	
public void addToUser( String username, String email, String password, String contact, String dateOfBirth ) {


	User user = new User( username,email,password,contact,dateOfBirth , userId  );
	
	userList.add(user);
	
}

public void addToTheatreOwner(String username,String  email,String password, String contact,String documentProofId, String dateOfBirth ,int cityId )
{
	
	TheatreOwner theatreOwner = new TheatreOwner( username, email, password, contact, documentProofId, theatreOwnerId, dateOfBirth , cityId   );
	
	theatreOwnerList.add(theatreOwner);
}

	public Roles isExistInAccount(String email, String password) {
		
		Roles user = null;
		
		for(int i=0; i<userList.size(); i++)
		{
			User userr = userList.get(i);
			
			if( userr.getEmail().equals(email) && userr.getPassword().equals(password) )
			{
				
				user = userr;
				break;
			}
		}
		
		if( user == null )
		{
			for(int i=0; i<theatreOwnerList.size(); i++)
			{
				TheatreOwner theatreOwner = theatreOwnerList.get(i);
				
				if( theatreOwner.getEmail().equals(email) && theatreOwner.getPassword().equals(password) )
				{
					
					user = theatreOwner;
					break;
				}
			}
		}
		

		
		return user;

	}


	
	// ===========================  THEATRE OWNER TASKS ==================================
	
	
	public  void viewCity() {
		
		for(int i=0; i<cityList.size(); i++)
		{
			System.out.println("-----------------------------\n");
			
			System.out.println("City Name : "  + cityList.get(i).getCityName() );
			
			System.out.println("City Id : "  + cityList.get(i).getCityId() );
		
			System.out.println("\n-----------------------------");
			
		}
		
	}
	
	//===================================================================
	
	
	

	
	//========================================================================================================
	
	
	
	public void addTheatre(String theatreName,  int cityId , String owner) {
		
		Theatre theatre = new Theatre( theatreId++, theatreName, cityId, owner  );
		
		this.theatreList = TheatreManagement.addTheatre(theatreList, theatre);
		
		City city = SearchManagement.getCity(cityList, cityId);
		
		city.setTheatre(theatre);
		
		System.out.println( theatreName + " theatre was Added Successfully in  the " + city.getCityName() + " city ");
		
		
	}

	
	
	//=======================================================================================================
	
	
	
	public void addScreens(int theatreId, String screenName) {

		
		Theatre theatre = TheatreManagement.getTheatre(theatreList, theatreId);
		
		if( theatre != null ) {
		
		Screen screen = new Screen(screenId++, screenName , theatre.getId() );
		
		ArrayList<Screen> screenListForThisTheatre  = theatre.getScreenList();
		
		theatre.setScreenList(ScreenManagement.addScreen(screenListForThisTheatre, screen));

		
		System.out.println(  screenName + " screen was added successfully in the " + theatre.getName() + " theatre" );
		
		}
		else {
			
			System.out.println("Theatre Not Found to add Screen");
			
		}
		
		
	}

//====================================================================================================================================================
	
	public void addShows(int theatreId, int screenId, String showName, String timeStart, int movieId, String datesForShow, int noOfSeats, int rate)  {
		
		Theatre theatre = TheatreManagement.getTheatre(theatreList, theatreId);
		
		if( theatre != null ) {
			
			ArrayList<Screen> screenListForThisTheatre  = theatre.getScreenList();
			
			Screen screen = ScreenManagement.getScreen(screenListForThisTheatre, screenId) ;
			
			if( screen != null )
			{
				
				Movie movie = MovieManagement.getMovie( movieId);

				if( movie!=null ) {
				
				Show show = new Show(showId++,showName, timeStart , movieId ,screenId, theatreId , noOfSeats , rate );
				
				try {
					this.hashmap = ShowAndDateManagement.addDateAndShow(hashmap, datesForShow, show);
				} catch (CloneNotSupportedException e) {
					
					e.printStackTrace();
				}
				
				ArrayList<Show> showListForThisScreen = new ArrayList<Show>();
			
				
				screen.setShowList(ShowManagement.addShow(showListForThisScreen, show)); 
				
				// this.showList = ShowManagement.addShow(showList, show) ;
				
				System.out.println( show.getName() +  " show Details added successfully to release the " + movie.getName() + " movie" );
				
				}
				else {
					System.out.println("Movie not found..");
				}
				
				
			}
			else {
				System.out.println("Screen Not Found for this Theatre Id : "  + theatreId );
			}	
			
		}
		else {
			
			System.out.println("Theatre Not Found to add Show");
		}
		
		
	}


	//===================================================================================================================================
	
	
	
	public void viewMovies() {
		
		for(int i=0; i<MovieManagement.movieList.size(); i++)
		{
			Movie movie = MovieManagement.movieList.get(i);
			
			System.out.println("\n ----------------------------- \n");
			
			System.out.println("Movie Id : " + movie.getId()+"\nMovie Name : " + movie.getName() +  "\nMovie Duration : " + movie.getDuration() + "\nMovie Description : " + movie.getDescription() + "\nMovie Type : " + movie.getType()  + "\nMovie Sensor Type : " + movie.getSensorType());
			
			System.out.println("\n ----------------------------- \n");
			
			
		}
		
	}

	
	//==================================================================================================================================
	
	
	public void releaseMovies(int theatreId, int screenId, int showId, int movieId  , String datesForShow, int rate, int seat) {
		

		Theatre theatre = TheatreManagement.getTheatre(theatreList, theatreId);
		
		if( theatre != null ) {
			
			ArrayList<Screen> screenListForThisTheatre  = theatre.getScreenList();
			
			Screen screen = ScreenManagement.getScreen(screenListForThisTheatre, screenId) ;
			
			if( screen != null )
			{
				Movie movie = MovieManagement.getMovie( movieId);
				
				if( movie!=null ) {
					
					ArrayList<Show> showListForThisScreen = screen.getShowList();
					
					Show show = ShowManagement.getShow(showListForThisScreen, showId) ;
				
					if( show != null )
					{
				
					//==================================================
						
							show.setRate(rate);
						
							show.setNoOfSeat(seat);
						
							show.setMovieId(movieId);
						
					//==================================================
						
						try {
							
							this.hashmap = ShowAndDateManagement.addDateAndShow(hashmap, datesForShow, show);
					
						} catch (CloneNotSupportedException e) {
							
							e.printStackTrace();
						}
					
						System.out.println("Show Details updated successfully...\nNew Movie : " + movie.getName() + " was released successfully..\n");
					}
				
					else {
						System.out.println("Show not found");
					}
				
				}
				else {
					System.out.println("Movie not found");
				}
								
				
			}
			else {
				System.out.println("Screen Not Found for this Theatre Id : "  + theatreId );
			}
			
			
			
		}
		else {
			
			System.out.println("Theatre Not Found to add Show");
		}
		
		
	}
	
	
	
	
	//=====================================================================================

	
	public void viewAllMovieInTheatre(int theatreId) {
		
		Theatre theatre = TheatreManagement.getTheatre(theatreList, theatreId);
		SearchManagement.viewAllMovieInTheatre(theatre);
	
	}
	
	//====================================================================================
	
	
	public void viewAllTheatreForMovie(int movieId) { 
		
	Movie movie = MovieManagement.getMovie(movieId);
	
		if( movie != null ) { 
		SearchManagement.viewAllTheatreForMovie( movie, theatreList );
		}
		else {
			System.out.println("Movie Not Found");
		}
		
	}
	
	
	//===========================================================================
	
	
	public void viewAllTheatreForCity(int cityId) { 
		
	City city =	SearchManagement.getCity(cityList, cityId);
		

		
		if( city != null ) {
			
		SearchManagement.viewAllTheatreForCity( theatreList , cityId );
		
		}
		else {
			System.out.println("City Not Found");
		}
		
	}


	//=====================================================================================
	
	
	public void bookTicket(int theatreId, String dateForMovie, int screenId , int showId, int noOfSeatForThisShow ,int userId, ArrayList<String> seatsDetails) {
		
		Theatre theatre = TheatreManagement.getTheatre(theatreList, theatreId);
		
		if( theatre != null ) {
			
		ArrayList<Screen> screenListForThisTheatre =	theatre.getScreenList();
		
		Screen screen = ScreenManagement.getScreen(screenListForThisTheatre, screenId);
		
		if( screen != null ) {
			
			Show show = ShowManagement.getShow(screen.getShowList(), showId);
			
			if( show != null ) {
		
		boolean result = ShowAndDateManagement.checkShowIsExistForDate( show, dateForMovie , hashmap );
		
		if( result )
		{
		
		ShowAndDateManagement.updateSeatOfShowForThatDate(show, dateForMovie, hashmap, noOfSeatForThisShow, Status.Booked,seatsDetails );
			
		int totalRate = noOfSeatForThisShow * show.getRate();
		
		String currentDate = String.valueOf( LocalDate.now() ) ;
		

			
		BookingTransaction bookingTransaction = new BookingTransaction( bookingTransactionId++, userId, theatre.getId(), showId , noOfSeatForThisShow ,totalRate, currentDate  , Status.Booked , dateForMovie , screen.getId(), seatsDetails  );
		
		this.bookingTransactionList.add(bookingTransaction);
		
		Movie movie = MovieManagement.getMovie(show.getMovieId());
		
		System.out.println("Your Ticket Booking was successfully completed... Enjoy the " + movie.getName() + " movie" );
		
		}
		else {
			
			System.out.println("Show not Available for this Date " + dateForMovie );
		}
		
			}
			else {
				System.out.println("Show id not found");
			}
		}
		else {
			System.out.println("Screen not found");
		}
		
		}
		else {
			System.out.println("Theatre not found..");
		}
		
		
		
	}

	//=================================================================================
	
	
	
	public void viewAllBookingTickets(  String date, int showId   ) {
		
	
		int availSeatForThisShowInThisDate = ShowAndDateManagement.getAvailableSeat(date, hashmap, showId);
		
		SearchManagement.viewAllBookingTickets(bookingTransactionList, date , showId, availSeatForThisShowInThisDate,  theatreList);
		
	}


	
	public void viewAvailableSeatForParticularShowInParticularDate(String date, int showId) {
		
		int availSeatForThisShowInThisDate = ShowAndDateManagement.getAvailableSeat(date, hashmap, showId);
		
		System.out.println("AVAILABLE SEAT FOR "+ showId + " SHOW ID IS " + availSeatForThisShowInThisDate );
		
	}


	
	
	public void historyViewForUser( int userId ) {
		
		System.out.println("\nHii " + userId + " Your Booking Details History is Here .. \n");
		
		SearchManagement.historyView(bookingTransactionList, userId, theatreList);
		
	}

	
	public void cancelTickets(int bookingTicketTransactionId) {
		
		
		BookingTransaction	bookingTransaction = null;
		
		
		for(int i=0; i< bookingTransactionList.size(); i++ )
		{
			BookingTransaction bookingTransaction1 = bookingTransactionList.get(i);
			
			if( bookingTransaction1.getId()  == bookingTicketTransactionId )
			{
				bookingTransaction = bookingTransaction1;
				break;
			}
		}
		
		
		if( bookingTransaction != null ) {
			
		String movieDate = bookingTransaction.getMovieDate();
		
		if( !movieDate.equals(  bookingTransaction.getBookingTimeAndDate()  ) ) {
			

			Theatre theatre = TheatreManagement.getTheatre(theatreList, bookingTransaction.getTheatreId() );
						
			if( theatre != null ) {
			
				ArrayList<Screen> screenList = theatre.getScreenList() ;
				
				Screen screen = ScreenManagement.getScreen(screenList, bookingTransaction.getScreenId() );
	
			
				if( screen != null ) {
			
					ArrayList<Show> showList = screen.getShowList();
					
					Show show = showList.get(bookingTransaction.getShowId());
			
					if( show != null ) {
			
						bookingTransaction.setStatus(Status.Cancelled);
						
						ArrayList<String> seatss = bookingTransaction.getSeatsDetails();
			
						ShowAndDateManagement.updateSeatOfShowForThatDate(show, bookingTransaction.getMovieDate() , hashmap, bookingTransaction.getNoOfBookedSeat(), Status.Cancelled, seatss);
			
						System.out.println("Your Ticket was Cancelled Successfully...");
			
					}
					else {
						
						System.out.println("show id not found");
					}
					
				}
			else {
				System.out.println("Screen id not found");
			}
			
			}
			else {
				System.out.println("Theatre id not found");
			}
		
		}
		else {
			System.out.println("You cannot cancel your ticket .. ");
		}
		
		}
	
		
		}



	
	public City getCity(int cityId) {
		
		City city = SearchManagement.getCity(cityList, cityId);
		
		return city;
	}



	
	public void makeSubscriptionSeats(int showId, String date,  ArrayList<String> seatsDetails) {
		
		//ShowAndDateManagement.makeSubscriptionSeats(hashmap,date,showId,  seatsDetails);
		
	}
	
	
		
	



	//=================================================================================

	
	
	
	
}
