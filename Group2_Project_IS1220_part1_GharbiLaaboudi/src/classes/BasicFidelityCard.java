/**
 * 
 */
package classes;

import java.util.Observable;

import interfaces.Offer;

/**
 * @author Fouad-Sams
 *
 */
public class BasicFidelityCard extends Observable implements Offer {
	
	
	/** 
	 * 
	 */
	@Override
	public double discountedPrice(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
