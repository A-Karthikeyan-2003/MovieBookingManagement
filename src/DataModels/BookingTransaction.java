package DataModels;

import java.util.ArrayList;

import Helper.Status;

public class BookingTransaction {


	private int id, totalRate, noOfBookedSeat, showId, screenId, userId, theatreId;
	
	private String bookingTimeAndDate, movieDate ;

	
	private Status status;
	
	private ArrayList<String> seatsDetails;


	public BookingTransaction(int id, int userId, int theatreId, int showId, int noOfBookedSeat, int totalRate,
			String bookingTimeAndDate, Status status, String movieDate, int screenId, ArrayList<String> seatsDetails ) {
		this.id = id;
		this.userId = userId;
		this.theatreId = theatreId;
		this.showId = showId;
		this.noOfBookedSeat = noOfBookedSeat;
		this.totalRate = totalRate;
		this.bookingTimeAndDate = bookingTimeAndDate;
		this.status = status;
		this.movieDate = movieDate;
		
		this.screenId = screenId;
		
		this.seatsDetails = seatsDetails;
		
	}



	public ArrayList<String> getSeatsDetails() {
		return seatsDetails;
	}



	public void setSeatsDetails(ArrayList<String> seatsDetails) {
		this.seatsDetails = seatsDetails;
	}



	public int getScreenId() {
		return screenId;
	}



	public void setScreenId(int screenId) {
		this.screenId = screenId;
	}



	public String getMovieDate() {
		return movieDate;
	}



	public int getUserId() {
		return userId;
	}



	public void setUserId(int userId) {
		this.userId = userId;
	}



	public int getTheatreId() {
		return theatreId;
	}



	public void setTheatreId(int theatreId) {
		this.theatreId = theatreId;
	}



	public int getId() {
		return id;
	}





	public int getNoOfBookedSeat() {
		return noOfBookedSeat;
	}

	public int getTotalRate() {
		return totalRate;
	}

	public String getBookingTimeAndDate() {
		return bookingTimeAndDate;
	}



	public int getShowId() {
		return showId;
	}



	public Status getStatus() {
		return status;
	}



	public void setStatus(Status status) {
		this.status = status;
	}
	
	
	
}
