package DataModels;

import java.util.ArrayList;

public class Screen {

	
	private int id;
	
	private String name;
	
	private int theatreId;
	
	private ArrayList<Show> showList;

	public Screen(int id, String name, int theatreId) {
	
		this.id = id;
	
		this.name = name;
		this.theatreId = theatreId;
		
		showList = new ArrayList<Show>();
		
	}
	
	



	public int getTheatreId() {
		return theatreId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public ArrayList<Show> getShowList() {
		return showList;
	}

	public void setShowList(ArrayList<Show>  showList) {
		this.showList = showList;
	}

	public int getId() {
		return id;
	}
	
	
	
	
}
