package UiDashBoards;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

import AccessService.TheatreOwnerAccess;

import DataModels.Roles;
import DataModels.TheatreOwner;
import Helper.ValidationManagement;

public class TheatrOwnerDashBoard implements DashBoards {
	
	TheatreOwnerAccess theatreOwnerAccess;

	Scanner ob = new Scanner(System.in);
	
	TheatreOwner theatreOwner;
	
	public TheatrOwnerDashBoard(TheatreOwnerAccess theatreOwnerAccess, TheatreOwner theatreOwner ) {
	
		this.theatreOwnerAccess = theatreOwnerAccess;
		
		this.theatreOwner = theatreOwner;
		
		
	}
	
	private void registerTheatreDetails() throws SQLException {
		
		String theatreName;
		
		int cityId = -1;
		
		while(true) { 
		System.out.println("Enter Theatre Name : " );
		if( ValidationManagement.validateInput(theatreName = ob.nextLine()) ) { break; } }
		
		while(true) {
		theatreOwnerAccess.viewCity();
		
		System.out.println("Enter City id : ");
		
		
		try { cityId = ob.nextInt(); } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
		
		
		boolean city = theatreOwnerAccess.getCity(cityId);
		
		if(city == false) { System.out.println("City not found");  } else { break; }
		
		}
			
		theatreOwnerAccess.addTheatre( theatreName, cityId, theatreOwner.getId()  );
		
	
		
		
	}


	

	private void addScreenDetails() throws SQLException {
		
		int theatreId = -1,row, col = -1;
		
		String screenName;
		
		while(true) {
		
		theatreOwnerAccess.viewTheatreForOwner( theatreOwner.getId()  );
		
		System.out.println("Enter Theatre id : " );
		try { theatreId = ob.nextInt(); } catch(InputMismatchException e) { System.out.println("Enter Number only");  }	
		if( !theatreOwnerAccess.isValidToAdd( theatreOwner.getId() , theatreId ) ) { System.out.println("You can not add screen to this theatre"); }else { break; }
		}
		
		while(true) { 
		System.out.println("Enter Screen Name : " );
		ob.next();
		try { screenName = ob.nextLine(); break; } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
		}
	
		while(true) {
		System.out.println("Enter no of rows For Seat Arrangement : " );
		
		try { row = ob.nextInt(); break; } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
		}
		
		while(true) { 
		System.out.println("Enter no of Column For Seat Arrangement : " );
		try { col = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
	
		if( row<=0 || col <=0 ) { System.out.println("Pls Enter positive number only"); }else { break; }
		
		}
		
		theatreOwnerAccess.addScreens( theatreId, screenName ,  row, col );
		
	} 
	
	

	private void addShowDetails() throws SQLException, NumberFormatException, ParseException {
		
		int  theatreId = -1, screenId = -1, noOfSeats, movieId = -1,  rate = -1 ;
		
		String showName, timeStart, datesForShow; 
		
		
		while(true) { 
		theatreOwnerAccess.viewTheatreForOwner( theatreOwner.getId()  );
		
		System.out.println("Enter Theatre id : " );
	
		try { theatreId = ob.nextInt(); } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
		if( !theatreOwnerAccess.isValidToAdd( theatreOwner.getId() , theatreId ) ) { System.out.println("You can not add show to this theatre");  }else { break; }
		}
		
		
		while(true) {
		theatreOwnerAccess.viewScreenForOwner( theatreId );
		System.out.println("Enter Screen id : " );
		try { screenId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
		if( !theatreOwnerAccess.isValidToAddScreen( screenId , theatreId ) ) { System.out.println("You can not add show to this screen");  }else { break; }
		}
		
		
		while(true) {
		System.out.println("Enter Show Name : " );  
	//	ob.next();
		if( ValidationManagement.validateInput(showName = ob.next()) ) { break; }
		}
		
		while(true) {
		System.out.println("Enter Show Time Start : HH:MM " );
		
		// ob.next();
		if( ValidationManagement.validateTime(timeStart = ob.next()) ) { break; }
		}
	

		while(true) {
		System.out.println("This is movie list which movie you want to show initially .. : "); 
		
		theatreOwnerAccess.viewMovies();
		

		System.out.println("Enter Movie ID : " );
		try { movieId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
		if( !theatreOwnerAccess.isValidMovieId(movieId) ) { System.out.println("Movie not found"); } else { break; }
		
		}
		
		
		while(true) {
		System.out.println("Enter dates to show this show activation : ( in comma seperation ) Format: yyyy-mm-dd ");
		//ob.next();
		datesForShow = ob.next();  
		String[] st = datesForShow.split(","); int c=0;
		
		for(int i=0; i<st.length; i++ ) {	if( !ValidationManagement.validateDate(st[i]) || !ValidationManagement.validateDateAfter(st[i]) ) { c=-1; break; } } if( c!=-1 ) { break; }
		
		}
		
		
		while(true) { 
		System.out.println("Enter show Rate for this movie : ");
		try { rate = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
		if( rate <=0 ) { System.out.println("Pls Enter positive number only");  } else { break; }
		}
		
		theatreOwnerAccess.addShows( theatreId, screenId, showName, timeStart , movieId , datesForShow, rate );
		
	}

	

	private void viewAllBookingTickets() throws SQLException {
		
		int theatreId = -1;
	
		String date ;

		while(true) {
		theatreOwnerAccess.viewTheatreForOwner( theatreOwner.getId()  );
		
		System.out.println("Enter theatre Id to view Booking Details : "); 
		try { theatreId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
		if( !theatreOwnerAccess.isValidToAdd( theatreOwner.getId() , theatreId ) ) { System.out.println("You can not view booking ticket for this theatre");  } else { break; } 
		
		}
		
		theatreOwnerAccess.viewAllBookingTickets( theatreId );
		
	}
	
	

	//========================================================
	


	private void editTheatre() throws SQLException {
		int theatreId = -1;
		String theatreName;
		
		while(true) {
			
		theatreOwnerAccess.viewTheatreForOwner( theatreOwner.getId()  );
		System.out.println("Enter Theatre id : " );
		
		try { theatreId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
		if( !theatreOwnerAccess.isValidToAdd( theatreOwner.getId() , theatreId ) ) { System.out.println("You can not add show to this theatre");  } else { break; }
		}
		
		
		while(true) { 
		System.out.println("Enter Theatre Name : " );
		if( ValidationManagement.validateInput(theatreName = ob.next()) ) { break; }
		}
		
		theatreOwnerAccess.editTheatre(theatreName,theatreId);
	}

	
	//============================================================
	
	private void editScreen() throws SQLException {

		int theatreId = -1,screenId = -1;
		String screenName;

		theatreOwnerAccess.viewTheatreForOwner( theatreOwner.getId()  );
		
		while(true) {
		System.out.println("Enter Theatre id : " );
		try { theatreId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only");  }	
		if( !theatreOwnerAccess.isValidToAdd( theatreOwner.getId() , theatreId ) ) { System.out.println("You can not add screen to this theatre");  } else { break; }
		}
		
		while(true) {
		System.out.println("Enter Screen Name : " );
		try { screenName = ob.next(); break; } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
		}
		
		while(true) {
		theatreOwnerAccess.viewScreenForOwner( theatreId );
		System.out.println("Enter Screen id : " );
		try { screenId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
		if( !theatreOwnerAccess.isValidToAddScreen( screenId , theatreId ) ) { System.out.println("You can not add show to this screen");  } else {  break; } 
		}
		
		theatreOwnerAccess.editScreen(screenName, screenId);
	}

	
	
	public int showDashboard() throws SQLException, NumberFormatException, ParseException {
		
		Scanner ob = new Scanner(System.in);
		
		int choice;
		System.out.println("\n==================================\n");
		System.out.println("! .. Welcome to " + theatreOwner.getName() + "'s DashBoard .. !" );
		System.out.println("\n==================================\n");
		System.out.println("\nEnter choice : \n1.Add Theatre\n2.Add Screen\n3.Add ShowDetails\n4.View All Booking Tickets in Our Theatre\n5.Edit Theatre\n6.Edit Screen\n7.Logout\n");
		
		try { choice = ob.nextInt();} catch(InputMismatchException e) { System.out.println("Enter Number only"); return 0; }
		
		
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
			this.viewAllBookingTickets();
		
			
			break;
			
		case 5:
			this.editTheatre();
			
			
			break;
			
		case 6:
	
			this.editScreen();
	
	break;

			
		case 7:
			
			return 0;
		
		}
		
		
		return 1;
	}



	


}
