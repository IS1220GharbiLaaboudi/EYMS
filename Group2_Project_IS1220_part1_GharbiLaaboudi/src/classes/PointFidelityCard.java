package classes;

import interfaces.Offer;

/**
 * 
 * This class represents the point fidelity card. The maximum amount of points in a card is set by default at 100 
 * points. When this limit is reached, the client gets a discount of a given percentage (here the discount is 10%
 * off). The user will get a point for every 10 euros spent in the restaurant.
 *  
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 */
public class PointFidelityCard implements Offer {
	/**
	 * The number of points contained in the card a given moment.
	 */
	private int points;
	/**
	 * The maximum number of points that can be contained in the card.
	 */
	public final int maxPoints = 100;
	/**
	 * The percentage off from the normal price of the order.
	 */
	public final double percentage = 0.1;
	
	/**
	 * Creates a point fidelity card with a certain amount of points.
	 * 
	 * @param points Number of points initially in the card. If the number of points can't be higher than the 
	 * maximum specified. If it is, the number of points will be automatically set back to the maximum number of 
	 * points.
	 */
	public PointFidelityCard(int points) {
		if (points>= maxPoints){
			this.points = maxPoints;
		}else{
			this.points = points;
		}
	}
	
	/**
	 * Basic constructor that creates a point fidelity card with initially 0 point.
	 */
	public PointFidelityCard() {
		this.points = 0;
	}
	
	
	/**
	 * One point added per 10euros. 100 points grants a discount.
	 * 
	 * @param order The order for which the discount applies.
	 * 
	 */
	public double discountedPrice(Order order) {
		if(points >= maxPoints){
			points = 0;
			System.out.println("Congratulation you have reached "+ maxPoints+" points ! This order has a "+ percentage*100 +"% dicount !");
			return order.getNormalPrice() * percentage;
		} else{
			points += Math.floor(order.getNormalPrice() / 10);
			if(points > maxPoints){
				points = maxPoints;
			}
			System.out.println("Your card has reached "+ points + " points.");
			return 0;
		}
		
	}
	
}
