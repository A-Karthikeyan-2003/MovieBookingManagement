package BookingManagement;

import java.util.Scanner;

import AccessService.TheatreOwnerAccess;
import DataModels.City;
import DataModels.TheatreOwner;

public class TheatrOwnerDashBoard implements DashBoards {
	
	TheatreOwnerAccess theatreOwnerAccess;

	Scanner ob = new Scanner(System.in);
	
	TheatreOwner theatreOwner;
	
	public TheatrOwnerDashBoard(TheatreOwnerAccess theatreOwnerAccess, TheatreOwner theatreOwner) {
	
		this.theatreOwnerAccess = theatreOwnerAccess;
		
		this.theatreOwner = theatreOwner;
		
		
	}
	
	private void registerTheatreDetails(){
		
		System.out.println("Enter Theatre Name : " );
		String theatreName = ob.next();
		
		theatreOwnerAccess.viewCity();
		
		System.out.println("Enter City Id : ");
		ob.next();
		int cityId = ob.nextInt();
		
		City city = theatreOwnerAccess.getCity(cityId);
		
		if(city!=null) {
		theatreOwnerAccess.addTheatre( theatreName, cityId, theatreOwner.getName()   );
		}
		else {
			System.out.println("City not found");
		}
		
		
	}


	private void releaseMovies() {
		
		
		System.out.println("Enter Theatre id : " );
		int theatreId = ob.nextInt();
		
		System.out.println("Enter Screen id : " );
		int screenId = ob.nextInt();
		
		System.out.println("Enter Show id : " );
		int showId = ob.nextInt();
		
		System.out.println("This is movie list which movie you want to show initially .. : ");
		
		theatreOwnerAccess.viewMovies();
		
		
		System.out.println("Enter Movie ID : " );
		int movieId = ob.nextInt();
		
		
		System.out.println("Enter dates to show this show activation : ( in comma seperation ) ");
		
		String datesForShow = ob.next();
		
		System.out.println("Enter show Rate for this movie : ");
		int rate = ob.nextInt();
		
		System.out.println("Enter No of Seat for this movie : ");
		int seat = ob.nextInt();
		
		theatreOwnerAccess.releaseMovies(theatreId, screenId, showId, movieId , datesForShow , rate , seat );
		
		
		System.out.println("Movie details for this " + showId + " is updated successfully .. ");
		
		
		
	}
	

	private void addScreenDetails() {
		

		System.out.println("Enter Theatre id : " );
		int theatreId = ob.nextInt();
		
		
		System.out.println("Enter Screen Name : " );
		String screenName = ob.next();
	
		theatreOwnerAccess.addScreens( theatreId, screenName );
		
	} 
	
	

	private void addShowDetails() {
		
		System.out.println("Enter Theatre id : " );
		ob.next();
		int theatreId = ob.nextInt();
		
		System.out.println("Enter Screen id : " );
		int screenId = ob.nextInt();
		
		System.out.println("Enter Show Name : " );
		String showName = ob.next();
		
		System.out.println("Enter Show Time Start : " );
		ob.next();
		String timeStart = ob.next();
		
		
		System.out.println("Enter No of Seats : " );
		ob.next();
		int noOfSeats = ob.nextInt();
		
		
		System.out.println("This is movie list which movie you want to show initially .. : "); 
		
		theatreOwnerAccess.viewMovies();
		

		System.out.println("Enter Movie ID : " );
		int movieId = ob.nextInt();
		
		
		System.out.println("Enter dates to show this show activation : ( in comma seperation ) Format: yyyy-mm-dd ");
		
		String datesForShow = ob.next();
		
		
		System.out.println("Enter show Rate for this movie : ");
		int rate = ob.nextInt();
		
		theatreOwnerAccess.addShows( theatreId, screenId, showName, timeStart , movieId , datesForShow , noOfSeats , rate );
		
	}

	

	private void viewAllBookingTickets() {
		
	
		
		System.out.println("Enter show Id to view Booking Details : ");
		int showId = ob.nextInt();
		
		System.out.println("Enter Date to view Booking Details : ");
		String date = ob.next();
		
		theatreOwnerAccess.viewAllBookingTickets( date, showId);
		
	}

	
	
	
	public int showDashboard() {
		
		Scanner ob = new Scanner(System.in);
		
		System.out.println("Enter choice : \n1.Add Theatre\n2.Add Screen\n3.Add ShowDetails\n4.Release Movie\n5.View All Booking Tickets in Our Theatre\n6.Logout\n");
		
		int choice = ob.nextInt();
		
		switch(choice)
		{
		
		case 1:
			
			this.registerTheatreDetails();
			
			break;
		case 2:
			
			this.addScreenDetails();
			
			break;
			
		case 3:
			
			this.addShowDetails();
			
			
			break;
			
		case 4:
			
			this.releaseMovies();
			
			break;
			
		case 5:
			
			this.viewAllBookingTickets();
			
			break;
			
		case 6:
			
			return 0;
		
		}
		
		
		return 1;
	}



}
