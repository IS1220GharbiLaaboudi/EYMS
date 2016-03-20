package classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import enums.UserRole;
import interfaces.Offer;
/**
 * 
 * This class represents the clients of the restaurant. It contains all the information needed for logging in, but
 * also information on how the restaurant will interact with him/her (type of fidelity card, contact information, ...).
 * 
 * The methods that aren't documented are the classic getters and setters. 
 *
 */
public class Client extends User implements Observer {
	

	/** The fidelity card type, will be basic by default. */
	private Offer card;
	
	/** The type of notifications the user accepts to receive. All fields will be set to Yes by default.
	 * The key will be the type of offer and the value is whether the client accepts the notifications or not.
	 * for example : 	"SpecialOffer" -> true
	 * 					"BirthdayOffer" -> true */
	private Map<String, Boolean> agreements;
	
	/**The key is the type of the contact information and the value is the contact information.
	 * For example : "email" -> "johnred@gmail.com"
	 * 				 "phone" -> "06 58 78 95 20" */
	private Map<String, String> contactInfo;
	/**
	 * The way the client would like to be contacted : if their is only one contact information, it will be this one
	 * by default. 
	 */
	private String favoriteContactInfo;
	
	/** Each time a client will receive a notification(for a special offer or a birthday offer), it will be 
	 *  written here */
	private String notificationWall;
	
	/**Enables to give birthday discounts, uses the Date class from Java.util */
	private Date birthdayDate;
	
	/**
	 * 
	 * This is the default constructor, the user will have a Basic Fidelity Card and he agrees to all kind of notification.
	 * 
	 * @param firstName The first name of the user.
	 * @param surName The surname of the user.
	 * @param userName The username of the user. In order to register, the username selected must be different from 
	 * all the other usernames.
	 * @param password The password of the user. 
	 * @throws ParseException 
	 * 
	 */
	public Client(String firstName, String surName, String userName, String password) {
		super(firstName, surName, userName, password, UserRole.Client);
		this.card = new BasicFidelityCard();
		this.notificationWall = this.getFirstName() + " " + this.getSurName() +"'s notification wall : ";
		agreements = new HashMap<String, Boolean>(); 
		this.setAgreement("BirthdayOffer", true);
		this.setAgreement("SpecialOffer", true);
		
		this.birthdayDate = new Date();
	}

	public Offer getCard() {
		return card;
	}

	public void setCard(Offer card) {
		this.card = card;
	}

	/**
	 * Replaces the getter, which is less relevant here.
	 * @param offerType Usually "BirthdayOffer" or "SpecialOffer"
	 * @return true or false depending on the choice of the user to receive or not to receive a notification related
	 * 		   to the offer.
	 */
	public Boolean getAgreement(String offerType) {
		return agreements.get(offerType);
	}

	/**
	 * Replaces the setter, which is less relevant here
	 * @param offerType Usually "BirthdayOffer" or "SpecialOffer"
	 * @param agreement Agreement to receive notification related to the offer.
	 */
	public void setAgreement(String offerType, Boolean agreement) {
		(this.agreements).put(offerType, agreement);
	}

	/**
	 * Replaces the getter, which is less relevant here
	 * @param type Usually "email" or "phone"
	 * @return the contact information of the type asked or null if the value doesn't exist
	 */
	public String getContactInfo(String type) {
		return (this.contactInfo).get(type);
	}

	/**
	 * Replaces the setter, which is less relevant here. Only one contact information is possible for a type of contact
	 * (only one email for example). If a value for the given type exists, it will be replaced by the new one.
	 * @param offerType Usually "BirthdayOffer" or "SpecialOffer"
	 * @param agreement Agreement to receive notification related to the offer.
	 */
	public void setContactInfo(String contactType, String contact) {
		(this.contactInfo).put(contactType, contact);
	}

	public String getFavoriteContactInfo() {
		return favoriteContactInfo;
	}

	public void setFavoriteContactInfo(String favoriteContactInfo) {
		this.favoriteContactInfo = favoriteContactInfo;
	}

	public String getNotificationWall() {
		return notificationWall;
	}

	/**
	 * This method replaces the setter, which is less relevant here.
	 * @param notification The notification that the user receives. 
	 */
	public void addToNotificationWall(String notification) {
		this.notificationWall = this.notificationWall + "/n" + notification;
	}
	
	public Date getBirthdayDate() {
		return birthdayDate;
	}
	
	public void setBirthdayDate(Date birthdayDate) {
		this.birthdayDate = birthdayDate;
	}
	/**
	 * This method handles the notification a client can get. For each kind of notification, there is a message
	 * that is written on the client's notification wall.
	 */
	@Override
	public void update(Observable o, Object arg) {
		if (o instanceof BirthdayOffer){
			this.addToNotificationWall("It's your birthday ! Enjoy Your Meal offers you "
					+ "a tasty discount on your next order !");
		}
		if (o instanceof Meal){
			this.addToNotificationWall("There is a new special offer ! Enjoy Your Meal offers you "
					+ "a tasty discount on the meal : " + ((Meal) o).getName() + ". " + (String) arg);
		}
		
	}
	
}
