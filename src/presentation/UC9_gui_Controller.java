package presentation;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ResourceBundle;

import domain.SlutDestination;
import domain.SlutDestinationImpl;
import domain.StartDestination;
import domain.StartDestinationImpl;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.Beskeder;
import logic.FTPControllerImpl;
import logic.InvalidInformationException;
import logic.Tilstande;
import sats.UnknownKommuneException;

public class UC9_gui_Controller implements Initializable {

	public UC9_gui_Controller(int koerselID) {
		this.koerselID = koerselID;
	}

	
	@FXML
	private Button gemKnap;
	@FXML
	private Button annullerKnap;
	@FXML
	private TextArea kommentarArea;

	int koerselID;
	
	FTPControllerImpl ftp = new FTPControllerImpl();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			kommentarArea.setText(ftp.getKommentar(koerselID));
		} catch (SQLException e) {
			// TODO indsæt fejlboks
			e.printStackTrace();
		}
	}
	@FXML
	public void haandterGem(){
		try {
			ftp.indtastKommentar(kommentarArea.getText(), koerselID);
		} catch (SQLException e) {
			// TODO FEJLBOKS
			e.printStackTrace();
		}
		gemKnap.getScene().getWindow().hide();
	}
	
	@FXML
	public void haandterAnnuller(){
		annullerKnap.getScene().getWindow().hide();
	}
		}
