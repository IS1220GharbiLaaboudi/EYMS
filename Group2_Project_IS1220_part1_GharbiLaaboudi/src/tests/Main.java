package tests;

import org.junit.Assert;

import classes.Client;
import classes.EYMS;
import classes.User;
import enums.UserRole;

public class Main {

	public static void main(String[] args) {
		EYMS system = new EYMS();
		system.register("Bob", "Red", "bobred", "123456", UserRole.Client);		
		system.login("bobred", "123456");
		User bob1 = system.getCurrentUser();
		User bob2 = new Client("Bob", "Red", "bobred", "123456");
		System.out.println(system.getCurrentUser());
		System.out.println(bob2);
		//Assert.assertEquals(bob2, bob1);
	}

}
