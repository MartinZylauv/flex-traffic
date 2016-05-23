package presentation;

import java.sql.Time;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Beskeder;

public class Information {

	
	public Alert bestillingInfo(Beskeder besked,String adresse,Time t){
		Alert info = new Alert(AlertType.INFORMATION);
		info.setTitle("Bestilling udført");
		info.setHeaderText(besked.toString());
		info.setContentText("Vi henter dig på:  " + adresse + " klokken " + t );
		return info;
		
	}
}
