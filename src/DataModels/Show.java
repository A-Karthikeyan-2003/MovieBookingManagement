package DataModels;

import java.util.ArrayList;
import java.util.Arrays;

public class Show implements Cloneable {


	private int id, noOfSeat, rate;
	
	private String name , timeStart;
	
	private int theatreId, screenId ;
	
	private int movieId;
	

	
	 private int[][] seats;
	
	
	 
	 public Show(Show show)
	 {
		 
		this( show.id, show.name , show.timeStart, show.movieId, show.screenId , show.theatreId, show.noOfSeat, show.rate );
	 }
	 

	public Show(int id, String name, String timeStart, int movieId, int screenId, int theatreId, int noOfSeat, int rate) {
		
		this.id = id;
		this.name = name;
		this.timeStart = timeStart;
		this.movieId = movieId;
		this.screenId = screenId;
		this.theatreId = theatreId;
		
		this.noOfSeat = noOfSeat;
		
		this.rate = rate;
		

		

		
		
		 this.seats = new int[5][5];
	}
	
	
	




	public int[][] getSeats() {
		return seats;
	}





	public void setSeats(int[][] seats) {
		this.seats = seats;
	}





	public int getRate() {
		return rate;
	}




	public void setRate(int rate) {
		this.rate = rate;
	}




	public int getNoOfSeat() {
		return noOfSeat;
	}

	public void setNoOfSeat(int noOfSeat) {
		this.noOfSeat = noOfSeat;
	}



	public int getTheatreId() {
		return theatreId;
	}




	public int getScreenId() {
		return screenId;
	}




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(String timeStart) {
		this.timeStart = timeStart;
	}


	
	
	
	public int getMovieId() {
		return movieId;
	}




	public void setMovieId(int movieId) {
		this.movieId = movieId;
	}




	public int getId() {
		return id;
	}
	
	
	
	





	public Object clone() throws CloneNotSupportedException {
		
		Show show = (Show) super.clone();
		
		
		return show;
	}

	
	
	
}
