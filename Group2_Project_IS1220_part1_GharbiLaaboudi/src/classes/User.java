package classes;

import enums.UserRole;

/**
 *  This class is the superclass for the Client class and can represent both a client or a chef. Anyone attempting to register will have an instance of this
 *  class created. 
 *  
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 *
 */
public class User {
	/** The first name of the user. */
	private String firstName;
	/** The surname of the user. */
	private String surName;
	/** The username of the user. */
	private String userName;
	/** The password of the user. */
	private String password;
	/** Is either UserRole.Chef or UserRole.Client*/
	private UserRole role;
	
	/**
	 * Basic constructor for the User class. A user can be either a client or a chef.
	 * 
	 * @param firstName The first name of the user.
	 * @param surName The surname of the user.
	 * @param userName The username of the user. In order to register, the username selected must be different from 
	 * all the other usernames.
	 * @param password The password of the user.
	 * @param role The role of the user, is either UserRole.Chef or UserRole.Client
	 */
	public User(String firstName, String surName, String userName, String password, UserRole role) {
		this.firstName = firstName;
		this.surName = surName;
		this.userName = userName;
		this.password = password;
		this.role = role;
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
	

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	/**
	 * Enables to get the basic information regarding the user (except the password which is hidden).
	 */
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", surName=" + surName + ", userName=" + userName + ", password="
				+ "*********" + "]";
	}
	
	
	

}
