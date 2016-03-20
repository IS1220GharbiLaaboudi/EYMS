package tests;

import java.util.Date;

import classes.BirthdayOffer;
import classes.Client;
import classes.Meal;
import classes.Order;
import interfaces.Offer;

public class Main {

	public static void main(String[] args) {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		Date date = new Date();
		date.setMonth(12);
		BirthdayOffer bdo = new BirthdayOffer(date);
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g"); // personalizeMeal permet aussi de rajouter des ingredients
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); // car c'est le plat original et pas un plat modifi�
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		
		
		Offer[] offers = new Offer[1];	
		offers[0] = bdo;
		double price = order.getPrice(offers);
		System.out.println(price);
	}

}
