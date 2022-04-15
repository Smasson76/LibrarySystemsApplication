package application;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;

public class LibrarySystemApplication extends Application {
	
	Stage applicationStage;		//Stage for all scenes
	
	//-- ARRAYLISTS --
	static ArrayList<PatronInformation> patronList = new ArrayList<PatronInformation>();
	static ArrayList<BookInformation> bookList = new ArrayList<BookInformation>();
	
	//scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
	
	//-- LABELS --
	Label usernameLabel;
	Label passwordLabel;
	Label questionLabel;
	
	//-- TEXTFIELD --
	TextField usernameTextField;
	TextField passwordTextField;
	
	//-- BUTTONS --
	Button confirmButton;
	Button confirmAccountCreateButton;
	Button cancelAccountCreateButton;
	
	//-- VARIABLES --
	static String usernameInput;
	static String passwordInput;
	static String idNumber;
	public static int currentUserDisplay;
	
	
	@Override
	public void start(Stage applicationStage) {
		GridPane gridPane = new GridPane();
		Scene signInScene = new Scene(gridPane);
		gridPane.setStyle("-fx-background-color: AZURE;"); 
		
		usernameLabel = new Label("Username:");
		usernameTextField = new TextField();
		usernameTextField.setPrefColumnCount(15);
		usernameTextField.setEditable(true);
		
		passwordLabel = new Label("Password:");
		passwordTextField = new TextField();
		passwordTextField.setPrefColumnCount(15);
		passwordTextField.setEditable(true);
		
		confirmButton = new Button("OK");
		confirmButton.setStyle("-fx-background-color: slateblue; -fx-text-fill: white;");
		confirmButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				usernameInput = usernameTextField.getText();
				passwordInput = passwordTextField.getText();
				
				if (usernameTextField.getText().trim().equals("") || passwordTextField.getText().trim().equals("")) {
					Alert alert = new Alert(AlertType.ERROR, "DO NOT LEAVE FIELDS EMPTY!");
					alert.showAndWait();
				}
				else {
					for (int i = 0; i < patronList.size(); i++) {
						if (patronList.get(i).getName().equals(usernameInput) && patronList.get(i).getAccountPassword().equals(passwordInput)) {
							currentUserDisplay = i;
							callNavMenu();
							applicationStage.close();
							break;
						}
						else if (i == patronList.size()-1) {
							Alert alert = new Alert(AlertType.ERROR, "ACCOUNT NOT FOUND");
							alert.showAndWait();
							createAccountOptionMenu();
						}
					}
				}
			}
		});
		
		gridPane.setPadding(new Insets(20, 20, 20, 20)); // Padding around  grid
	    gridPane.setHgap(10);                            // Spacing between columns
	    gridPane.setVgap(10); 
	    gridPane.add(usernameLabel, 0, 0);  // Add wage label to location (0, 0)
	    gridPane.add(usernameTextField, 1, 0);  // Add wage text field to location (1, 0)
	    gridPane.add(passwordLabel, 0, 1);   // Add salary label to location (0, 1)
	    gridPane.add(passwordTextField, 1, 1);   // Add salary text field to location (1, 1)
	    gridPane.add(confirmButton, 0, 2);
		
		applicationStage.setScene(signInScene);
		applicationStage.setTitle("Library System");
		applicationStage.show();
	}
	
	public static void callNavMenu() {
		NavigationMenu nav = new NavigationMenu();
		nav.start(NavigationMenu.applicationStage);
	}
	
	public void createAccountOptionMenu() {
		Stage applicationStage = new Stage();
		GridPane gridPane = new GridPane();
		Scene signInScene = new Scene(gridPane);
		
		questionLabel = new Label("Hello, your account is invalid, would you like \nto create account using this information?");
		confirmAccountCreateButton = new Button("Confirm");
		cancelAccountCreateButton = new Button("Cancel");
		confirmAccountCreateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					createAccount();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		cancelAccountCreateButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				applicationStage.close();
			}
		});
		
		gridPane.setPadding(new Insets(100)); // Padding around  grid
	    gridPane.setHgap(10);                            // Spacing between columns
	    gridPane.setVgap(10);
	    gridPane.add(questionLabel, 0, 0);
	    gridPane.add(confirmAccountCreateButton, 0, 1);
	    gridPane.add(cancelAccountCreateButton, 0, 2);
	    gridPane.setAlignment(Pos.CENTER);
		
		applicationStage.setScene(signInScene);
		applicationStage.setTitle("Library System");
		applicationStage.show();
	}
	
	public static void createAccount() throws IOException {
		Random randomObj = new Random();
		int randomNumber = randomObj.nextInt(99999);
        idNumber = String.valueOf(randomNumber);
                
		PatronInformation pi = new PatronInformation(usernameInput, passwordInput, idNumber, 0.0, 0);
		patronList.add(pi);
		
		PatronSender patronSender = new PatronSender(pi);
	}
	
	//Main Method's purpose is to Start the program
	public static void main(String[] args) throws IOException {
		
		//Reading Currently saved users
		FileInputStream patronFileInput = new FileInputStream("PatronListFile.txt");
		PatronReader patronInfo = new PatronReader(patronFileInput);
		
		//Reading Currently saved books
		FileInputStream booksFileInput = new FileInputStream("BookListFile.txt");
		BookReader booksInfo = new BookReader(booksFileInput);
				
		launch(args);
	}
}
