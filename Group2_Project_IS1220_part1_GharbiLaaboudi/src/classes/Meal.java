/**
 * 
 */
package classes;

import java.util.Map;
import java.util.Observable;

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
	 * 
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
	}
	
	
	/**
	 * @return the meal's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param mealName the mealName to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the ingredientMap
	 */
	public Map<String, String> getIngredientMap() {
		return ingredientMap;
	}
	
	/**
	 * 
	 * @param ingredient An ingredient of the meal.
	 * @param quantity The quantity needed for the given ingredient in this meal. To remove the ingredient, put "0g"
	 */
	public void personalizeMeal(String ingredient, String quantity) {
			ingredientMap.replace(ingredient, quantity);
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
	 * @param ingredientMap the ingredientMap to set
	 */
	public void setIngredientMap(Map<String, String> ingredientMap) {
		this.ingredientMap = ingredientMap;
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

	@Override
	public String toString() {
		if (this.isSpecial){
			return "Meal [mealName=" + name + ", ingredientMap=" + ingredientMap + ", price=" + price
				+ ", specialPrice=" + specialPrice + "]";
		} else {
			return "Meal [mealName=" + name + ", ingredientMap=" + ingredientMap + ", price=" + price + "]";
		}
	}
}
