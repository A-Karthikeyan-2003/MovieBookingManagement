package AccessService;

import java.sql.SQLException;

import java.util.ArrayList;



public interface TheatreOwnerAccess extends CommonAccess {
	
	 void addTheatre( String theatreName,int cityId, int ownerId  ) throws SQLException;
	
	 void addScreens(int theatreId, String screenName ,  int row, int col   ) throws SQLException;
	
	 void addShows( int theatreId,int screenId,String showName,String timeStart ,int movieId, String datesForShow , int rate  ) throws SQLException;

	 void viewAllBookingTickets( int theatreId ) throws SQLException;

	 void viewTheatreForOwner(int id) throws SQLException;

	 boolean isValidToAdd(int userId, int theatreId) throws SQLException;

	 void viewScreenForOwner(int theatreId) throws SQLException;

	 boolean isValidToAddScreen(int screenId, int theatreId) throws SQLException;
	
	 void deleteTheatre(int  theatreId) throws SQLException;
	
	 void editTheatre( String theatreName , int theatreId ) throws SQLException;
	
	 void editScreen( String screenName , int screenId ) throws SQLException;

}
