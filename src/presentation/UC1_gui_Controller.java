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
	Advarsler advarsler = new Advarsler();
Information info = new Information();

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
				advarsler.postnrFejl().showAndWait();
			}
			slut.setAdresse(slutAdresseFelt.getText());
			slut.setBynavn(slutByFelt.getText());
			if(slutPostnummerFelt.getText().length()==4){
				slut.setPostnummer(Integer.parseInt(slutPostnummerFelt.getText()));
				} else{
					advarsler.postnrFejl().showAndWait();
				}
			Date dato = Date.valueOf(datoVaelger.getValue());
			ftp.angivInformationer(start, slut, dato, antalPersonerChoice.getValue(), antalHjaelpleChoice.getValue(),
					antalBagageChoice.getValue(), kommentarArea.getText(), brugerummer, t,
					Double.parseDouble(kmFelt.getText()));
				info.bestillingInfo(ftp.accepterPris(), start.getAdresse(), t).showAndWait();
				
			
		} catch (NumberFormatException e) {
			advarsler.nummerFejl().showAndWait();
		} catch (InvalidInformationException e) {
			advarsler.indtastningFejl(e).showAndWait();	//TODO TEST
		} catch(SQLException e){
			advarsler.SQLFejl().showAndWait();
		} catch(NullPointerException e){
			advarsler.indtastningFejl(e).showAndWait();	//TODO TEST
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
				advarsler.indtastningFejl(e).showAndWait();	//TODO TEST
				e.printStackTrace();
			}
			progressBar.setVisible(true);
			prisLabel.setText("Udregner pris, vent venligst...");
			} else{
				advarsler.postnrFejl().showAndWait();
			}
		
		} catch(NumberFormatException e){
			advarsler.nummerFejl().showAndWait();
		} catch(NullPointerException e){
			advarsler.datoFejl().showAndWait();
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
						advarsler.ukendtFejl().showAndWait();
						
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
