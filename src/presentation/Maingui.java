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
		
		LoggedIn loggedin = new LoggedIn();
		try {
			FXMLLoader loader = new FXMLLoader();
			LoginController logincontroller = new LoginController(loggedin);
			loader.setLocation(Maingui.class.getResource("Login.fxml"));
			loader.setController(logincontroller);
			
				AnchorPane mainWindow = (AnchorPane) loader.load();
				Scene scene = new Scene(mainWindow);
				
				primaryStage.setScene(scene);
				primaryStage.show();
			} catch (Exception exc) {
			}
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
