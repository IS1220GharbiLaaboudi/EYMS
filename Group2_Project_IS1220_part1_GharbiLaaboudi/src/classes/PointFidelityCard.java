package classes;

import java.util.Observable;

import interfaces.Offer;

/**
 * @author Fouad-Sams
 *
 */
public class PointFidelityCard implements Offer {
	/* (non-Javadoc)
	 * @see Group2_Project_IS1220_part1_GharbiLaaboudi.Offer#finalPrice(Group2_Project_IS1220_part1_GharbiLaaboudi.Order)
	 */
	
	private int points;
	public final int maxPoints = 100;
	
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
	
	@Override
	public double discountedPrice(User user, Order order) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
