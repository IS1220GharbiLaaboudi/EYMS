package classes;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import enums.FidelityCard;
import enums.OrderingCriteria;
import enums.UserRole;
import interfaces.Offer;
/**
 * 
 * This class is the one containing all the methods that will be called by the Command Line User Interface. It also 
 * stores the data related to the users, the meals and the orders.
 * 
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 */
public class EYMS {
	/**
	 * Usually the user that is logged in
	 */
	private User currentUser;
	/**
	 * Usually the meal that is being created by the chef
	 */
	private Meal currentMeal ;
	/**
	 * Usually the order that is being made by the client
	 */
	private Order currentOrder;
	/**
	 * The date of the system, enables to deal with birthdays and avoids lottery cards to be used twice the same day
	 */
	private Date date;
	/**
	 * A map of all the users registered (clients and chef alike). The key is the username and the value is an instance of the 
	 * User or the Client class.
	 */
	private Map<String, User> mapUsers;
	/**
	 * A map of all the meals created by the chef. The key is the name of the meal and the value is an instance of the 
	 * Meal class.
	 */
	private Map<String, Meal> mapMeal;
	/**
	 * A map of all the orders made by the clients. The key is the ID of the order (an integer) and the value is an
	 * instance of the Order class.
	 */
	private Map<Integer, Order> mapOrders;
	/**
	 * A list of all the offers available in the restaurant (not taking into account the fidelity cards). 
	 * Here, it's a singleton : the only offer available is the birthday offer.
	 */
	private Offer[] offers;
	
	/**
	 * The only constructor of the EYMS class.
	 * The default date is the date at which the system is executed.
	 * The offers list is initialized with only the birthday offer.
	 * All the maps are empty in the beginning.
	 */
	public EYMS(){
		date =  new Date();
		offers = new Offer[1];
		BirthdayOffer bdo = new BirthdayOffer(date);
		offers[0] = bdo;
		mapUsers = new HashMap<String, User>();
		mapMeal = new HashMap<String, Meal>();
		mapOrders = new HashMap<Integer, Order>();
	}
	
	/*
	 * Ordinary getters and setters
	 * For the maps, no setters and the getters, given a certain key, return only a unique value.
	 * 
	 */
	/**
	 * Ordinary getter
	 * @return The current user (the one logged in)
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * Ordinary setter
	 * @param currentUser The current user (the one logged in)
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}

	/**
	 * Ordinary getter
	 * @return currentMeal The current meal (the one getting created)
	 */
	public Meal getCurrentMeal() {
		return currentMeal;
	}

	/**
	 * Ordinary setter
	 * @param currentMeal The current meal (the one getting created)
	 */
	public void setCurrentMeal(Meal currentMeal) {
		this.currentMeal = currentMeal;
	}

	/**
	 * Ordinary getter
	 * @return currentOrder The current order (the one getting created)
	 */
	public Order getCurrentOrder() {
		return currentOrder;
	}

	/**
	 * Ordinary setter
	 * @param currentOrder The current order (the one getting created)
	 */
	public void setCurrentOrder(Order currentOrder) {
		this.currentOrder = currentOrder;
	}
	/**
	 * Enables to get a User object stored in the system from his username
	 * 
	 * @param username The username of the client we want to get
	 * @return The user related to the username
	 */
	public User getUser(String username){
		return mapUsers.get(username);
	}
	/**
	 * Enables to get a Meal object stored in the system from his name
	 * 
	 * @param mealName The name of the meal we want to get
	 * @return The meal (instance of the Meal class) related to the name given
	 */
	public Meal getMeal(String mealName){
		return mapMeal.get(mealName);
	}
	/**
	 * Enables to get a Order object stored in the system from his ID
	 * 
	 * @param orderId The ID of the order we want to get
	 * @return The order (instance of the Order class) related to the ID given
	 */
	public Order getOrder(Integer orderId){
		return mapOrders.get(orderId);
	}
	
	
	/**
	 * Ordinary getter
	 * @return the date of the system
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * Ordinary setter. It will also update the offers list. Therefore, the birthdays of the date can be 
	 * correctly identified
	 * 
	 * @param dateString the date to set in the format dd/mm/yyyy (this is a string)
	 * @throws ParseException if the beginning of the specified string cannot be parsed.
	 */
	public void setDate(String dateString) throws ParseException {
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date date1 = dateFormat.parse(dateString);
		this.date = date1;
		((BirthdayOffer) offers[0]).setDate(date);
	}


	/**
	 * Login method of the system. It first performs a "logout" by reinitializing the current meal and order.
	 * The login will be successful only if the username and the password match those of a registered user.
	 * 
	 * @param userName user name of the user
	 * @param pwd password of the user
	 * @return true if there is a user with the corresponding user name and password, 
	 * false otherwise.
	 */
	public boolean login(String userName, String pwd){
		// Resets the objects that were handled (a kind of logout)
		currentMeal = null;
		currentOrder = null;
		// Actual login
		User u = mapUsers.get(userName);
		User user = new User(userName, pwd);
		if(user.equals(u)){
			setCurrentUser(u);
		}
		return user.equals(u);
	}
	
	
	/**
	 * Adds a user in the userMap. If a user of the same name already exists, the method will return false.
	 * 
	 * @param firstName first name of the user.
	 * @param lastName last name of the user.
	 * @param username user name of the user.
	 * @param pwd password of the user.
	 * @param role role of the user. (Client or Chef)
	 * @return true if the user name isn't already taken and all the given information is not empty, 
	 * false otherwise.
	 */
	public boolean register(String firstName, String lastName, String username, String pwd, UserRole role){
		// if the user doesn't exist and all the
		if (firstName != "" && lastName != "" && username !="" && pwd != "" && mapUsers.get(username) == null){
			
			if (role == UserRole.Chef){
				User user = new User(firstName,lastName,username,pwd,role);
				mapUsers.put(username, user);
			}
			else if(role == UserRole.Client){
				Client client= new Client(firstName,lastName,username,pwd);
				mapUsers.put(username, client);
			}
			return true;
		}
		return false;
	}
	
	/**
	 * Personalize an ingredient in a certain meal. If the ingredient doesn't exists, it will add it, and otherwise
	 * it will only change the quantity of the ingredient specified.
	 * In order to allow both the personnalized and the original meal to coexist in the system, a new meal will be
	 * created with the name "Modified mealName". The ingredients will be exactly the same except for the one that
	 * has been modified in the method.
	 *
	 * 
	 * @param mealName the name of the meal.
	 * @param ingredient the ingredient to change.
	 * @param quantity the new quantity for the ingredient. ("0g" if wanted to be removed)
	 * @return true if the meal was correctly personalized,
	 * false otherwise.
	 */
	public boolean personalizeMeal(String mealName, String ingredient, String quantity){
		Meal meal = mapMeal.get(mealName);
		//if the meal exists
		if( meal != null){
			Meal personalizedMeal ;
			if (meal.getName().startsWith("Modified")){
				personalizedMeal= new Meal(mealName, meal.getPrice());
			}else{
				personalizedMeal= new Meal("Modified " + mealName, meal.getPrice());
			}
			// A copy of the meal that isn't related to it (not the same address in memory)
			for (String ingredientName : meal.getIngredientMap().keySet()){
				personalizedMeal.personalizeMeal(ingredientName, meal.getIngredientMap().get(ingredientName), true);
			}
			if (meal.isSpecial()){
				personalizedMeal.setSpecial(true);
				personalizedMeal.setSpecialPrice(meal.getSpecialPrice());
			}
			personalizedMeal.personalizeMeal(ingredient, quantity, true);
			mapMeal.put(personalizedMeal.getName(), personalizedMeal);
			return true;
		}
		return false;
	}
	
	/**
	 * Saves a non null order in the order map and incrementing the order counter of each meal in it 
	 * if the current user is a client.
	 * 
	 * @return true if the order was stored in the map and current user is a client,
	 * false otherwise.
	 */
	public boolean saveOrder(){
		Order order = currentOrder;
		if (order != null && currentUser != null && currentUser.getRole() == UserRole.Client){
			double finalPrice = order.getPrice(offers);
			currentUser = order.getClient();
			mapUsers.put(currentUser.getUserName(), currentUser); // in order to store his card chages (points..)
			for(Meal meal : order.getSetMeal()){
				int n = order.getQuantityMeal(meal);
				System.out.println(n+" "+meal);
				Meal mealNotModified;
				if(meal.isModified()){
					mealNotModified = getMeal(meal.getName().substring("Modified ".length()));
					mealNotModified.incrementOrderCounter("Modified", n);
				} else
					mealNotModified = meal;
					meal.incrementOrderCounter("NotModified", n);
				if(meal.isOnlySpecial() || meal.isSpecial())
					mealNotModified.incrementOrderCounter("OnSale", n);
				else
					mealNotModified.incrementOrderCounter("NotOnSale", n);
			}
			mapOrders.put(order.getId(), order);
			System.out.println("The order have been successfully saved. The amount you have to pay is "+ finalPrice + " euros");
			currentOrder = null;
			return true;
		}
		return false;
	}
	
	/**
	 * Adds or updates a contact information to the current user (it can be the favorite contact information or not). 
	 * 
	 * @param typeContact the type of contact ("email", "telephone number",...)
	 * @param info the actual information ("johnred@mymail.com","0601010101", ..)
	 * @param favorite true if this information is the favorite contact information of the user. 
	 * @return true if correctly added or updated the contact info,
	 * false otherwise.
	 */
	public boolean addInfo(String typeContact, String info, boolean favorite){
		if(currentUser != null && currentUser.getRole() == UserRole.Client && typeContact != "" && info != ""){
			Client client = (Client) currentUser;
			client.setContactInfo(typeContact, info);
			if(favorite)
				client.setFavoriteContactInfo(typeContact);
			mapUsers.put(client.getUserName(), client);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds or updates current user's birthday. 
	 * 
	 * @param birthday the birthday date in this format dd/MM/yyyy
	 * @return true if correctly added or updated the contact info,
	 * false otherwise.
	 * @throws java.text.ParseException if the beginning of the specified string
     *            cannot be parsed.
	 */
	public boolean addBirthday(String birthday) throws java.text.ParseException{
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		date = dateFormat.parse(birthday);
		if(currentUser != null && currentUser.getRole() == UserRole.Client && birthday.equals(dateFormat.format(date))){
			Client client = (Client) currentUser;
			client.setBirthdayDate(date);
			mapUsers.put(client.getUserName(), client);
			return true;
		}
		return false;
	}
	
	/**
	 * Associate a fidelity card to any user.
	 * 
	 * @param user the user for which we want to associate a fidelity card
	 * @param typeCard the type of the card to be associated to the current user. 
	 * Can be "Lottery", "Point" or "Basic".
	 * @return true if typeCard is a know type of card and current user is a client,
	 * false otherwise.
	 */
	public boolean associateCard(User user, FidelityCard typeCard){
		if((typeCard == FidelityCard.Lottery || typeCard == FidelityCard.Point || typeCard == FidelityCard.Basic)
				&& user != null && user.getRole() == UserRole.Client){
			Client client = (Client) user;
			Offer card = new BasicFidelityCard(); //BasicFidelityCard by default.
			if(typeCard == FidelityCard.Lottery){
				card = new LotteryFidelityCard();
			} else if (typeCard == FidelityCard.Point){
				card = new PointFidelityCard();
			}
			client.setCard(card);
			mapUsers.put(user.getUserName(), client);
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * Associate an agreement for a certain client.
	 * 
	 * @param username user name of the client
	 * @param agreementType the type of the agreement
	 * @param agree true if the user agrees to this type of notification
	 * @return true if the user is a registered client,
	 * false otherwise.
	 */
	public boolean associateAgreement(String username, String agreementType, boolean agree){
		User user = mapUsers.get(username);
		if(user != null && agreementType != "" && user.getRole() == UserRole.Client){
			Client client = (Client) user;
			client.setAgreement(agreementType, agree);
			return true;
		}
		else{
			return false;
		}
	}
	
	/**
	 * Given a certain criteria, returns the meals of meals respecting it. 
	 * If the ordering criteria is not known, return all the meals.
	 * 
	 * @param orderingCriteria a criteria to sort the meals. Usually "OnSale", "NotOnSale", "Modified" and 
	 * "NotModified"
	 * @return a set of meals respecting the ordering criteria.
	 */
	public Set<Meal> showMeal(OrderingCriteria orderingCriteria){
		Set<Meal> orderedSet = new HashSet<Meal>();
		if (orderingCriteria == OrderingCriteria.JustOnSale){
			for(Order order : mapOrders.values()){ //to get only what was ordered
				for(Meal meal : order.getSetMeal()){
					if(meal.getOrderCounter("OnSale") != 0 && meal.getOrderCounter("NotOnSale") == 0 && !meal.isModified())
						orderedSet.add(meal);
				}
			}
		} else if (orderingCriteria == OrderingCriteria.MostlyModified){
			for(Order order : mapOrders.values()){ //to get only what was ordered
				for(Meal meal : order.getSetMeal()){
					if(meal.getOrderCounter("Modified") > meal.getOrderCounter("NotModified") && !meal.isModified())
						orderedSet.add(meal);
				}
			}
		} else if (orderingCriteria == OrderingCriteria.AsItIs ){
			for(Order order : mapOrders.values()){ //to get only what was ordered
				for(Meal meal : order.getSetMeal()){
					if(meal.getOrderCounter("Modified") == 0 && !meal.isModified() )
						orderedSet.add(meal);
				}
			}
		} else {
			orderedSet = new HashSet<Meal>(mapMeal.values());
		}
		return orderedSet;
	}
	
	public Set<Meal> showMeal(){
		return new HashSet<Meal>(mapMeal.values());
	}
	
	/**
	 * Get the map of ingredients and their quantities for a certain meal.
	 * 
	 * @param mealName the name of a meal.
	 * @return a map with ingredients as keys and their quantities as value.
	 * If the meal is not known, return null.
	 */
	public Map<String, String> listIngredients(String mealName){
		Map<String, String> listIngred = null;
		Meal meal = mapMeal.get(mealName);
		if (meal != null){
			listIngred = meal.getIngredientMap();
		}
		return listIngred;
	}
	
	/**
	 * Create new current meal if current user is a chef.
	 * 
	 * @param mealName name of the meal to create.
	 * @param price price of the meal to create.
	 * @return true if the current user is chef,
	 * false otherwise.
	 */
	public boolean createMeal(String mealName, double price){
		if(currentUser.getRole() == UserRole.Chef && price > 0){
			currentMeal = new Meal(mealName, price);
			return true;
		}
		return false;
	}
	
	/**
	 * Add an ingredient with non-zero quantity to the current meal.
	 * 
	 * @param ingredient name of the ingredient.
	 * @param quantity quantity of the ingredient
	 * @return true if the ingredient was well added
	 */
	public boolean addIngredient(String ingredient, String quantity){
		if(currentMeal != null){
			return currentMeal.personalizeMeal(ingredient, quantity, false);
		}
		return false;
	}
	
	/**
	 * Saves the current meal to the meals map if the user is a chef, then proceeds to reinitialize the current Meal
	 * attribute.
	 * 
	 * @return true if current user is a chef,
	 * false otherwise.
	 */
	public boolean saveMeal(){
		if(currentUser != null && currentUser.getRole() == UserRole.Chef && currentMeal != null){
			mapMeal.put(currentMeal.getName(), currentMeal);
			currentMeal = null;
			return true;
		} 
		return false;
	}
	
	/**
	 * Puts an existing meal in a special offer if current user is a chef. The difference between the old and the 
	 * new price is the discount that the client (with a basic fidelity card) will get each time he orders the meal
	 * in question.
	 * 
	 * @param mealName the name of the meal.
	 * @param newPrice the new price of the meal.
	 * @return true if the meal exists and the new price is different from the old and current user is a chef,
	 * false otherwise.
	 */
	public boolean putInSpecialOffer(String mealName, double newPrice){
		Meal meal = mapMeal.get(mealName);
		if(meal != null && newPrice != meal.getPrice() && currentUser.getRole() == UserRole.Chef && newPrice > 0){
			meal.setSpecialPrice(newPrice);
			meal.setSpecial(true);
			mapMeal.put(mealName, meal);
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Removes a meal from special offers if current user is a chef. 
	 * If the meal is in a temporary special offer, it will only set it price back to the usual one.
	 * However, if the meal has been added through the insertOffer method, it will be removed from the system.
	 * 
	 * @param mealName the name of the meal.
	 * @return true if the meal exists and current user is a chef,
	 * false otherwise.
	 */
	public boolean removeFromSpecialOffer(String mealName){
		Meal meal = mapMeal.get(mealName);
		if(meal != null && currentUser != null && currentUser.getRole() == UserRole.Chef){
			if (meal.isOnlySpecial()){
				mapMeal.remove(meal.getName());
			}else{
				meal.setSpecialPrice(meal.getPrice());
				meal.setSpecial(false);
				mapMeal.put(mealName, meal);
			}
			
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Creates a current meal with a new special offer if current user is a chef. The meals created through this 
	 * method can be ordered by any client (not only by those with a Basic Fidelity Card).
	 * These meals represent temporary offers that can be removed easily from the system with the method 
	 * removeFromSpecialOffer.
	 * 
	 * @param mealName the name of the meal.
	 * @param price the price of the meal.
	 * @return true if the meal does not already exist and current user is a chef,
	 * false otherwise.
	 */
	public boolean insertOffer(String mealName, double price ){
		Meal meal = mapMeal.get(mealName);
		if(meal == null && currentUser != null && currentUser.getRole() == UserRole.Chef && price > 0){
			currentMeal = new Meal(mealName, price, true);
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Notifies users that agreed to have notifications about special offers of a current special offer.
	 * This method also puts the meal into a special offer.
	 * 
	 * @param message Message related to the special offer.
	 * @param mealName Name of the meal that is on sale
	 * @param specialPrice The new price of the meal. Should be inferior to the normal price.
	 * @return true if current user is a chef and a special offer was created,
	 * false otherwise.
	 */
	public boolean notifyAd(String message, String mealName, double specialPrice){ 
		boolean t = putInSpecialOffer(mealName, specialPrice);
		if(t && currentUser.getRole() == UserRole.Chef && specialPrice > 0){
			Meal meal = mapMeal.get(mealName);
			for(User user : mapUsers.values()){
				if (user instanceof Client){
					Client client = (Client) user;
					if(client.getAgreement("SpecialOffer")){
						meal.addObserver(client);
					}
				}
			}
			meal.notifyObservers(message);
			return true;
		}	
		return false;
	}
	
	/**
	 * Notifies the users that have their birthdays on the day specified to the system that they can enjoy a 
	 * discount for this special occasion.
	 * 
	 * @return true if current user is a chef,
	 * false otherwise.
	 */
	@SuppressWarnings("deprecation")
	public boolean notifyBirthday(){
		if(currentUser.getRole() == UserRole.Chef){
			BirthdayOffer bd = (BirthdayOffer) offers[0];
			for(User user : mapUsers.values()){
				if (user instanceof Client){
					Client client = (Client) user;
				
					if(client.getBirthdayDate().getDate() == date.getDate() && client.getBirthdayDate().getMonth() == date.getMonth()
							&& client.getAgreement("BirthdayOffer")){
						bd.addObserver(client);
					}
				}
			}
			bd.notifyObservers();
			return true;
		} 
		return false;
	}
	
	/**
	 * Add a quantity of meal to the current order. If no order is currently being made, a new order will be
	 * created.
	 * If the meal had already been ordered, the quantity with which it had been ordered will be changed.
	 * If the quantity is set to 0, the meal will be deleted from the order.
	 * @param mealName the name of the meal
	 * @param quantity the quantity of the meal
	 * @return true if the meal name corresponds to an actual meal,
	 * false otherwise.
	 * */
	 public boolean selectMeal(String mealName, int quantity){
		 Meal meal = mapMeal.get(mealName);
		 if(meal != null && currentUser != null && currentUser.getRole() == UserRole.Client && quantity >= 0){
			 if(currentOrder == null){
				 currentOrder = new Order((Client) currentUser, getDate());
			 }	
			 currentOrder.setNumberOfMeal(meal, quantity);
			 return true;
		 }
		 return false;
	 }
}
