package UserInterface;

import java.util.ArrayList;
import java.util.Scanner;

import AccessService.UserAccessService;
import DataModels.User;

public class UserDashBoard implements DashBoards {

	
	UserAccessService userAccessService;
	
	User user;

	

	Scanner ob = new Scanner(System.in);
	
	public UserDashBoard(UserAccessService userAccessService, User user) {
		
		this.userAccessService = userAccessService;
		
		this.user = (User) user;
		
	}
	
	//==========================================
	

	private void search() {
		
		System.out.println("Enter Filter search By ., \n1. View Movie's in Theatre\n2. View Theatre's For Movie\n3. View Theatre's For City\n ");
		int choiceForSearch = ob.nextInt();
		
		if( choiceForSearch == 1 )
		{
			
			System.out.println("Enter the theatre id to view Movies : ");
			int theatreId = ob.nextInt();
			
			this.userAccessService.viewAllMovieInTheatre(theatreId);
		}
		else if( choiceForSearch == 2 )
		{
			System.out.println("Enter the movie id to view Theatre : ");
			int movieId= ob.nextInt();
			
			this.userAccessService.viewAllTheatreForMovie(movieId);
		
		}
		else if( choiceForSearch == 3 )
		{
			System.out.println("Enter the city id to view Theatre : ");
			int cityId= ob.nextInt();
			
			
			this.userAccessService.viewAllTheatreForCity(cityId);
		}
		else if( choiceForSearch == 4 )
		{
			
		}
		
	}
	
	
	//====================================================
	
	
	private void viewAvailableSeatForParticularShowInParticularDate() {
		
		System.out.println("Enter show Id to view Booking Details : ");
		int showId = ob.nextInt();
		
		System.out.println("Enter Date to view Booking Details : ");
		String date = ob.next();
		
		this.userAccessService.viewAvailableSeatForParticularShowInParticularDate( date, showId);
		
	}

	
	
	//=========================================
	
	private void bookTicket() {
		
		System.out.println("Enter Theatre id : ");
		int theatreId = ob.nextInt();
		
		System.out.println("Enter Date : Format : ( DD/MM/YYYY ) ");
		String dateForMovie = ob.next();
		
		System.out.println("Enter screen Id : ");
		int screenId = ob.nextInt();
		
		System.out.println("Enter show Id : ");
		int showId = ob.nextInt();
		
		System.out.println("How many seat you want to book : ");
		int noOfSeatForThisShow = ob.nextInt();
		
		ArrayList<String> seatsDetails = new ArrayList<String>();
		
		for(int i=0; i<noOfSeatForThisShow; i++)
		{
			System.out.println("Enter "+ (i+1) + "'s Seat : ");
		
			seatsDetails.add( ob.next() );
			
		}
		
		this.userAccessService.bookTicket(theatreId,dateForMovie, screenId , showId, noOfSeatForThisShow , user.getId(), seatsDetails );
		
		
	}

	
	//===================================================
	

	private void history() {
		
		this.userAccessService.historyViewForUser(  user.getId() );
	
		
	}

	
	private void cancelTickets() {
		
		this.userAccessService.historyViewForUser( user.getId() );
		
		System.out.println("Enter BookingTicket Transaction Id to cancel the Tickets : ");
		
		int bookingTicketTransactionId = ob.nextInt();
		
		this.userAccessService.cancelTickets(bookingTicketTransactionId);
		
		
	}

	

	public int showDashboard() {
		
		
		System.out.println("Enter choice : \n1.Show Details Search\n2.Book Ticket\n3.Cancel Ticket\n4.History\n5.View Available Seat For Particular Show in Particular Date \n6.Logout\n");
		
		int choice = ob.nextInt();
		
		switch(choice)
		{
		
		case 1:
			
			this.search();
			
			break;
		case 2:
			
			this.bookTicket();
			
			break;
			
		case 3:
			
			this.cancelTickets();
			
			break;
			
		case 4:
			
			this.history();
			
			break;
			
		case 5:
			
			this.viewAvailableSeatForParticularShowInParticularDate();
			
			break;
			
		case 6:
			return 0;
		
		}
		
		
		return 1;
	}







}
