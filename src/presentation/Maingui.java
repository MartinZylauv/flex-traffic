package presentation;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.stage.Stage;
import logic.FTPControllerImpl;
import persistence.DBCreator;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;


public class Maingui extends Application {
	@Override
	public void start(Stage primaryStage) {
		FTPControllerImpl ftp = new FTPControllerImpl();
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
				ftp.createDB();//TODO SQL ALERT DB KUNNE IKKE LAVES, KONTAKT ADMINISTRATOR 
				
			} catch (Exception exc) {
				exc.printStackTrace();
			}
	}

	public static void main(String[] args) {
		launch(args);
	}
}
