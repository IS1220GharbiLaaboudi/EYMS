package classes;

import java.util.Date;
import java.util.Observable;

import interfaces.Offer;

/**
 * This class handles the birthday offers, from the notification to the discount in itself.
 *
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 */
public class BirthdayOffer extends Observable implements Offer {
	/**
	 * The percentage that will be discounted if the client celebrates his birthday at the given date.
	 */
	private final double percentage = .5;
	/**
	 * Usually the date of the system, it will be compaed to the birthday of the client.
	 */
	private Date date;
	/**
	 * Default constructor of the method. Only sets the date, and specifies that this observable instance has changed 
	 * in order to allow notifications to be sent.
	 * @param date the date of the system
	 */
	public BirthdayOffer(Date date){
		this.date = date;
		setChanged();
	}
	
	/**
	 * Returns the discounted price from an order.
	 *  
	 * @param order The order for which the offer may apply.
	 * @return the discounted price.
	 *
	 */	
	@SuppressWarnings("deprecation")
	public double discountedPrice(Order order) {
		Client client = order.getClient();
		if(client.getBirthdayDate().getDate() == date.getDate() && 
				client.getBirthdayDate().getMonth() == date.getMonth()){
			System.out.println("It's your birthday ! Enjoy a "+percentage*100+"% on your order !");
			return order.getCurrentPrice() * percentage;
		} else {
			return 0;
		}
	}
	

}
