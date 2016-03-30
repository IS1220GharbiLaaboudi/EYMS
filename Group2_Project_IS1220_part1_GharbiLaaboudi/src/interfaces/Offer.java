package interfaces;

import classes.Order;

/**
<<<<<<< Updated upstream
 * This interface enables us to use the pattern strategy. The method discountedPrice returns for a given order the
 * price that have been removed from the initial price.
=======
 * @author Younes Laaboudi
 * @author Achraf Gharbi
>>>>>>> Stashed changes
 *
 */
public interface Offer {

	public double discountedPrice(Order order);	
}
