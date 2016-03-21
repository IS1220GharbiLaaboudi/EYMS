package interfaces;

import classes.Order;

/**
 * This interface enables us to use the pattern strategy. The method discountedPrice returns for a given order the
 * price that have been removed from the initial price.
 *
 */
public interface Offer {

	public double discountedPrice(Order order);	
}
