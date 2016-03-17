package tests;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Test;

import classes.CardType;
import classes.Client;
import classes.System;
import classes.Meal;

public class Test1 {

	@Test
	public void UserNotFound() {
		System.login("bobred","123456");
	}
	
	@Test
	public void UserFoundButWrongPassword() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob); // choix à faire ( mettre register(bob) ou register("bob", "red", ....) )
		// agreement etc à mettre en place séparement (valeurs à choisir par défaut) -> plusieurs méthodes
		System.login("bobred","123456789");
		// comment vérifer que le test marche ??
	}
	
	@Test
	public void UserFoundButWrongPassword2() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob);
		System.login("bobred",123456);
	}
	
	@Test
	public void UserFoundAndRightPassword() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob);
		System.login("bobred","123456789");
	}
	
	@Test
	public void newMeal() {
		Meal raclette = new Meal();
		raclette.addIngredient("cheese", "90g");
		raclette.addIngredient("ham", "40g");
		raclette.addIngredient("potatoes", "50g"); //créer une classe Ingrédients ???
		
		raclette.getListIngredients();
		
		Database.saveMeal(raclette);
	}
	
	
	@Test
	public void addContactInfo() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob);
		Database.addContactInfo(bob, "email : bobred@gmail.com"); //ajouter fonction addContactInfo?
		Database.addContactInfo(bob, "phone : 0650655047");
	}
	
	@Test
	public void associateCard() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob);
		Database.associateCard(bob, CardType.Basic); // choix à justifier dans le rapport
	}
	
	@Test
	public void associateCard() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob);
		Database.associateCard(bob, CardType.Basic); 
	}
	
	@Test
	public void clientAgreement{
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob);
		Database.associateAgreement(bob, AgreementType.No);
	}
	
	@Test
	public void clientAgreement{
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		System.register(bob);
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
		Assert.assertArrayEquals(expecteds, actuals); // à quel moment on ajoute les dates d'anniversaire
	}
	
	@Test
	public void racletteInSpecialOffer{
	}
	
	@Test
	public void racletteInSpecialOfferAndNotifyUsers{
	}
	

}
