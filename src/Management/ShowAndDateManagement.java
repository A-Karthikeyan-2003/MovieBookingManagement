package Management;

import java.util.ArrayList;
import java.util.HashMap;

import DataModels.Show;
import DataModels.Theatre;

public class ShowAndDateManagement  {
	

	 
	public static HashMap<String,ArrayList<Show>> addDateAndShow( HashMap<String,ArrayList<Show>> hashmap, String dateForShow, Show show)
	
	
			throws CloneNotSupportedException {
		
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
		showList.add( (Show) show.clone() );
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
	
	int index = showList.indexOf( shows );

	if( Status.Booked == status ) {
		
		
		boolean[][] seatsssUpdated = shows.getSeats();
		
		for(int i=0; i<seatsDetails.size(); i++)
		{
			String[] seatt = seatsDetails.get(i).split("-");
			
			int v1 =  ( seatt[0].charAt(0) ) - 65 ;
			int v2 = Integer.parseInt( seatt[1] )-1;
		
			
			seatsssUpdated[v1][v2] = true;
			
//			ArrayList<ArrayList<Boolean>> seatsssUpdated = shows.getSeats();
//			
//			ArrayList<Boolean> seatRow = seatsssUpdated.get(v1);
//			
//			seatRow.add(v2,true);
			
		
			
			
		}
		
		System.out.println("\n-----------------\n");
		
		for(int i=0; i<seatsssUpdated.length; i++)
		{
			for(int j=0; j<seatsssUpdated.length; j++)
			{
				System.out.print( seatsssUpdated[i][j] + " " );
			}
			
			System.out.println();
		}
		
		System.out.println("\n-----------------\n");
		
		shows.setSeats(seatsssUpdated);
		
		
	
	int updatedSeat =  shows.getNoOfSeat() - Seat ;
	
	shows.setNoOfSeat(updatedSeat);

	
	
	}
	else {
		boolean[][] seatsssUpdated = shows.getSeats();
		
		for(int i=0; i<seatsDetails.size(); i++)
		{
			String[] seatt = seatsDetails.get(i).split("-");
			
			int v1 =  ( seatt[0].charAt(0) ) - 65 ;
			int v2 = Integer.parseInt( seatt[1] )-1;
			
		
			
			seatsssUpdated[v1][v2] = false;
			
			
//			ArrayList<ArrayList<Boolean>> seatsssUpdated = shows.getSeats();
//			
//			ArrayList<Boolean> seatRow = seatsssUpdated.get(v1);
//			
//			seatRow.add(v2,false);
			
	
			
			
		}
		
		shows.setSeats(seatsssUpdated);
		

		System.out.println("\n-----------------\n");
		
		for(int i=0; i<seatsssUpdated.length; i++)
		{
			for(int j=0; j<seatsssUpdated.length; j++)
			{
				System.out.print( seatsssUpdated[i][j] + " " );
			}
			
			System.out.println();
		}
		
		System.out.println("\n-----------------\n");
		
		int updatedSeat =  shows.getNoOfSeat() + Seat ;
		
		shows.setNoOfSeat(updatedSeat);
		
	}
	

//	showList.add( index, shows);
//
//	hashmap.put(dateForMovie, showList);
	
	
	ArrayList<Show> s1 =	hashmap.get("2024-12-30");
	ArrayList<Show> s2 =	hashmap.get("2024-12-31");
	
	Show sh = s1.get(0);
	
	boolean[][] vb= sh.getSeats();

	System.out.println("\n-----------------\n");
	
	for(int i=0; i<vb.length; i++)
	{
		for(int j=0; j<vb.length; j++)
		{
			System.out.print( vb[i][j] + " " );
		}
		
		System.out.println();
	}
	
	System.out.println("\n-----------------\n");
Show sh2 = s2.get(0);
	
	boolean[][] vb2= sh2.getSeats();

	System.out.println("\n-----------------\n");
	
	for(int i=0; i<vb2.length; i++)
	{
		for(int j=0; j<vb2.length; j++)
		{
			System.out.print( vb2[i][j] + " " );
		}
		
		System.out.println();
	}
	
	System.out.println("\n-----------------\n");
		
	}
	
	
	public static int getAvailableSeat( String dateForMovie, HashMap<String,ArrayList<Show>> hashmap, int showId )
	{
		ArrayList<Show> showList =	hashmap.get(dateForMovie);
		
		ArrayList<Show> s1 =	hashmap.get("2024-12-30");
		ArrayList<Show> s2 =	hashmap.get("2024-12-31");
		
		Show sh = s1.get(0);
		
		boolean[][] vb= sh.getSeats();

		System.out.println("\n-----------------\n");
		
		for(int i=0; i<vb.length; i++)
		{
			for(int j=0; j<vb.length; j++)
			{
				System.out.print( vb[i][j] + " " );
			}
			
			System.out.println();
		}
		
		System.out.println("\n-----------------\n");
	Show sh2 = s2.get(0);
		
		boolean[][] vb2= sh2.getSeats();

		System.out.println("\n-----------------\n");
		
		for(int i=0; i<vb2.length; i++)
		{
			for(int j=0; j<vb2.length; j++)
			{
				System.out.print( vb2[i][j] + " " );
			}
			
			System.out.println();
		}
		
		System.out.println("\n-----------------\n");
		
//		
//
//		System.out.println(hashmap);
//		
//		System.out.println(showList);
		
		Show shows = null;
		
	//	System.out.println(shows);
		
		for(int i=0; i<showList.size(); i++)
		{

			Show showw = showList.get(i);
			
			if( showw.getId() == showId )
			{
				shows = showw;
				break;
			}
		}
		
		
//		System.out.println(shows.toString());
//		System.out.println(shows);
		
		System.out.println("\n============================================\n");
		
		boolean[][] seatDetails = shows.getSeats();
		

		
		//ArrayList<ArrayList<Boolean>> seatDetails = shows.getSeats();
		
	
		
		char c = 'A'; int y=1;
		
//		for(int i=0; i<seatDetails.size(); i++)
//			{
//				if( i==0 )
//				{
//					for(int j=0; j<seatDetails.size(); j++) {
//					System.out.print(  "       "  + (c++) );
//					}
//					
//					System.out.println();
//				}
//				
//				ArrayList<Boolean> seat = seatDetails.get(i);
//				
//				for(int j=0; j<seat.size(); j++)
//				{
//					
//					
//					
//					if( j==0 )
//					{
//						System.out.print( (y++) + "   " );
//					}
//					
//					System.out.print(  ( ( seat.get(j) == true ) ? "\u001B[41m" + "Book!" + "\u001B[0m" : "Avail" ) + "   " );
//					
//				}
//				
//				System.out.println();
//				
//			}
		
		
		
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
				
				System.out.print(  ( ( seatDetails[i][j] == true ) ? "\u001B[41m" + "Book!" + "\u001B[0m" : "Avail" ) + "   " );
				
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
	
	//======================================================================
	
	

}
