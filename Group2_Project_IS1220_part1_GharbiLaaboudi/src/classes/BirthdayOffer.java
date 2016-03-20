package classes;

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
	
	@Override
	public double discountedPrice(User user, Order order) {
		return order.getNormalPrice() * percentage;
	}
	

}
