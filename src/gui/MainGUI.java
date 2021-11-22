package gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import events.Event;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGUI extends Application {
	private Map<Integer, Event> allEvents;
	private ScrollPane eventsDisplay;

	@Override
	public void start(Stage primaryStage) throws Exception {

		/*
		 * MenuBar Creation
		 * Save - Calls the local save class with the current allEvents Map
		 * Preferences - Calls the preferences menu, perhaps remove? 
		 * About - Pulls up the GitHub page? Add class for this
		 */
		
		
		// Create Menu --------------------------------------------------
		MenuItem Save = new MenuItem("Save");
		MenuItem Preferences = new MenuItem("Preferences");
		MenuItem About = new MenuItem("About");
		MenuItem Quit = new MenuItem("Quit");
		
		Menu Menu = new Menu("File");
		Menu.setStyle("-fx-text-fill: #ccc2cc");
		Menu.getItems().addAll(Save, Preferences, About, Quit);
		// TODO Fix stupid formatting
		
		MenuBar MenuBar = new MenuBar(Menu);	
		MenuBar.setStyle("-fx-background-color: #e945ff");
		// End of Menu --------------------------------------------------

		
		/*
		 * Create Main Application Area, Lower part of the screen 
		 * Split using a SplitPane
		 * Half is allocated to displaying Events, other half for creating Events
		 */
		
		/*
		 * Displaying Events
		 * For now it starts by just creating an AnchorPane, and styling it
		 * Calls showEvents method with the Events map and the AnchorPane to populate
		 */
		
		
		// Creates the Display AnchorPane -------------------------------
		showEvents();
		AnchorPane wrapEvents = new AnchorPane(eventsDisplay);
		AnchorPane.setTopAnchor(eventsDisplay, 0.0);
		AnchorPane.setLeftAnchor(eventsDisplay, 0.0);
		AnchorPane.setRightAnchor(eventsDisplay, 0.0);
		AnchorPane.setBottomAnchor(eventsDisplay, 0.0);		
		// End of Display AnchorPane -------------------------------
		
		
		/* 
		 * Creating Events
		 * Three TextFields
		 * - Name: Place to enter name
		 * - Time: Place to enter UnixTime of event
		 * - Description: Place to enter description
		 * Button Submit, creates event using aforementioned parameters
		 * When fields are populated, the user can press the button to create Events
		 */
		
		
		// Creates the Creation AnchorPane ------------------------------
		TextField Name = new TextField();
		Name.setPromptText("Name of Event");
		Name.setStyle("-fx-font-size: 15");
		
		TextField Time = new TextField();
		Time.setPromptText("Time of Event, in UNIX Time");
		Time.setStyle("-fx-font-size: 15");

		TextField Description = new TextField();
		Description.setPromptText("Description of Event");
		Description.setStyle("-fx-font-size: 15");

		Button Button = new Button("Create");
		Button.setTextFill(Color.web("#ebe1eb"));
		Button.setStyle("-fx-background-color: #424144; -fx-border-color: #ebe1eb; -fx-font-size: 20");

		VBox createFields = new VBox(Name, Time, Description, Button);
		createFields.setAlignment(Pos.TOP_CENTER);
		createFields.setSpacing(25);
		
		AnchorPane CreateBox = new AnchorPane(createFields);
		AnchorPane.setTopAnchor(eventsDisplay, 0.0);
		AnchorPane.setLeftAnchor(eventsDisplay, 0.0);
		AnchorPane.setRightAnchor(eventsDisplay, 0.0);
		AnchorPane.setBottomAnchor(eventsDisplay, 0.0);	
		// End of Creation AnchorPane -----------------------------------
		
		
		SplitPane SplitPane = new SplitPane(wrapEvents, CreateBox);
		SplitPane.setStyle("-fx-background-color: #424144");
		SplitPane.setDividerPositions(0.65);

		VBox Main = new VBox(MenuBar, SplitPane);
		VBox.setVgrow(SplitPane, Priority.ALWAYS);
		Scene Scene = new Scene(Main, 1200, 700);
		
		primaryStage.setTitle("Procrastination Application");
		primaryStage.getIcons().add(new Image("file:src/gui/assets/logo.png"));
		primaryStage.setScene(Scene);
		primaryStage.show();

	}

	private void showEvents() {
		// Count number of events 
		// If 1, make default event
		// Convert all events to AnchorPane with preset styling
		// Depending on number of events, figure out how many VBox to create
		int numOfEvents = -1;
		
		try {
			numOfEvents = allEvents.size();
			
		} catch (Exception e) {
			System.out.println("Unable to get size of event map. " + e);
			numOfEvents = 0;
			
		}
		
		List<AnchorPane> events = new ArrayList<AnchorPane>();
		
		System.out.println(numOfEvents);
		if (numOfEvents == 0) { 
			events.add(vboxCreater());
			
		}
		
		HBox HBox = new HBox();
		HBox.getChildren().addAll(events);
		HBox.setStyle("-fx-background-color: #424144");
		HBox.setPadding(new Insets(25));
		HBox.setPrefWidth(1000);
		eventsDisplay = new ScrollPane(HBox);
		eventsDisplay.setPannable(true);
		eventsDisplay.setFitToHeight(true);
		eventsDisplay.setPadding(new Insets(25));
		eventsDisplay.setStyle("-fx-background-color: #424144");
		
	}

	private AnchorPane vboxCreater() { // Default version		
		Text Name = new Text("Default Name");
		Name.setFill(Color.web("#ebe1eb"));
		Name.setStyle("-fx-font-size: 30");
		
		Text Time = new Text("Default Time");
		Time.setFill(Color.web("#ccc2cc"));
		
		Text Description = new Text("This is the default description. For your events, custom text you set will show up here! Create some events on the left to start");
		Description.setFill(Color.web("#ccc2cc"));
		Description.setWrappingWidth(250);
		
		VBox defaultEvent = new VBox();
		defaultEvent.getChildren().addAll(Name, Time, Description);		
		defaultEvent.setPadding(new Insets(25));
		
		AnchorPane defAnchorPane = new AnchorPane(defaultEvent);
		defAnchorPane.setStyle("-fx-background-color: #424144; -fx-border-color: #ccc2cc");
		defAnchorPane.setMinSize(300, 250);
		defAnchorPane.setMaxSize(300, 250);
		
		return defAnchorPane;
		
	}

	public void start(Map<Integer, Event> allEvents) {
		this.allEvents = allEvents;
		
		launch();

	}
	
	public void end() {
		try {
			stop();
			
		} catch (Exception e) {
			ErrorGUI.display(e);
			
		}	
	}
}