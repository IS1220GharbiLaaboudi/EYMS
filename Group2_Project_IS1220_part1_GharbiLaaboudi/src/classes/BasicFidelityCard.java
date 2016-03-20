/**
 * 
 */
package classes;

import java.util.Observable;

import interfaces.Offer;

/**
 * @author Achraf Gharbi
 * @author Younes Laaboudi
 */
public class BasicFidelityCard extends Observable implements Offer {
	
	
	/** 
	 * 
	 */

	@Override
	public double discountedPrice(User user, Order order) {
		
		return 0;
	}
	
}
