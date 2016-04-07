package classes;

import java.text.DateFormat;
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
 * This class is the one containing all the methods that will be called by the Command Line Interface. It also 
 * stores the data related to the users, the meals and the orders.
 * 
 *
 */
public class EYMS {
	
	private User currentUser;
	
	private Meal currentMeal ;
	
	private Order CurrentOrder;
	
	private Date date;
	
	private Map<String, User> mapUsers;
	
	private Map<String, Meal> mapMeal;
	
	private Map<Integer, Order> mapOrders;
	
	private Offer[] offers;
	
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
	
	public User getCurrentUser() {
		return currentUser;
	}


	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}


	public Meal getCurrentMeal() {
		return currentMeal;
	}


	public void setCurrentMeal(Meal currentMeal) {
		this.currentMeal = currentMeal;
	}


	public Order getCurrentOrder() {
		return CurrentOrder;
	}


	public void setCurrentOrder(Order currentOrder) {
		CurrentOrder = currentOrder;
	}
	/**
	 * Enables to get a User object stored in the system from his user name
	 * 
	 * @param username The username of the client we want to get
	 * @return The user related to the username
	 */
	public User getUser(String username){
		return mapUsers.get(username);
	}
	
	public Meal getMeal(String mealName){
		return mapMeal.get(mealName);
	}
	
	public Order getOrder(Integer orderId){
		return mapOrders.get(orderId);
	}
	
	
	/**
	 * @return the date
	 */
	public Date getDate() {
		return date;
	}


	/**
	 * @param date the date to set
	 */
	public void setDate(Date date) {
		this.date = date;
	}


	/**
	 * 
	 * @param userName user name of the user
	 * @param pwd password of the user
	 * @return true if there is a user with the corresponding user name and password, 
	 * false otherwise.
	 */
	public boolean login(String userName, String pwd){
		User u = mapUsers.get(userName);
		User user = new User(userName, pwd);
		if(u.equals(user)){
			setCurrentUser(u);
		}
		return u.equals(user);
	}
	
	
	/**
	 * Adds a user in the userMap.
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
	 * Personalize an ingredient in a certain meal.
	 * 
	 * @param mealName the name of the meal.
	 * @param ingredient the ingredient to change.
	 * @param quantity the new quantity for the ingredient. ("0g" if wanted to be removed)
	 * @return true if the meal was correctly personalized,
	 * false otherwise.
	 */
	public boolean personalizeMeal(String mealName, String ingredient, String quantity){
		Meal meal = mapMeal.get(mealName);  
		if( meal != null){ //if the meal exists
			meal.personalizeMeal(ingredient, quantity, true);
			return true;
		}
		return false;
	}
	
	/**
	 * Saves a non null order in the order map and incrementing the order counter of each meal in it 
	 * if the current user is a client.
	 * 
	 * @param order the order to store.
	 * @return true if the order was stored in the map and current user is a client,
	 * false otherwise.
	 */
	public boolean saveOrder(){
		Order order = CurrentOrder;
		if (order != null && currentUser != null && currentUser.getRole() == UserRole.Client){
			double finalPrice = order.getPrice(offers);
			currentUser = order.getClient();
			mapUsers.put(currentUser.getUserName(), currentUser); // in order to store his card chages (points..)
			for(Meal meal : order.getSetMeal()){
				System.out.println(meal);
				int n = order.getQuantityMeal(meal);
				if(meal.isOnlySpecial() || meal.isSpecial())
					meal.incrementOrderCounter("OnSale", n);
				else
					meal.incrementOrderCounter("NotOnSale", n);
				if(meal.isModified())
					meal.incrementOrderCounter("Modified", n);
				else
					meal.incrementOrderCounter("NotModified", n);
			}
			mapOrders.put(order.getId(), order);
			System.out.println("The order have been successfully saved. The amount you have to pay is "+ finalPrice + " euros");
			return true;
		}
		return false;
	}
	
	/**
	 * Adds or updates a contact information to the current user. 
	 * 
	 * @param typeContact the type of contact ("email", "telephone number",...)
	 * @param info the actual information ("johnred@mymail.com","0601010101", ..)
	 * @return true if correctly added or updated the contact info,
	 * false otherwise.
	 */
	public boolean addInfo(String typeContact, String info, boolean bool){
		if(currentUser != null && currentUser.getRole() == UserRole.Client && typeContact != "" && info != ""){
			Client client = (Client) currentUser;
			client.setContactInfo(typeContact, info);
			if(bool)
				client.setFavoriteContactInfo(typeContact);
			mapUsers.put(client.getUserName(), client);
			return true;
		}
		return false;
	}
	
	/**
	 * Adds or updates current user's birthday. 
	 * 
	 * @param birthday the birthday date in this format yyyy/MM/dd.
	 * @return true if correctly added or updated the contact info,
	 * false otherwise.
	 * @throws java.text.ParseException 
	 */
	public boolean addBirthday(String birthday) throws java.text.ParseException{
		Date date = null;
		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
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
	 * Associate a fidelity card to the current user.
	 * 
	 * @param typeCard the type of the card to be associated to the current user. 
	 * Can be "Lottery", "Point" or "Basic".
	 * @return true if typeCard is a know type of card and current user is a client,
	 * false otherwise.
	 */
	public boolean associateCard(FidelityCard typeCard){
		if((typeCard == FidelityCard.Lottery || typeCard == FidelityCard.Point || typeCard == FidelityCard.Basic)
				&& currentUser != null && currentUser.getRole() == UserRole.Client){
			Client client = (Client) currentUser;
			Offer card = new BasicFidelityCard(); //BasicFidelityCard by default.
			if(typeCard == FidelityCard.Lottery){
				card = new LotteryFidelityCard();
			} else if (typeCard == FidelityCard.Point){
				card = new PointFidelityCard();
			}
			client.setCard(card);
			mapUsers.put(currentUser.getUserName(), client);
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
	 * @param agree 
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
					if(meal.getOrderCounter("OnSale") != 0 && meal.getOrderCounter("NotOnSale") == 0)
						orderedSet.add(meal);
				}
			}
		} else if (orderingCriteria == OrderingCriteria.MostlyModified){
			for(Order order : mapOrders.values()){ //to get only what was ordered
				for(Meal meal : order.getSetMeal()){
					if(meal.getOrderCounter("Modified") > meal.getOrderCounter("NotModified"))
						orderedSet.add(meal);
				}
			}
		} else if (orderingCriteria == OrderingCriteria.AsItIs){
			for(Order order : mapOrders.values()){ //to get only what was ordered
				for(Meal meal : order.getSetMeal()){
					if(meal.getOrderCounter("Modified") == 0)
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
		if(currentUser.getRole() == UserRole.Chef){
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
	 */
	public boolean addIngredient(String ingredient, String quantity){
		if(currentMeal != null){
			return currentMeal.personalizeMeal(ingredient, quantity, false);
		}
		return false;
	}
	
	/**
	 * 
	 * @return the current meal
	 */
	public Meal currentMeal(){
		return currentMeal;
	}
	
	/**
	 * Saves the current meal to the meals map if the user is a chef.
	 * 
	 * @return true if current user is a chef,
	 * false otherwise.
	 */
	public boolean saveMeal(){
		if(currentUser != null && currentUser.getRole() == UserRole.Chef && currentMeal != null){
			mapMeal.put(currentMeal.getName(), currentMeal);
			return true;
		} 
		return false;
	}
	
	/**
	 * Puts an existing meal in a special offer if current user is a chef.
	 * 
	 * @param mealName the name of the meal.
	 * @param newPrice the new price of the meal.
	 * @return true if the meal exists and the new price is different from the old and current user is a chef,
	 * false otherwise.
	 */
	public boolean putInSpecialOffer(String mealName, double newPrice){
		Meal meal = mapMeal.get(mealName);
		if(meal != null && newPrice != meal.getPrice() && currentUser.getRole() == UserRole.Chef){
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
	 * 
	 * @param mealName the name of the meal.
	 * @return true if the meal exists and current user is a chef,
	 * false otherwise.
	 */
	public boolean removeFromSpecialOffer(String mealName){
		Meal meal = mapMeal.get(mealName);
		if(meal != null && currentUser != null && currentUser.getRole() == UserRole.Chef){
			meal.setSpecialPrice(meal.getPrice());
			meal.setSpecial(false);
			meal.setOnlySpecial(false);
			mapMeal.put(mealName, meal);
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Creates a current meal with a new special offer if current user is a chef.
	 * 
	 * @param mealName the name of the meal.
	 * @param price the price of the meal.
	 * @return true if the meal does not already exist and current user is a chef,
	 * false otherwise.
	 */
	public boolean insertOffer(String mealName, double price ){
		Meal meal = mapMeal.get(mealName);
		if(meal == null && currentUser != null && currentUser.getRole() == UserRole.Chef){
			currentMeal = new Meal(mealName, price, true);
			return true;
		} else{
			return false;
		}
	}
	
	/**
	 * Notifies users that agreed to have notifications about special offers of a current special offer.
	 * 
	 * @param message Message related to the special offer.
	 * @param mealName Name of the meal that is on sale
	 * @param specialPrice The new price of the meal. Should be inferior to the normal price.
	 * @return true if current user is a chef and a special offer was created,
	 * false otherwise.
	 */
	public boolean notifyAd(String message, String mealName, double specialPrice){ 
		boolean t = putInSpecialOffer(mealName, specialPrice);
		if(t && currentUser.getRole() == UserRole.Chef){
			Meal meal = mapMeal.get(mealName);
			for(User user : mapUsers.values()){
				Client client = (Client) user;
				if(client.getAgreement("SpecialOffer")){
					meal.addObserver(client);
				}
			}
			meal.notifyObservers(message);;
			return true;
		}	
		return false;
	}
	
	/**
	 * Notifies users that have their birthdays of special discount.
	 * 
	 * @return true if current user is a chef,
	 * false otherwise.
	 */
	@SuppressWarnings("deprecation")
	public boolean notifyBirthday(){
		if(currentUser.getRole() == UserRole.Chef){
			BirthdayOffer bd = new BirthdayOffer(date);
			for(User user : mapUsers.values()){
				Client client = (Client) user;
				if(client.getBirthdayDate().getDate() == date.getDate() && client.getBirthdayDate().getMonth() == date.getMonth()){
					bd.addObserver(client);
				}
			}
			bd.notifyObservers();
			return true;
		} 
		return false;
	}
	
	/**
	 * Add a quantity of meal to the current order.
	 * 
	 * @param mealName the name of the meal
	 * @param quantity the quantity of the meal
	 * @return true if the meal name corresponds to an actual meal,
	 * false otherwise.
	 * */
	 public boolean selectMeal(String mealName, int quantity){
		 Meal meal = mapMeal.get(mealName);
		 if(meal != null && currentUser != null && currentUser.getRole() == UserRole.Client){
			 if(CurrentOrder == null){
				 CurrentOrder = new Order((Client) currentUser, getDate());
			 }	
			 CurrentOrder.setNumberOfMeal(meal, quantity);
			 return true;
		 }
		 return false;
	 }
}
