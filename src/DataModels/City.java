package DataModels;

import java.util.ArrayList;

public class City {

	private int cityId;
	
	private String cityName , cityZipcode, cityDistrict;
	
	private ArrayList<Theatre> theatre;	

	public City(int cityId, String cityName, String cityZipcode, String cityDistrict) {
		
		this.cityId = cityId;
		this.cityName = cityName;
		this.cityZipcode = cityZipcode;
		this.cityDistrict = cityDistrict;
		
		theatre = new ArrayList<Theatre>();
		
	} 

	public ArrayList<Theatre> getTheatre() {
		return theatre;
	}

	public void setTheatre(Theatre theatre) {
		this.theatre.add(theatre);
	}

	public int getCityId() {
		return cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public String getCityZipcode() {
		return cityZipcode;
	}

	public String getCityDistrict() {
		return cityDistrict;
	}
	
	
	
}
