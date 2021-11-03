package gui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MainGUI extends Application {

	@Override
	public void start(Stage primaryStage) throws Exception {

		Parent root = FXMLLoader.load(getClass().getResource("_MainGUI/gui.fxml"));
		
		Scene scene = new Scene(root, 300, 275);

		primaryStage.setTitle("Procrastination Application");
		primaryStage.setScene(scene);
		primaryStage.show();
	
		// FXML needs to be edited to reflect
	
	}

	public void start() {
		launch();

	}
}
