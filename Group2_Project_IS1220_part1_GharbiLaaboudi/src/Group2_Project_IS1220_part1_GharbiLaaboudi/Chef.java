package Group2_Project_IS1220_part1_GharbiLaaboudi;

/**
 * @author Fouad-Sams
 *
 */
public class Chef extends User {

	boolean isActive;

	/**
	 * @param firstName
	 * @param surName
	 * @param userName
	 * @param password
	 */
	public Chef(String firstName, String surName, String userName, String password) {
		super(firstName, surName, userName, password);
		this.isActive = true;
	}
	
	
}
