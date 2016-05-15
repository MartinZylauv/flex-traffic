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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.FTPControllerImpl;
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

	FTPControllerImpl ftp = new FTPControllerImpl();
	Alert fejl = new Alert(AlertType.WARNING);
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
		System.out.println(loggedin.getKundenummer());
		ftp.setKundenummer(kundenummer);
		try {
			profil = ftp.anmodOmProfil(kundenummer);
		} catch (SQLException e) {
			fejl.setTitle("Fejl");
			fejl.setHeaderText("ups..");
			fejl.setContentText(
					"Der skete en uventet fejl. Prøv venligst at genstarte programmet, eller at kontakte kundeservice."); // TODO
																															// fejl
																															// koder.
			e.printStackTrace();
		}
		navnDefault = profil.getFuldtNavn();
		emailDefault = profil.getEmail();
		tlfDefault = profil.getTlfNummer();
		kundeNummer.setText(String.valueOf(kundenummer));
		fulde_navn.setText(navnDefault);
		email.setText(emailDefault);
		tlfnummer.setText(String.valueOf(tlfDefault));
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
		tlfDefault = Long.valueOf(tlfnummer.getText());

		try {
			ftp.indtastNyeInformationer(navnDefault, emailDefault, tlfDefault);

			gem.setVisible(false);
			rediger.setVisible(true);
			email.setEditable(false);
			fulde_navn.setEditable(false);
			tlfnummer.setEditable(false);
		} catch (SQLException e) {
			fejl.setTitle("Fejl");
			fejl.setHeaderText("ups..");
			fejl.setContentText(
					"Der skete en uventet fejl. Prøv venligst at genstarte programmet, eller at kontakte kundeservice."); // TODO
																															// fejl
																															// koder.
			e.printStackTrace();
		}
	}

	@FXML
	public void haandteerHjaelp() {
		// TODO tilføj en form for hjælpe alert boks.
	}

	@FXML
	public void haandteerTilbage() { // TODO måske skal tilbage ikke være med
										// pga tabs?
		email.setEditable(true);
		fulde_navn.setEditable(true);
		tlfnummer.setEditable(true);
		email.setText(emailDefault);
		fulde_navn.setText(navnDefault);
		tlfnummer.setText(String.valueOf(tlfDefault));
		tilbage.setText("Tilbage"); //TODO set til en final static øverst, meget pænere
	}
}
