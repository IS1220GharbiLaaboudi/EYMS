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
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
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
	 * The normal price of the meal (with no display applied). This price is set by the chef. 
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
		incrementOrderCounter("OnSale", 0);
		incrementOrderCounter("NotOnSale", 0);
		incrementOrderCounter("Modified", 0);
		incrementOrderCounter("NotModified", 0);
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
		this.orderCounter = new HashMap<String, Integer>();
		incrementOrderCounter("OnSale", 0);
		incrementOrderCounter("NotOnSale", 0);
		incrementOrderCounter("Modified", 0);
		incrementOrderCounter("NotModified", 0);
	}
	
	
	/**
	 * Ordinary getter
	 * @return the meal's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Ordinary setter
	 * @param name the meal's name
	 */
	public void setName(String name) {
		this.name = name;
	}

	
	/**
	 * This method enables to add a new ingredient to the meal or to change its quantity.
	 * 
	 * @param ingredient An ingredient of the meal.
	 * @param quantity The quantity needed for the given ingredient in this meal. To remove the ingredient, put "0g"
	 * @param isModified true if it's a client that personalizes his meal, false if it's the chef that specifies the
	 * ingredients of a new meal
	 * @return true if the meal was well personalized
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
	 * Replaces the ordinary getter. For a given ingredient, it enables us to access easily to the quantity of this
	 * ingredient in a meal. 
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
	 * Ordinary getter
	 * @return the normal price of the meal, without any discount
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Ordinary setter
	 * @param price the normal price of the meal, without any discount
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * Ordinary getter
	 * 
	 * @return the discounted price of the meal when it's in a special offer 
	 */
	public double getSpecialPrice() {
		return specialPrice;
	}

	/**
	 * Ordinary setter
	 * 
	 * @param specialPrice the discounted price of the meal when it's in a special offer 
	 */
	public void setSpecialPrice(double specialPrice) {
		if (this.specialPrice != specialPrice && specialPrice != this.price){
			setChanged();
		}
		this.specialPrice = specialPrice;
	}

	/**
	 * Ordinary getter
	 * @return true if the meal is in a special offer
	 */
	public boolean isSpecial() {
		return isSpecial;
	}

	/**Ordinary setter
	 * @param isSpecial true if the meal is in a special offer
	 */
	public void setSpecial(boolean isSpecial) {
		if (!this.isSpecial && isSpecial) {
			setChanged();
		}
		this.isSpecial = isSpecial;
	}
	
	
	/**
	 * Ordinary getter
	 * @return true if the meal is in a temporary special offer
	 */
	public boolean isOnlySpecial() {
		return isOnlySpecial;
	}
	
	/**
	 * Ordinary setter
	 * @param isOnlySpecial true if the meal is in a temporary special offer
	 */
	public void setOnlySpecial(boolean isOnlySpecial) {
		this.isOnlySpecial = isOnlySpecial;
	}
	
	/**
	 * Replaces the classic getter, and gives an easy access to the number of time a meal has been 
	 * ordered following this criteria
	 * 
	 * @param orderingCriteria The criteria chosen to analyze the behavior of the clients
	 * @return the number of times this meal has been ordered following this criteria
	 */
	public Integer getOrderCounter(String orderingCriteria) {
		return orderCounter.get(orderingCriteria);
	}


	/**
	 * Replaces the classical setter, this method is a easy way to record that a meal has been ordered following
	 * a certain criteria
	 * @param orderingCriteria ordering criteria for which we should add an order
	 * It works even if the orgeringCriteria haven't been put as a key of the map.
	 * @param number the number of time the meal has been ordered 
	 */
	public void incrementOrderCounter(String orderingCriteria, int number) {
		if (orderCounter.containsKey(orderingCriteria)){
			orderCounter.put(orderingCriteria, orderCounter.get(orderingCriteria) + number);
		} else {
			orderCounter.put(orderingCriteria, number);
		}
		
	}



	/**
	 * Ordinary getter
	 * @return the map storing how this meal is usually ordered (on sale or not, modified or not ...)
	 */
	public Map<String, Integer> getOrderCounter() {
		return orderCounter;
	}


	/**
	 * Ordinary setter
	 * @param orderCounter the map storing how this meal is usually ordered (on sale or not, modified or not ...)
	 */
	public void setOrderCounter(Map<String, Integer> orderCounter) {
		this.orderCounter = orderCounter;
	}


	/**
	 * Ordinary getter
	 * @return true if the meal is modified
	 */
	public boolean isModified() {
		return isModified;
	}


	/**
	 * Ordinary setter
	 * @param isModified true if the meal is modified
	 */
	public void setModified(boolean isModified) {
		this.isModified = isModified;
	}

	/**
	 * Ordinary getter
	 * @return the ingredientMap that contains all the ingredients names (keys) and their quantities in the 
	 * meal (values)
	 */
	public Map<String, String> getIngredientMap() {
		return ingredientMap;
	}


	/**
	 * Ordinary setter
	 * @param ingredientMap the ingredientMap that contains all the ingredients names (keys) and their quantities in the 
	 * meal (values)
	 */
	public void setIngredientMap(Map<String, String> ingredientMap) {
		this.ingredientMap = ingredientMap;
	}

	
	@Override
	public String toString() {
		if (this.isSpecial){
			return "[ " +name + ": Ingredients = " + ingredientMap + ", Regular Price = " + price
				+ ", Special Price = " + specialPrice+ " ]";
		} else {
			return "[ " + name + ": Ingredients = " + ingredientMap + ", Price = " + price+ " ]";
		}
	}
}
