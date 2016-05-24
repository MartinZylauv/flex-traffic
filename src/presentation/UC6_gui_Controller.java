package presentation;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.ResourceBundle;

import domain.Bil;
import domain.KoerselHistorikImpl;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ContextMenu;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Beskeder;
import logic.CSVWriter;
import logic.FTPControllerImpl;
import logic.Tilstande;

public class UC6_gui_Controller implements Initializable {

	public UC6_gui_Controller(LoggedIn loggedin) {
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
	private TableColumn<KoerselHistorikImpl, Integer> kundenummerCol;
	@FXML 
	private TableColumn<KoerselHistorikImpl, String> adminKommentar;
	@FXML 
	private TableColumn<KoerselHistorikImpl, Boolean> erGodkendt;
	@FXML
	private DatePicker startDato;
	
	@FXML
	private DatePicker slutDato;
	@FXML
	private Button eksporter;
	@FXML
	private Button soeg;
	@FXML
	private CheckBox afholdteKoersler;
	
	@FXML
	private Button godkendKoersel;
	
	@FXML
	private ChoiceBox<Integer> vaelgBilChoiceBox;
	
	@FXML
	private TextField kundenummerField;
	
	@FXML
	private Label bilTildelt;
	

	FTPControllerImpl ftp = new FTPControllerImpl();
	Advarsler advarsler = new Advarsler();
	Tilstande tilstand;
	
	LoggedIn loggedin = null;
	int kundenummer;
	String navnDefault;
	String emailDefault;
	long tlfDefault;
	ArrayList<KoerselHistorikImpl> koerselhistorik;
	ContextMenu menu = new ContextMenu();
	MenuItem kommenter = new MenuItem("Kommenter");
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		menu.getItems().add(kommenter);
		koerselsHistorik.setContextMenu(menu);
		try {
			
			vaelgBilChoiceBox.setItems( FXCollections.observableArrayList(ftp.getBiler()));
		} catch (SQLException e1) {
			// TODO sql alert igen
			e1.printStackTrace();
		}

	kundenummer = 0;
	
		
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
		 kundenummerCol.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Integer>("brugerNummer"));
		 adminKommentar.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, String>("adminKommentar"));
		 erGodkendt.setCellValueFactory(new PropertyValueFactory<KoerselHistorikImpl, Boolean>("erGodkendt"));
		 
		 ObservableList<KoerselHistorikImpl> oListHistorik = null;
		 
		try {
			koerselhistorik = ftp.anmodOmBrugeresKørselHistorik(kundenummer, null, null);
			oListHistorik = FXCollections.observableArrayList(ftp.anmodOmBrugeresKørselHistorik(kundenummer, null, null));
		} catch (SQLException e) {
			advarsler.SQLFejl().showAndWait();
			e.printStackTrace();
		}
		 
			koerselsHistorik.setItems(oListHistorik );
			
			kommenter.setOnAction(new EventHandler<ActionEvent>(){
				public void handle(ActionEvent e){
					UC9_gui_Controller uc9 = new UC9_gui_Controller(koerselsHistorik.getSelectionModel().getSelectedItem().getID());
					FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UC9.fxml"));
					fxmlLoader.setController(uc9);
					Parent root = null;
					try {
						root = (Parent) fxmlLoader.load();
					} catch (IOException e1) {
						// TODO Indsæt fejlboks
						e1.printStackTrace();
					}
					Stage stage = new Stage();
					stage.setTitle("Kommenter");
					stage.setScene(new Scene(root));
					stage.show();
				}
			});
		
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
       	
			csv.writeToCSV(koerselhistorik, file,loggedin.getAdmin());
		} catch (IOException e) {
			advarsler.IOFejl().showAndWait();
			e.printStackTrace();
		}
	}

	@FXML
	public void haandterSoeg() {
		
		if(kundenummerField.getText().isEmpty()){
				kundenummer = 0;
		}else{
		kundenummer = Integer.parseInt(kundenummerField.getText());
		}
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
	
	@FXML
	public void haandterGodkendKoersel(){
		if(vaelgBilChoiceBox.getValue()!=null){
			if(!koerselsHistorik.getSelectionModel().isEmpty()){
				ftp.tildelBil(vaelgBilChoiceBox.getValue());
				try {
					ftp.angivKoerselTilVedligeholdelse(koerselsHistorik.getSelectionModel().getSelectedItem());
					
					koerselsHistorik.setItems(FXCollections.observableArrayList(ftp.anmodOmBrugeresKørselHistorik(kundenummer, null, null)) );
				} catch (SQLException e) {
					// TODO SQL ALERT HER
					e.printStackTrace();
				}
			}
			//alert med at en kørsel ikke er valgt.
		}
		//alert med at en bil ikke er valgt.
	}

	@FXML
	public void haandterTableKlik(){
		try {
			if(koerselsHistorik.getSelectionModel().getSelectedItem().getErGodkendt()){
			bilTildelt.setText(ftp.getBilFraID(koerselsHistorik.getSelectionModel().getSelectedItem().getID()).toString());
			} else{
				bilTildelt.setText("");
			}
		} catch (SQLException e) {
			advarsler.SQLFejl().showAndWait();
			e.printStackTrace();
		}
	}
	@FXML
	public void haandterCheckbox(){
		if(afholdteKoersler.isSelected()){
			if(kundenummerField.getText().isEmpty()){
				kundenummer = 0;
		}else{
		kundenummer = Integer.parseInt(kundenummerField.getText());
		}
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
				oListHistorik = FXCollections.observableArrayList(ftp.anmodOmBrugeresKørselHistorikAfholdt(kundenummer, startDato, slutDato));
			} catch (SQLException e) {
				advarsler.SQLFejl().showAndWait();
				e.printStackTrace();
			}
			 
				koerselsHistorik.setItems(oListHistorik );
		;
		} else if(afholdteKoersler.isSelected()==false){
			if(kundenummerField.getText().isEmpty()){
				kundenummer = 0;
		}else{
		kundenummer = Integer.parseInt(kundenummerField.getText());
		}
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
		;
		}
	}
	}


