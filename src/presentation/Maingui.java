package presentation;
	
import java.io.IOException;
import java.sql.SQLException;

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
		Advarsler advarsel = new Advarsler();
		FTPControllerImpl ftp = new FTPControllerImpl();
		LoggedIn loggedin = new LoggedIn();
		
			
			FXMLLoader loader = new FXMLLoader();
			LoginController logincontroller = new LoginController(loggedin);
			loader.setLocation(Maingui.class.getResource("Login.fxml"));
			loader.setController(logincontroller);
			
				AnchorPane mainWindow = null;
				try {
					mainWindow = (AnchorPane) loader.load();
				} catch (IOException e1) {
				advarsel.IOFejl().showAndWait();
				}
				Scene scene = new Scene(mainWindow);
				
				primaryStage.setScene(scene);
				primaryStage.show();
				try {
					ftp.createDB();
				} catch (SQLException e) {
					advarsel.SQLFejlDBCreate().showAndWait();
					e.printStackTrace();
				}
				
			
	}

	public static void main(String[] args) {
		launch(args);
	}
}
