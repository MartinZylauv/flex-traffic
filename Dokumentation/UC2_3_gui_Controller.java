package presentation;

import java.net.URL;
import java.sql.SQLException;
import java.util.Observer;
import java.util.ResourceBundle;

import domain.Profil;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import logic.FTPControllerImpl;
import persistence.ProfilKartotekImpl;

public class UC2_3_gui_Controller implements Initializable {
	
	public UC2_3_gui_Controller(long kundenummer){
		this.kundenummer = kundenummer;
	}
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
	
	String navnDefault;
	String emailDefault;
	long tlfDefault;
	FTPControllerImpl ftp = new FTPControllerImpl();
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Profil profil = null;
		ftp.setKundenummer(kundenummer);
		try {
			
			profil = ftp.anmodOmProfil(kundenummer);
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		navnDefault =profil.getFuldtNavn() ;
		emailDefault = profil.getEmail();
		tlfDefault = profil.getTlfNummer();
		kundeNummer.setText(String.valueOf(kundenummer));
		fulde_navn.setText(navnDefault);
		email.setText(emailDefault);
		tlfnummer.setText(String.valueOf(tlfDefault));
		
		
		rediger.setOnAction(new EventHandler() {
		    

			@Override
			public void handle(Event event) {
				if(rediger.getText()== "Rediger")
				{
				email.setEditable(true);
		fulde_navn.setEditable(true);
		tlfnummer.setEditable(true);
		rediger.setText("Gem");
				} else if(rediger.getText() == "Gem"){
					navnDefault = fulde_navn.getText();
					emailDefault = email.getText();
					tlfDefault = Long.valueOf(tlfnummer.getText());
					try {
						ftp.indtastNyeInformationer(navnDefault,emailDefault ,tlfDefault );
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					rediger.setText("Rediger");
					
				}
				
			}
		});
		
	}
	
	//@FXML
	public void haandterHjaelp(){
		
	}
	
	//@FXML
	public void haandterRediger(){
		if(rediger.getText()== "Rediger")
		{
		email.setEditable(true);
fulde_navn.setEditable(true);
tlfnummer.setEditable(true);
rediger.setText("Gem");
		} else if(rediger.getText() == "Gem"){
			navnDefault = fulde_navn.getText();
			emailDefault = email.getText();
			tlfDefault = Long.valueOf(tlfnummer.getText());
			try {
				ftp.indtastNyeInformationer(navnDefault,emailDefault ,tlfDefault );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			rediger.setText("Rediger");
			
		}
	}
	
	//@FXML
	public void haandterTilbage(){
if (rediger.getText() == "Anuller"){
	rediger.setText("Tilbage");
	email.setEditable(false);
	email.setText(emailDefault);
fulde_navn.setEditable(false);
fulde_navn.setText(navnDefault);
tlfnummer.setEditable(false);
tlfnummer.setText(String.valueOf(tlfDefault));
}
else{
	//skift vindue.
}

	}
	
}
