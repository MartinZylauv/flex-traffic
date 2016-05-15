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
import javafx.scene.control.ProgressBar;
import javafx.scene.control.Tab;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import logic.FTPController;
import logic.FTPControllerImpl;
import logic.InvalidInformationException;
import logic.Tilstande;
import sats.UnknownKommuneException;

public class MainHubController implements Initializable {

	public MainHubController(LoggedIn loggedin) {
		this.loggedin = loggedin;
	}

	LoggedIn loggedin = null;
	int kundenummer;
	@FXML
	private Tab bestilKoersel;

	@FXML
	private Tab profilOplysninger;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			System.out.println(loggedin.getKundenummer());
			UC1_gui_Controller uc1 = new UC1_gui_Controller(loggedin);
			UC23_gui_Controller uc23 = new UC23_gui_Controller(loggedin);
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UC1.fxml"));
			fxmlLoader.setController(uc1); // TODO:sætter controlleren manuelt
											// så der kan kaldes en constructor
											// med brugernavnet så vi ved hvem
											// der er logget ind, hele vejen ned
											// endda.
			Parent root = (Parent) fxmlLoader.load();
			bestilKoersel.setContent(root);

			FXMLLoader fxml2Loader = new FXMLLoader(getClass().getResource("UC2-3.fxml"));
			fxml2Loader.setController(uc23);
			Parent root1 = (Parent) fxml2Loader.load();
			profilOplysninger.setContent(root1);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setKundenummer(int kundenummer) {
		this.kundenummer = kundenummer;
	}
}
