/**
 * 
 */
package tests;

import java.util.Date;

import org.junit.Assert;
import org.junit.Test;

import classes.Client;
import classes.Meal;
import classes.Order;


/**
 *
 *
 */
public class OrderTests {

	@Test
	public void getNormalPriceTest() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		Date date = new Date();
		
		
		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g"); // personalizeMeal permet aussi de rajouter des ingredients
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); // car c'est le plat original et pas un plat modifié
		
		Meal burger = new Meal("Burger", 7);
		burger.personalizeMeal("cheese", "110g"); // personalizeMeal permet aussi de rajouter des ingredients
		burger.personalizeMeal("onion", "20g");
		burger.personalizeMeal("meat", "150g");
		burger.setModified(false); // car c'est le plat original et pas un plat modifié
		
		Meal kebab = new Meal("Kebab", 3.50);
		kebab.personalizeMeal("tomato", "50g");
		kebab.personalizeMeal("oninon", "30g");
		kebab.personalizeMeal("salad", "10g");
		kebab.personalizeMeal("samurai sauce", "5.5g");
		kebab.personalizeMeal("kebab meat", "125g");
		
		Order order = new Order(bob,date);
		order.setNumberOfMeal(raclette, 3);
		order.setNumberOfMeal(burger, 4);
		order.setNumberOfMeal(kebab, 5);
		
		Assert.assertTrue((order.getNormalPrice() == 3*17+7*4+3.5*5));	
		
	}


	@Test
	public void getNormalPriceTest2() {
		Client bob = new Client("Bob", "Red", "bobred", "123456");
		Date date = new Date();


		Meal raclette = new Meal("Raclette", 17);
		raclette.personalizeMeal("cheese", "90g"); // personalizeMeal permet aussi de rajouter des ingredients
		raclette.personalizeMeal("ham", "40g");
		raclette.personalizeMeal("potatoes", "50g");
		raclette.setModified(false); // car c'est le plat original et pas un plat modifié

		Meal burger = new Meal("Burger", 7);
		burger.personalizeMeal("cheese", "110g"); // personalizeMeal permet aussi de rajouter des ingredients
		burger.personalizeMeal("onion", "20g");
		burger.personalizeMeal("meat", "150g");
		burger.setModified(false); // car c'est le plat original et pas un plat modifié

		Meal kebab = new Meal("Kebab", 3.50);
		kebab.personalizeMeal("tomato", "50g");
		kebab.personalizeMeal("oninon", "30g");
		kebab.personalizeMeal("salad", "10g");
		kebab.personalizeMeal("samurai sauce", "5.5g");
		kebab.personalizeMeal("kebab meat", "125g");

		Order order = new Order(bob,date);
		order.setNumberOfMeal(raclette, 1);
		order.setNumberOfMeal(burger, 0);
		order.setNumberOfMeal(kebab, 1);

		Assert.assertTrue((order.getNormalPrice() == 17+3.5));
	}

}
