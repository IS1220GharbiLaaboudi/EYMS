/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import classes.*;
import enums.FidelityCard;
import enums.UserRole;
import interfaces.Offer;

/**
 *
 *
 */
public class EYMSTest {
	
	@Test
	public void testRegisterClient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);
		Client bob1 = (Client) system.getUser("bobred");
		Client bob2 = new Client("Bob", "Red", "bobred", "123456");
		
		Assert.assertEquals(bob2, bob1);
	}
	
	@Test
	public void testLoginClient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		User bob1 = system.getCurrentUser();
		User bob2 = new Client("Bob", "Red", "bobred", "123456");
		Assert.assertEquals(bob2, bob1);
	}
	
	@Test
	public void testRegisterChef(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);
		User bob1 = system.getUser("bobred");
		
		User bob2 = new User("Bob", "Red", "bobred", "123456", UserRole.Chef);
		
		Assert.assertEquals(bob2, bob1);
		
		
	}
	@Test
	public void testLoginChef(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		User bob1 = system.getCurrentUser();
		User bob2 = new User("Bob", "Red", "bobred", "123456", UserRole.Chef);
		
		Assert.assertEquals(bob1,bob2);
	}
	@Test
	public void testRegisterSameUsername(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);
		system.register("Bernard", "Rouge", "bobred", "123456", UserRole.Client);
		system.login("bobred", "123456");
		User bob = system.getCurrentUser();
		
		Assert.assertEquals("Bob", bob.getFirstName());
	}
	@Test
	public void testPersonalizeMeal(){
		EYMS system = new EYMS();
		system.register("bob", "red", "bobred", "123456", UserRole.Chef);
		system.login("bobred", "123456");
		system.setCurrentMeal(new Meal("Raclette", 15));
		system.addIngredient("patate", "20g");
		system.saveMeal();
		system.setCurrentMeal(new Meal("Hamburger", 15));
		system.addIngredient("thon", "20g");
		system.saveMeal();
		system.personalizeMeal("Raclette", "patate", "300g");
		
		system.register("bob", "red", "bobred5", "123456", UserRole.Client);
		system.login("bobred5", "123456");
		system.personalizeMeal("Hamburger", "thon", "0.0g");
		
		Assert.assertEquals("300g",system.listIngredients("Modified Raclette").get("patate"));
		Assert.assertEquals(null,system.listIngredients("Modified Hamburger").get("thon"));
	}
	@Test
	public void testSaveOrder(){
		EYMS system = new EYMS();
		system.register("Bob", "Green", "bobgreen", "123456", UserRole.Chef);
		system.login("bobgreen", "123456");
		system.createMeal("Raclette", 17);
		system.saveMeal();
		
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);	
		system.login("bobred", "123456");
		try {
			system.addBirthday("17/09/1995");
		} catch (ParseException e) {System.out.println("Error in testSaveOdrder");}

		system.selectMeal("Raclette", 3);
		
		system.saveOrder();
		Assert.assertEquals((int)  (system.getOrder(1).getQuantityMeal(system.getMeal("Raclette")) ), 3);
	}
	@Test
	public void addInfo(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		system.addInfo("email", "bobred@gmail.com", true);
		
		Client bob = (Client) system.getCurrentUser();
		String mailAdress = bob.getContactInfo("email");
		Assert.assertEquals("bobred@gmail.com", mailAdress);
	}
	@Test
	public void testAssociateCard(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		system.associateCard(system.getCurrentUser(), FidelityCard.Lottery);
		
		Client bob = (Client) system.getCurrentUser();
		Offer card = bob.getCard();
		Assert.assertTrue(card instanceof LotteryFidelityCard);
	}
	@Test
	public void associateAgreement(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		system.associateAgreement("bobred","SpecialOffer",false); // set specialoffer agreement to false
		
		Client bob = (Client) system.getCurrentUser();
		boolean agree = bob.getAgreement("SpecialOffer");
		Assert.assertTrue(!agree);
	}
	
	
	@Test
	public void createMealClient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		Meal raclette = system.getCurrentMeal();
		Assert.assertTrue(raclette == null);
	}
	
	@Test
	public void createMealChef(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		Meal raclette = system.getCurrentMeal();
		Assert.assertTrue(raclette.getName() == "Raclette");
	
	}
	@Test
	public void testAddIngredient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		Assert.assertEquals("50g", system.getMeal("Raclette").getQuantity("Cheese"));
		
	}
	
	@Test
	public void testListIngredients(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
	
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		assertEquals("50g",system.listIngredients("Raclette").get("Cheese"));
	}
	
	@Test
	public void testSaveMeal(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
	
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		assertEquals("50g",system.listIngredients("Raclette").get("Cheese"));
	}
	@Test
	public void testputInSpecialOffer(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		system.putInSpecialOffer("Raclette", 15);
		Meal raclette = system.getMeal("Raclette");
		Assert.assertTrue(raclette.isSpecial());
	
	}
	@Test
	public void testRemoveFromSpecialOffer(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		system.putInSpecialOffer("Raclette", 15);
		system.removeFromSpecialOffer("Raclette");
		
		Meal raclette = system.getMeal("Raclette");
		Assert.assertTrue(!raclette.isSpecial());
	}
	@Test
	public void testInsertOffer(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.insertOffer("Raclette", 15);
		Meal raclette = system.getCurrentMeal();
		
		Assert.assertTrue(raclette.isOnlySpecial());
	}
	@Test
	public void testNotifyAdClientWhoAgrees(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred2", "123456", UserRole.Client);
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);
		
		system.login("bobred2", "123456");
		system.associateAgreement("bobred2","SpecialOffer",true);
		
		system.login("bobred", "123456");
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();	
		
		system.notifyAd("The best Raclette in the world !","Raclette",15);
		
		system.login("bobred2", "123456");
		Client bob = (Client) system.getCurrentUser();
		assertEquals("Bob Red's notification wall : "+System.lineSeparator() +"There is a new special offer ! Enjoy Your Meal offers you "
				+ "a tasty discount on the meal : " + "Raclette" + ". " + "The best Raclette in the world !"
				, bob.getNotificationWall());
	}
	@Test
	public void testNotifyAdClientWhoDisagrees(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred2", "123456", UserRole.Client);
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);
		
		system.login("bobred2", "123456");
		system.associateAgreement("bobred2","SpecialOffer",false);
		
		system.login("bobred", "123456");
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();	
		
		system.notifyAd("The best Raclette in the world !","Raclette",15);
		
		system.login("bobred2", "123456");
		Client bob = (Client) system.getCurrentUser();
		assertEquals("Bob Red's notification wall : "
				, bob.getNotificationWall());
	}
	
	@Test
	public void testNotifyBirthday() throws ParseException{
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred2", "123456", UserRole.Client);
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);
		
		system.login("bobred2", "123456");
		system.addBirthday("10/04/1994");
		system.login("bobred", "123456");
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();	
		
		system.setDate("10/04/2016");
		system.notifyBirthday();
		
		system.login("bobred2", "123456");
		Client bob = (Client) system.getCurrentUser();
		assertEquals("Bob Red's notification wall : "+System.lineSeparator() + "It's your birthday ! Enjoy Your Meal offers you "
				+ "a tasty discount on your next order !"
				, bob.getNotificationWall());
		
	}
}