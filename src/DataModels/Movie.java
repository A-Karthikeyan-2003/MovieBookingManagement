package DataModels;

import java.util.ArrayList;

public class Movie {
	
	private int rating , id ;
	
	private String name ,type , description , language , sensorType , duration ;
	


	public Movie(int id, String name, String type, String description, String language, String sensorType,
			String duration) {
	
		this.id = id;
		this.name = name;
		this.type = type;
		this.description = description;
		this.language = language;
		this.sensorType = sensorType;
		this.duration = duration;
		
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getDuration() {
		return duration;
	}

	public void setDuration(String duration) {
		this.duration = duration;
	}



	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getSensorType() {
		return sensorType;
	}
	
	
	
	
	

}
