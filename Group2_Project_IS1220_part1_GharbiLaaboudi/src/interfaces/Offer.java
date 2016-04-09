package interfaces;

import classes.Order;

/**
 * This interface enables us to use the pattern strategy. The method discountedPrice returns for a given order the
 * price that have been removed from the initial price.
 * 
 * @author Younes Laaboudi
 * 
 * @author Achraf Gharbi
 *
 */
public interface Offer {
	/**
	 * Returns the discounted price from an order. This discounted amount has to be subtracted to the normal price
	 * of the order
	 *  
	 * @param order The order for which the offer may apply.
	 * @return the discounted price.
	 */
	public double discountedPrice(Order order);	
}
