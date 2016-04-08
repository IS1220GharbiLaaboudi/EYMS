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
 * @author Achraf Gharbi
 * @author Younes Laaboudi
 *
 */
public class CLUI {

	/**
	 * @param args
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
					System.out.println("Last notification : " + client.getNotificationWall());
					System.out.println("-------------------------");
					System.out.println("Contact info : "+ client.getFavoriteContactInfo());
					if(system.getCurrentOrder() != null){
						System.out.println("-------------------------");
						System.out.println("Current order : " + system.getCurrentOrder());
					}
				}
				else if(user.getRole() == UserRole.Chef){
					if(system.getCurrentMeal() != null) {
						System.out.println("Current meal : "+system.currentMeal());
						
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
					System.out.println("YYYY/MM/DD");
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
			//parsing the input
			String[] sarray = s.split(" ",2);
			command = sarray[0];
			arguments = new ArrayList<String>(); // we need to reinitialize it otherwise the same arguments will be called
			if (sarray.length > 1){
				String argums = sarray[1];
				String[] argarray = argums.split(" ");
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
				System.out.println("associateAgreement (username,agreement): to associate an agreement to a user ");
				System.out.println("insertChef (firstName, lastName, username, password): to add a chef to the system ");
				System.out.println("notifyAd (message, mealName, specialPrice): send a message to alert the users about an offer ");
				System.out.println("notifyBirthday (): send a special offer to the users that celebrate their birthday ");
				System.out.println("showMeal (orderingCriteria): to see the list of the meals ordered according to a certain criteria ");
				System.out.println("importFile (fileName): imports the commands of a given text file (for exemple fileName = eval_1) ");

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
			else if (command.equalsIgnoreCase("createmeal") && arguments.size() > 1 ){
	
				double price = Double.parseDouble(arguments.get(arguments.size()-1)); 
				String mealName = "";
				for (int i = 0; i< arguments.size()-1; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 2){
						mealName += " ";
					}
				}
				boolean t = system.createMeal(mealName, price);			
				
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
			else if(command.equalsIgnoreCase("currentMeal")  && arguments.size() == 0){
				if(system.getCurrentMeal() == null)
					System.out.println("No current meal.");
				else
					System.out.println(system.getCurrentMeal());
			}
			//saveMeal
			else if(command.equalsIgnoreCase("saveMeal")  && arguments.size() == 0){
				boolean t = system.saveMeal();
				if(t)
					System.out.println("Current meal was well added to the meals list");
				else
					System.out.println("Cannot save meal. Current user is not a chef or no current meal.");
			}
			//selectMeal
			else if(command.equalsIgnoreCase("selectmeal")  && arguments.size() > 1){
				int quantity = Integer.parseInt(arguments.get(arguments.size()-1));
				String mealName = "";
				for (int i = 0; i< arguments.size()-1; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 2){
						mealName += " ";
					}
				}
				boolean t = system.selectMeal(mealName, quantity);		
				
				if(t && quantity != 0)
					System.out.println("Current meal was well added to the meals list");
				else
					System.out.println("Cannot save meal. Current user is not a chef or no current meal.");
			}
			//personalizeMeal
			else if(command.equalsIgnoreCase("personalizemeal")  && arguments.size() > 2){
				String mealName = "";
				for (int i = 0; i< arguments.size()-2; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 3){
						mealName += " ";
					}
				}
				boolean t = system.personalizeMeal(mealName, arguments.get(arguments.size()-2), arguments.get(arguments.size()-1));	
				if(t)
					System.out.println("The meal was correctly personalized");
				else
					System.out.println("Meal not known.");
			}
			//putInSpecialOffer
			else if(command.equalsIgnoreCase("putinspecialoffer")  && arguments.size() > 1){
				double price = Double.parseDouble(arguments.get(arguments.size()-1));
				String mealName = "";
				for (int i = 0; i< arguments.size()-1; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 2){
						mealName += " ";
					}
				}
				boolean t = system.putInSpecialOffer(mealName, price);
		
				if(t)
					System.out.println("The selected meal has now a special offer.");
				else
					System.out.println("Cannot put meal in special offer. Meal is unknown or new price is equal to the normal price. Verify that current user is a chef.");
			}
			//removeFromSpecialOffer
			else if(command.equalsIgnoreCase("removefromspecialoffer")  && arguments.size() > 0){
				String mealName = "";
				for (int i = 0; i< arguments.size()-1; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 2){
						mealName += " ";
					}
				}
				boolean t = system.removeFromSpecialOffer(mealName);

				if(t)
					System.out.println("The selected meal is now not in special offer.");
				else
					System.out.println("Cannot remove meal from special offer. Meal is unknown or current user is not a chef.");
			}
			//listIngredients
			else if(command.equalsIgnoreCase("listIngredients")  && arguments.size() > 0){				
				String mealName = "";
				for (int i = 0; i< arguments.size()-1; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 2){
						mealName += " ";
					}
				}
				Map<String, String> myMap = system.listIngredients(mealName);
				if(myMap != null){
					System.out.println(arguments.get(0)+" is composed of : ");
				
					for(String ingr : myMap.keySet()){
						System.out.println("\t > "+myMap.get(ingr) + " of " + ingr);
					}
				} else
					System.out.println("Meal not known.");
			}
			//saveOrder
			else if(command.equalsIgnoreCase("saveorder")  && arguments.size() == 0){
				boolean t = system.saveOrder();
				if(t)
					System.out.println("The selected meal is now not in special offer.");
				else
					System.out.println("Cannot remove meal from special offer. Meal is unknown or current user is not a chef.");
			}
			//insertOffer
			else if(command.equalsIgnoreCase("insertoffer") && arguments.size() > 1){
				double price = Double.parseDouble(arguments.get(arguments.size()-1));
				String mealName = "";
				for (int i = 0; i< arguments.size()-1; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 2){
						mealName += " ";
					}
				}
				boolean t = system.insertOffer(mealName, price);
				if(t)
					System.out.println("New special offer meal created. Please add ingredients with the proper command.");
				else
					System.out.println("An error occured : Please check that you're connected as a chef, and that the meal does not already exist");
			}
			//registerClient
			else if(command.equalsIgnoreCase("registerClient")  && arguments.size() == 4){
				boolean t = system.register(arguments.get(0),arguments.get(1),arguments.get(2),arguments.get(3), UserRole.Client);
				if(t)
					System.out.println("Your account was correctly added. You can login now with the proper command.");
				else
					System.out.println("An error occured. The username is maybe already taken. Please check that you did not specify empty arguments");
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
					System.out.println("An error occured. Please be sure that you're connected as a client. Make sure that you did not provide empty arguments.");
			}
			//associateCard
			else if(command.equalsIgnoreCase("associatecard")  && arguments.size() == 1){
				boolean t = false;
				if(arguments.get(0).equalsIgnoreCase("basic"))
					t = system.associateCard(FidelityCard.Basic);
				else if(arguments.get(0).equalsIgnoreCase("point"))
					t = system.associateCard(FidelityCard.Point);
				else if(arguments.get(0).equalsIgnoreCase("lottery"))
					t = system.associateCard(FidelityCard.Lottery);
				
				if(t)
					System.out.println("The new card has successfly been associated to your account");
				else
					System.out.println("Please be sure that you're connected as a client. Make sure that your argument was : basic, point or lottery.");					
			}
			//associateAgreement
			else if(command.equalsIgnoreCase("associateagreement")  && arguments.size() == 2){
				boolean t = system.associateAgreement(arguments.get(0), arguments.get(1), true);
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
			else if(command.equalsIgnoreCase("notifyAd")  && arguments.size() > 2){
				double price = Double.parseDouble(arguments.get(arguments.size()-1));
				String notifiactionText = arguments.get(0).substring(1, arguments.get(0).length() - 2);
				int k = 1;
				while (!arguments.get(k).contains("\"")){
					notifiactionText += " " + arguments.get(k);
					k += 1;
				}
				notifiactionText += " " + arguments.get(k).substring(0, arguments.get(0).length() - 2);
				k += 1;
				String mealName = "";
				for (int i = k; i< arguments.size()-1; i++){
					mealName += arguments.get(i);
					if (i != arguments.size() - 2){
						mealName += " ";
					}
				}
				boolean t = system.notifyAd(notifiactionText, mealName, price);
				if(t)
					System.out.println("Notification sent to all the users");
				else
					System.out.println("An error occured, please check that you're logged in as a chef and the meal was already created.");
			}
			//notifyBirthday
			else if(command.equalsIgnoreCase("notifyBirthday")  && arguments.size() == 0){
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
				
				System.out.println(system.showMeal(oc));
			}
			else if(command.equalsIgnoreCase("importfile") && arguments.size() == 1){
				try{
					commandsList = CLUI.importfile(arguments.get(0));
					readFromFile = !commandsList.isEmpty();
				}catch(Exception e){
					System.out.println("Unable to import file");
				}
			}
			//other
			else{
				System.out.println("Unknown command, type h for the list of the commands.");
			}
			
			//Skip 2 lines in order to have more visibility on the effect of each command
			System.out.println();
			System.out.println();
		}
		
		ui.close();

	}

	private static ArrayList<String> importfile(String fileName) throws IOException {
		ArrayList<String> commandsList = new ArrayList<String>();
		FileReader file = null;
		BufferedReader reader = null;
		file = new FileReader("eval\\"+fileName+".txt");
			
		reader = new BufferedReader(file);
		String line = "";
			
		while((line = reader.readLine()) != null){
			commandsList.add(line);
		}
		
		reader.close(); 
		
		return commandsList ;
	}

}
