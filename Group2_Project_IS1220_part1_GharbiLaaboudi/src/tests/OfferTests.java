/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import classes.*;
import interfaces.Offer;

/**
 * @author Fouad-Sams
 *
 */
public class OfferTests {

	@Test
	public void BasicFidelityCardOfferWithoutOffer() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
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
		System.out.println("normal price" + price);
		Assert.assertEquals(3*17, price);
	}
	
	@Test
	public void BasicFidelityCardOfferWithSpecialOffer() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g");
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		
		
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
		Assert.assertEquals(3*15, price);
	}
	
	@Test
	public void BasicFidelityCardOfferWithSpecialOfferOnOnlyOneMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g");
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		// creation of a meal
		Meal burger = new Meal("Burger", 8);
		raclette.personalizeMeal("cheese", "40g"); 
		raclette.personalizeMeal("ham", "90g");
		raclette.personalizeMeal("beef", "250g");
		raclette.setModified(false); 
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		order.setNumberOfMeal(burger, 1);
		
		
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
		Assert.assertEquals(3*15+8, price);
	}
	
	@Test
	public void LotteryFidelityCardOfferWithOnlyOneMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new LotteryFidelityCard());
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g");
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		boolean winLottery = false;
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		for (int i = 0; i < 100; i++) {
			bob.setCard(new LotteryFidelityCard());
			double price = order.getPrice(offers);
			if (price==17*2){
				winLottery = true;
			}
		}
		
		Assert.assertTrue(winLottery);
	}
	
	@Test
	public void LotteryFidelityCardOfferWithOnlyOneMealCheckIfCanWinOnlyOnce() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new LotteryFidelityCard());
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g");
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		boolean winLottery = false;
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = order.getPrice(offers);
		for (int i = 0; i < 100; i++) {
			price = order.getPrice(offers);
			if (price==17*2){
				winLottery = true;
			}
		}
		
		Assert.assertTrue(!winLottery);
	}
	
	@Test
	public void LotteryFidelityCardOfferWithTwoMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new LotteryFidelityCard());
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g");
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		// creation of a meal
		Meal burger = new Meal("Burger", 8);
		raclette.personalizeMeal("cheese", "40g"); 
		raclette.personalizeMeal("ham", "90g");
		raclette.personalizeMeal("beef", "250g");
		raclette.setModified(false); 
				
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		order.setNumberOfMeal(burger, 1);
		boolean winLottery = false;
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		for (int i = 0; i < 100; i++) {
			bob.setCard(new LotteryFidelityCard());
			double price = order.getPrice(offers);
			if (price==17*3){
				winLottery = true;
			}
		}
		
		Assert.assertTrue(winLottery);
	}
	
	@Test
	public void PointFidelityCardOfferWithOnlyOneMealNoDiscount() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new PointFidelityCard());
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g");
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		boolean winLottery = false;
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = 0;
		for (int i = 0; i < 1; i++) {
			price = order.getPrice(offers);
		}
		
		Assert.assertEquals(3*17,price);
	}
	
	@Test
	public void PointFidelityCardOfferWithOnlyOneMeal() {
		// creation of a user
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		bob.setCard(new PointFidelityCard());
		BirthdayOffer bdo = new BirthdayOffer(new Date());
		
		// creation of a meal
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g");
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); 
		raclette.setSpecialPrice(15);
		raclette.setSpecial(true);
		
		//creation of an order
		Order order = new Order(bob);
		order.setNumberOfMeal(raclette, 3);
		boolean winLottery = false;
		Offer[] offers = new Offer[1];
		offers[0] = bdo;
		double price = 0;
		for (int i = 0; i < 2; i++) {
			price = order.getPrice(offers);
		}
		
		Assert.assertEquals(3*17*.9, price);
	}

	@Test
	public void BirthdayOfferWithOnlyOneMeal() {
		
	}
	
	@Test
	public void BirthdayOfferWithTwoMeal() {
		
	}
}