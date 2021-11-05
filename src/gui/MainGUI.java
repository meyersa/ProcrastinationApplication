package gui;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.SplitPane;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

public class MainGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		MenuItem Preferences = new MenuItem("Preferences");
		MenuItem Save = new MenuItem("Save");
		MenuItem About = new MenuItem("About");
		MenuItem Quit = new MenuItem("Quit");
		
		Menu Menu = new Menu();
		
		Menu.getItems().addAll(Preferences, Save, About, Quit);
		
		MenuBar MenuBar = new MenuBar(Menu);
		
		// Top Bar 
		
		ScrollPane EventsPane = new ScrollPane();
		
		EventsPane.pannableProperty().set(true);

		EventsPane.fitToWidthProperty().set(true);
		EventsPane.fitToHeightProperty().set(true);

		AnchorPane EventsAnchor = new AnchorPane(EventsPane);
		
		// Events Half of the Splitpane
		
		AnchorPane AddEventAnchor = new AnchorPane();
		
		
		// Create Events Half of the SplitPane
		
		SplitPane SplitPane = new SplitPane();
		
		SplitPane.getItems().addAll(EventsAnchor, AddEventAnchor);
		
		// Lower half
		
		VBox VBox = new VBox(MenuBar, SplitPane);
		
		VBox.setPrefWidth(1000);
		VBox.setPrefHeight(600);
		
		Scene scene = new Scene(VBox);
		
		primaryStage.setTitle("Procrastination Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	
	}

	public void start() {
		launch();

	}
}
