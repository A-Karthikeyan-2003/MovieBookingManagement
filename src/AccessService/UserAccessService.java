package AccessService;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserAccessService extends CommonAccess {
	
	 void viewAllMovieInTheatre(int theatreId) throws SQLException;
	
	 void viewAllTheatreForMovie(int movieId) throws SQLException;
	
	 void viewAllTheatreForCity(int cityId) throws SQLException;

	 void bookTicket(int theatreId, String dateForMovie,int screenId, int showId , int noOfSeatForThisShow ,int userId, ArrayList<String> seatsDetails ) throws SQLException;

	 void historyViewForUser(int userId) throws SQLException;
	
	 void cancelTickets( int bookingTicketTransactionId ) throws SQLException;

	 boolean isValidForBookingId(int bookingTicketTransactionId, int userId) throws SQLException;

	 void viewTheatreForUser() throws SQLException;

	 boolean isExistId(int theatreId) throws SQLException;

	 void displayScreenIdNameForThisTheatreId(int theatreId) throws SQLException;

	 boolean isValidToScreen(int screenId, int theatreId) throws SQLException;

	 void displayShowIdAndNameForThisScreen( int screenId ) throws SQLException;

	 boolean isValidToShow(int showId, int screenId)throws SQLException;


}
