package DataModels;

public class User implements Roles {

	private String name, email, password, contact, dateOfBirth   ;
	
	private int  id, age;

	public User(String name, String email, String password, String contact, String dateOfBirth, int id) {
		
		this.name = name;
		this.email = email;
		this.password = password;
		this.contact = contact;
		this.dateOfBirth = dateOfBirth;
		this.id = id;
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

	public int getId() {
		return id;
	}

	public int getAge() {
		return age;
	}

	
	
	
	
}
