package BookingManagement;

import DataModels.Roles;

import DataModels.TheatreOwner;
import DataModels.User;
import UiDashBoards.DashBoards;
import UiDashBoards.MainAdminDashBoard;
import UiDashBoards.TheatrOwnerDashBoard;
import UiDashBoards.UserDashBoard;
import AccessService.UserAccessService;
import AccessService.MainAdminAccess;
import AccessService.TheatreOwnerAccess;

public class Navigate {


	public static DashBoards navigation( BookingManagementSystem bookingManagementSystem, Roles user   ){
		
		DashBoards dashboard = null ;
		
		if ( user instanceof User ) {
			
			dashboard = new UserDashBoard( (UserAccessService) bookingManagementSystem, (User) user );
			
		} else if ( user instanceof TheatreOwner  ) {
			
			dashboard = new TheatrOwnerDashBoard( (TheatreOwnerAccess) bookingManagementSystem, (TheatreOwner) user );
			
		}  
		return dashboard;
		
	}

	public static DashBoards navigation(BookingManagementSystem bookingManagementSystem) {
		
		return new MainAdminDashBoard( (MainAdminAccess) bookingManagementSystem );
	}
	
}
	
	
	
