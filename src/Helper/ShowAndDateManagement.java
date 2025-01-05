package Helper;

import java.util.ArrayList;

import java.util.HashMap;

import DataModels.Show;
import DataModels.Theatre;

public class ShowAndDateManagement  {
	

	 
	public static HashMap<String,ArrayList<Show>> addDateAndShow( HashMap<String,ArrayList<Show>> hashmap, String dateForShow, Show show)
	
	
			throws CloneNotSupportedException {
		
		
		
		if( dateForShow.contains(dateForShow) )
		{
			
		
		String[] dateForShowArray = dateForShow.split(",");
		
		
		for(int i=0; i<dateForShowArray.length; i++) {

			String date = dateForShowArray[i];
			
			if(	hashmap.containsKey(date) ) {
		
		ArrayList<Show> showList= hashmap.get(date);
		
		showList.add(  (Show) show.clone() );
		
		hashmap.put(date,showList);
		
			}
			
			else {
				
		ArrayList<Show> showList = new ArrayList<Show>();
		
		Show sh = new Show(show);
		
		showList.add(sh);
		
		hashmap.put(date,showList);
	}
	
		}
		
		
	}
	else {
		
		String date = dateForShow;
		
		if(	hashmap.containsKey(date) ) {
			
			ArrayList<Show> showList= hashmap.get(date);
			
			showList.add(  (Show) show.clone() );
			
			hashmap.put(date,showList);
			
		}
		else {
			ArrayList<Show> showList = new ArrayList<Show>();
			
			Show sh = new Show(show);
			
			showList.add(sh);
			
			hashmap.put(date,showList);
		}
	}
	
	return hashmap;
		
	}
	
	
	//====================================================================
	

	public static void updateSeatOfShowForThatDate(Show show, String dateForMovie, HashMap<String,ArrayList<Show>> hashmap, int Seat, Status status, ArrayList<String> seatsDetails ) {
		
	ArrayList<Show> showList =	hashmap.get(dateForMovie);
	


	
	Show shows = null;
	
	for(int i=0; i<showList.size(); i++)
	{

		Show showw = showList.get(i);
		
		if( showw.getId() == show.getId() )
		{
			shows = showw;
			break;
		}
	}
	


	if( Status.Booked == status ) {
		

		
		for(int i=0; i<seatsDetails.size(); i++)
		{
			String[] seatt = seatsDetails.get(i).split("-");
			
			int v2 =  ( seatt[0].charAt(0) ) - 65 ;
			int v1 = Integer.parseInt( seatt[1] )-1;
		
			
			shows.getSeats()[v1][v2] = 1; // true;
			
			
		}
		


		
		
	
	int updatedSeat =  shows.getNoOfSeat() - Seat ;
	
	shows.setNoOfSeat(updatedSeat);

	
	
	}
	else {

		for(int i=0; i<seatsDetails.size(); i++)
		{
			String[] seatt = seatsDetails.get(i).split("-");
			
			int v2 =  ( seatt[0].charAt(0) ) - 65 ;
			int v1 = Integer.parseInt( seatt[1] )-1;
			
		
			
			shows.getSeats()[v1][v2] = 0; // false;
			
		
			
		}
		
	
	
		
		int updatedSeat =  shows.getNoOfSeat() + Seat ;
		
		shows.setNoOfSeat(updatedSeat);
		
	}
	

		
	}
	
	
	public static int getAvailableSeat( String dateForMovie, HashMap<String,ArrayList<Show>> hashmap, int showId )
	{
		ArrayList<Show> showList =	hashmap.get(dateForMovie);
		

		
		Show shows = null;

		
		for(int i=0; i<showList.size(); i++)
		{

			Show showw = showList.get(i);
			
			if( showw.getId() == showId )
			{
				shows = showw;
				break;
			}
		}
		
		
		System.out.println(shows.toString());
		System.out.println(shows);
		
		System.out.println("\n============================================\n");
		

		
		int[][] seatDetails = shows.getSeats();
	
		char c = 'A'; int y=1;
		
		
		
		for(int i=0; i<seatDetails.length; i++)
		{
			if( i==0 )
			{
				for(int j=0; j<seatDetails.length; j++) {
				System.out.print(  "       "  + (c++) );
				}
				
				System.out.println();
			}
			
			for(int j=0; j<seatDetails[i].length; j++)
			{			
				if( j==0 )
				{
					System.out.print( (y++) + "   " );
				}
				
				System.out.print(  ( ( seatDetails[i][j] == 1 ) ? "\u001B[41m" + "Book!" + "\u001B[0m" : "Avail" ) + "   " );
				
			}
			
			System.out.println();
			
		}
		
		
		
		System.out.println("\n============================================\n");
		
		return shows.getNoOfSeat();
		
	}
	
	//=========================================================================
	
	
	
	public static boolean checkShowIsExistForDate(Show show, String dateForMovie, HashMap<String,ArrayList<Show>> hashmap ) {
		
	ArrayList<Show> showList =	hashmap.get(dateForMovie);
	
	for(int i=0; i<showList.size(); i++)
	{
		Show showw = showList.get(i);
		
		if( showw.getId() == show.getId() && showw.getNoOfSeat() != 0 )
		{
			return true;
		}
	}
		
		return false;
		
		
	}


	public static void makeSubscriptionSeats(HashMap<String, ArrayList<Show>> hashmap, String dateForMovie, int showId,  ArrayList<String> seatsDetails) {
	
		
		ArrayList<Show> showList =	hashmap.get(dateForMovie);
		
		Show shows = null;

		
		for(int i=0; i<showList.size(); i++)
		{

			Show showw = showList.get(i);
			
			if( showw.getId() == showId )
			{
				shows = showw;
				break;
			}
		}
		
		
		for(int i=0; i<seatsDetails.size(); i++)
		{
			String[] seatt = seatsDetails.get(i).split("-");
			
			int v2 =  ( seatt[0].charAt(0) ) - 65 ;
			int v1 = Integer.parseInt( seatt[1] )-1;
		
			
			shows.getSeats()[v1][v2] = -1; // true;
			
			
			// -1 -- subscription user
			//  0 -- available
			//  1 -- booked 	\u001B[34m
			
		}
		
		
		
		
		
	}
	
	//======================================================================
	
	

}
