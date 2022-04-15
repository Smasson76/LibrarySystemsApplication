package application;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

public class NavigationMenu extends Application {
	
	static Stage applicationStage = new Stage();
		
	//-- LABELS --
	Label usernameLabel;
	Label feesLabel;
	Label numOfBooksHeldLabel;
	Label checkInOrOutLabel;
	
	//-- TEXTFIELD --
	TextField checkOutTextField;
	
	//-- BUTTONS --
	Button myAccountButton;
	Button catalogButton;
	Button checkButton;
	Button signoutButton;
	Button checkInButton;
	Button checkOutButton;
	
	//-- VARIABLES --
	String bookCodeInput;
	
		
	@Override
	public void start(Stage applicationStage) {	
		GridPane gridPane = new GridPane();
		Scene signInScene = new Scene(gridPane);
		gridPane.setStyle("-fx-background-color: AZURE;"); 
		
		myAccountButton = new Button("MyAccount");
		catalogButton = new Button("Book Catalog");
		checkButton = new Button("Check in/out");
		signoutButton = new Button("Sign out");
		myAccountButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				myAccountMenu();
			}
		});
		catalogButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				catalogMenu();
			}
		});
		checkButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				checkMenu();
			}
		});
		
		
		gridPane.setPadding(new Insets(50, 50, 50, 50)); // Padding around  grid
	    gridPane.setHgap(20);                            // Spacing between columns
	    gridPane.setVgap(20); 
	    gridPane.add(myAccountButton, 1, 0);
	    gridPane.add(catalogButton, 2, 0);
	    gridPane.add(checkButton, 3, 0);
	    gridPane.add(signoutButton, 4, 0);
	    gridPane.setAlignment(Pos.CENTER);
	    
	    Rectangle2D primScreenBounds = Screen.getPrimary().getVisualBounds();
	    applicationStage.setX((primScreenBounds.getWidth() - applicationStage.getWidth()) / 1);
	    applicationStage.setY((primScreenBounds.getHeight() - applicationStage.getHeight()) / 1);
		
		applicationStage.setScene(signInScene);
		applicationStage.setTitle("Library System");
		applicationStage.show();
	}
	
	
	public void myAccountMenu() {
		Stage applicationStage = new Stage();
		GridPane gridPane = new GridPane();
		Scene signInScene = new Scene(gridPane);
		
		usernameLabel = new Label("Hello, " + LibrarySystemApplication.patronList.get(LibrarySystemApplication.currentUserDisplay).getName());
		feesLabel = new Label("Fees: " + LibrarySystemApplication.patronList.get(LibrarySystemApplication.currentUserDisplay).getFees());
		numOfBooksHeldLabel = new Label("Number of books checked-out: " + LibrarySystemApplication.patronList.get(LibrarySystemApplication.currentUserDisplay).getNumOfBooksHeld() + "/25");
		
		gridPane.setPadding(new Insets(200)); // Padding around  grid
	    gridPane.setHgap(20);                            // Spacing between columns
	    gridPane.setVgap(20);
	    gridPane.add(usernameLabel, 0, 0);
	    gridPane.add(feesLabel, 0, 1);
	    gridPane.add(numOfBooksHeldLabel, 0, 2);
	    gridPane.setAlignment(Pos.CENTER);
		
		applicationStage.setScene(signInScene);
		applicationStage.setTitle("Library System");
		applicationStage.show();
	}
	
	public void catalogMenu() {
		Stage applicationStage = new Stage();
		Scene scene = new Scene(new Group());
		applicationStage.setWidth(700);
		applicationStage.setHeight(500);
 
        final Label label = new Label("Book Catalog");
        
		TableView<BookInformation> table = new TableView<BookInformation>();
        final ObservableList<BookInformation> data = FXCollections.observableArrayList();
        
        for (int i=0; i < LibrarySystemApplication.bookList.size(); i++) {
        	data.add(new BookInformation(LibrarySystemApplication.bookList.get(i).getName(), LibrarySystemApplication.bookList.get(i).getItemNumber(), LibrarySystemApplication.bookList.get(i).getCopiesAvailable()));
		}
 
        table.setEditable(true);
 
        TableColumn bookNameCol = new TableColumn("Book Title");
        bookNameCol.setCellValueFactory(new PropertyValueFactory<>("name"));
        bookNameCol.setMinWidth(200);
        TableColumn idCol = new TableColumn("Book Item Number");
        idCol.setCellValueFactory(new PropertyValueFactory<>("itemNumber"));
        idCol.setMinWidth(150);
        TableColumn copiesCol = new TableColumn("Copies Available");
        copiesCol.setCellValueFactory(new PropertyValueFactory<>("copiesAvailable"));
        copiesCol.setMinWidth(200);
        
        ObservableList<String> list = FXCollections.observableArrayList();
        table.setItems(data);
        table.getColumns().addAll(bookNameCol, idCol, copiesCol);
 
        final VBox vbox = new VBox();
        vbox.setSpacing(15);
        vbox.setPadding(new Insets(10));
        vbox.setAlignment(Pos.CENTER);
        vbox.getChildren().addAll(label, table);
 
        ((Group) scene.getRoot()).getChildren().addAll(vbox);
 
        applicationStage.setScene(scene);
		applicationStage.setTitle("Library Book Catalog");
        applicationStage.show();
	}
	
	public void checkMenu() {
		Stage applicationStage = new Stage();
		GridPane gridPane = new GridPane();
		Scene checkMenuScene = new Scene(gridPane);
		gridPane.setStyle("-fx-background-color: AZURE;"); 
		
		checkInOrOutLabel = new Label("Which would you like to do?");
		checkInButton = new Button("Check in");
		checkOutButton = new Button("Check out");
		checkInButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				
			}
		});
		checkOutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				try {
					checkOutMenu();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		
		
		gridPane.setPadding(new Insets(50, 50, 50, 50)); // Padding around  grid
	    gridPane.setHgap(20);                            // Spacing between columns
	    gridPane.setVgap(20); 
	    gridPane.add(checkInOrOutLabel, 0, 1);
	    gridPane.add(checkInButton, 0, 2);
	    gridPane.add(checkOutButton, 0, 3);
	    gridPane.setAlignment(Pos.CENTER);
	    		
		applicationStage.setScene(checkMenuScene);
		applicationStage.setTitle("Library System");
		applicationStage.show();
	}
	
	public void checkInMenu() {
		
	}
	
	public void checkOutMenu() throws IOException {
		Stage applicationStage = new Stage();
		GridPane gridPane = new GridPane();
		Scene checkOutMenu = new Scene(gridPane);
		gridPane.setStyle("-fx-background-color: AZURE;"); 
		
		checkInOrOutLabel = new Label("Type in the Book Item Number");
		checkOutTextField = new TextField();
		checkOutTextField.setPrefColumnCount(6);
		checkOutTextField.setEditable(true);
		checkOutButton = new Button("Confirm");
		checkOutButton.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				bookCodeInput = checkOutTextField.getText();
				
				if (checkOutTextField.getText().trim().equals("")) {
					Alert alert = new Alert(AlertType.ERROR, "DO NOT LEAVE FIELDS EMPTY!");
					alert.showAndWait();
				}
				else {
					for (int i = 0; i < LibrarySystemApplication.bookList.size(); i++) {
						if (LibrarySystemApplication.bookList.get(i).getItemNumber().equals(bookCodeInput)) {
							System.out.println(LibrarySystemApplication.bookList.get(i).getCopiesAvailable());
							int s = Integer.parseInt(LibrarySystemApplication.bookList.get(i).getCopiesAvailable());
							if (s == 2) {
								BookInformation bi = new BookInformation(LibrarySystemApplication.bookList.get(i).getName(), LibrarySystemApplication.bookList.get(i).getItemNumber(), "1");
								LibrarySystemApplication.bookList.set(i, bi);
								PatronInformation pi = new PatronInformation(LibrarySystemApplication.patronList.get(LibrarySystemApplication.currentUserDisplay).getName(), LibrarySystemApplication.patronList.get(i).getAccountPassword(), LibrarySystemApplication.patronList.get(i).getAccountID(), LibrarySystemApplication.patronList.get(i).getFees(), 1);
								LibrarySystemApplication.patronList.set(LibrarySystemApplication.currentUserDisplay, pi);
								
								try {
									BookUpdater patronSender = new BookUpdater(bi);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else if (s == 1) {
								BookInformation bi = new BookInformation(LibrarySystemApplication.bookList.get(i).getName(), LibrarySystemApplication.bookList.get(i).getItemNumber(), "0");
								LibrarySystemApplication.bookList.set(i, bi);
								PatronInformation pi = new PatronInformation(LibrarySystemApplication.patronList.get(LibrarySystemApplication.currentUserDisplay).getName(), LibrarySystemApplication.patronList.get(i).getAccountPassword(), LibrarySystemApplication.patronList.get(i).getAccountID(), LibrarySystemApplication.patronList.get(i).getFees(), 2);
								LibrarySystemApplication.patronList.set(LibrarySystemApplication.currentUserDisplay, pi);
								
								try {
									BookUpdater patronSender = new BookUpdater(bi);
								} catch (IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								}
							}
							else {
								System.out.println("none avail");
								Alert alert = new Alert(AlertType.ERROR, "NO COPIES ARE AVAILABLE FOR THIS ITEM");
								alert.showAndWait();
							}
							break;
						}
						else if (i == LibrarySystemApplication.bookList.size()-1) {
							Alert alert = new Alert(AlertType.ERROR, "BOOK NOT FOUND");
							alert.showAndWait();
						}
					}
				}
			}
		});
		
		gridPane.setPadding(new Insets(50, 50, 50, 50)); // Padding around  grid
	    gridPane.setHgap(20);                            // Spacing between columns
	    gridPane.setVgap(20); 
	    gridPane.add(checkInOrOutLabel, 0, 1);
	    gridPane.add(checkOutTextField, 0, 2);
	    gridPane.add(checkOutButton, 0, 3);
	    gridPane.setAlignment(Pos.CENTER);
	    		
		applicationStage.setScene(checkOutMenu);
		applicationStage.setTitle("Library System");
		applicationStage.show();
	}
}