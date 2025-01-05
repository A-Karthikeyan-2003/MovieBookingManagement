package Helper;

import java.util.ArrayList;

import DataModels.Screen;
import DataModels.Theatre;

public class ScreenManagement {
	
	
	

	public static Screen getScreen(ArrayList<Screen> screenList , int screenId)
	{

		Screen screen = null;
		
		for(int i=0; i<screenList.size(); i++)
		{
			Screen	screen1 = screenList.get(i);
		
		if( screen1.getId() == screenId )
		{
			screen = screen1;
			break;
		}
			
		}
		
		return screen;
		
	}
	
	
	
	
	//================================================
	
	public static ArrayList<Screen> addScreen(ArrayList<Screen> screeneList , Screen screen )
	{

		screeneList.add(screen);
		
		return screeneList;
		
	}

	//================================================



}
