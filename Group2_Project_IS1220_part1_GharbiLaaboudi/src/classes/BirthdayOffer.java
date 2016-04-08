package classes;

import java.util.Date;
import java.util.Observable;

import interfaces.Offer;

/**
 * This class handles the birthday offers, from the notification to the discount in itself.
 *
 */
public class BirthdayOffer extends Observable implements Offer {
	private final double percentage = .5;
	private Date date;
	
	public BirthdayOffer(Date date){
		this.date = date;
	}
	
	public void enableNotifications(){
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
			return order.getCurrentPrice() * percentage;
		} else {
			return 0;
		}
	}
	

}
