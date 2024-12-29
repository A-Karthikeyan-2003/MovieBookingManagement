package DataModels;

public class TheatreOwner implements Roles {
	
	private String name, email, password, contact, documentProofId, dateOfBirth   ;
	
	private int cityId;
	
	private int  id, age;

	public TheatreOwner(String name, String email, String password, String contact, String documentProofId, int id,
			String dateOfBirth, int cityId) {
	
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.documentProofId = documentProofId;
		this.id = id;
		this.dateOfBirth = dateOfBirth;
		
		this.cityId = cityId;
		
	}


	public int getCityId() {
		return cityId;
	}


	public void setCityId(int cityId) {
		this.cityId = cityId;
	}


	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getDocumentProofId() {
		return documentProofId;
	}

	public int getId() {
		return id;
	}

	public int getAge() {
		return age;
	}
	
	
	

}
