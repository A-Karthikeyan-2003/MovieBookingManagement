package Manager;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import BookingManagement.DBConnection;

public class MovieManager {


	  Connection conn =  null ; 

		
	  PreparedStatement  preparedStatement = null;
	  
	public MovieManager( Connection conn   )
	{
			
		  this.conn = conn; 
		
		 
	}

	public int viewMovies() throws SQLException {
	
		
		String s = "select * from movie";
		
		preparedStatement = conn.prepareStatement(s);
		ResultSet result = preparedStatement.executeQuery();
		System.out.println("\n=================================================\n");
		System.out.println("\n\t\tALL MOVIE DETAILS\t\t\n");
		System.out.println("\n================================================\n");
		
		int c=0;
		while( result.next() ) {
			c++;
		 System.out.println( "\nMovie Id :	" + result.getString(1) +  "\nMovie Name : " + result.getString(2) + "\nMovie Category : " + result.getObject(3) + "\nLanguage : " + result.getObject(4) + "\nFilm Certification : " + result.getObject(5) + "\nDuration : " + String.valueOf(result.getObject(6)).substring(22, String.valueOf(result.getObject(6)).length()-9 )  );

		
		}
		System.out.println("\n================================================\n");
		
		if( c==0 ) {
		return 0;
		}else {
			return 1;
		}
	}

	public String getMovieNameById(int movieId) throws SQLException {
		String s = "select name from movie where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,movieId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();

		while(res.next())
		{   
			name = res.getString("name");
			
		}
		
		return name;	
	}

	public boolean getExistData(int movieId) throws SQLException {
		
		String s = "select id from movie where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,movieId);
		
		int idd = -1;
		
		ResultSet res = preparedStatement.executeQuery();
		
		ArrayList<Integer> allTh  = new ArrayList<Integer>();
		
		while( res.next()  )
		{
			idd = res.getInt("id");
			
			allTh.add(idd);
		}
			if( idd >=0 && allTh.contains(movieId) ) { return true; } else {	
		
		return false;
		
			}
		
		
	}

	public int add(String moviename, String categorys, String languages, String filmCertifications, String duration) throws SQLException {
		
		String s = "insert into movie(name,category,language,film_certification,duration) values(?,?,?,?,?)";
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,moviename);
		preparedStatement.setObject(2,categorys , Types.OTHER );
		preparedStatement.setObject(3, languages , Types.OTHER );
		preparedStatement.setObject(4, filmCertifications , Types.OTHER );
		preparedStatement.setObject(5, duration , Types.OTHER );
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
		
	}
	
	
	public int editMovie( int movieId , String language, String duration ) throws SQLException
	{
		
		String s = "update movie set language = ? , duration = ? where id = ? ";
	
		preparedStatement = conn.prepareStatement(s);
		
		
		preparedStatement.setObject(1, language , Types.OTHER );
		
		preparedStatement.setObject(2, duration , Types.OTHER );
		
		preparedStatement.setInt(3, movieId  );
		
		int res = preparedStatement.executeUpdate();  
		
		return res;
		
		
	}
	

	public int getCount() throws SQLException {
		
			String s = "select count(*) from movie ";
			
			preparedStatement = conn.prepareStatement(s);
		
			
					
			ResultSet res = preparedStatement.executeQuery();
			
			int val =0;
			while( res.next() )
			{
				val=	res.getInt(1);
			}
			
			return val;
		}

	public int addCategoryEnum(String category) throws SQLException {
	   
	    if (category.contains("'")) {
	        category = category.replace("'", "''");
	    }

	    String s = "ALTER TYPE movie_category ADD VALUE '" + category + "'";

	  
	    Statement statement = (Statement) conn.createStatement();
	    return ((java.sql.Statement) statement).executeUpdate(s);
	}

	
	
	public int addLanguageEnum(String lanuage) throws SQLException {
		
		 if (lanuage.contains("'")) {
			 lanuage = lanuage.replace("'", "''");
		    }

		    String s = "ALTER TYPE language ADD VALUE '" + lanuage + "'";

		  
		    Statement statement = (Statement) conn.createStatement();
		    return ((java.sql.Statement) statement).executeUpdate(s);
	}
	
	
}
