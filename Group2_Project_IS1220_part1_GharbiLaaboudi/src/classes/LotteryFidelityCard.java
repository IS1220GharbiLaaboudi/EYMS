/**
 * 
 */
package classes;

import java.util.Date;
import java.util.Observable;

import interfaces.Offer;

/**
 * @author Fouad-Sams
 *
 */
public class LotteryFideltyCard implements Offer {

	/* (non-Javadoc)
	 * @see Group2_Project_IS1220_part1_GharbiLaaboudi.Offer#finalPrice(Group2_Project_IS1220_part1_GharbiLaaboudi.Order)
	 */
	
	private Date lastOrder;
	private final double probability = .1;
	
	@Override
	public double finalPrice(Order order) {
		// TODO Auto-generated method stub
		return 0;
	}

}
