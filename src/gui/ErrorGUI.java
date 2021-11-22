// For displaying errors when everything else crashes. Make an error method in main package that calls this 

package gui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class ErrorGUI extends Application {
		private static Text textStackTrace;
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		Text textError = new Text("Critical Error");
		
		textError.setId("textError");
		textStackTrace.setId("textStackTrace");
		
		textError.setFill(Color.web("#f2f2f2"));
		textError.setStyle("-fx-font-size: 53");
		
		textStackTrace.setFill(Color.web("#f2f2f2"));
		textStackTrace.setStyle("-fx-font-size: 20");
		textStackTrace.setWrappingWidth(450);
		
		VBox VBox = new VBox(textError, textStackTrace);
			
		VBox.setAlignment(Pos.TOP_CENTER);
		VBox.setPadding(new Insets(40, 0, 0, 0));
		VBox.setSpacing(20);
	
		Scene scene = new Scene(VBox, 500, 250);
				
		scene.setFill(Color.web("#ff6961"));
		
		primaryStage.setTitle("Procrastination Application: Critical Error");
		primaryStage.setScene(scene);
		primaryStage.setOpacity(0.85);
		primaryStage.getIcons().add(new Image("file:src/gui/assets/logo.png"));
		primaryStage.show();
		
	}

	public static void display(Exception e) {
		textStackTrace = new Text(e.toString());
		launch();
		
	}
}
