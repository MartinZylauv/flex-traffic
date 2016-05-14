package presentation;

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
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import logic.FTPController;
import logic.FTPControllerImpl;
import logic.InvalidInformationException;
import logic.Tilstande;
import sats.UnknownKommuneException;

public class UC23_gui_Controller implements Initializable, Observer {



	
	long kundenummer = 1; //TODO BARE TIL MIDLERTIDIG REFERENCE ER KUNDENUMMERET 1
	@FXML
	private Label kundeNummer;
	
	@FXML
	private TextField fulde_navn;
	
	@FXML
	private TextField email;
	
	@FXML
	private TextField tlfnummer;
	
	@FXML
	private Button hjaelp;
	
	@FXML
	private Button rediger;
	
	@FXML
	private Button tilbage;
	
	@FXML
	private Button gem;
	
	String navnDefault;
	String emailDefault;
	long tlfDefault;
	FTPControllerImpl ftp = new FTPControllerImpl();
	@FXML
	private Button accepterKnap;

	private boolean startAdresseAendret = false;
	private boolean startPostnummerAendret = false;
	private boolean startByAendret = false;
	private boolean slutAdresseAendret = false;
	private boolean slutPostnummerAendret = false;
	private boolean slutByAendret = false;
	private boolean kmAendret = false;
	Tilstande tilstand;
	double pris;

	
	ObservableList<Integer> cursors = FXCollections.observableArrayList(01,02,03); //husk 00 pls
	StartDestination start = new StartDestinationImpl();
	SlutDestination slut = new SlutDestinationImpl();

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Profil profil = null;
		ftp.setKundenummer(kundenummer);
		try {
			
			profil = ftp.anmodOmProfil(kundenummer);
			navnDefault =profil.getFuldtNavn() ;
			emailDefault = profil.getEmail();
			tlfDefault = profil.getTlfNummer();
			kundeNummer.setText(String.valueOf(kundenummer));
			fulde_navn.setText(navnDefault);
			email.setText(emailDefault);
			tlfnummer.setText(String.valueOf(tlfDefault));
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}
	
	}

	

	@Override
	public void update(Observable arg0, Object arg) {
		
		

	}

	@FXML
	public void haandterRediger() { // TODO STAVEFEJL

		gem.setVisible(true);
		rediger.setVisible(false);
		email.setEditable(true);
fulde_navn.setEditable(true);
tlfnummer.setEditable(true);

		
			navnDefault = fulde_navn.getText();
			emailDefault = email.getText();
			tlfDefault = Long.valueOf(tlfnummer.getText());
			
			
			
			
		}
	
	@FXML
	public void haandterGem() { // TODO STAVEFEJL
		navnDefault = fulde_navn.getText();
		emailDefault = email.getText();
		tlfDefault = Long.valueOf(tlfnummer.getText());
		

		
	
			try {
				ftp.indtastNyeInformationer(navnDefault,emailDefault ,tlfDefault );
				
				gem.setVisible(false);
				rediger.setVisible(true);
				email.setEditable(false);
				fulde_navn.setEditable(false);
				tlfnummer.setEditable(false);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			
		}
		
		
	

	@FXML
	public void haandteerHjaelp() {

		
	}
	
	@FXML
	public void haandteerTilbage(){

		
	}
}
