package presentation;

import java.net.URL;
import java.sql.SQLException;
import java.util.Observable;
import java.util.Observer;
import java.util.ResourceBundle;

import domain.SlutDestination;
import domain.SlutDestinationImpl;
import domain.StartDestination;
import domain.StartDestinationImpl;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
import logic.Tilstande;
import sats.UnknownKommuneException;

public class UC1_gui_Controller implements Initializable,Observer {
	
	FTPControllerImpl ftp = new FTPControllerImpl();
	

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
	private ChoiceBox antalHjaelpleChoice;
	
	@FXML
	private ChoiceBox antalBagageChoice;
	
	@FXML
	private ChoiceBox antalPersonerChoice;
	
	@FXML
	private DatePicker datoVaelger;
	
	@FXML
	private TextArea kommentarArea;
	
	@FXML 
	private ChoiceBox tidHChoice;
	
	@FXML
	private ChoiceBox tidMChoice;
	
	@FXML
	private Label prisLabel;
	
	//@FXML
	//private ProgressBar progressBar;
	
	@FXML
	private Button udregnKnap;
	
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
	
	StartDestination start = new StartDestinationImpl();
	SlutDestination slut = new SlutDestinationImpl();
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		Platform.setImplicitExit(false);
		ftp.addObserver(this);
		startAdresseFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (startAdresseFelt.getText().isEmpty() == false){
					startAdresseAendret = true;
					try {
						erAlleIndtastet();
					} catch (SQLException | UnknownKommuneException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					startAdresseAendret = false;
				}
				
				
			}
		});
		
startPostnummerFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (startPostnummerFelt.getText().isEmpty() == false){
					startPostnummerAendret = true;
					try {
						erAlleIndtastet();
					} catch (SQLException | UnknownKommuneException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}else{
					startPostnummerAendret = false;
				}
				
			}
		});

startByFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if (startByFelt.getText().isEmpty() == false){
			startByAendret = true;
			try {
				erAlleIndtastet();
			} catch (SQLException | UnknownKommuneException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			startByAendret = false;
		}
		
	}
});

slutAdresseFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if (slutAdresseFelt.getText().isEmpty() == false){
			slutAdresseAendret = true;
			try {
				erAlleIndtastet();
			} catch (SQLException | UnknownKommuneException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			slutAdresseAendret = false;
		}
		
		
	}
});

slutPostnummerFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if (slutPostnummerFelt.getText().isEmpty() == false){
			slutPostnummerAendret = true;
			try {
				erAlleIndtastet();
			} catch (SQLException | UnknownKommuneException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			slutPostnummerAendret = false;
		}
		
	}
});

slutByFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
@Override
public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
if (slutByFelt.getText().isEmpty() == false){
	slutByAendret = true;
	try {
		erAlleIndtastet();
	} catch (SQLException | UnknownKommuneException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}else{
	slutByAendret = false;
}

}
});

kmFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
@Override
public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
if (kmFelt.getText().isEmpty() == false){
	kmAendret = true;
	try {
		erAlleIndtastet();
	} catch (SQLException | UnknownKommuneException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}else{
	kmAendret = false;
}

}
});
	}
	
	public void erAlleIndtastet() throws SQLException, UnknownKommuneException{
		
		if(startAdresseAendret == true && startPostnummerAendret == true && startByAendret == true
				&&slutAdresseAendret == true && slutPostnummerAendret == true && slutByAendret == true && kmAendret == true ){
			System.out.println("vi kan udregne pris nu.");
			
			start.setAdresse(startAdresseFelt.getText());
			start.setBynavn(startByFelt.getText());
			start.setPostnummer(Integer.parseInt(startPostnummerFelt.getText()));
			slut.setAdresse(slutAdresseFelt.getText());
			slut.setBynavn(slutByFelt.getText());
			slut.setPostnummer(Integer.parseInt(slutPostnummerFelt.getText()));
			
			ftp.getPrisTilbud(start, slut,Double.parseDouble(kmFelt.getText()));
			prisLabel.setVisible(false);
			//progressBar.setVisible(true);
			
			
		} else{
			System.out.println("pris kan ikke udregnes nu");
			System.out.println("status: " + startAdresseAendret + " " + startPostnummerAendret + " " +startByAendret + " " + slutAdresseAendret + " " + slutPostnummerAendret + " " +slutByAendret );
		}
	

}

	@Override
	public void update(Observable arg0, Object arg) {
		System.out.println("hej");
		pris = ftp.getPris();
		s();
		
	}
	
	@FXML
	public void haandteerUdregn() throws NumberFormatException, SQLException, UnknownKommuneException{
		start.setAdresse(startAdresseFelt.getText());
		start.setBynavn(startByFelt.getText());
		start.setPostnummer(Integer.parseInt(startPostnummerFelt.getText()));
		slut.setAdresse(slutAdresseFelt.getText());
		slut.setBynavn(slutByFelt.getText());
		slut.setPostnummer(Integer.parseInt(slutPostnummerFelt.getText()));
		
		ftp.getPrisTilbud(start, slut,Double.parseDouble(kmFelt.getText()));
	}
	
	public void s(){
		prisLabel.setText(String.valueOf(pris));
	}

	
	
}
