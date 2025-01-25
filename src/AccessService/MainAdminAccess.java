package AccessService;

import java.sql.SQLException;

public interface MainAdminAccess extends CommonAccess  {

	void addMovie(String moviename, int categorys, int languages, String filmCertifications, String duration) throws SQLException;

	void editMovie(int movieId, int languageId,String duration) throws SQLException;

	void getReport() throws SQLException;

	 void addCategoryEnum( String category ) throws SQLException ;
	
	 void addLanguageEnum( String language ) throws SQLException;
	
	 void addCityEnum( String city, String district, long zipcode ) throws SQLException;
	
	 void viewLanguage() throws SQLException ;
	 
	 void viewCategory() throws SQLException ;
	 
	 public boolean existCategoryId(int id);
	 
	 public boolean existLanguageId(int id);
	
}
