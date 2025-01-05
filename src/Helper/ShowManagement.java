package Helper;

import java.util.ArrayList;

import DataModels.Screen;
import DataModels.Show;

public class ShowManagement {

	

	public static Show getShow(ArrayList<Show> showList , int showId)
	{

		Show show = null;
		
		for(int i=0; i<showList.size(); i++)
		{
			Show show1 = showList.get(i);
		
		if( show1.getId() == showId )
		{
			show = show1;
			break;
		}
			
		}
		
		return show;
		
	}

	
	
	
	//================================================
	
	public static ArrayList<Show> addShow(ArrayList<Show> showList , Show show )
	{

		showList.add(show);
		
		return showList;
		
	}

	//================================================

	
	
}
