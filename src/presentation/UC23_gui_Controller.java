package presentation;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import domain.Profil;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.FTPControllerImpl;
import logic.InvalidInformationException;
import logic.Tilstande;

public class UC23_gui_Controller implements Initializable {

	public UC23_gui_Controller(LoggedIn loggedin) {
		this.loggedin = loggedin;
	}

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
	@FXML
	private Button accepterKnap;
	@FXML
	private Label cprNummer;

	FTPControllerImpl ftp = new FTPControllerImpl();
	Advarsler advarsler = new Advarsler();
	Tilstande tilstand;
	double pris;
	LoggedIn loggedin = null;
	long kundenummer;
	String navnDefault;
	String emailDefault;
	long tlfDefault;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {	
		Profil profil = null;

		kundenummer = loggedin.getKundenummer();
		ftp.setKundenummer(kundenummer);
		try {
			profil = ftp.anmodOmProfil(kundenummer);
		} catch (SQLException e) {
			advarsler.SQLFejl().showAndWait();																																																		
			e.printStackTrace();}
		navnDefault = profil.getFuldtNavn();
		emailDefault = profil.getEmail();
		tlfDefault = profil.getTlfNummer();
		kundeNummer.setText(String.valueOf(kundenummer));
		fulde_navn.setText(navnDefault);
		email.setText(emailDefault);
		tlfnummer.setText(String.valueOf(tlfDefault));
		cprNummer.setText(profil.getCPR());
	}

	@FXML
	public void haandterRediger() {
		gem.setVisible(true);
		rediger.setVisible(false);
		email.setEditable(true);
		fulde_navn.setEditable(true);
		tlfnummer.setEditable(true);
		tilbage.setText("Anullér rediger");
		navnDefault = fulde_navn.getText();
		emailDefault = email.getText();
		tlfDefault = Long.valueOf(tlfnummer.getText());
	}

	@FXML
	public void haandterGem() {
		navnDefault = fulde_navn.getText();
		emailDefault = email.getText();
		try {
		if(tlfnummer.getText().isEmpty()){
			tlfDefault = 0;
		}else if(tlfnummer.getText().length()!= 8){
		throw new NumberFormatException();
		} else{
			tlfDefault = Long.valueOf(tlfnummer.getText());
		}

		
			ftp.indtastNyeInformationer(navnDefault, emailDefault, tlfDefault);

			gem.setVisible(false);
			rediger.setVisible(true);
			email.setEditable(false);
			fulde_navn.setEditable(false);
			tlfnummer.setEditable(false);
		} catch (SQLException e) {
			
			
			e.printStackTrace();
		} catch (InvalidInformationException e) {
			advarsler.indtastningFejl(e).showAndWait();
		} catch (NumberFormatException e){
			advarsler.tlfFejl().showAndWait();
		}
	}

	@FXML
	public void haandteerHjaelp() {
		// TODO tilføj en form for hjælpe alert boks.
	}

	@FXML
	public void haandteerTilbage() { 
		email.setEditable(true);
		fulde_navn.setEditable(true);
		tlfnummer.setEditable(true);
		email.setText(emailDefault);
		fulde_navn.setText(navnDefault);
		tlfnummer.setText(String.valueOf(tlfDefault));

	}
}
