package classes;

import java.util.Observable;

import interfaces.Offer;

/**
 * @author Fouad-Sams
 *
 */
public class PointFidelityCard implements Offer {
	private int points;
	public final int maxPoints = 100;
	public final double percentage = 0.1;
	
	/**
	 * @param points
	 */
	public PointFidelityCard(int points) {
		// TODO Auto-generated constructor stub
		this.points = points;
	}
	
	/**
	 * @param points
	 */
	public PointFidelityCard() {
		// TODO Auto-generated constructor stub
		this.points = 0;
	}
	
	
	/**
	 * 
	 * @param points the points of the client. One point added per 10euros. 100 points grants a discount.
	 * @Override
	 */
	public double discountedPrice(Order order) {
		if(points >= 100){
			System.out.println("points "+points+"");
			points = 0;
			return order.getNormalPrice() * percentage;
		} else{
			points += Math.floor(order.getNormalPrice() / 10);
			System.out.println("points "+points+"");
			if(points > 100)
				points = 100;
			return 0;
		}
		
	}
	
}
