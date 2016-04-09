/**
 * 
 */
package classes;

import java.util.Date;
import interfaces.Offer;

/**
 * This class represents the lottery fidelity card. The lottery related to a given client can be run only once
 *  every 24 hours.
 *  
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 */
public class LotteryFidelityCard implements Offer {
	/**
	 * It contains the last date at which an order was made using this card. It enables us to check whether or not
	 * a client has already tried his chance at the lottery on a given day. 
	 */
	private Date lastOrder;
	/**
	 * The probability with which the client can win the lottery.
	 */
	private final double probability = .1;
	
	/**
	 * Default constructor for this class. It sets the default date at January 1, 1970 in order to make sure that 
	 * the first order that will be made will be made more than a day after this date.
	 */
	public LotteryFidelityCard() {
		lastOrder = new Date();
		lastOrder.setTime(0);
	}
	
	/**
	 * Ordinary getter
	 * @return the lastOrder the time at which the last order was made
	 */
	public Date getLastOrder() {
		return lastOrder;
	}

	/**
	 * Ordinary setter
	 * @param lastOrder the time at which the last order was made
	 */
	public void setLastOrder(Date lastOrder) {
		this.lastOrder = lastOrder;
	}



	/**
	 * Returns the discounted price from an order. The lottery will be run if the last order was made at least a 
	 * day before (at least 24 hours before) this new order.
	 * 
	 * @param order The order for which the offer may apply.
	 * @return the discounted price.
	 * 
	 */
	public double discountedPrice(Order order) {
		Date date =  order.getDate();
		boolean t = date.getTime() > (lastOrder.getTime() + (24 * 3600));
		lastOrder = date;
		double prob = Math.random();
		if(t && prob <probability){
			System.out.println("Congratulation you won the lottery !");
			return order.getNormalPrice();
		} else {
			return 0;
		}
	}
	


}
