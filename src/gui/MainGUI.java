package gui;

import javafx.event.ActionEvent;

import java.util.Map;

import events.Event;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class MainGUI extends Application {
	private Map<Integer, Event> allEvents;
	
	private double xOffset = 0;
	private double yOffset = 0;

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("assets/gui2.fxml"));

		Scene scene = new Scene(root);

		primaryStage.setTitle("Procrastination Application");
		primaryStage.initStyle(StageStyle.UNDECORATED);

		root.setOnMousePressed(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				xOffset = event.getSceneX();
				yOffset = event.getSceneY();
			}
		});
		root.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				primaryStage.setX(event.getScreenX() - xOffset);
				primaryStage.setY(event.getScreenY() - yOffset);
			}
		});

		primaryStage.setScene(scene);
		primaryStage.show();

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
	
	private void updateGUI() {
		// Make a public Scrollpane, with a HBox, divided into VBox's 
		// Check to see how many VBox's there are, for the last one (Greatest num), check to see how many 
		
	}
}