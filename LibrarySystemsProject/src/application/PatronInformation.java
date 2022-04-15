package application;


public class PatronInformation {
	
	//-- VARIABLES --
	String name;
	String accountPassword;
	String accountID;
	double fees;
	int numOfBooksHeld;
	
	//Default Constructor
	public PatronInformation() {
		name = "Undecided";
		accountPassword = "1234";
		accountID = "Undecided";
		fees = 0.0;
	}
	
	//Parameterized Constructor
	public PatronInformation(String name, String accountPassword, String accountID, double fees, int numOfBooksHeld) {
		this.name = name;
		this.accountPassword = accountPassword;
		this.accountID = accountID;
		this.fees = fees;
		this.numOfBooksHeld = numOfBooksHeld;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAccountPassword() {
		return accountPassword;
	}
	
	public String getAccountID() {
		return accountID;
	}
	
	public double getFees() {
		return fees;
	}
	
	public int getNumOfBooksHeld() {
		return numOfBooksHeld;
	}
}