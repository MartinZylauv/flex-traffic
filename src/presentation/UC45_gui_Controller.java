package presentation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import domain.KoerselHistorikImpl;
import domain.KoerselImpl;
import domain.Profil;
import domain.SlutDestination;
import domain.StartDestination;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Beskeder;
import logic.CSVWriter;
import logic.FTPControllerImpl;
import logic.Tilstande;

public class UC45_gui_Controller implements Initializable {

	public UC45_gui_Controller(LoggedIn loggedin) {
		this.loggedin = loggedin;
	}

	@FXML
	private TableView <KoerselHistorikImpl>koerselsHistorik;
	@FXML 
	private TableColumn<KoerselHistorikImpl,Date> dato;
	@FXML 
	private TableColumn<KoerselHistorikImpl,Time> tidspunkt;
	@FXML 
	private TableColumn <KoerselHistorikImpl,String> startAdresse;
	@FXML 
	private TableColumn <KoerselHistorikImpl,Integer> startPostnr;
	@FXML 
	private TableColumn<KoerselHistorikImpl,String> slutAdresse;
	@FXML 
	private TableColumn<KoerselHistorikImpl,Integer> slutPostnr;
	@FXML 
	private TableColumn<KoerselHistorikImpl,Double> antalKm;
	@FXML 
	private TableColumn<KoerselHistorikImpl,Double> pris;
	@FXML 
	private TableColumn<KoerselHistorikImpl,String> kommentar;
	@FXML 
	private TableColumn<KoerselHistorikImpl, Integer> hjaelpemidler;
	@FXML 
	private TableColumn<KoerselHistorikImpl,Integer> bagage;
	@FXML 
	private TableColumn<KoerselHistorikImpl, Integer> antalPersoner;
	@FXML
	private DatePicker startDato;
	
	@FXML
	private DatePicker slutDato;
	@FXML
	private Button eksporter;
	@FXML
	private Button soeg;
	

	FTPControllerImpl ftp = new FTPControllerImpl();
	
	Tilstande tilstand;
	Advarsler advarsler = new Advarsler();
	LoggedIn loggedin = null;
	int kundenummer;
	String navnDefault;
	String emailDefault;
	long tlfDefault;
	ArrayList<KoerselHistorikImpl> koerselhistorik;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {

	
		kundenummer = loggedin.getKundenummer();
		ftp.setKundenummer(kundenummer);
		
		 dato.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Date>("dato"));
		 antalPersoner.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Integer>("antalPersoner"));
		 hjaelpemidler.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Integer>("antalHjaelpemidler"));
		 bagage.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Integer>("antalBagage"));
		 kommentar.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, String>("kommentar"));
		 tidspunkt.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Time>("tid"));
		 antalKm.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Double>("antalKm"));
		 pris.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Double>("pris"));
		 startAdresse.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, String>("startAdresse"));
		 startPostnr.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Integer>("startPostnummer"));
		 slutAdresse.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, String>("slutAdresse"));
		 slutPostnr.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Integer>("slutPostnummer"));
		 
		 ObservableList<KoerselHistorikImpl> oListHistorik = null;
		try {
			koerselhistorik = ftp.anmodOmBrugeresKørselHistorik(kundenummer, null, null);
			oListHistorik = FXCollections.observableArrayList(ftp.anmodOmBrugeresKørselHistorik(kundenummer, null, null));
		} catch (SQLException e) {
			advarsler.SQLFejl().showAndWait();
			e.printStackTrace();
		}
		 
			koerselsHistorik.setItems(oListHistorik );
		
		}
		 
		     
		
	

	@FXML
	public void haandterEksporter() {
		FileChooser fileChooser = new FileChooser();
		fileChooser.setTitle("Eksporter historik");
         CSVWriter csv = new CSVWriter();
         
         File file = fileChooser.showSaveDialog(eksporter.getScene().getWindow());
         if(!file.toString().toLowerCase().endsWith(".csv")){
        	 file = new File(file + ".csv");
         }
       
         try {
        	 
			csv.writeToCSV(koerselhistorik, file, loggedin.getAdmin());
		} catch (IOException e) {
			advarsler.IOFejl().showAndWait();
			e.printStackTrace();
		}
	}

	@FXML
	public void haandterSoeg() {
		
		Date startDato;
		Date slutDato;
		if(this.slutDato.getValue() == null || this.startDato.getValue() == null){
			startDato = null;
			slutDato = null;
		} else{
			startDato = Date.valueOf(this.startDato.getValue());
			slutDato = Date.valueOf(this.slutDato.getValue());
			
		}
		
		 ObservableList<KoerselHistorikImpl> oListHistorik = null;
			try {
				koerselhistorik = ftp.anmodOmBrugeresKørselHistorik(kundenummer, startDato, slutDato);
				oListHistorik = FXCollections.observableArrayList(ftp.anmodOmBrugeresKørselHistorik(kundenummer, startDato, slutDato));
			} catch (SQLException e) {
				advarsler.SQLFejl().showAndWait();
				e.printStackTrace();
			}
			 
				koerselsHistorik.setItems(oListHistorik );
		}
	

	}


