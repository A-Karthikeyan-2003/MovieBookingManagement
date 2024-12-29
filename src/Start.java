import java.util.Scanner;

import AccessService.CommonAccess;
import BookingManagement.BookingManagementSystem;
import BookingManagement.DashBoards;
import BookingManagement.Navigate;
import DataModels.City;
import DataModels.Roles;
import DataModels.TheatreOwner;
import DataModels.User;

public class Start {

	
	CommonAccess commonAccess =  new BookingManagementSystem();
	
	Scanner ob = new Scanner(System.in);

	
	

	public void register() {
	
		
		System.out.println("Enter username : ");
		String username = ob.next();
		
		System.out.println("Enter email : ");
		String email = ob.next();
		
		System.out.println("Enter password : ");
		String password = ob.next();
		
		System.out.println("Enter Date of birth : ");
		String dateOfBirth = ob.next();
		
		System.out.println("Enter contact : ");
		String contact = ob.next();
		
		System.out.println("Enter userType : \t1.User\t 2.ThatreOwner\n");
		int choiceForUserType = ob.nextInt();
		

		
		if( choiceForUserType == 1  )
		{
			commonAccess.addToUser(username, email, password, contact, dateOfBirth);
			
		}
		else {
			

			
			System.out.println("Enter Document Proof Id : ");
			String documentProofId = ob.next();
			
			commonAccess.viewCity();
			
			System.out.println("Enter City Id : ");
			int cityId = ob.nextInt();
			
			City city = commonAccess.getCity(cityId);
			
			if( city!=null ) {
			
			commonAccess.addToTheatreOwner(username, email, password, contact, documentProofId, dateOfBirth, cityId);
	
			}
			else {
				System.out.println("City not found");
			}
			
		}
		
		System.out.println("Register Successfully..");
		
		
		
	}
	
	
	//==============================================================================================
	
	
	public void login() {
		
		DashBoards dashBoards=null;
		
		Roles user = null;
		
		System.out.println("Enter email : ");
		String email = ob.next();
		
		System.out.println("Enter password : ");
		String password = ob.next();
		
		
		user = commonAccess.isExistInAccount(email,password); //bookingManagementSystem.login(email,password);
		
		
		
		dashBoards = Navigate.navigation( (BookingManagementSystem) commonAccess , user );
		
		if( dashBoards != null )
		{
			while(true) {
			
			int result = dashBoards.showDashboard();
			
			if( result == 0 )
			{
				break;
			}
			
			}
		}
	
		
		if( user == null )

		{
			
			System.out.println("Account not found");

		}
	}

	
	//=============================================================
	
	
	
	public static void main(String[] args)
	{
		
		
		Start start  = new Start();
		
		Scanner ob = new Scanner(System.in);
		
		
	
		
		while(true) {
		
		System.out.println("\nEnter choice : \n1.Login\n2.Register\n");
		
		int choice = ob.nextInt();
		
		switch(choice)
		{
		case 1:
			
			start.login();
			
			break;
		case 2:
			
			start.register();
			
			break;
			
		
		}
		
		}
		
	}
	
}
