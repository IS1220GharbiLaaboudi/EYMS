/**
 * 
 */
package classes;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Observable;
import java.util.Random;

import interfaces.Offer;

/**
 * @author Achraf Gharbi
 * @author Younes Laaboudi
 *
 */
public class LotteryFidelityCard implements Offer {
	
	private Date lastOrder;
	private final double probability = .1;
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
	
	
	
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
	 * @param order
	 * @return the discounted price.
	 * @Override 
	 */
	public double discountedPrice(Order order) {
		Random rand = new Random();
		Date date = new Date();
		lastOrder = date;
		boolean t = date.getTime() > (lastOrder.getTime() + (24 * 3600 * 1000)); 
		if(t && (rand.nextInt(101)/100)<probability){
			return order.getNormalPrice();
		} else {
			return 0;
		}
	}
	


}
