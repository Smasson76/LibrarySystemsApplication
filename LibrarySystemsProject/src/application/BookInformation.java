package application;


public class BookInformation {
	
	//-- VARIABLES --
	String name;
	String itemNumber;
	String copiesAvailable;
	
	//Default Constructor
	public BookInformation() {
		name = "Undecided";
		itemNumber = "1234";
		copiesAvailable = "2";
	}
	
	//Parameterized Constructor
	public BookInformation(String name, String itemNumber, String copiesAvailable) {
		this.name = name;
		this.itemNumber = itemNumber;
		this.copiesAvailable = copiesAvailable;
	}
	
	public String getName() {
		return name;
	}
	
	public String getItemNumber() {
		return itemNumber;
	}
	
	public String getCopiesAvailable() {
		return copiesAvailable;
	}
}
