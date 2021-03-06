/**
 * 
 */
package classes;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;

import enums.FidelityCard;
import enums.OrderingCriteria;
import enums.UserRole;

/**
 * This class handles the Command Line User Interface of the Enjoy Your Meal System.
 * 
 * @author Achraf Gharbi
 * 
 * @author Younes Laaboudi
 *
 */
public class CLUI {

	/**
	 * This method consists of a loop that displays a "header" with the information on the current user and the object
	 * he is manipulating (Meal or Order). Then at any time, we can enter a command as a input. If the command has an 
	 * argument or arguments, there has to be a space between the command and the bracket before the first argument.
	 * 
	 * The methods related to each command are executed from the EYMS class. This is requirement of the responsibility
	 * driven development : this class only handles the interaction with the user.
	 * 
	 * @param args the arguments here don't have any influence on the way the static method will be executed.
	 */
	public static void main(String[] args) {
		/*
		 * Initialization of the variables
		 */
		Scanner ui = new Scanner(System.in);
		EYMS system = new EYMS();
		boolean exit = false;
		String command = "";
		ArrayList <String> arguments = new ArrayList<String>();
		boolean readFromFile = false;
		ArrayList<String> commandsList= new ArrayList<String>();
		
		
		/*
		 * Welcome display
		 */
		System.out.println("*****************");
		System.out.println("Welcome on EYMS !");
		System.out.println("*****************");
		while(!exit){
			/*
			 * Information display
			 */
			
			if(system.getCurrentUser() != null){
				User user = system.getCurrentUser();
				String role;
				if(user.getRole() == UserRole.Chef)
					role = "Chef";
				else
					role = "Client";
				
				System.out.println("Hello "+user.getFirstName()+" !");
				System.out.println("-------------------------");
				System.out.println(user.getFirstName() + " " + user.getSurName() + " ( " + role + " )");
				System.out.println("-------------------------");
				if(user.getRole() == UserRole.Client){
					Client client = (Client) user;
					System.out.println("Latest notifications : " + client.getNotificationWall());
					System.out.println("-------------------------");
					System.out.println("Contact info : "+ client.getFavoriteContactInfo());
					if(system.getCurrentOrder() != null){
						System.out.println("-------------------------");
						System.out.println("Current order : " + system.getCurrentOrder());
					}
				}
				else if(user.getRole() == UserRole.Chef){
					if(system.getCurrentMeal() != null) {
						System.out.println("Current meal : "+system.getCurrentMeal());
						
					} else {
						System.out.println("No current meal you're working on.");
					}
				}
				System.out.println("-------------------------");
			} else {
				System.out.println("You're not logged in. You can login or register with the proper commands. Type h for the command list.");
			}

			System.out.println("Press < h > for help or < q > to exit the system.");
			
			/*
			 * First login management 
			 */
			
			if(system.getCurrentUser() != null && system.getCurrentUser().getRole() == UserRole.Client){
				Client client = (Client) system.getCurrentUser();
				// if no contact info
				if(client.getFavoriteContactInfo() == null){
					System.out.println("You dont have any favorite information contact. Please insert your contact information like so :");
					System.out.println("typeOfContact, value");
					System.out.println("Example : email, johnred@eyms.org");
					boolean t = false;
					while(!t){
						String s;
						if (readFromFile){
							s = commandsList.remove(0);
							System.out.println("-- Executing command : " + s);
							readFromFile = !commandsList.isEmpty();
						}else{
							s = ui.nextLine(); 
						}
						String[] sarray = s.split(", |,| , | ,",2);
						t = system.addInfo(sarray[0], sarray[1], true);
						if(!t)
							System.out.println("Please follow the example given.");
					}
					System.out.println("Thank you for your cooperation.");
				}
				// if no birthday
				if(client.getBirthdayDate() == null){
					System.out.println("You did not specify your birthday date yet. Please enter it following this format");
					System.out.println("DD/MM/YYYY");
					boolean t = false;
					while(!t){
						String s;
						if (readFromFile){
							s = commandsList.remove(0);
							System.out.println("-- Executing command : " + s);
							readFromFile = !commandsList.isEmpty();
						}else{
							s = ui.nextLine();
						}
						try {
							t = system.addBirthday(s);
						} catch (ParseException e) {
							t = false;
						}
						if(!t){
							System.out.println("Please respect the format given.");
						}
						else{
							System.out.println("Thank you for your cooperation.");
						}
					}
				}
			}
			
			/*
			 * Input management
			 */
			String s;
			if (readFromFile){
				s = commandsList.remove(0);
				System.out.println("-- Executing command : " + s);
				readFromFile = !commandsList.isEmpty();
			}else{
				s = ui.nextLine(); // like command (arg1, arg2, ...)

			}
			//--parsing the input

			String[] sarray = s.split(" ", 2);
			command = sarray[0];
			arguments = new ArrayList<String>(); // we need to reinitialize it otherwise the same arguments will be called
			if (sarray.length > 1 ){
	            String argums = sarray[1].substring(1, sarray[1].length() - 1);
				String[] argarray = argums.split(" , | ,|, |,"); //the order of the delimiters is important
				for(String m : argarray){
					arguments.add(m);
				}
			}
			
			
			/*
			 * Beginning of commands
			 */
			//exit command
			if (command.equalsIgnoreCase("q"))
				exit = true;
			//help command
			else if (command.equalsIgnoreCase("h")){ //print different commands
				System.out.println("login (username, password) :  to perform the login");
				System.out.println("createMeal (mealName, price):  to create a mail with a given name ");
				System.out.println("addIngredient (ingredientName, quantity):  to add an ingredient to the current meal ");
				System.out.println("currentMeal ():  to show the name of the under construction meal ");
				System.out.println("saveMeal ():  to save the current meal ");
				System.out.println("selectMeal (mealName, quantity):  to add quantity meals of a given name to an order"); 
				System.out.println("personalizeMeal (mealName,ingredient,quantity): to add/remove an ingredient from a meal ");
				System.out.println("putInSpecialOffer (mealName, price):  to add a meal in a special offer ");
				System.out.println("removeFromSpecialOffer (mealName):  to reset a special offer ");
				System.out.println("listIngredients (mealName):  shows all the ingredients of a meal ");
				System.out.println("saveOrder ():  to save the current order ");
				System.out.println("insertOffer (mealName,newPrice): to insert a meal in a special offer policy ");
				System.out.println("registerClient (firstName, lastName, username, password): to add a user to the system ");
				System.out.println("addContactInfo(contactInfoType, value, favoriteContactInfo): to add a contact info to the current user ");
				System.out.println("associateCard (userName,cardType): to associate a  delity card to a user ");
				System.out.println("associateAgreement (username,agreement): to associate an agreement to a user (Possibilities : YesToAll, SpecialOffersOnly, BirthayOffersOnly or NotoAll) ");
				System.out.println("insertChef (firstName, lastName, username, password): to add a chef to the system ");
				System.out.println("notifyAd (message, mealName, specialPrice): send a message to alert the users about an offer ");
				System.out.println("notifyBirthday (): send a special offer to the users that celebrate their birthday ");
				System.out.println("showMeal (orderingCriteria): to see the list of the meals ordered according to a certain criteria. If there is no ordering criteria, ");
				System.out.println("importFile (fileName): imports the commands of a given text file (for exemple fileName = eval_1) ");
				System.out.println("setDate (YYYY/MM/DD): sets the date of the system ");

			}
			//login
			else if (command.equalsIgnoreCase("login") && arguments.size() == 2){
				boolean t = system.login(arguments.get(0), arguments.get(1));
				if(t)
					System.out.println("You've been correctly connected.");
				else
					System.out.println("Incorrect username or password given.");
			}
			//createMeal
			else if (command.equalsIgnoreCase("createmeal") && arguments.size() == 2 ){
				double price = Double.parseDouble(arguments.get(1)); 
				boolean t = system.createMeal(arguments.get(0), price);			
				
				if(t)
					System.out.println("Meal correctly added");
				else
					System.out.println("Current user is not a chef to create a meal.");
			}
			//addIngredient
			else if (command.equalsIgnoreCase("addingredient")  && arguments.size() == 2){
				boolean t = system.addIngredient(arguments.get(0), arguments.get(1));			
				if(t)
					System.out.println("Ingredient correctly added to the current meal.");
				else
					System.out.println("Verify that you added a non zero quantity of ingredient and that you created a current meal.");
			}
			//currentMeal
			else if(command.equalsIgnoreCase("currentMeal") && arguments.size() ==1 && arguments.get(0).length() == 0){
				if(system.getCurrentMeal() == null)
					System.out.println("No current meal.");
				else
					System.out.println(system.getCurrentMeal());
			}
			//saveMeal
			else if(command.equalsIgnoreCase("saveMeal")  && arguments.size() ==1 && arguments.get(0).length() == 0){
				boolean t = system.saveMeal();
				if(t)
					System.out.println("Current meal was well added to the meals list");
				else
					System.out.println("Cannot save meal. Current user is not a chef or no current meal.");
			}
			//selectMeal
			else if(command.equalsIgnoreCase("selectmeal")  && arguments.size() == 2){
				int quantity = Integer.parseInt(arguments.get(1));
				boolean t = system.selectMeal(arguments.get(0), quantity);		
				
				if(t && quantity != 0)
					System.out.println("Current meal was well added to the meals list");
				else
					System.out.println("Cannot select meal. Current user is not a client or meal doesn't exist.");
			}
			//personalizeMeal
			else if(command.equalsIgnoreCase("personalizemeal")  && arguments.size() == 3){
				boolean t = system.personalizeMeal(arguments.get(0), arguments.get(1), arguments.get(2));	
				if(t)
					System.out.println("The meal was correctly personalized");
				else
					System.out.println("Meal not known.");
			}
			//putInSpecialOffer
			else if(command.equalsIgnoreCase("putinspecialoffer")  && arguments.size() == 2){
				double price = Double.parseDouble(arguments.get(1));
				
				boolean t = system.putInSpecialOffer(arguments.get(0), price);
		
				if(t)
					System.out.println("The selected meal has now a special offer.");
				else
					System.out.println("Cannot put meal in special offer. Meal is unknown or new price is equal to the normal price. Verify that current user is a chef.");
			}
			//removeFromSpecialOffer
			else if(command.equalsIgnoreCase("removefromspecialoffer")  && arguments.size() == 1){
				boolean t = system.removeFromSpecialOffer(arguments.get(0));

				if(t)
					System.out.println("The selected meal isn't in special offer anymore.");
				else
					System.out.println("Cannot remove meal from special offer. Meal is unknown or current user is not a chef.");
			}
			//listIngredients
			else if(command.equalsIgnoreCase("listIngredients")  && arguments.size() == 1){				
				Map<String, String> myMap = system.listIngredients(arguments.get(0));
				if(myMap != null){
					System.out.println(arguments.get(0)+" is composed of : ");
				
					for(String ingr : myMap.keySet()){
						System.out.println("\t > "+myMap.get(ingr) + " of " + ingr);
					}
				} else
					System.out.println("Meal not known.");
			}
			//saveOrder
			else if(command.equalsIgnoreCase("saveorder")  && arguments.size() ==1 && arguments.get(0).length() == 0){
				boolean t = system.saveOrder();
				if(t)
					System.out.println("Enjoy your meal !");
				else
					System.out.println("Cannot save the order.");
			}
			//insertOffer
			else if(command.equalsIgnoreCase("insertoffer") && arguments.size() == 2 ){
				double price = Double.parseDouble(arguments.get(1));
				boolean t = system.insertOffer(arguments.get(0), price);
				if(t)
					System.out.println("New special offer meal created. Please add ingredients with the proper command.");
				else
					System.out.println("An error occured : Please check that you're connected as a chef, and that the meal does not already exist (if so use the command putInSpecialOffer). ");
			}
			//registerClient
			else if(command.equalsIgnoreCase("registerClient")  && arguments.size() == 4){
				boolean t = system.register(arguments.get(0),arguments.get(1),arguments.get(2),arguments.get(3), UserRole.Client);
				if(t)
					System.out.println("Your account was correctly added. You can login now with the proper command.");
				else
					System.out.println("An error occured. The username maybe already taken. Please check that you did not specify empty arguments");
			}
			//addContactInfo
			else if(command.equalsIgnoreCase("addcontactinfo")  && arguments.size() == 3){
				boolean bool = false;
				
				if(arguments.get(2).equalsIgnoreCase("yes"))
					bool = true;
				else
					bool = false;
				
				boolean t = system.addInfo(arguments.get(0), arguments.get(1), bool);
				if(t)
					System.out.println("The contact info you provided was correctly added.");
				else
					System.out.println("An error occured. Please make sure that you're connected as a client or that you did not provide empty arguments.");
			}
			//associateCard
			else if(command.equalsIgnoreCase("associatecard")  && arguments.size() == 2){
				boolean t = false;
				User user = system.getUser(arguments.get(0));
				if(arguments.get(1).equalsIgnoreCase("basic"))
					t = system.associateCard(user, FidelityCard.Basic);
				else if(arguments.get(1).equalsIgnoreCase("point"))
					t = system.associateCard(user, FidelityCard.Point);
				else if(arguments.get(1).equalsIgnoreCase("lottery"))
					t = system.associateCard(user, FidelityCard.Lottery);
				
				if(t)
					System.out.println("The new card has successfly been associated to your account");
				else
					System.out.println("A problem occured. Please make sure that your argument was : basic, point or lottery.");					
			}
			//associateAgreement
			else if(command.equalsIgnoreCase("associateagreement")  && arguments.size() == 2){
				boolean t= false;
				switch (arguments.get(1).toLowerCase()) {
				case "yestoall":
					t = system.associateAgreement(arguments.get(0), "BirthdayOffer", true)
						&& system.associateAgreement(arguments.get(0), "SpecialOffer", true);
					break;
				case "notoall":
					t = system.associateAgreement(arguments.get(0), "BirthdayOffer", false)
						&& system.associateAgreement(arguments.get(0), "SpecialOffer", false);
					break;
				case "birthdayoffersonly":
					t = system.associateAgreement(arguments.get(0), "BirthdayOffer", true)
						&& system.associateAgreement(arguments.get(0), "SpecialOffer", false);
					break;
				case "specialoffersonly":
					t = system.associateAgreement(arguments.get(0), "BirthdayOffer", false)
						&& system.associateAgreement(arguments.get(0), "SpecialOffer", true);
					break;
				}
				if(t)
					System.out.println("The agreement was correctly associated to the specified client account.");
				else
					System.out.println("A problem occured. Please check that the user exists and that the specified arguments are not empty");
			}
			//insertChef
			else if(command.equalsIgnoreCase("insertchef")  && arguments.size() == 4){
				boolean t = system.register(arguments.get(0),arguments.get(1),arguments.get(2),arguments.get(3), UserRole.Chef);
				if(t)
					System.out.println("Your account was correctly added. You can login now with the proper command.");
				else
					System.out.println("An error occured. The username is maybe already taken. Please check that you did not specify empty arguments");
			}
			//notifyAd
			else if(command.equalsIgnoreCase("notifyAd")  && arguments.size() == 3){
				double price = Double.parseDouble(arguments.get(2));
				boolean t = system.notifyAd(arguments.get(0), arguments.get(1), price);
				if(t)
					System.out.println("Notification sent to all the users");
				else
					System.out.println("An error occured, please check that you're logged in as a chef and the meal was already created.");
			}
			//notifyBirthday
			else if(command.equalsIgnoreCase("notifyBirthday") && arguments.size() ==1 && arguments.get(0).length() == 0){
				boolean t = system.notifyBirthday();
				if(t)
					System.out.println("Birthday notifications correctly sent.");
				else
					System.out.println("Please verify that you are logged in as a chef.");
			}
			//showMeal
			else if(command.equalsIgnoreCase("showmeal") && arguments.size() == 1){
				OrderingCriteria oc = null;
				if(arguments.get(0).equalsIgnoreCase("JustOnSale"))
					oc = OrderingCriteria.JustOnSale;
				else if(arguments.get(0).equalsIgnoreCase("MostlyModified"))
					oc = OrderingCriteria.MostlyModified;
				else if(arguments.get(0).equalsIgnoreCase("AsItIs"))
					oc = OrderingCriteria.AsItIs;

				for (Meal meal : system.showMeal(oc)){
					System.out.println(meal);
				}
			}
			// importfile
			else if(command.equalsIgnoreCase("importfile") && arguments.size() == 1){
				try{
					commandsList.addAll(0, CLUI.importfile(arguments.get(0))); // in case a command from the file is importfile
					readFromFile = !commandsList.isEmpty();
				}catch(IOException e){
					System.out.println("Unable to import file");
				}
				System.out.println("The file have been successfully imported");
			} // setdate
			else if(command.equalsIgnoreCase("setdate") && arguments.size() == 1){
				try {
					system.setDate(arguments.get(0));
					System.out.println("The date has been successfully changed");
				} catch (ParseException e) {
					System.out.println("The date could not be changed. Please respect the format given.");
				}
				
			}
			//other
			else{
				System.out.println("Unknown command, type h for the list of the commands. Don't forget to put a space between the command and the arguments");
			}
			
			//Skip lines in order to have more visibility on the effect of each command
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
			System.out.println();
		}
		
		ui.close();

	}
	/**
	 * 
	 * This methods enables the user to read a file located in the eval directory. 
	 * 
	 * @param fileName The name of the file containing the commands, without its extension (for example : eval_1)
	 * @return an array of the lines contained in the file (/!\ blank lines and comment lines are also in this array)
	 * @throws IOException
	 */
	private static ArrayList<String> importfile(String fileName) throws IOException {
		ArrayList<String> commandsList = new ArrayList<String>();
		FileReader file = null;
		BufferedReader reader = null;
		file = new FileReader("eval\\"+fileName+".txt");
			
		reader = new BufferedReader(file);
		String line = "";
			
		while((line = reader.readLine()) != null){
			if (line.length() != 0 && !line.startsWith("//")){
			commandsList.add(line);
			}
		}
		
		reader.close(); 
		
		return commandsList ;
	}

}
