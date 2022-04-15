package application;

import java.io.FileInputStream;
import java.util.Scanner;

public class BookReader {
	
	FileInputStream fileInputHolder = null;
	
	Scanner scnr = new Scanner(System.in);
	
	public BookReader(FileInputStream fileInput) {
		fileInputHolder = fileInput;
		
		Scanner fileReader = new Scanner(fileInput);
		
		System.out.println("New File Being Read...");
		while (fileReader.hasNextLine()) {
			String data = fileReader.nextLine();

            String[] details = data.split("; ");
            String data1 = details[0];
            String data2 = details[1];
            String data3 = details[2];
			
			try {
				BookInformation pi = new BookInformation(data1, data2, data3);
				LibrarySystemApplication.bookList.add(pi);
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
		for (int i = 0; i < LibrarySystemApplication.bookList.size(); i++) {
			System.out.println(LibrarySystemApplication.bookList.get(i).getName() + " " + LibrarySystemApplication.bookList.get(i).getItemNumber() + " " + LibrarySystemApplication.bookList.get(i).getCopiesAvailable());
		}
	}
}
