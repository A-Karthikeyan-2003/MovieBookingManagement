package Management;

import java.util.ArrayList;

import DataModels.Movie;
import DataModels.Show;

public class MovieManagement {

	public static ArrayList<Movie> movieList  = new ArrayList<Movie>();
	
	

	public static Movie getMovie( int movieId)
	{

		Movie movie = null;
		
		for(int i=0; i<movieList.size(); i++)
		{
			Movie movie1 = movieList.get(i);
		
		if( movie1.getId() == movieId )
		{
			movie = movie1;
			break;
		}
			
		}
		
		return movie;
		
	}
	
	
}
