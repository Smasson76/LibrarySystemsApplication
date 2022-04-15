package application;

import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class PatronSender {
	
	
	public PatronSender(PatronInformation pi) throws IOException {
		try {
			  FileWriter fstream = new FileWriter("PatronListFile.txt", true);
			  BufferedWriter out = new BufferedWriter(fstream);
			  out.newLine();
			  out.write(pi.getName());
			  out.write(" ");
			  out.write(pi.getAccountPassword());
			  out.write(" ");
			  out.write(pi.getAccountID());
			  
			  out.close();
		  } 
		catch (Exception e) {
			 System.err.println("Error while writing to file: " +
		     e.getMessage());
		}
		
		printInfo();
	}
	
	
	public void printInfo() {
		for (int i = 0; i < LibrarySystemApplication.patronList.size(); i++) {
			System.out.println(LibrarySystemApplication.patronList.get(i).getName() + " " + LibrarySystemApplication.patronList.get(i).getAccountPassword() + " " + LibrarySystemApplication.patronList.get(i).getAccountID());
		}
	}
}