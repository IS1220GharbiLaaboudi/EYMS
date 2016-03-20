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
	 * Returns the discounted price from an order. 
	 * @param order
	 * @return the discounted price.
	 * @Override 
	 */
	public double discountedPrice(Order order) {
		double sum = 0;
		for (Meal meal : order.getSetMeal()){
			sum += order.getQuantityMeal(meal)*meal.getSpecialPrice();
		}
		return order.getNormalPrice() - sum;
	}
	
}
