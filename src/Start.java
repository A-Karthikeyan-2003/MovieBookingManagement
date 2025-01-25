import java.util.Arrays;


import java.util.InputMismatchException;
import java.util.Scanner;
import AccessService.NormalAccess;
import AccessService.UserAccessService;
import AccessService.MainAdminAccess;
import BookingManagement.BookingManagementSystem;
import BookingManagement.Navigate;
import DataModels.Roles;
import DataModels.TheatreOwner;
import DataModels.User;
import Helper.SecurityPassword;
import Helper.ValidationManagement;
import UiDashBoards.DashBoards;
import UiDashBoards.MainAdminDashBoard;
import UiDashBoards.UserDashBoard;

import java.sql.Statement;
import java.text.ParseException;
import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Start {

	
	
	NormalAccess commonAccess =  new BookingManagementSystem();
	
	
	Scanner ob = new Scanner(System.in);


	public void register() throws SQLException, NumberFormatException, ParseException {
		
		String username,email,password,dateOfBirth, documentProofId;
		int choiceForUserType = -1;
		long contact;
		Roles user = null ;
		int res=-1;
		
		while(true) {
		System.out.println("Enter username : ");
		if( !ValidationManagement.validateInput( username = ob.next()) ) { System.out.println("Enter Valid name"); } else { break; } }
		
		while(true) {
		System.out.println("Enter email : ");
		email = ob.next();
		if( !ValidationManagement.validateEmail(email) ) {  System.out.println("Enter Valid email") ; }else { break; }
		}
		
		while(true) {
		System.out.println("Enter password : ");
		password = ob.next();
		if( password.trim().length() == 0 ) { System.out.println("password cannot empty"); break; }
		
		if( ValidationManagement.validatePassword(password) ) { break; } 
		
		user = commonAccess.isExistInAccount( email, password   );
		
		if(  user != null ) { System.out.println("Account Already Exist"); }else { break; }
		
		}
		password = SecurityPassword.encryptPassword(password);
	
		while(true) {
		System.out.println("Enter Date of birth : Format : (yyyy-mm-dd) ");
		if( ValidationManagement.validateDate( dateOfBirth = ob.next() ) ) { break; } 
		if( ValidationManagement.validateDateAfter(dateOfBirth) ) {  break; }
		}
		
		while(true) {
		System.out.println("Enter contact : ");
		contact = ob.nextLong();
		if( !ValidationManagement.validateContact(contact) ) { System.out.println("Enter Valid contact"); } else { break ; }  }
		
		while(true) {
		System.out.println("Enter userType : \t1.User\t 2.TheatreOwner\n");
		try { choiceForUserType = ob.nextInt(); } catch(InputMismatchException e) { System.out.println("Enter Number only");  } 
		
	
		if(  choiceForUserType != 1 && choiceForUserType != 2 ) { System.out.println("Pls Enter 1 or 2 only");  } else { break; } }
		

		
		
		if( choiceForUserType == 1  )
		{
			res = commonAccess.addToUser(username, email, password, contact, dateOfBirth, choiceForUserType);
			
		}
		else {
			

			
			System.out.println("Enter Document Proof Id : ");
			documentProofId = ob.next();
			

			res = commonAccess.addToTheatreOwner(username, email, password, contact, documentProofId, dateOfBirth, choiceForUserType);

		}
		
		if( res > 0 ) {
	
		System.out.println("Register Successfully..");
		
		} else {
			System.out.println("Register Failed Try Again..");
		}
		
		}
		

	
	
	
	//==============================================================================================
	

	
	public void login() throws Exception {
		
		DashBoards dashBoards = null;
		
		Roles user = null;
		
		String email, password;
		
		System.out.println("Enter email : ");
		email = ob.next();
		
		System.out.println("Enter password : ");
		password = ob.next();

		 password  = password.equals("admin") ? password : SecurityPassword.encryptPassword(password);

		user = commonAccess.isExistInAccount(email, password   );
	
		if( user == null  && !( email.equals("admin@gmail.com") && password.equals("admin") ) )

		{
		
			System.out.println("Account not found");
			return;

		}
		
		dashBoards = Navigate.navigation( (BookingManagementSystem) commonAccess , user  );
		if( email.equals("admin@gmail.com") && password.equals("admin") ) { dashBoards =  Navigate.navigation( (BookingManagementSystem) commonAccess ); }; 
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
	
		
		
	}

	
	//==============================================================================================
	
	
	
	public static void main(String[] args) throws Exception
	{
		
	
		Start start  = new Start();
		
		Scanner ob = new Scanner(System.in);
		
     	int choice;
     	System.out.println("\n=========================================\n");
		System.out.println("\nWelcome To Movie Ticket Booking Application\n");
		System.out.println("\n=========================================\n");
		
		
		
		do  {
		
		System.out.println("\nEnter choice : \n1.Login\n2.Register\n3.Exit\n");
		
		try { choice = ob.nextInt(); } catch(InputMismatchException e) { System.out.println("Enter Number only"); return; }
		
		switch(choice)
		{
		case 1:
			
			start.login();
			
			break;
		case 2:
			
			start.register();
			
			break;
		
		case 3 :
			
			System.out.println("! -- Code Exit -- !");
			System.exit(0);
		default :
			System.out.println("! -- Enter 1 or 2 only -- !");
		continue;
		
		}
		
		} while(true);
		
		
		
		
		
	}
	
}
