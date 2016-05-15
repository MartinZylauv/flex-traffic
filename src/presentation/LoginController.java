package presentation;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import domain.Profil;
import domain.SlutDestination;
import domain.SlutDestinationImpl;
import domain.StartDestination;
import domain.StartDestinationImpl;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.FTPController;
import logic.FTPControllerImpl;
import logic.InvalidInformationException;
import logic.Tilstande;
import sats.UnknownKommuneException;

public class LoginController implements Initializable{

	LoggedIn loggedin;
	
public LoginController(LoggedIn loggedin){
	this.loggedin=loggedin;
}

	
	int kundenummer = 1; //TODO BARE TIL MIDLERTIDIG REFERENCE ER KUNDENUMMERET 1
	@FXML
	private TextField kundenr;
	
	@FXML
	private PasswordField kodeord;
	
	@FXML
	private Button logInd;
	
	

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		System.out.println(kundenummer);
		
	
	}

	

	

	@FXML
	public void haandterLogInd() { 
		loggedin.setkundenummer(kundenummer);
		MainHubController mainhub = new MainHubController(loggedin);
	        try {
	        	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainHub.fxml"));
	        	fxmlLoader.setController(mainhub);
	            Parent root1 = (Parent) fxmlLoader.load();
	            Stage stage = new Stage();
	            stage.setTitle("ABC");
	            stage.setScene(new Scene(root1));  
	            stage.show();
	            
	           
	        } catch (IOException e) {
	            e.printStackTrace();
	        }	
			
			
		}
	
	
}
