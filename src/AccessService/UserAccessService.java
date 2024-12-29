package AccessService;

import java.util.ArrayList;

public interface UserAccessService {
	
	public void viewAllMovieInTheatre(int theatreId);
	
	public void viewAllTheatreForMovie(int movieId);
	
	public void viewAllTheatreForCity(int cityId);

	public void bookTicket(int theatreId, String dateForMovie,int screenId, int showId , int noOfSeatForThisShow ,int userId, ArrayList<String> seatsDetails );

	public void viewAvailableSeatForParticularShowInParticularDate(String date, int showId);

	public void historyViewForUser(int userId);
	
	public void cancelTickets( int bookingTicketTransactionId );
	


}
