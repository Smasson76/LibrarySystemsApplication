package application;

import java.io.FileInputStream;
import java.util.Scanner;

public class PatronReader {
	
	FileInputStream fileInputHolder = null;
	
	Scanner scnr = new Scanner(System.in);
	
	public PatronReader(FileInputStream fileInput) {
		fileInputHolder = fileInput;
		
		Scanner fileReader = new Scanner(fileInput);
		
		System.out.println("New File Being Read...");
		while (fileReader.hasNextLine()) {
			String data = fileReader.nextLine();

            String[] details = data.split(" ");
            String data1 = details[0];
            String data2 = details[1];
            String data3 = details[2];
			
			try {
				PatronInformation pi = new PatronInformation(data1, data2, data3, 0.0, 0);
				LibrarySystemApplication.patronList.add(pi);
			}
			catch(NumberFormatException e) {
				System.out.println("Invalid input: " + data);
			}
		}
		printInfo();
		System.out.println("");		//Leaving a space between Both Outputs.
		System.out.println("");
		fileReader.close();
	}

	public void printInfo() {
		for (int i = 0; i < LibrarySystemApplication.patronList.size(); i++) {
			System.out.println(LibrarySystemApplication.patronList.get(i).getName() + " " + LibrarySystemApplication.patronList.get(i).getAccountPassword() + " " + LibrarySystemApplication.patronList.get(i).getAccountID());
		}
	}
}