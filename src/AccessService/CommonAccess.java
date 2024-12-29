package AccessService;

import DataModels.City;
import DataModels.Roles;

public interface CommonAccess {

	public void addToTheatreOwner(String username,String  email,String password, String contact,String documentProofId, String dateOfBirth ,int cityId );
	
	public void addToUser( String username, String email, String password, String contact, String dateOfBirth );
	
	public void viewCity();
	
	public City getCity(int cityId);
	
	public Roles isExistInAccount(String email, String password);
	
}
