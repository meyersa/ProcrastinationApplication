package gui;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import events.Event;
import events.ReminderEvent;
import events.ScheduledEvent;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.control.TextField;
import javafx.scene.control.ScrollPane.ScrollBarPolicy;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class MainGUI extends Application {
	static private Map<Long, Event> allEvents;
	static private ScrollPane eventsDisplay;

	static private TextField creationName;
	static private TextField creationTime;
	static private TextField creationDescription;
	static private AnchorPane errorPane;
	static private Text errorField;

	static private AnchorPane wrapEvents;
	static private SplitPane SplitPane;
	static private AnchorPane CreateBox;

	@Override
	public void start(Stage primaryStage) throws Exception {
		System.out.println("MainGUI.start Method Called");

		/*
		 * MenuBar Creation Save - Calls the local save class with the current allEvents
		 * Map Preferences - Calls the preferences menu, perhaps remove? About - Pulls
		 * up the GitHub page? Add class for this
		 */

		// Create Menu --------------------------------------------------
		MenuItem Save = new MenuItem("Save");
		Save.setId("MenuItem");
		Save.setOnAction(event -> Save());

		MenuItem About = new MenuItem("About");
		About.setId("MenuItem");
		About.setOnAction(event -> About());

		MenuItem Quit = new MenuItem("Quit");
		Quit.setId("MenuItem");
		Quit.setOnAction(event -> menuQuit());

		Menu Menu = new Menu("File");
		Menu.setId("Menu");
		Menu.getItems().addAll(Save, About, Quit);
		// TODO Fix stupid formatting

		MenuBar MenuBar = new MenuBar(Menu);
		MenuBar.setStyle("-fx-background-color: #e945ff");
		// End of Menu --------------------------------------------------

		/*
		 * Create Main Application Area, Lower part of the screen Split using a
		 * SplitPane Half is allocated to displaying Events, other half for creating
		 * Events
		 */

		/*
		 * Displaying Events For now it starts by just creating an AnchorPane, and
		 * styling it Calls showEvents method with the Events map and the AnchorPane to
		 * populate
		 */

		// Creates the Display AnchorPane -------------------------------
		showEvents();
		// End of Display AnchorPane -------------------------------

		/*
		 * Creating Events Three TextFields - Name: Place to enter name - Time: Place to
		 * enter UnixTime of event - Description: Place to enter description Button
		 * Submit, creates event using aforementioned parameters When fields are
		 * populated, the user can press the button to create Events
		 */

		// Creates the Creation AnchorPane ------------------------------
		Text creationText = new Text("Create Event");
		creationText.setFill(Color.web("#ebe1eb"));
		creationText.setStyle("-fx-font-size: 30");

		creationName = new TextField();
		creationName.setPromptText("Name of Event");
		creationName.setStyle(
				"-fx-background-color: #424144; -fx-border-color: #ebe1eb; -fx-font-size: 15; -fx-text-fill: #ebe1eb");

		creationTime = new TextField();
		creationTime.setPromptText("Time of Event, in UNIX Time");
		creationTime.setStyle(
				"-fx-background-color: #424144; -fx-border-color: #ebe1eb; -fx-font-size: 15; -fx-text-fill: #ebe1eb");

		creationDescription = new TextField();
		creationDescription.setPromptText("Description of Event");
		creationDescription.setStyle(
				"-fx-background-color: #424144; -fx-border-color: #ebe1eb; -fx-font-size: 15; -fx-text-fill: #ebe1eb");

		Button creationButton = new Button("Create");
		creationButton.setTextFill(Color.web("#ebe1eb"));
		creationButton.setStyle("-fx-background-color: #424144; -fx-border-color: #ebe1eb; -fx-font-size: 15");
		creationButton.setOnAction(event -> Create());

		Button clearButton = new Button("Clear");
		clearButton.setTextFill(Color.web("#ebe1eb"));
		clearButton.setStyle("-fx-background-color: #424144; -fx-border-color: #ebe1eb; -fx-font-size: 15");
		clearButton.setOnAction(event -> Clear());

		HBox buttonRow = new HBox(creationButton, clearButton);
		buttonRow.setSpacing(25);
		buttonRow.setAlignment(Pos.CENTER);

		TextField creationBlankField = new TextField();
		creationBlankField.setId("creationBlankField");
		creationBlankField.setMinWidth(300);
		creationBlankField.setMaxHeight(0.01);
		creationBlankField.setStyle("-fx-background-color: #424144; -fx-text-fill: #424144");

		errorField = new Text("Input Invalid. Please try again");
		errorField.setFill(Color.web("#ebe1eb"));
		errorField.setStyle("-fx-font-size: 15;");

		errorPane = new AnchorPane(errorField);
		errorPane.setStyle("-fx-background-color: #ff6961");
		errorPane.setPadding(new Insets(10));
		errorPane.setVisible(false);

		AnchorPane.setTopAnchor(errorField, 0.0);
		AnchorPane.setLeftAnchor(errorField, 0.0);
		AnchorPane.setRightAnchor(errorField, 0.0);
		AnchorPane.setBottomAnchor(errorField, 0.0);

		creationBlankField.setPadding(new Insets(0));

		VBox createFields = new VBox(creationBlankField, creationText, creationName, creationTime, creationDescription,
				buttonRow, errorPane);
		createFields.setAlignment(Pos.TOP_CENTER);
		createFields.setSpacing(25);
		createFields.setPadding(new Insets(25));

		CreateBox = new AnchorPane(createFields);
		AnchorPane.setTopAnchor(createFields, 0.0);
		AnchorPane.setLeftAnchor(createFields, 0.0);
		AnchorPane.setRightAnchor(createFields, 0.0);
		AnchorPane.setBottomAnchor(createFields, 0.0);
		// End of Creation AnchorPane -----------------------------------

		/*
		 * Main Program Formatting Creates SplitPane to format, sets background color
		 * Creates the Main VBox that formats the SplitPane and MenuBar Assigns to
		 * Scene, assigns StyleSheet to the Scene Assigns to stage and shows
		 * 
		 * TODO Add a scene handler so multiple UIs can be shown
		 */

		// Main Formatting ----------------------------------------------
		SplitPane = new SplitPane(wrapEvents, CreateBox);
		SplitPane.setStyle("-fx-background-color: #424144");
		SplitPane.setDividerPositions(0.75);

		VBox Main = new VBox(MenuBar, SplitPane);
		VBox.setVgrow(SplitPane, Priority.ALWAYS);

		Scene Scene = new Scene(Main, 1200, 700);
		Scene.getStylesheets().add("file:src/gui/assets/guiCSS.css");

		primaryStage.setOnCloseRequest(event -> menuQuit());

		primaryStage.setTitle("Procrastination Application");
		primaryStage.getIcons().add(new Image("file:src/gui/assets/logo.png"));
		primaryStage.setScene(Scene);
		primaryStage.show();
		// End of Main Formatting ---------------------------------------

	}

	private void Clear() {
		System.out.println("MainGUI.Create Method Called");

		creationName.clear();
		creationDescription.clear();
		creationTime.clear();

	}

	private int Create() {
		System.out.println("MainGUI.Create Method Called");

		/*
		 * Takes input from creationName, creationTime, creationDescription Then calls
		 * the showEvents class to display again If it fails, show errorPane
		 */

		if ((creationName.getText().equals("") && creationDescription.getText().equals(""))) {
			errorPane.setVisible(true);
			return 0;
					
		} else {
			try {
				if (creationTime.getText().equals("")) {
					allEvents.put(System.currentTimeMillis(), new ReminderEvent(creationName.getText(),
							creationDescription.getText(), System.currentTimeMillis()));

				} else {
					allEvents.put(System.currentTimeMillis(),
							new ScheduledEvent(creationName.getText(), creationDescription.getText(),
									System.currentTimeMillis(), Long.parseLong(creationTime.getText())));

				}

				errorPane.setVisible(false);

			} catch (Exception e) {
				errorPane.setVisible(true);

			}
		}

		showEvents();

		SplitPane.getItems().clear();
		SplitPane.getItems().addAll(wrapEvents, CreateBox);
		SplitPane.setDividerPositions(0.75);

		return 1;

	}

	private void menuQuit() {
		System.out.println("MainGUI.menuQuit Method Called");
		end();

	}

	private void About() {
		System.out.println("MainGUI.About Method Called");

		getHostServices().showDocument("https://github.com/meyersa/ProcrastinationApplication");

	}

	private void showEvents() {
		System.out.println("MainGUI.showEvents Method Called");

		int numOfEvents = -1;
		List<AnchorPane> events = new ArrayList<AnchorPane>();
		List<VBox> columns = new ArrayList<VBox>();

		allEvents = main.Sort.sortMapByValue(allEvents);
		main.Notification.initNotification(allEvents);

		try {
			numOfEvents = allEvents.size();

		} catch (Exception e) {
			System.out.println("Unable to get size of event map. " + e);
			numOfEvents = 0;

		}

		System.out.println("-- Number of Events to Process " + numOfEvents + " --");

		if (numOfEvents == 0 || numOfEvents < 0) {
			events.add(vboxCreater());

		} else {
			events = vboxCreatorInput();

		}

		for (AnchorPane current : events) {
			current.setStyle("-fx-background-color: #424144; -fx-border-color: #ccc2cc");
			current.setMinSize(300, 250);
			current.setMaxSize(300, 250);

		}

		if (events.size() == 1) {
			columns.add(new VBox(events.get(0)));

		} else {
			for (int i = events.size() - 1; i >= 1; i -= 2) {
				columns.add(new VBox(events.get(i), events.get(i - 1)));

			}

			if (events.size() % 2 != 0) {
				columns.add(new VBox(events.get(0)));

			}
		}

		HBox HBox = new HBox();
		HBox.getChildren().addAll(columns);

		System.out.println("-- Number of columns " + columns.size() + " --");

		HBox.setStyle("-fx-background-color: #424144");
		HBox.setPadding(new Insets(25));
		HBox.setMinWidth(1200);
		HBox.setSpacing(25);

		eventsDisplay = new ScrollPane(HBox);
		eventsDisplay.setHbarPolicy(ScrollBarPolicy.ALWAYS);
		eventsDisplay.setPannable(true);
		eventsDisplay.setFitToHeight(true);
		eventsDisplay.setPadding(new Insets(25));
		eventsDisplay.setStyle("-fx-background-color: #424144");

		wrapEvents = new AnchorPane(eventsDisplay);

		AnchorPane.setTopAnchor(eventsDisplay, 0.0);
		AnchorPane.setLeftAnchor(eventsDisplay, 0.0);
		AnchorPane.setRightAnchor(eventsDisplay, 0.0);
		AnchorPane.setBottomAnchor(eventsDisplay, 0.0);

	}

	private AnchorPane vboxCreater() { // Default version
		System.out.println("MainGUI.vboxCreator Method called");

		Text Name = new Text("Default Name");
		Name.setFill(Color.web("#ebe1eb"));
		Name.setStyle("-fx-font-size: 30");

		Text Time = new Text("Default Time");
		Time.setFill(Color.web("#ccc2cc"));

		Text Description = new Text(
				"This is the default description. For your events, custom text you set will show up here! Create some events on the left to start");
		Description.setFill(Color.web("#ccc2cc"));
		Description.setWrappingWidth(250);

		VBox defaultEvent = new VBox();
		defaultEvent.getChildren().addAll(Name, Time, Description);
		defaultEvent.setPadding(new Insets(25));

		AnchorPane defAnchorPane = new AnchorPane(defaultEvent);

		return defAnchorPane;

	}

	private List<AnchorPane> vboxCreatorInput() {
		System.out.println("MainGUI.vboxCreatorInput Method Called");

		List<AnchorPane> eventList = new ArrayList<AnchorPane>();
		Event currentEvent;

		Text Name;
		Text Time;
		Text Description;
		VBox defaultEvent;

		for (Long current : allEvents.keySet()) {
			currentEvent = allEvents.get(current);

			if (currentEvent instanceof ReminderEvent) {
				Name = new Text(currentEvent.getName());
				Description = new Text(currentEvent.getDescription());

				Name.setFill(Color.web("#ebe1eb"));
				Name.setStyle("-fx-font-size: 30");

				Description.setFill(Color.web("#ccc2cc"));
				Description.setWrappingWidth(250);

				defaultEvent = new VBox();

				Region spacer = new Region();
				VBox.setVgrow(spacer, Priority.ALWAYS);
				// Spacer

				Button deleteButton = new Button("Delete");
				deleteButton.setTextFill(Color.web("#ebe1eb"));
				deleteButton.setStyle("-fx-background-color: #424144; -fx-border-color: #ebe1eb");
				deleteButton.setOnAction(event -> Delete(allEvents.get(current)));
				// Delete button

				defaultEvent.getChildren().addAll(Name, Description, spacer, deleteButton);
				defaultEvent.setPadding(new Insets(25));

				AnchorPane.setTopAnchor(defaultEvent, 0.0);
				AnchorPane.setBottomAnchor(defaultEvent, 0.0);
				AnchorPane.setLeftAnchor(defaultEvent, 0.0);
				AnchorPane.setRightAnchor(defaultEvent, 0.0);

				eventList.add(new AnchorPane(defaultEvent));

			} else if (currentEvent instanceof ScheduledEvent) {
				Name = new Text(currentEvent.getName());
				Time = new Text((new java.util.Date(((ScheduledEvent) currentEvent).getOffHold()).toString()));
				Description = new Text(currentEvent.getDescription());

				Name.setFill(Color.web("#ebe1eb"));
				Name.setStyle("-fx-font-size: 30");

				Time.setFill(Color.web("#ccc2cc"));

				Description.setFill(Color.web("#ccc2cc"));
				Description.setWrappingWidth(250);

				defaultEvent = new VBox();

				Region spacer = new Region();
				VBox.setVgrow(spacer, Priority.ALWAYS);
				// Spacer

				Button deleteButton = new Button("Delete");
				deleteButton.setTextFill(Color.web("#ebe1eb"));
				deleteButton.setStyle("-fx-background-color: #424144; -fx-border-color: #ebe1eb");
				deleteButton.setOnAction(event -> Delete(allEvents.get(current)));
				// Delete button

				defaultEvent.getChildren().addAll(Name, Time, Description, spacer, deleteButton);
				defaultEvent.setPadding(new Insets(25));

				AnchorPane.setTopAnchor(defaultEvent, 0.0);
				AnchorPane.setBottomAnchor(defaultEvent, 0.0);
				AnchorPane.setLeftAnchor(defaultEvent, 0.0);
				AnchorPane.setRightAnchor(defaultEvent, 0.0);

				eventList.add(new AnchorPane(defaultEvent));

			}

			currentEvent = null;
			Name = null;
			Time = null;
			Description = null;
			defaultEvent = null;

		}

		System.out.println("-- New eventList size " + eventList.size() + " --");

		return eventList;

	}

	private void Delete(Event currentEvent) {		
		allEvents.remove(currentEvent.getTime());
		
		showEvents();

		SplitPane.getItems().clear();
		SplitPane.getItems().addAll(wrapEvents, CreateBox);
		SplitPane.setDividerPositions(0.75);
		
	}

	public void start(Map<Long, Event> allEvents) {
		System.out.println("MainGUI.start Method Called");

		this.allEvents = allEvents;

		launch();

	}

	public void end() {
		System.out.println("MainGUI.end Method Called");

		Save();

		try {
			Platform.exit();
			System.exit(0);

		} catch (Exception e) {
			System.out.println(e.toString());
			
		}
	}

	private void Save() {
		System.out.println("MainGUI.Save Method Called");
		main.LocalStorage.writeCache(allEvents);

	}

	public static void displayError(String errorText) {
		errorField.setText(errorText);
		errorPane.setVisible(true);

	}

	public static void hideError() {
		errorPane.setVisible(false);
		errorField.setText("Input Invalid. Please try again");

	}
}