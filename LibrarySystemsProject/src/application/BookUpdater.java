package application;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class BookUpdater {
	
	FileInputStream fileInputHolder = null;
	Scanner scnr = new Scanner(System.in);
	
	public BookUpdater(BookInformation bi) throws IOException {
		
		try {
			  FileWriter fstream = new FileWriter("BookListFile.txt", false);
			  BufferedWriter out = new BufferedWriter(fstream);
			  
			  for (int i = 0; i < LibrarySystemApplication.bookList.size(); i++) {
				  out.write(LibrarySystemApplication.bookList.get(i).getName());
				  out.write("; ");
				  out.write(LibrarySystemApplication.bookList.get(i).getItemNumber());
				  out.write("; ");
				  out.write(LibrarySystemApplication.bookList.get(i).getCopiesAvailable());
				  out.newLine();
			  }
			  
			  out.close();
		  } 
		catch (Exception e) {
			 System.err.println("Error while writing to file: " +
		     e.getMessage());
		}
		
		printInfo();
	}
	
	public void printInfo() {
		for (int i = 0; i < LibrarySystemApplication.bookList.size(); i++) {
			System.out.println(LibrarySystemApplication.bookList.get(i).getName() + " " + LibrarySystemApplication.bookList.get(i).getItemNumber() + " " + LibrarySystemApplication.bookList.get(i).getCopiesAvailable());
		}
	}
}