package classes;

import enums.UserRole;

/**
 *  This class is the superclass for both the Chef and the Client class. Anyone attempting to login should have 
 *  an instance of the Chef or Client class created.
 *  
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 *
 */
public class User {
	
	private String firstName;
	private String surName;
	private String userName;
	private String password;
	private UserRole role;
	
	/**
	 * @param firstName The first name of the user.
	 * @param surName The surname of the user.
	 * @param userName The username of the user. In order to register, the username selected must be different from 
	 * all the other usernames.
	 * @param password The password of the user.
	 */
	public User(String firstName, String surName, String userName, String password, UserRole role) {
		this.firstName = firstName;
		this.surName = surName;
		this.userName = userName;
		this.password = password;
		this.r
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getSurName() {
		return surName;
	}

	public void setSurName(String surName) {
		this.surName = surName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", surName=" + surName + ", userName=" + userName + ", password="
				+ password + "]";
	}
	
	
	

}
