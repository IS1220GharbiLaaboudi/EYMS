/**
 * 
 */
package tests;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import classes.*;
import interfaces.Offer;

/**
 * 
 *
 */
public class OfferTests {

	@SuppressWarnings("deprecation")
	@Test
	public void BasicFidelityCardOfferWithoutOffer() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		Date date1 = new Date();
		bob.setBirthdayDate(date1);
		Date date2 = new Date();
		date2.setMonth(12);
		BirthdayOffer bdo = new BirthdayOffer(date2);
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g", false); // personalizeMeal permet aussi de rajouter des ingredients
		raclette.personalizeMeal("ham", "40g", false);
		raclette.personalizeMeal("potatoes", "50g", false);
		raclette.setModified(false); // car c'est le plat original et pas un plat modifi�
		
		//creation of an order
		Order order = new Order(bob,date2);
		order.setNumberOfMeal(raclette, 3);
		
		
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
		Assert.assertTrue( 3*17.0 ==  price);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void BasicFidelityCardOfferWithSpecialOffer() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		Date date1 = new Date();
		bob.setBirthdayDate(date1);
		Date date2 = new Date();
		date2.setMonth(12);
		BirthdayOffer bdo = new BirthdayOffer(date2);
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g", false);
		raclette.personalizeMeal("ham", "40g", false);
		raclette.personalizeMeal("potatoes", "50g", false);
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob,date2);
		order.setNumberOfMeal(raclette, 3);
		
		
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
		Assert.assertTrue(3*15 ==  price);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void BasicFidelityCardOfferWithSpecialOfferOnOnlyOneMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		Date date1 = new Date();
		bob.setBirthdayDate(date1);
		Date date2 = new Date();
		date2.setMonth(12);
		BirthdayOffer bdo = new BirthdayOffer(date2);
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g", false);
		raclette.personalizeMeal("ham", "40g", false);
		raclette.personalizeMeal("potatoes", "50g", false);
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		// creation of a meal
		Meal burger = new Meal("Burger", 8);
		raclette.personalizeMeal("cheese", "40g", false); 
		raclette.personalizeMeal("ham", "90g", false);
		raclette.personalizeMeal("beef", "250g", false);
		raclette.setModified(false); 
		
		//creation of an order
		Order order = new Order(bob,date2);
		order.setNumberOfMeal(raclette, 3);
		order.setNumberOfMeal(burger, 1);
		
		
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
		Assert.assertTrue(3*15+8 == price);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void LotteryFidelityCardOfferWithOnlyOneMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new LotteryFidelityCard());
		Date date = new Date();
		bob.setBirthdayDate(date);
		date.setMonth(12);
		BirthdayOffer bdo = new BirthdayOffer(date);
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g", false);
		raclette.personalizeMeal("ham", "40g", false);
		raclette.personalizeMeal("potatoes", "50g", false);
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		boolean winLottery = false;
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		for (int i = 0; i < 100; i++) {
			Order order = new Order(bob,date);
			order.setNumberOfMeal(raclette, 3);
			double price = order.getPrice(offers);
			if (price==0){
				winLottery = true;
			}
			bob.setCard(new LotteryFidelityCard());
		}	
		Assert.assertTrue(winLottery);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void PointFidelityCardOfferWithOnlyOneMealNoDiscount() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new PointFidelityCard());
		Date date1 = new Date();
		bob.setBirthdayDate(date1);
		Date date2 = new Date();
		date2.setMonth(12);
		BirthdayOffer bdo = new BirthdayOffer(date2);
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g", false);
		raclette.personalizeMeal("ham", "40g", false);
		raclette.personalizeMeal("potatoes", "50g", false);
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob,date2);
		order.setNumberOfMeal(raclette, 3);
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = 0;
		for (int i = 0; i < 1; i++) {
			price = order.getPrice(offers);
		}
		
		Assert.assertTrue(3*17 == price);
	}
	
	@SuppressWarnings("deprecation")
	@Test
	public void PointFidelityCardOfferWithOnlyOneMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new PointFidelityCard(100));
		Date date1 = new Date();
		bob.setBirthdayDate(date1);
		Date date2 = new Date();
		date2.setMonth(12);
		BirthdayOffer bdo = new BirthdayOffer(date2);
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g", false);
		raclette.personalizeMeal("ham", "40g", false);
		raclette.personalizeMeal("potatoes", "50g", false);
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob,date2);
		order.setNumberOfMeal(raclette, 3);
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
		
		Assert.assertTrue(3*17*.9 == price);
	}

	@Test
	public void BirthdayOfferWithOnlyOneMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new PointFidelityCard());
		Date date = new Date();
		bob.setBirthdayDate(date);
		BirthdayOffer bdo = new BirthdayOffer(date);
				
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g", false);
		raclette.personalizeMeal("ham", "40g", false);
		raclette.personalizeMeal("potatoes", "50g", false);
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
				
		//creation of an order
		Order order = new Order(bob,date);
		order.setNumberOfMeal(raclette, 3);
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
				
		Assert.assertTrue(3*17*.5 == price);
	}
		
	@Test
	public void BirthdayOfferAndSpecialOffer() {
		// creation of a user
				Client bob = new Client("Bob", "Red", "bobred", "123456");
				bob.setCard(new BasicFidelityCard());
				Date date = new Date();
				bob.setBirthdayDate(date);
				BirthdayOffer bdo = new BirthdayOffer(date);
						
				// creation of a meal
				Meal raclette = new Meal("Raclette", 17);
				raclette.personalizeMeal("cheese", "90g",false);
				raclette.personalizeMeal("ham", "40g", false);
				raclette.personalizeMeal("potatoes", "50g", false);
				raclette.setModified(false); 
				raclette.setSpecialPrice(15);
				raclette.setSpecial(true);
						
				//creation of an order
				Order order = new Order(bob,date);
				order.setNumberOfMeal(raclette, 3);
				Offer[] offers = new Offer[1];
				offers[0] = bdo;
				double price = order.getPrice(offers);
				
				Assert.assertTrue(3*17*.5-3*(17-15) == price);
		
	}
}