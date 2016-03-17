package classes;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import interfaces.Offer;

public class Client extends User implements Observer {
	/** The fidelity card type, will be basic by default. */
	private Offer card;
	/** The type of notifications the user accepts to receive. Will be set to Yes by default	 */
	private Map<String, Boolean> agreements;
	
	private Map<String, String> contactInfo;
	
	/**
	 * @param firstName The first name of the user.
	 * @param surName The surname of the user.
	 * @param userName The username of the user. In order to register, the username selected must be different from 
	 * all the other usernames.
	 * @param password The password of the user.
	 * 
	 * This is the default constructor, the user will have a Basic Fidelity Card and he agrees to all kind of notification. 
	 * 
	 */
	


	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	
	

}
