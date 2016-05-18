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

public class UC1_gui_Controller implements Initializable {

	public UC1_gui_Controller(LoggedIn loggedin) {
		this.loggedin = loggedin;
	}

	
	@FXML
	private TextField startAdresseFelt;
	@FXML
	private TextField startPostnummerFelt;
	@FXML
	private TextField startByFelt;
	@FXML  
	private TextField slutAdresseFelt;
	@FXML
	private TextField slutPostnummerFelt;
	@FXML
	private TextField slutByFelt;
	@FXML
	private TextField kmFelt;
	@FXML
	private ChoiceBox<Integer> antalHjaelpleChoice;
	@FXML
	private ChoiceBox<Integer> antalBagageChoice;
	@FXML
	private ChoiceBox<Integer> antalPersonerChoice;
	@FXML
	private DatePicker datoVaelger;
	@FXML
	private TextArea kommentarArea;
	@FXML
	private ChoiceBox<String> tidHChoice;
	@FXML
	private ChoiceBox<String> tidMChoice;
	@FXML
	private Label prisLabel;
	@FXML
	private ProgressBar progressBar;
	@FXML
	private Button udregnKnap;
	@FXML
	private Button accepterKnap;

	private boolean startAdresseAendret = false;
	private boolean startPostnummerAendret = false;
	private boolean startByAendret = false;
	private boolean slutAdresseAendret = false; // TODO Slet eventuelt, de skal
												// ikke bruges p.t.
	private boolean slutPostnummerAendret = false;
	private boolean slutByAendret = false;
	private boolean kmAendret = false;
	
	
	
	LoggedIn loggedin = null;
	FTPControllerImpl ftp = new FTPControllerImpl();
	int brugerummer;
	Tilstande tilstand;
	double pris;
	Alert info = new Alert(AlertType.INFORMATION);
	Alert fejl = new Alert(AlertType.ERROR);

	ObservableList<String> minutter = FXCollections.observableArrayList("00", "05", "10", "15", "20", "25", "30", "35",
			"40", "45", "50", "55");
	ObservableList<String> timer = FXCollections.observableArrayList("00", "01", "02", "03", "04", "05", "06", "07",
			"08", "09", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23");
	ObservableList<Integer> nulTilNi = FXCollections.observableArrayList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
	StartDestination start = new StartDestinationImpl();
	SlutDestination slut = new SlutDestinationImpl();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		brugerummer = loggedin.getKundenummer();
		tidHChoice.setItems(timer);
		tidMChoice.setItems(minutter);
		antalBagageChoice.setItems(nulTilNi);
		antalHjaelpleChoice.setItems(nulTilNi);
		antalPersonerChoice.setItems(nulTilNi);
	}
	//TODO check hvad decrepated er herunder på stackoverflow.
	public void haandterAccepter() {   //TODO lav noteditable textfields for de ting prisen udregnes for .
		Time t = new Time(Integer.parseInt(tidHChoice.getValue()), Integer.parseInt(tidMChoice.getValue()), 0);
		start.setAdresse(startAdresseFelt.getText());
		start.setBynavn(startByFelt.getText());
		start.setPostnummer(Integer.parseInt(startPostnummerFelt.getText()));
		slut.setAdresse(slutAdresseFelt.getText());
		slut.setBynavn(slutByFelt.getText());
		slut.setPostnummer(Integer.parseInt(slutPostnummerFelt.getText()));

		try {
			Date dato = Date.valueOf(datoVaelger.getValue());
			ftp.angivInformationer(start, slut, dato, antalPersonerChoice.getValue(), antalHjaelpleChoice.getValue(),
					antalBagageChoice.getValue(), kommentarArea.getText(), brugerummer, t,
					Double.parseDouble(kmFelt.getText()));
			Beskeder besked = ftp.accepterPris();
				info.setTitle("Bestilling udført");
				info.setHeaderText(besked.toString());
				info.setContentText("Vi henter dig på:  " + start.getAdresse() + " klokken " + t );
				info.showAndWait();
			
		} catch (NumberFormatException e) {
			fejl.setHeaderText("Der er sket en fejl");
			fejl.setContentText("Du har desværre indtastet bogstaver hvor der skulle have været tal. Prøv venligst at bestille igen.");
			fejl.showAndWait();
		} catch (InvalidInformationException e) {
			fejl.setHeaderText("Der er sket en fejl");
			fejl.setContentText("Du har desværre indtastet nogle ugyldige informationer. Prøv at indtaste dine informationer igen, og så at bestille igen."); //TODO FEJLKODER
			fejl.showAndWait();
		} catch(SQLException e){
			fejl.setHeaderText("Der er sket en fejl");
			fejl.setContentText("En uventet fejl er sket. Prøv venligst at genstarte programmet eller at kontakte kundeservice."); //TODO FEJLKODER
			fejl.showAndWait();
			e.printStackTrace();
		}
	}

	@FXML
	public void haandterUdregn()
			throws NumberFormatException, SQLException, UnknownKommuneException, InterruptedException {
		
		
		
		start.setAdresse(startAdresseFelt.getText());
		start.setBynavn(startByFelt.getText());
		start.setPostnummer(Integer.parseInt(startPostnummerFelt.getText()));
		slut.setAdresse(slutAdresseFelt.getText());
		slut.setBynavn(slutByFelt.getText());
		slut.setPostnummer(Integer.parseInt(slutPostnummerFelt.getText()));
		
		ftp.getPrisTilbud(start, slut, Double.parseDouble(kmFelt.getText()),Date.valueOf(datoVaelger.getValue()));
		progressBar.setVisible(true);
		prisLabel.setText("Udregner pris, vent venligst...");
		//TODO vi skal have en form at håndtere hvis man har glemt at indtaste noget. 
		new Thread(new Runnable() {
			@Override
			public void run() {
				ftp.setPris(0);
				while (ftp.getPris() == 0 && kmFelt.getText()!="") {
					try {
						Thread.currentThread().sleep(1000);
					} catch (InterruptedException e) {
						fejl.setHeaderText("Der er sket en fejl");
						fejl.setContentText("Der er sket en uventet fejl i programmet. Prøv at genstarte det og prøve at bestille igen. Fortsætter problemet bedes de henvende dem til kundeservice.");
						fejl.showAndWait();
					}
				}
				Platform.runLater(new Runnable() {

					@Override
					public void run() {
						prisLabel.setVisible(true);
						progressBar.setVisible(false);
						prisLabel.setText(String.valueOf(ftp.getPris()));
						accepterKnap.setVisible(true);

					}
				});

			}
		}).start();

	}
	
	
	}
}