package classes;

import java.util.Map;
import java.util.Set;

import interfaces.Offer;

/**
 * The order class represents a list of meals that the client might order in the restaurant. These meal can 
 * be personalized, and the final price of these orders will be computed according to the offers and discounts that
 * apply.
 *
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
	 * @param client The client the made the order
	 */
	public Order(Client client) {
		this.id = nextId;
		nextId += 1;
		this.client = client;
	}
	
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
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
	 * @param meal One of the meals available in the restaurant
	 * @param n Number of this kind of meal in this order
	 */
	public void setNumberOfMeal(Meal meal, Integer n ){
		mealMap.put(meal, n);	
	}
	
	/**
	 * @return A set of the meals ordered
	 */
	public Set<Meal> getSetMeal(){
		return mealMap.keySet();
	}
	
	/**
	 * @return the client
	 */
	public Client getClient() {
		return client;
	}

	/**
	 * @return the currentPrice
	 */
	public double getCurrentPrice() {
		return currentPrice;
	}

	/**
	 * @param currentPrice the currentPrice to set
	 */
	public void setCurrentPrice(double currentPrice) {
		this.currentPrice = currentPrice;
	}
	
	public double getNormalPrice(){
		double normalPrice = 0;
		Set<Meal> mealSet = getSetMeal(); 
		
		for (Meal meal : mealSet) {
			normalPrice += getQuantityMeal(meal)*meal.getPrice();
		}
		return normalPrice;
	}

	/**
	 * @param We have to put all the offers available in the list offers in order to be able to 
	 * @return
	 */
	public double getPrice(Offer[] offers){
		
		Offer card =getClient().getCard() ;
		double normalPrice = getNormalPrice();
		double discountedPrice = 0;
		Set<Meal> mealSet = getSetMeal(); 
				
		discountedPrice += card.discountedPrice(getClient(), this);
		currentPrice = normalPrice - discountedPrice;
		
		
		for(Offer offer : offers){
			discountedPrice += offer.discountedPrice(getClient(), this);
			currentPrice = normalPrice - discountedPrice;
		}
		return  Math.max(currentPrice, (double) 0);
		}
	
	

}
