package Management;

import java.util.ArrayList;

import DataModels.Theatre;

public class TheatreManagement {
	
	
	public static Theatre getTheatre(ArrayList<Theatre> theatreList , int theatreId)
	{

		Theatre theatre = null;
		
		for(int i=0; i<theatreList.size(); i++)
		{
		Theatre	theatree = theatreList.get(i);
		
		if( theatree.getId() == theatreId )
		{
			theatre = theatree;
			break;
		}
			
		}
		
		return theatre;
		
	}
	
	//================================================
	
	public static ArrayList<Theatre> addTheatre(ArrayList<Theatre> theatreList , Theatre theatre)
	{

		theatreList.add(theatre);
		
		return theatreList;
		
	}



	//================================================

}
