package UiDashBoards;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import AccessService.UserAccessService;
import DataModels.Roles;
import DataModels.User;
import Helper.ValidationManagement;

public class UserDashBoard implements DashBoards {

	
	UserAccessService userAccessService;
	
	User user;

	

	Scanner ob = new Scanner(System.in);
	
	public UserDashBoard(UserAccessService userAccessService, User user ) {
		
		this.userAccessService = userAccessService;
		
		this.user =  user;
		
	}
	
	//==========================================
	

	private void search() throws SQLException {
		
		int choiceForSearch = -1, theatreId = -1, movieId = -1, cityId = -1 ;
		
		System.out.println("Enter Filter search By ., \n1. View Movie's in Theatre\n2. View Theatre's For Movie\n3. View Theatre's For City\n ");
		while(true) {
			
		try { choiceForSearch = ob.nextInt();  } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }
		
		if( choiceForSearch == 1 )
		{
			while(true) {
			userAccessService.viewTheatreForUser();
			System.out.println("Enter the theatre id to view Movies : ");	
			//theatreId = ob.nextInt();  
			try { theatreId = ob.nextInt();   } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }
			if( !userAccessService.isExistId(theatreId) ) { System.out.println("Not Found");   
			} else { break; }  }
			
			this.userAccessService.viewAllMovieInTheatre(theatreId);
		}
		else if( choiceForSearch == 2 )
		{
			
			while(true) { 
			userAccessService.viewMovies();
			System.out.println("Enter the movie id to view Theatre : ");
			// movieId= ob.nextInt();  
			 try { movieId = ob.nextInt();  } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }
			 if( userAccessService.isValidMovieId(movieId) ) { break ; }else { System.out.println("Enter valid movie id"); }
			  }
			 
			this.userAccessService.viewAllTheatreForMovie(movieId);
		
		}
		else if( choiceForSearch == 3 )
		{  
			while(true) {
			userAccessService.viewCity();
			System.out.println("Enter the city id to view Theatre : ");
			// cityId= ob.nextInt();
			 try { cityId = ob.nextInt();  } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }

				boolean city = userAccessService.getCity(cityId);
				
				if(city == false) { System.out.println("City not found"); } else { break; }  }
					
		
			
			this.userAccessService.viewAllTheatreForCity(cityId);
		}
	
		if( choiceForSearch== 1 || choiceForSearch ==2 || choiceForSearch ==3 )
		{
			break;
		}
		
		}
		
	}
	
	
	//====================================================
	
	

	
	
	//=========================================
	
	private void bookTicket() throws SQLException {
		
		int theatreId = -1,screenId = -1,showId = -1,noOfSeatForThisShow = -1;
		ArrayList<String> seatsDetails ;
		String dateForMovie;
		
		while(true) {	
		userAccessService.viewTheatreForUser();
		System.out.println("Enter Theatre id : ");
		try { theatreId = ob.nextInt();   } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }
		if( !userAccessService.isExistId(theatreId) ) { System.out.println("Not Found"); } else { break; }  } 
		
	
		
		while(true) {	
		System.out.println("Enter Date : Format : ( YYYY-MM-DD ) ");
		dateForMovie = ob.next();
		if( !ValidationManagement.validateDate( dateForMovie ) ) { } else { break; }  }
		
		while(true) {
		userAccessService.displayScreenIdNameForThisTheatreId(theatreId);
		System.out.println("Enter screen Id : ");
		 try { screenId = ob.nextInt();   } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }
		if( !userAccessService.isValidToScreen( screenId , theatreId ) ) { System.out.println("You can not access this screen ");  
		} else { break; }  }
		
		
		while(true) {
		 userAccessService.displayShowIdAndNameForThisScreen( screenId );
		 System.out.println("Enter show Id : "); 
		 try { showId = ob.nextInt();  } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }
		if( !userAccessService.isValidToShow( showId, screenId ) ) { System.out.println("You can not access this show ");  } else { break; }  }
		 
		while(true) {
		System.out.println("How many seat you want to book : ");
		try { noOfSeatForThisShow = ob.nextInt();   } catch(InputMismatchException exception) { System.out.println("Enter Numerical value");  }
		if( noOfSeatForThisShow <=0 ) { System.out.println("pls Enter Positive value only");  } else { break; }  }
		
		while(true) { 
		 seatsDetails = new ArrayList<String>();
		int c=0;
		for(int i=0; i<noOfSeatForThisShow; i++)
		{
			System.out.println("Enter "+ (i+1) + "'s Seat : Format : Like (A-1,B-2)");
			String seat = ob.next();
			if(!ValidationManagement.validateSeat( seat ) ) { c=-1; break; }
			seatsDetails.add( seat );
			
		}
		
		if( c!=-1 ) { break; }
		
		
		}
		
		this.userAccessService.bookTicket(theatreId,dateForMovie, screenId , showId, noOfSeatForThisShow , user.getId(), seatsDetails );
		
		
	}

	
	//===================================================
	

	private void history() throws SQLException {
		
		this.userAccessService.historyViewForUser(  user.getId() );
	
		
	}

	
	private void cancelTickets() throws SQLException {
		
		int bookingTicketTransactionId;
		
		
		while(true) {
		this.userAccessService.historyViewForUser( user.getId() );
		
		 System.out.println("Enter BookingTicket Transaction Id to cancel the Tickets : ");
		 bookingTicketTransactionId = ob.nextInt(); 
		if ( this.userAccessService.isValidForBookingId(bookingTicketTransactionId , this.user.getId()) ) { break; } else { System.out.println("you cannot access this booking transaction id "); }
		}
		
		this.userAccessService.cancelTickets(bookingTicketTransactionId);
		
		
		
	}

	

	public int showDashboard() throws SQLException {
		
		int choice;
		System.out.println("\n==================================\n");
		System.out.println("! .. Welcome to " + user.getName() + "'s DashBoard .. !" );
		System.out.println("\n==================================\n");
		System.out.println("\nEnter choice : \n1.Show Details Search\n2.Book Ticket\n3.Cancel Ticket\n4.History\n5.Logout\n");
		
		choice = ob.nextInt();
		if( !ValidationManagement.validateInput(choice) ) { return 0; }
		
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
			return 0;
		
		}
		
		
		return 1;
	}







}
