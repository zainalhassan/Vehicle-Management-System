package Models;

public class User 
{
	private String firstname;
	private String surname;
	private String username;
	private String password;
	private String user_type;
	private String api_key;
	
	public User (String firstname, String surname, String username, String password, String user_type, String api_key)
	{
		this.firstname = firstname;
		this.surname = surname;
		this.username = username;
		this.password = password;
		this.user_type = user_type;
		this.api_key = api_key;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUser_type() {
		return user_type;
	}

	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}

	public String getApi_key() {
		return api_key;
	}

	public void setApi_key(String api_key) {
		this.api_key = api_key;
	}
	
	
	
	
}
