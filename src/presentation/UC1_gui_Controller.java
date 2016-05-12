package presentation;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class UC1_gui_Controller implements Initializable {

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

	private boolean startAdresseAendret = false;
	private boolean startPostnummerAendret = false;
	private boolean startByAendret = false;
	private boolean slutAdresseAendret = false;
	private boolean slutPostnummerAendret = false;
	private boolean slutByAendret = false;
	
	
	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		
		startAdresseFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (startAdresseFelt.getText()!= ""){
					startAdresseAendret = true;
				}else{
					startAdresseAendret = false;
				}
				
				
			}
		});
		
startPostnummerFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
			@Override
			public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
				if (startPostnummerFelt.getText()!= ""){
					startPostnummerAendret = true;
				}else{
					startPostnummerAendret = false;
				}
				
			}
		});

startByFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if (startByFelt.getText()!= ""){
			startByAendret = true;
		}else{
			startByAendret = false;
		}
		
	}
});

slutAdresseFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if (slutAdresseFelt.getText()!= ""){
			slutAdresseAendret = true;
		}else{
			slutAdresseAendret = false;
		}
		
		
	}
});

slutPostnummerFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
	@Override
	public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
		if (slutPostnummerFelt.getText()!= ""){
			slutPostnummerAendret = true;
		}else{
			slutPostnummerAendret = false;
		}
		
	}
});

slutByFelt.focusedProperty().addListener(new ChangeListener<Boolean>() {
@Override
public void changed(ObservableValue<? extends Boolean> arg0, Boolean arg1, Boolean arg2) {
if (startByFelt.getText()!= ""){
	startByAendret = true;
}else{
	startByAendret = false;
}

}
});
	}
	
	public void erAlleIndtastet(){
		if(startAdresseAendret == true && startPostnummerAendret == true && startByAendret == true
				&&slutAdresseAendret == true && slutPostnummerAendret == true && slutByAendret == true){
			System.out.println("vi kan udregne pris nu.");
		} else{
			System.out.println("pris kan ikke udregnes nu");
		}
	

}
	
}
