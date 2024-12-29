package Management;

import java.util.ArrayList;
import java.util.HashMap;

import DataModels.BookingTransaction;
import DataModels.City;
import DataModels.Movie;
import DataModels.Screen;
import DataModels.Show;
import DataModels.Theatre;

public class SearchManagement {

	public static void viewAllMovieInTheatre( Theatre theatre ) {
		
		System.out.println("\n========================================\n");
		
		System.out.println(".. WELCOME TO " + theatre.getName() + " Theatre .." );
		
		System.out.println("\n========================================\n");
		
		ArrayList<Screen> screenList = theatre.getScreenList();
	
		for(int i=0; i<screenList.size(); i++)
		{
			System.out.println("\n-------------------------\n");
			
			Screen screen = screenList.get(i);
			System.out.println( "\tScreen Name : " + screen.getName() + "\n"  );
			
			ArrayList<Show> showList = screen.getShowList();
			
			System.out.println("\n--- Show Details ---\n");
			
			for(int j=0; j<showList.size(); j++)
			{
				Show show = showList.get(j);
				
				Movie movie = MovieManagement.getMovie(  show.getMovieId() );
				
				System.out.println("\nShow Name : " + show.getName() + "\nShow Starting Time : " + show.getTimeStart() + "\n Movie Name : " +  movie.getName() + " \nMovie Duration : " + movie.getDuration() + "\nAvailable Seats : " + show.getNoOfSeat() + "\n" );
		
			
			}
			
			System.out.println("\n-------------------------\n");
			
		}
		
		System.out.println("\n========================================\n");
		
	}

	
	
	
	
	//===========================================================================================================================
	
	
	
	
	public static void viewAllTheatreForMovie( Movie movie, ArrayList<Theatre> theatreList ) {
	
		
		for(int k=0; k<theatreList.size(); k++)
		{
			System.out.println("\n-------------------------\n");
			
			Theatre theatre = theatreList.get(k);
			
			ArrayList<Screen> screenList = theatre.getScreenList();
			
			for(int i=0; i<screenList.size(); i++)
			{
				
				
				Screen screen = screenList.get(i);
				
				
				ArrayList<Show> showList = screen.getShowList();
				
			
				
				for(int j=0; j<showList.size(); j++)
				{
					Show show = showList.get(j);
					
					Movie movie1 = MovieManagement.getMovie(show.getMovieId()) ;
					
					if( movie1.getName().equals(movie.getName()) )
					{
						System.out.println("\nTheatre ID : " + theatre.getId() +"\nTheatre Name : " + theatre.getName() + "\n" );
					}
				
				}
		
				
			}
			
			
			System.out.println("\n-------------------------\n");
		}
		
	}
	
	
	
	//=======================================================================================================================
	
	

	public static void viewAllTheatreForCity(ArrayList<Theatre> theatreList, int cityId) {
		
		for(int i=0; i<theatreList.size(); i++)
		{
			Theatre theatre = theatreList.get(i);
			
		if( theatre.getCityId() == cityId )
		{
			SearchManagement.viewAllMovieInTheatre(theatre);
		}
		
			
		}
		
		
	}


//===========================================================================
	
	
	
	public static City getCity(ArrayList<City> cityList, int cityId) {
		
		City city=null;
		
		for(int i=0; i<cityList.size(); i++)
		{
			City city1 = cityList.get(i);
			
			if( cityId == city1.getCityId()  )
			{
				city = city1;
				break;
			}
		}
		
		return city;
		
	}
	

//==========================================================================
	

	
	//=======================================================================


public static void viewAllBookingTickets(ArrayList<BookingTransaction>  bookingTransactionList, String date,  int showId , int availSeatForThisShowInThisDate , ArrayList<Theatre> theatreList )
{
	
 
	
	for(int i=0; i<bookingTransactionList.size(); i++)
	{
		System.out.println("\n-------------------------------------------------\n");
		
		BookingTransaction bookingTransaction = bookingTransactionList.get(i);
		
		
		Theatre theatre = TheatreManagement.getTheatre(theatreList, bookingTransaction.getTheatreId() );
		
		ArrayList<Screen> screenList = theatre.getScreenList() ;
		
		Screen screen = ScreenManagement.getScreen(screenList, bookingTransaction.getScreenId() );
		
		ArrayList<Show> showList = screen.getShowList();
		
		Show show = ShowManagement.getShow(showList, showId);
		
	
		
	
		
		System.out.println("\nBooking Details For show ID : " + showId + " in " + date + " Date\n");
		
		System.out.println("\nMovie Date: " + bookingTransaction.getMovieDate() + "\nScreen Name : " + screen.getName() + "\nShow Name : " + show.getName() + "\nShow Time : " + show.getTimeStart() + "\nThatre Name : "  + theatre.getName() + "\nMovie Date : " + bookingTransaction.getMovieDate() );
		
		if( bookingTransaction.getMovieDate().equals(date) && bookingTransaction.getShowId() == showId )
		{
			System.out.println("\nBookinTicketId : " + bookingTransaction.getId() + "\nNo of Seat Booked : " + bookingTransaction.getNoOfBookedSeat()  + "\nTotal Rate : " + bookingTransaction.getTotalRate() + "\nUserId : " + bookingTransaction.getUserId() + "\nBooking Date : " + bookingTransaction.getBookingTimeAndDate() +  "\nStatus : " + bookingTransaction.getStatus() );
			
			
		}
		
	
	
	}
	
	System.out.println("\nAVAILABLE SEAT : " + ( ( availSeatForThisShowInThisDate != 0 ) ? availSeatForThisShowInThisDate : "HOUSE FULL" ) + "\n" );
	
	System.out.println("\n-------------------------------------------------\n");
	
}





//=================================================================================


public static void historyView( ArrayList<BookingTransaction>  bookingTransactionList, int userId,  ArrayList<Theatre> theatreList )
		{

	

	
	for(int i=0; i<bookingTransactionList.size(); i++)
	{
		BookingTransaction bookingTransaction = bookingTransactionList.get(i);
		
		
		
		Theatre theatre = TheatreManagement.getTheatre(theatreList, bookingTransaction.getTheatreId() );
		
		ArrayList<Screen> screenList = theatre.getScreenList() ;
		
		Screen screen = ScreenManagement.getScreen(screenList, bookingTransaction.getScreenId() );
		
		ArrayList<Show> showList = screen.getShowList();
		
		Show show = ShowManagement.getShow(showList, bookingTransaction.getShowId());
		
		System.out.println("\n---------------------------------------------------------------\n");
		
		System.out.println("\nMovie Date: " + bookingTransaction.getMovieDate() + "\nScreen Name : " + screen.getName() + "\nShow Name : " + show.getName() + "\nShow Time : " + show.getTimeStart() + "\nTheatre Name : "  + theatre.getName() + "\nMovie Date : " + bookingTransaction.getMovieDate() );
		
		if( bookingTransaction.getUserId() == userId && ( bookingTransaction.getStatus() == Status.Booked || bookingTransaction.getStatus() == Status.Cancelled ) );
		{
			System.out.println("\nBookinTicketId : " + bookingTransaction.getId() + "\nNo of Seat Booked : " + bookingTransaction.getNoOfBookedSeat()  + "\nTotal Rate : " + bookingTransaction.getTotalRate() + "\nUserId : " + bookingTransaction.getUserId() + "\nBooking Date : " + bookingTransaction.getBookingTimeAndDate() +  "\nStatus : " + bookingTransaction.getStatus() );
			
			
		}
		
		System.out.println("\n---------------------------------------------------------------\n");
	
	}
	
	
	
}


}