package AccessService;

import java.sql.SQLException;



import DataModels.Roles;


public interface NormalAccess {

	public int addToTheatreOwner(String username,String  email,String password, long contact,String documentProofId, String dateOfBirth, int choiceForUserType ) throws SQLException;
	
	public int addToUser( String username, String email, String password, long contact, String dateOfBirth ,int choiceForUserType) throws SQLException;
	
	public void viewCity() throws SQLException;
	
	public Roles isExistInAccount(String email, String password) throws SQLException;
	
	
	
}
