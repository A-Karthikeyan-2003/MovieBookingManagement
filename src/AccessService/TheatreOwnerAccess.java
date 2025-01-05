package AccessService;

import java.util.ArrayList;

import DataModels.City;

public interface TheatreOwnerAccess {
	
	public void addTheatre( String theatreName,int cityId, String owner  );
	
	public void addScreens(int theatreId, String screenName   );
	
	public void addShows( int theatreId,int screenId,String showName,String timeStart ,int movieId, String datesForShow , int noOfSeats , int rate  );
	
	public void releaseMovies(int theatreId, int screenId, int showId, int movieId , String datesForShow,int rate, int seat );

	public void viewMovies();
	
	public void viewCity();
	
	public City getCity(int cityId);
	
	public void viewAllBookingTickets( String Date, int showId );

	public void makeSubscriptionSeats(int showId, String date, ArrayList<String> seatsDetails);
	
}
