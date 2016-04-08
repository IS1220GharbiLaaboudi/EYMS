/**
 * 
 */
package classes;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * This class represents the meals that are on offer in the restaurant. A meal has a name, a price, and a list of 
 * ingredients.
 * If the meal is on a special offer, its special price has to be specified.
 *
 */
public class Meal extends Observable {
	
	/** 
	 * The meal's name. It's the main characteristic of the meal and will be used to refer to it. 
	 */
	private String name;
	
	/**
	 * A map for which the key is the name of an ingredient and the value is a string containing the quantity of
	 * this ingredient needed.
	 * For example, "ham" -> "200g"
	 * 				"cheese" -> "50g" 
	 */
	private Map<String, String> ingredientMap;
	
	/**
	 * The price of the meal. This price is set by the chef. 
	 */
	private double price;
	
	/**
	 * If there is an offer on this particular meal, this attribute will be different from price. In other cases,
	 * it is the same as price.
	 */
	private double specialPrice;
	
	/**
	 * A boolean set to true if specialPrice != price 
	 */
	private boolean isSpecial;
	
	/**
	 * A boolean representing a meal when it is only special. 
	 */
	private boolean isOnlySpecial;
	
	/**
	 * This map acts as a counter : for each order that enters in a ordering criteria(the key), we increment 
	 * the counter(the value)
	 * Keys used are : Modified, NotModified, OnSale, NotOnSale
	 */
	private Map<String, Integer> orderCounter;
	
	/**
	 * This boolean is used to help count the number of meals ordered after being personalized
	 */
	private boolean isModified;
	
	/**
	 * This constructor initializes the creation of a meal. The ingredients still need to be added.
	 * By default, the special price is set as equal to the normal price.
	 * 
	 * @param name The meal's name. It's the main characteristic of the meal and will be used to refer to it.
	 * @param price The price of the meal. This price is set by the chef.
	 */
	public Meal(String name, double price) {
		this.name = name;
		this.price = price;
		this.specialPrice = price;
		this.isSpecial = false;
		this.isOnlySpecial = false;
		this.isModified = false;
		this.ingredientMap = new HashMap<String, String>();
		this.orderCounter = new HashMap<String, Integer>();
	}
	/**
	 * This constructor initializes the creation of a special offer meal. The ingredients still need to be added.
	 * 
	 * @param name The meal's name. It's the main characteristic of the meal and will be used to refer to it.
	 * @param price The price of the meal. This price is set by the chef.
	 * @param isOnlySpecial Set to true is the meal is created through the insertOffer method.
	 */
	public Meal(String name, double price, boolean isOnlySpecial) {
		this.name = name;
		this.price = price;
		this.specialPrice = price;
		this.isSpecial = false;
		this.isOnlySpecial = isOnlySpecial;
		this.isModified = false;
		this.ingredientMap = new HashMap<String, String>();
	}
	
	
	/**
	 * @return the meal's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the mealName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * 
	 * @param ingredient An ingredient of the meal.
	 * @param quantity The quantity needed for the given ingredient in this meal. To remove the ingredient, put "0g"
	 */
	public boolean personalizeMeal(String ingredient, String quantity, boolean isModified) {
		boolean t = false;
		Pattern pattern = Pattern.compile("^0[^1-9]*$");
		Matcher matcher = pattern.matcher(quantity);
		
		if(matcher.matches()){ // if quantity given is 0, remove the ingredient
			ingredientMap.remove(ingredient);
		} else{
			ingredientMap.put(ingredient, quantity);
			t = true;
		}
		setModified(isModified);
		return t;
	}
	
	/**
	 * 
	 * @param ingredient An ingredient of the meal. 
	 * @return The quantity needed for the given ingredient in this meal. If the ingredient isn't found, it will
	 * return "0g"
	 */
	public String getQuantity(String ingredient) {
		if (ingredientMap.containsKey(ingredient)){
			return ingredientMap.get(ingredient);
		} else {
			return "0g";
		}
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * @return the specialPrice
	 */
	public double getSpecialPrice() {
		return specialPrice;
	}

	/**
	 * @param specialPrice the specialPrice to set
	 */
	public void setSpecialPrice(double specialPrice) {
		this.specialPrice = specialPrice;
	}

	/**
	 * @return the isSpecial
	 */
	public boolean isSpecial() {
		return isSpecial;
	}

	/**
	 * @param isSpecial the isSpecial to set
	 */
	public void setSpecial(boolean isSpecial) {
		this.isSpecial = isSpecial;
	}
	
	
	/**
	 * @return the isOnlySpecial
	 */
	public boolean isOnlySpecial() {
		return isOnlySpecial;
	}
	
	/**
	 * @param isOnlySpecial the isOnlySpecial to set
	 */
	public void setOnlySpecial(boolean isOnlySpecial) {
		this.isOnlySpecial = isOnlySpecial;
	}
	
	/**
	 * @param orderingCriteria The criteria chosen to analyze the behavior of the clients
	 * @return the number of times this meal has been ordered following this criteria
	 */
	public Integer getOrderCounter(String orderingCriteria) {
		return orderCounter.get(orderingCriteria);
	}


	/**
	 * @param orderingCriteria ordering criteria for which we should add an order
	 * It works even if the orgeringCriteria haven't been put as a key of the map.
	 */
	public void incrementOrderCounter(String orderingCriteria, int number) {
		if (orderCounter.containsKey(orderingCriteria)){
			orderCounter.put(orderingCriteria, orderCounter.get(orderingCriteria) + number);
		} else {
			orderCounter.put(orderingCriteria, number);
		}
		
	}



	/**
	 * @return the orderCounter
	 */
	public Map<String, Integer> getOrderCounter() {
		return orderCounter;
	}


	/**
	 * @param orderCounter the orderCounter to set
	 */
	public void setOrderCounter(Map<String, Integer> orderCounter) {
		this.orderCounter = orderCounter;
	}


	/**
	 * @return true if the meal is modified
	 */
	public boolean isModified() {
		return isModified;
	}


	/**
	 * @param isModified set if the meal id modified or not
	 */
	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	/**
	 * @return the ingredientMap
	 */
	public Map<String, String> getIngredientMap() {
		return ingredientMap;
	}


	/**
	 * @param ingredientMap the ingredientMap to set
	 */
	public void setIngredientMap(Map<String, String> ingredientMap) {
		this.ingredientMap = ingredientMap;
	}


	@Override
	public String toString() {
		if (this.isSpecial){
			return name + ": Ingredients =" + ingredientMap + ", Regular Price =" + price
				+ ", Special Price=" + specialPrice;
		} else {
			return "[ " + name + ": Ingredients =" + ingredientMap + ", Price =" + price+ " ]";
		}
	}
}
