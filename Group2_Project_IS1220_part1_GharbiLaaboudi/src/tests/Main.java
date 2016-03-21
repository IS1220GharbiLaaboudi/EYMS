package tests;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;

import classes.Client;
import classes.EYMS;
import classes.Meal;
import classes.User;
import enums.UserRole;

public class Main {

	public static void main(String[] args) {
		Pattern pattern = Pattern.compile("^0[^1-9]*$");
		Matcher matcher = pattern.matcher("0.0g");
		
		if(matcher.matches()){ // if quantity given is 0, remove the ingredient
			System.out.println("abc");
		}
	}

}
