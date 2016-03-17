package Group2_Project_IS1220_part1_GharbiLaaboudi;

import java.util.Observable;
import java.util.Observer;

import fr.ecp.IS1220.tutorial3.ex2a.StudentType;

public class Client extends User implements Observer {
	/** The fidelity card type, will be basic by default	 */
	private CardType cardType;
	/** The type of notifications the user accepts to receive. Will be set to Yes by default	 */
	private AgreementType agreementType;
	
	/**
	 * @param firstName The first name of the user.
	 * @param surName The surname of the user.
	 * @param userName The username of the user. In order to register, the username selected must be different from 
	 * all the other usernames.
	 * @param password The password of the user.
	 * 
	 * This is the default constructor, the user will have a Basic Fidelity Card and he agrees to all kind of notification. 
	 * 
	 */
	public Client(String firstName, String surName, String userName, String password) {
		super(firstName, surName, userName, password);
		this.cardType = CardType.Basic ;
		this.agreementType = AgreementType.Yes ;
	}
	
	

	public CardType getCardType() {
		return cardType;
	}



	public void setCardType(CardType cardType) {
		this.cardType = cardType;
	}



	public AgreementType getAgreementType() {
		return agreementType;
	}



	public void setAgreementType(AgreementType agreementType) {
		this.agreementType = agreementType;
	}

	

	@Override
	public String toString() {
		return "Client [cardType=" + cardType + ", agreementType=" + agreementType + "]";
	}



	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}
	
	

}
