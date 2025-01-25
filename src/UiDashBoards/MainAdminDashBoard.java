package UiDashBoards;

import java.sql.SQLException;

import java.util.EnumSet;
import java.util.InputMismatchException;
import java.util.Scanner;

import AccessService.MainAdminAccess;
import AccessService.UserAccessService;
import BookingManagement.BookingManagementSystem;
import DataModels.User;
import Enum.film_certification;
import Helper.ValidationManagement;

 public class MainAdminDashBoard implements DashBoards {

	
	 
		MainAdminAccess mainAdminAccess ;
		

		Scanner ob = new Scanner(System.in);
		
		 
		 public MainAdminDashBoard(MainAdminAccess bookingManagementSystem) {
			
			 mainAdminAccess = new BookingManagementSystem();
		}

		private void addMovie() throws SQLException {
			 
			 String moviename, filmCertifications,duration ;
			 
			 int categorys = -1, languages = -1;
			 
			 while(true) {
			 System.out.println("Enter Movie Name : ");
			 if( ValidationManagement.validateInput(moviename = ob.nextLine()) ) { break; } }
			 
	
			 while(true) {
			 mainAdminAccess.viewCategory();
			 
			 System.out.println("\nEnter Movie category id : ");
			
			 try { categorys = ob.nextInt(); } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
			 
			if( mainAdminAccess.existCategoryId( categorys ) ) { break; }else { System.out.println("Enter valid id"); }
			 
			 }
			 
			 
			 while(true) {
	
			 mainAdminAccess.viewLanguage();
			 
			 System.out.println("\nEnter Movie language id : ");

			 try { languages = ob.nextInt(); } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
			 if( mainAdminAccess.existLanguageId( languages ) ) { break; }else { System.out.println("Enter valid id"); }
			 
			 }
			 
			 System.out.println("\nview All Film Certification : ");
			 EnumSet.allOf(film_certification.class)
	            .forEach(fc -> System.out.println(fc));
			 
			 System.out.println("\nEnter film Certification : ");
			 if( !ValidationManagement.validateInput(filmCertifications = ob.next()) ) { return; }
			 
			 
			 while(true) { 
			 System.out.println("Enter Movie duration : ");
			 if( ValidationManagement.validateTime(duration = ob.next()) ) { break; } }
			 
			 
			 
			 mainAdminAccess.addMovie(   moviename, categorys, languages,filmCertifications,duration );
			 
			
		 }
		
		
		private void editMovie() throws SQLException {
			
			String  duration;
			
			int movieId = -1;
			
			int languageId = -1;
			
			while(true) {
			System.out.println("This is movie list which movie you want to show initially .. : "); 
			
			mainAdminAccess.viewMovies();
			

			System.out.println("Enter Movie ID : " );
			try { movieId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only"); }
			if( !mainAdminAccess.isValidMovieId(movieId) ) { System.out.println("Movie not found");  } else { break; }
			
			}
			

			while(true) {
			mainAdminAccess.viewLanguage();
		 
		 System.out.println("\nEnter Movie language Id : ");
			try { languageId = ob.nextInt();  } catch(InputMismatchException e) { System.out.println("Enter Number only");  }
			 if( mainAdminAccess.existLanguageId( languageId ) ) { break; }
			}


		while(true) {
			 System.out.println("Enter Movie duration : ");
			 if( ValidationManagement.validateTime(duration = ob.next()) ) { break; } 
		}
			 
			 mainAdminAccess.editMovie( movieId, languageId, duration);
			
		}
		
		
		private void getReport() throws SQLException {
		
			mainAdminAccess.getReport();
		}

		
		
		private void addCategory() throws SQLException {
			
			String categoryName;
			
			while(true) {
			 System.out.println("Enter Category Name : ");
			 if( ValidationManagement.validateInput(categoryName = ob.next()) ) { break; }
			}
			 
			 mainAdminAccess.addCategoryEnum(categoryName);
		}

	 
		
		private void addCity() throws SQLException {
			
			String cityName,district;
			long zipcode = -1;
			
			while(true) {
			 System.out.println("Enter City Name : ");
			 if( ValidationManagement.validateInput(cityName = ob.next()) ) { break; }
			}
			
			while(true) {
			 System.out.println("Enter City District : ");
			 if( ValidationManagement.validateInput(district = ob.next()) ) { break; }
			}
			
			while(true) {
			 System.out.println("Enter City zipcode : ");
			try { zipcode = ob.nextLong();  break; } catch(InputMismatchException e) { break; }
			}
			
			 mainAdminAccess.addCityEnum(cityName, district , zipcode ); 
			
		}

		private void addLanguage() throws SQLException {
			String languageName;
			
			while(true) {
			 System.out.println("Enter Language Name : ");
			 if( ValidationManagement.validateInput(languageName = ob.next()) ) { break; }
			}
			
			 mainAdminAccess.addLanguageEnum(languageName); 
			
		}
		
		
		private void viewAll() throws SQLException {
			
			System.out.println("\n---------------------\n\nMOVIES LIST  \n\n---------------------\n");
			mainAdminAccess.viewMovies();
			System.out.println("\n---------------------\n\nLANGUAGE LIST  \n\n---------------------\n");
			mainAdminAccess.viewLanguage();
			
			System.out.println("\n---------------------\n\nCATEGORY LIST  \n\n---------------------\n");
			mainAdminAccess.viewCategory();
			
			System.out.println("\n---------------------\n\nCITY LIST  \n\n---------------------\n");
			mainAdminAccess.viewCity();
			
			
		}

	 
public int showDashboard() throws SQLException {
	

		
		Scanner ob = new Scanner(System.in);
		
		int choice;
		System.out.println("\n==================================\n");
		System.out.println("! .. Welcome to ADMIN 's DashBoard .. !" );
		System.out.println("\n==================================\n");
		System.out.println("\nEnter choice : \n1.Add Movie\n2.Edit Movie\n3.Get Report\n4.Add Category\n5.Add Language\n6.Add City\n7.View Movie,Category,Language,City\n8.Logout\n");
		
		try { choice = ob.nextInt();} catch(InputMismatchException e) { System.out.println("Enter Number only"); return 0; }
		
		
		
		switch(choice)
		{
		
		case 1:
			
			this.addMovie();
			
			break;
		case 2:
			this.editMovie();
			break;
		case 3:
			
			this.getReport();
			break;
		case 4:
			this.addCategory();
			break;
		case 5:
			this.addLanguage();
			break;
		case 6:
			this.addCity();
			break;
		case 7:
			this.viewAll();
			break;
		case 8:
			return 0;
			

		}
		
		
		return 1;
	}








	
}
