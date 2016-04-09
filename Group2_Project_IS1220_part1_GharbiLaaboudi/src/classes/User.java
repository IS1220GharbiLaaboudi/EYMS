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
	/**
	 * Quick constructor for the User class. Used for login.
	 * 
	 * @param userName The username of the user. In order to register, the username selected must be different from 
	 * all the other usernames.
	 * @param password The password of the user.
	 */
	public User(String userName, String password){
		this.userName = userName;
		this.password = password;
	}
	/**
	 * Ordinary getter
	 * @return the first name of the user
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * Ordinary setter
	 * @param firstName the first name of the user
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * Ordinary getter
	 * @return the surname of the user
	 */
	public String getSurName() {
		return surName;
	}
	/**
	 * Ordinary setter
	 * @param surName the surname of the user
	 */
	public void setSurName(String surName) {
		this.surName = surName;
	}
	/**
	 * Ordinary getter
	 * @return the username of the user
	 */
	public String getUserName() {
		return userName;
	}
	/**
	 * Ordinary setter
	 * @param userName the username of the user
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * Ordinary getter
	 * @return the password of the user
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Ordinary setter
	 * @param password the password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	
	/**
	 * Ordinary getter
	 * @return the role of the user (usually UserRole.Client or UserRole.Chef)
	 */
	public UserRole getRole() {
		return role;
	}
	/**
	 * Ordinary setter
	 * @param role The role of the user (usually UserRole.Client or UserRole.Chef)
	 */
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
	
	/**
	 * Compares the username and the password.
	 * 
	 */
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		User other = (User) obj;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (userName == null) {
			if (other.userName != null)
				return false;
		} else if (!userName.equals(other.userName))
			return false;
		return true;
	}
	
	
	

}
