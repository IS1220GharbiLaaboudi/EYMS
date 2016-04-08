package classes;

import interfaces.Offer;

/**
 * 
 * This class represents the point fidelity card. The maximum amount of points in a card is set at 100 points. When
 * this limit is reach, the client gets a discount of a given percentage (here the discount is 10% off). The user
 * will get a point for every 10 euros spent in the restaurant.
 *  
 *  
 */
public class PointFidelityCard implements Offer {
	private int points;
	public final int maxPoints = 100;
	public final double percentage = 0.1;
	
	/**
	 * Creates a point fidelity card with a certain amount of points.
	 * 
	 * @param points Number of points initially in the card. This should be inferior to 100 points.
	 */
	public PointFidelityCard(int points) {
		// TODO Auto-generated constructor stub
		this.points = points;
	}
	
	/**
	 * Basic constructor that creates a point fidelity card with initially 0 point.
	 */
	public PointFidelityCard() {
		// TODO Auto-generated constructor stub
		this.points = 0;
	}
	
	
	/**
	 * One point added per 10euros. 100 points grants a discount.
	 * 
	 * @param order The order for which the discount applies.
	 * 
	 */
	public double discountedPrice(Order order) {
		if(points >= 100){
			points = 0;
			System.out.println("Congratulation you have reached 100 points ! This order is dicounted !");
			return order.getNormalPrice() * percentage;
		} else{
			points += Math.floor(order.getNormalPrice() / 10);
			if(points > 100)
				points = 100;
			System.out.println("Your card has "+ points + " points.");
			return 0;
		}
		
	}
	
}
