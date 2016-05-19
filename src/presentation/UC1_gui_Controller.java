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
	public void haandterAccepter() {   //TODO lav noteditable textfields for de ting prisen udregnes for, så en "udregn ny pris knap"


		try {
			Time t = new Time(Integer.parseInt(tidHChoice.getValue()), Integer.parseInt(tidMChoice.getValue()), 0);
			start.setAdresse(startAdresseFelt.getText());
			start.setBynavn(startByFelt.getText());
			if(startPostnummerFelt.getText().length()==4){
			start.setPostnummer(Integer.parseInt(startPostnummerFelt.getText()));
			} else{
				postNummerFejl();
			}
			slut.setAdresse(slutAdresseFelt.getText());
			slut.setBynavn(slutByFelt.getText());
			if(slutPostnummerFelt.getText().length()==4){
				slut.setPostnummer(Integer.parseInt(slutPostnummerFelt.getText()));
				} else{
					postNummerFejl();
				}
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
			nummerFejl();
		} catch (InvalidInformationException e) {
			setFejlIndtastning();
			fejl.setContentText(e.getMessage());
			fejl.showAndWait();
		} catch(SQLException e){
			setFejlSQL();
		} catch(NullPointerException e){
			setFejlIndtastning();
			fejl.setContentText(Beskeder.MANGLER.getDescription());
			fejl.showAndWait();
		}
	}

	@FXML
	public void haandterUdregn()
			throws NumberFormatException, SQLException, UnknownKommuneException, InterruptedException {
		
		
		try{
		
		if(startPostnummerFelt.getText().length()==4 && slutPostnummerFelt.getText().length()==4 ){
			start.setAdresse(startAdresseFelt.getText());
			start.setBynavn(startByFelt.getText());
			start.setPostnummer(Integer.parseInt(startPostnummerFelt.getText()));
			slut.setPostnummer(Integer.parseInt(slutPostnummerFelt.getText()));
			slut.setAdresse(slutAdresseFelt.getText());
			slut.setBynavn(slutByFelt.getText());
			try {
				ftp.getPrisTilbud(start, slut, Double.parseDouble(kmFelt.getText()),Date.valueOf(datoVaelger.getValue()));
			} catch (InvalidInformationException e) {
				setFejlIndtastning();
				fejl.setContentText(e.getMessage());
				e.printStackTrace();
			}
			progressBar.setVisible(true);
			prisLabel.setText("Udregner pris, vent venligst...");
			} else{
				postNummerFejl();
			}
		
		} catch(NumberFormatException e){
			nummerFejl();
		} catch(NullPointerException e){
			setDatoFejl();
		}
		
		
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
	public void nummerFejl(){
		fejl.setTitle("Nummer fejl");
		fejl.setHeaderText("Fejl i et eller flere felter");
		fejl.setContentText(Beskeder.NUMMMER_FEJL.getDescription());
		fejl.showAndWait();
	}
	
	public void setFejlIndtastning(){
		fejl.setTitle("Indtastningsfejl");
		fejl.setHeaderText("Indtastningsfejl");
	}
	
	public void setFejlSQL(){
		
		fejl.setTitle("SQL fejl");
		fejl.setHeaderText("Fejl i databasen");
		fejl.setContentText(Beskeder.UKENDT_SQL.getDescription()); 
		fejl.showAndWait();
	
}
	public void setDatoFejl(){
		fejl.setTitle("Dato fejl.");
		fejl.setHeaderText("Fejl i dato");
		fejl.setContentText(Beskeder.DATO_MANGLER.getDescription());
		fejl.showAndWait();
	}
	
	public void postNummerFejl(){
		fejl.setTitle("Postnummer fejl.");
		fejl.setHeaderText("Fejl i postnummer");
		fejl.setContentText(Beskeder.POSTNR_FEJL.getDescription());
		fejl.showAndWait();
	}
	
	}
