package DataModels;

import java.util.ArrayList;

public class Show implements Cloneable {


	private int id, noOfSeat, rate;
	
	private String name , timeStart;
	
	private int theatreId, screenId ;
	
	private int movieId;
	
	 private boolean[][] seats;
	
	// private ArrayList<ArrayList<Boolean>> seats;

	public Show(int id, String name, String timeStart, int movieId, int screenId, int theatreId, int noOfSeat, int rate) {
		
		this.id = id;
		this.name = name;
		this.timeStart = timeStart;
		this.movieId = movieId;
		this.screenId = screenId;
		this.theatreId = theatreId;
		
		this.noOfSeat = noOfSeat;
		
		this.rate = rate;
		
//		this.seats = new ArrayList<ArrayList<Boolean>>();
//		
//		for(int i=0; i<5; i++)
//		{
//			ArrayList<Boolean> seat = new ArrayList<Boolean>();
//			
//			for(int j=0; j<5; j++)
//			{
//				seat.add(false);
//			}
//			
//			this.seats.add(seat);
//		}
		
		this.seats = new boolean[5][5];
	}
	
	
	
	

	public boolean[][] getSeats() {
		return seats;
	}




	public void setSeats(boolean[][] seats) {
		this.seats = seats;
	}



//
//	public ArrayList<ArrayList<Boolean>> getSeats() {
//		return seats;
//	}





//	public void setSeats(ArrayList<ArrayList<Boolean>> seats) {
//		this.seats = seats;
//	}
//




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
		
		return super.clone();
	}

	
	
	
}
