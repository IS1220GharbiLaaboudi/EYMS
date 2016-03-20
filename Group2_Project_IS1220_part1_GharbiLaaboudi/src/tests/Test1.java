package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import classes.CardType;
import classes.Client;
import classes.EYMS;
import classes.Meal;

public class Test1 {

	@Test
	public void UserNotFound() {
		EYMS.login("bobred","123456");
	}
	
	@Test
	public void UserFoundButWrongPassword() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob); // choix � faire ( mettre register(bob) ou register("bob", "red", ....) )
		// agreement etc � mettre en place s�parement (valeurs � choisir par d�faut) -> plusieurs m�thodes
		EYMS.login("bobred","123456789");
		// comment v�rifer que le test marche ??
	}
	
	@Test
	public void UserFoundButWrongPassword2() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob);
		EYMS.login("bobred",123456);
	}
	
	@Test
	public void UserFoundAndRightPassword() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob);
		EYMS.login("bobred","123456");
	}
	
	@Test
	public void newMeal() {
		Meal raclette = new Meal();
		raclette.addIngredient("cheese", "90g");
		raclette.addIngredient("ham", "40g");
		raclette.addIngredient("potatoes", "50g"); //cr�er une classe Ingr�dients ???
		
		raclette.getListIngredients();
		
		Database.saveMeal(raclette);
	}
	
	
	@Test
	public void addContactInfo() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob);
		Database.addContactInfo(bob, "email : bobred@gmail.com"); //ajouter fonction addContactInfo?
		Database.addContactInfo(bob, "phone : 0650655047");
	}
	
	@Test
	public void associateCard() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob);
		Database.associateCard(bob, CardType.Basic); // choix � justifier dans le rapport
	}
	
	@Test
	public void associateCard() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob);
		Database.associateCard(bob, CardType.Basic); 
	}
	
	@Test
	public void clientAgreement{
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob);
		Database.associateAgreement(bob, AgreementType.No);
	}
	
	@Test
	public void clientAgreement{
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		EYMS.register(bob);
		Database.associateAgreement(bob, AgreementType.No);
	}

	@Test
	public void orderRaclette{
	}
	
	@Test
	public void personalizeRacletteAddCheese{
	}
	
	@Test
	public void personalizeRacletteRemoveCheese{
	}
	
	@Test
	public void order2RacletteAndSaveOrder{
	}
	
	@Test
	public void notifyBirthday{
		Assert.assertArrayEquals(expecteds, actuals); // � quel moment on ajoute les dates d'anniversaire
	}
	
	@Test
	public void racletteInSpecialOffer{
	}
	
	@Test
	public void racletteInSpecialOfferAndNotifyUsers{
	}
	

}
