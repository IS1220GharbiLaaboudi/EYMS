/**
 * 
 */
package classes;

import interfaces.Offer;

/**
 * This class handles the special offers. It only handles the discount operation, the notifications are the 
 * responsibility of each meal.
 */
public class BasicFidelityCard implements Offer {
	
	/**
	 * Returns the discounted price from an order. 
	 * @param order The order for which the discount may apply.
	 * @return the discounted price.
	 */
	public double discountedPrice(Order order) {
		double sum = 0;
		for (Meal meal : order.getSetMeal()){
			sum += order.getQuantityMeal(meal)*meal.getSpecialPrice();
		}
		return order.getNormalPrice() - sum;
	}
	
}
