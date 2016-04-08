/**
 * 
 */
package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import interfaces.Offer;

/**
 * This class represents the lottery fidelity card. The lottery related to a given client can be run only once
 *  every 24 hours.
 *
 */
public class LotteryFidelityCard implements Offer {
	
	private Date lastOrder;
	private final double probability = .1;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	public LotteryFidelityCard() {
		lastOrder = new Date();
		lastOrder.setTime(0);
	}
	
	/**
	 * @return the lastOrder
	 */
	public Date getLastOrder() {
		return lastOrder;
	}

	/**
	 * @param lastOrder the lastOrder to set
	 */
	public void setLastOrder(Date lastOrder) {
		this.lastOrder = lastOrder;
	}



	/**
	 * Returns the discounted price from an order. 
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
			return order.getNormalPrice();
		} else {
			System.out.println("Congratulation you won the lottery !");
			return 0;
		}
	}
	


}
