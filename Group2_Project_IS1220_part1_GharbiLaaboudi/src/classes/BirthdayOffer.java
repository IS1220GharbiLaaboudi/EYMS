package classes;

import java.util.Date;
import java.util.Observable;

import interfaces.Offer;

/**
 * @author Achraf Ghabri
 * 
 * @author Younes Laaboudi
 *
 */
public class BirthdayOffer extends Observable implements Offer {
	private final double percentage = .5;
	private Date date;
	
	public BirthdayOffer(Date date){
		this.date = date;
	}
	
	/**
	 * Returns the discounted price from an order.
	 *  
	 * @param order
	 * @return the discounted price.
	 * @Override 
	 */	
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
