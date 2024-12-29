package DataModels;

import java.util.ArrayList;

public class Theatre {
	
	private int id;
	
	private String name, owner;
	
	private ArrayList<Screen> screenList;
	
	private int cityId;

	public Theatre(int id, String name, int cityId, String owner) {

		this.id = id;
		this.name = name;
		this.cityId = cityId;
		this.owner = owner;
		
		screenList = new ArrayList<Screen>();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}



	public int getCityId() {
		return cityId;
	}

	public void setCityId(int cityId) {
		this.cityId = cityId;
	}

	public ArrayList<Screen> getScreenList() {
		return screenList;
	}

	public void setScreenList(ArrayList<Screen> screenList) {
		
		this.screenList = screenList;
	}

	public int getId() {
		return id;
	}

	public String getOwner() {
		return owner;
	}
	
	

}
