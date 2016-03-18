/**
 * 
 */
package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import classes.*;
import enums.UserRole;
import interfaces.Offer;

/**
 * @author Fouad-Sams
 *
 */
public class SystemTest {
	
	public void testRegisterClient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);
		Client bob1 = system.getUser("bobred");
		
		Client bob2 = new Client("Bob", "Red", "bobred", "123456");
		
		Assert.assertEquals(bob2, bob1);
		
		
	}

	public void testLoginClient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		Client bob1 = system.getCurrentUser();
		
		Client bob2 = new Client("Bob", "Red", "bobred", "123456");
		
		Assert.assertEquals(bob2, bob1);
	}
	
	public void testRegisterChef(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);
		User bob1 = system.getUser("bobred");
		
		User bob2 = new User("Bob", "Red", "bobred", "123456", UserRole.Chef);
		
		Assert.assertEquals(bob2, bob1);
		
		
	}

	public void testLoginChef(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		User bob2 = system.getCurrentUser();
		
		User bob2 = new User("Bob", "Red", "bobred", "123456", UserRole.Chef);
	}
	
	public void testRegisterSameUsername(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);
		system.register("Bernard", "Rouge", "bobred", "123456", UserRole.Client);
		system.login("bobred", "123456");
		User bob = system.getCurrentUser();
		
		Assert.assertEquals("Bob", bob.getFirstName());
	}
	
	
	
	public void testPersonalizeMeal(){}
	
	public void testSaveOrder(){
	}
	
	public void addInfo(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		system.addInfo("email", "bobred@gmail.com");
		
		Client bob = system.getCurrentUser();
		String mailAdress = bob.getContactInfo("email");
		Assert.assertEquals("bobred@gmail.com", mailAdress);
	}
	
	public void testAssociateCard(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		system.associateCard("Lottery");
		
		Client bob = system.getCurrentUser();
		Offer card = bob.getCard();
		Assert.assertTrue(card instanceof LotteryFidelityCard);
	}
	
	public void associateAgreement(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		system.associateAgreement(); // set specialoffer agreement to false
		
		Client bob = system.getCurrentUser();
		boolean agree = bob.getAgreement("SpecialOffer");
		Assert.assertTrue(!agree);
	}
	
	public void testShowMealWithoutLoggingIn(){
		EYMS system = new EYMS();
		system.ShowMeal();
	}
	public void createMealClient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		Meal raclette = system.currentMeal();
		Assert.assertTrue(raclette.getName() == "Raclette");
	
	}
	
	public void createMealChef(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		Meal raclette = system.currentMeal();
		Assert.assertTrue(raclette.getName() == "Raclette");
	
	}
	
	public void testShowMealWhileLoggedIn(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		system.ShowMeal();
	}
	
	public void testAddIngredient(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.showMeal();
	}
	
	public void testListIngredients(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
	
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.listIngredient();
	}
	

	public void testSaveMeal(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
	
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
	}
	
	public void testPutInSpecialOffer(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		System.PutInSpecialOffer("Raclette", 15);
		Meal raclette = system.currentMeal();
		Assert.assertTrue(raclette.isSpecial());
	
	}
	
	public void testRemoveFromSpecialOffer(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.createMeal("Raclette", 17);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		System.PutInSpecialOffer("Raclette", 15);
		system.removeFromSpecialOffer("Raclette");
		
		Meal raclette = system.currentMeal();
		Assert.assertTrue(!raclette.isSpecial());
	}
	
	public void testInsertOffer(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.InsertOffer("Raclette", 15);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		Meal raclette = system.currentMeal();
		Assert.assertTrue(raclette.isSpecial());
		
		
		
	}
	
	public void testInsertOffer2(){
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
		system.login("bobred", "123456");
		
		system.InsertOffer("Raclette", 15);
		system.addIngredient("Cheese", "50g");
		system.saveMeal();
		
		system.removeFromSpecialOffer("Raclette"); //ça devrait pas marcher ? : Insert offer -> plat special		
		Meal raclette = system.currentMeal();
		Assert.assertTrue(raclette.isSpecial());
		
		
		
	}
	
	public void testNotifyAdClientWhoAgrees(){EYMS system = new EYMS();
	system.register("Bob", "Red", "bobred2", "123456", UserRole.Client);
	system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);		
	system.login("bobred", "123456");
	
	system.createMeal("Raclette", 17);
	system.addIngredient("Cheese", "50g");
	system.saveMeal();
	
	System.PutInSpecialOffer("Raclette", 15);
	
	system.notifyAd();
	system.login("bobred2", "123456");
	Client bob = system.currentUser();
	System.out.println(bob.getNotificationWall());
	
	
	}
	
	public void testNotifyAdClientWhoDisagrees(){EYMS system = new EYMS();
	system.register("Bob", "Red", "bobred2", "123456", UserRole.Client);
	system.register("Bob", "Red", "bobred", "123456", UserRole.Chef);
	system.login("bobred2", "123456");
	system.associateAgreement();
	system.login("bobred", "123456");
	
	system.createMeal("Raclette", 17);
	system.addIngredient("Cheese", "50g");
	system.saveMeal();
	
	System.PutInSpecialOffer("Raclette", 15);
	
	system.notifyAd();
	 //Mettre non pour special offers
	system.login("bobred2", "123456");
	Client bob = system.currentUser();
	System.out.println(bob.getNotificationWall());
	
	
	}
	
	public void testNotifyBirthday(){}
	// je sais pas comment on va mettre en place les dates
}
