package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import classes.EYMS;
import enums.UserRole;


public class Main {

	public static void main(String[] args) {
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobre", "123456", UserRole.Chef);		
		system.login("bobre", "123456");
		system.createMeal("Raclette", 17);
		system.saveMeal();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");

		system.selectMeal("Raclette", 3);
		system.saveOrder();
		//System.out.println(t);
	}

}
