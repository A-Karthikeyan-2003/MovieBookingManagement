package BookingManagement;

import DataModels.Roles;
import DataModels.TheatreOwner;
import DataModels.User;

import AccessService.UserAccessService;

import AccessService.TheatreOwnerAccess;

public class Navigate {


	public static DashBoards navigation(BookingManagementSystem bookingManagementSystem, Roles user){
		
		DashBoards dashboard = null ;
		
		if ( user instanceof User ) {
			
			dashboard = new UserDashBoard( (UserAccessService) bookingManagementSystem, (User) user);
			
		} else if ( user instanceof TheatreOwner ) {
			
			dashboard = new TheatrOwnerDashBoard( (TheatreOwnerAccess) bookingManagementSystem, (TheatreOwner) user);
			
		}  else {
			
			System.out.println("Something Wrong..!");
			
		}
		
		return dashboard;
		
	}
	
}
	
	
	
