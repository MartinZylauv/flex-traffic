package presentation;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.Beskeder;

public class Advarsler {

	public Alert ukendtKundenummerFejl(){
		Alert fejl = new Alert(AlertType.ERROR);
		fejl.setTitle("Fejl i kundenummer");
		fejl.setHeaderText("Log-ind fejl.");
		fejl.setContentText(Beskeder.UKENDT_KUNDENUMMER.getDescription());
		
		return fejl;
		
	}
	
	public Alert SQLFejl(){
		Alert fejl = new Alert(AlertType.ERROR);
		fejl.setTitle("SQL fejl");
		fejl.setHeaderText("Fejl i databasen");
		fejl.setContentText(Beskeder.UKENDT_SQL.getDescription()); 
		
		return fejl;
		
	}
	
	public Alert IOFejl(){
		Alert fejl = new Alert(AlertType.ERROR);
		fejl.setTitle("I/O fejl");
		fejl.setHeaderText("Ukendt fejl");
		fejl.setContentText(Beskeder.UKENDT_FEJL.getDescription()); 
		
		return fejl;
		
	}
}
