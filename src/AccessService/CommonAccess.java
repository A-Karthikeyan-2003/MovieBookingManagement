package AccessService;

import java.sql.SQLException;

public interface CommonAccess  {


	 void viewMovies() throws SQLException;
	
	 void viewCity() throws SQLException;
	
	 boolean getCity(int cityId) throws SQLException;
	
	 boolean isValidMovieId(int movieId) throws SQLException;
	
}
