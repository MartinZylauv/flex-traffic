package presentation;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;


public class Maingui extends Application {
	@Override
	public void start(Stage primaryStage) {
		try {
			FXMLLoader loader = new FXMLLoader();

			loader.setLocation(Maingui.class.getResource("UC2-3.fxml"));
			
				AnchorPane mainWindow = (AnchorPane) loader.load();
				Scene scene = new Scene(mainWindow);
				
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception exc) {
			}
	}
	
	public static void main(String[] args) {
		System.out.println("hey det dur?");
		launch(args);
	}
}
