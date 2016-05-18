package presentation;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.FTPControllerImpl;
import persistence.ProfilKartotekImpl;

public class LoginController implements Initializable {

	LoggedIn loggedin;

	public LoginController(LoggedIn loggedin) {
		this.loggedin = loggedin;
	}

	@FXML
	private TextField kundenr;
	@FXML
	private PasswordField kodeord;
	@FXML
	private Button logInd;

	int kundenummer; 
	Alert fejl = new Alert(AlertType.ERROR);
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		

	}

	@FXML
	public void haandterLogInd() {
		FTPControllerImpl ftp = new FTPControllerImpl();
		try {
			
			loggedin.setkundenummer(Integer.parseInt(kundenr.getText()));
			if(ftp.checkProfil(loggedin.getKundenummer())){ //TODO her ses særligt fokus på 3 lags modellen, vi kunne lige så godt have spurgt profilkartoteket direkte, me nvi vælger at spørge controlleren først så det kan afkobles senere.
				if(ftp.checkAdmin(loggedin.getKundenummer())){
					MainHubControllerAdmin mainhubadmin = new MainHubControllerAdmin(loggedin,ftp.checkAdmin(loggedin.getKundenummer()));
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainHubAdmin.fxml"));
					fxmlLoader.setController(mainhubadmin);
					Parent root1 = (Parent) fxmlLoader.load();
					Stage stage = new Stage();
					stage.setTitle("Flexturs Program v1.0");
					stage.setScene(new Scene(root1));
					stage.show();
					logInd.getScene().getWindow().hide();  
				} else{
				MainHubController mainhub = new MainHubController(loggedin,ftp.checkAdmin(loggedin.getKundenummer()));
				FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainHub.fxml"));
				fxmlLoader.setController(mainhub);
				Parent root1 = (Parent) fxmlLoader.load();
				Stage stage = new Stage();
				stage.setTitle("Flexturs Program v1.0");
				stage.setScene(new Scene(root1));
				stage.show();
				logInd.getScene().getWindow().hide(); 
				}//får fat i en af noderne og får dens vindue, og lukker derefter vinduet.
			} else{
				fejl.setTitle("Fejl i kundenummer");
				fejl.setHeaderText("Log-ind fejl.");
				fejl.setContentText("Der findes ingen kunde med kundenummret du angav. Prøv venligst igen."); //TODO enum fejlbesked tak.
				fejl.showAndWait();
			}
		} catch (SQLException e1) {
			// TODO en form for fejlbesked tak.
			e1.printStackTrace();
		}catch (IOException e) {
			//TODO EN FORM FOR FEJLBESKED.
			e.printStackTrace();
		}

	}

}
