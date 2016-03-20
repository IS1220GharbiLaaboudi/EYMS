package interfaces;

import classes.Order;
import classes.User;

/**
 * @author Fouad-Sams
 *
 */
public interface Offer {

	public double discountedPrice(User user, Order order);	
}
