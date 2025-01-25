package Manager;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.HashMap;

import BookingManagement.DBConnection;

public class CityManager {

	
	Connection conn =  null ; 

	
	PreparedStatement  preparedStatement = null;
	
	public CityManager( Connection conn )
	{
		
		this.conn = conn;
		
	}
	
	
	
	public HashMap<Integer, String> setCity(HashMap<Integer, String> cityMap) throws SQLException 
	{
		
	String cityNames = null;
	String s = "SELECT enum_range(null::city)";
	
	preparedStatement = conn.prepareStatement(s);
	
	ResultSet result = preparedStatement.executeQuery();
	
	while( result.next() ) {
		
	 cityNames =	result.getString(1);

	
	}
	
	if(cityNames != null) {
		
	String cityName = cityNames.substring(1 , cityNames.length()-1 );
		
	String[] st = cityName.split(",");
	
	int key=0;
	for(String name : st )
	{
		
		cityMap.put( key++, name ) ;
	}
	}
	else {
		
		System.out.println("city values empty " + cityNames );
	}
	return cityMap;
	
	}



	public ResultSet getCity() throws SQLException {
		
		
		String s = "Select * from city";	
		
		preparedStatement = conn.prepareStatement(s);
		
		int tid=0;
				
		ResultSet res = preparedStatement.executeQuery();


		return res;
		

	}

	

	public int addCityEnum(String city, String district, long zipcode) throws SQLException {
	
		String s = "insert into city(name,district,zipcode) values(?,?,?)";
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setString(1,city);
		preparedStatement.setString(2,district);
		preparedStatement.setLong( 3, zipcode );
		
		
		int res = preparedStatement.executeUpdate();  
		
		return res;	
		
		
		
//
//		 if (city.contains("'")) {
//			 city = city.replace("'", "''");
//		    }
//
//		    String s = "ALTER TYPE city ADD VALUE '" + city + "'";
//
//		  
//		    Statement statement = (Statement) conn.createStatement();
//		    return ((java.sql.Statement) statement).executeUpdate(s);
		
		}



	public HashMap<Integer, String> setLanguage(HashMap<Integer, String> languageMap) throws SQLException {
		
		String langNames = null;
		String s = "SELECT enum_range(null::language)";
		
		preparedStatement = conn.prepareStatement(s);
		
		ResultSet result = preparedStatement.executeQuery();
		
		while( result.next() ) {
			
			langNames =	result.getString(1);

		
		}
		
		if(langNames != null) {
			
		String cityName = langNames.substring(1 , langNames.length()-1 );
			
		String[] st = cityName.split(",");
		int key=0;
		for(String name : st )
		{
			
			languageMap.put( key++, name ) ;
		}
		}
		return languageMap;
		
		
	}



	public boolean getLanguage(HashMap<Integer, String> languageMap) {

		if( languageMap.size() != 0 ) {
			

			int key=0;
			
			System.out.println("==============\n\nLanguageId\tLanguage\n\n==============\n");
			for(String name : languageMap.values() )
			{
				
				languageMap.put( key, name ) ;
				
			
				
				System.out.println( "\n" + (key++) + "\t" + name);
				
		
			}
			
			System.out.println("\n==============");
			return false;
			}
			
			else {
				System.out.println("Language Not Found");
				return true;
			}
	}


	public HashMap<Integer, String> setCategory(HashMap<Integer, String> categoryMap) throws SQLException {
		
		String categoryNames = null;
		String s = "SELECT enum_range(null::movie_category)";
		
		preparedStatement = conn.prepareStatement(s);
		
		ResultSet result = preparedStatement.executeQuery();
		
		while( result.next() ) {
			
			categoryNames =	result.getString(1);

		
		}
		
		if(categoryNames != null) {
			
		String cityName = categoryNames.substring(1 , categoryNames.length()-1 );
			
		String[] st = cityName.split(",");
		int key=0;
		for(String name : st )
		{
			
			categoryMap.put( key++, name ) ;
		}
		}
		return categoryMap;
		
	}



	public boolean getCategory(HashMap<Integer, String> categoryMap) {
		
		if( categoryMap.size() != 0 ) {
			

			int key=0;
			
			System.out.println("==============\n\nCategoryId\tCategory\n\n==============\n");
			for(String name : categoryMap.values() )
			{
				
				categoryMap.put( key, name ) ;
				
			
				
				System.out.println( "\n" + (key++) + "\t" + name);
				
		
			}
			
			System.out.println("\n==============");
			return false;
			}
			
			else {
				System.out.println("Category Not Found");
				return true;
			}
	}



	public String getCityNameById(int cityId) throws SQLException {
		
		String s = "select name from city where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,cityId);
		
		String name = null ;
				
		ResultSet res = preparedStatement.executeQuery();

		while(res.next())
		{   
			name = res.getString("name");
			
		}
		
		return name;
		
	}



	public boolean isCityExist(int cityId) throws SQLException {
		
	String s = "select id from city where id = ?";
		
		
		preparedStatement = conn.prepareStatement(s);
		
		preparedStatement.setInt(1,cityId);
		
		int idd = -1;
		
		ResultSet res = preparedStatement.executeQuery();
		
		ArrayList<Integer> allTh  = new ArrayList<Integer>();
		
		while( res.next()  )
		{
			idd = res.getInt("id");
			
			allTh.add(idd);
		}
			if( idd >=0 && allTh.contains(cityId) ) { return true; } else {	
		
		return false;
		
			}
	}



	public int getCount() throws SQLException {
	
		String s = "select count(*) from city ";
		
		preparedStatement = conn.prepareStatement(s);
	
		
				
		ResultSet res = preparedStatement.executeQuery();
		
		int val =0;
		while( res.next() )
		{
			val=	res.getInt(1);
		}
		
		return val;
	}
	
	
	//================================================================================================================
	


	
}
