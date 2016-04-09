package classes;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import interfaces.Offer;

/**
 * The order class represents a list of meals that the client might order in the restaurant. These meal can 
 * be personalized, and the final price of these orders will be computed according to the offers and discounts that
 * apply.
 *
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 */
public class Order {
	
	/**
	 * nextId has the role of giving to each order a unique id
	 */
	private static int nextId = 1;
	
	/**
	 * ID of the order -> enables to store the orders in a chronological way
	 */
	private int id;
	
	/**
	 * mealMap represents the list of the meals ordered.
	 * The keys of the map are the meals, and the values are the quantities 
	 */
	private Map<Meal, Integer> mealMap;
	
	/**
	 * This is the client who is ordering the meal.
	 */
	private Client client;
	
	/**
	 * Enables to have multiple discounts and check the price of the meal with the previous discounts before 
	 * applying a new one.
	 */
	private double currentPrice;
	/**
	 * The date at which the order is made. It is useful for the Lottery Fidelity Card.
	 */
	private Date date;
	
	/**
	 * @param client The client the made the order.
	 * @param date The date at which the order have been made.
	 */
	public Order(Client client, Date date) {
		this.id = nextId;
		nextId += 1;
		this.client = client;
		this.mealMap = new HashMap<Meal, Integer>();
		this.date = date;
	}
	
	/**
	 * Ordinary getter
	 * @return the id of the order
	 */
	public int getId() {
		return id;
	}

	/**
	 * Replaces the ordinary getter, and gives an easy access to the number of times a certain meal has been ordered 
	 * @param meal One of the meals available in the restaurant
	 * @return Number of this kind of meal in this order
	 */
	public Integer getQuantityMeal(Meal meal){
		if (mealMap.containsKey(meal)){
			return mealMap.get(meal);
		} else{
			return 0;
		}
	}
	
	/**
	 * Replaces the ordinary setter and allows us to add a meal to the order.
	 * @param meal One of the meals available in the restaurant
	 * @param n Number of this kind of meal in this order
	 */
	public void setNumberOfMeal(Meal meal, Integer n ){
		mealMap.put(meal, n);	
	}
	
	/**
	 * Ordinary setter
	 * @return The set of the meals ordered in this order
	 */
	public Set<Meal> getSetMeal(){
		return mealMap.keySet();
	}
	
	/**
	 * Ordinary getter
	 * @return the client the has made the order
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * Ordinary setter
	 * @return the currentPrice of the order given that a certain number of discounts have been applied.
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * Ordinary setter
	 * @param currentPrice the currentPrice of the order given that a certain number of discounts have been applied.
	 */
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	/**
	 * Ordinary getter
	 * @return the normal price of the order (assuming that no discount is applied)
	 */
	public double getNormalPrice(){
		double normalPrice = 0;
		Set<Meal> mealSet = getSetMeal(); 
		
		for (Meal meal : mealSet) {
			normalPrice += getQuantityMeal(meal)*meal.getPrice();
		}
		return normalPrice;
	}

	/**
	 * Ordinary getter
	 * @return the date at which the order has been made
	 */
	public Date getDate() {
		return date;
	}

	/**
	 * Ordinary setter
	 * @param date the date at which the order has been made
	 */
	public void setDate(Date date) {
		this.date = date;
	}

	/**
	 * This method is the one that computes the price the client will have to pay given that a certain number of 
	 * discounts may apply.
	 * 
	 * @param offers We have to put all the offers available in the list offers.
	 * @return the final price of the order after discounts.
	 */
	public double getPrice(Offer[] offers){
		Client client = getClient();
		Offer card = client.getCard() ;
		double normalPrice = getNormalPrice();
		double discountedPrice = 0;
		currentPrice = normalPrice;
		
		for(Offer offer : offers){
			discountedPrice += offer.discountedPrice(this);
			currentPrice = normalPrice - discountedPrice;
		}
		
		discountedPrice += card.discountedPrice(this);
		currentPrice = normalPrice - discountedPrice;
		client.setCard(card);
		
		
		return  Math.max(currentPrice, (double) 0);
		}

	
	@Override
	public String toString() {
		return "[ OrderId = " + id + "; Meals Selected =" + mealMap+ " ]";
	}
	
	
	
}
