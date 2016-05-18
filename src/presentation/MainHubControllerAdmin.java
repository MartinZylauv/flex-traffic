package presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;

public class MainHubControllerAdmin implements Initializable {
	public MainHubControllerAdmin(LoggedIn loggedin, boolean erAdmin) {
		this.erAdmin = erAdmin;
		this.loggedin = loggedin;
	}
	boolean erAdmin;
	LoggedIn loggedin = null;
	int kundenummer;
	
	@FXML
	private Tab koerselshistorik;


	
	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			UC6_gui_Controller uc6 = new UC6_gui_Controller(loggedin);
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UC6.fxml"));
			fxmlLoader.setController(uc6); 
			Parent root = (Parent) fxmlLoader.load();
			koerselshistorik.setContent(root);

			
			
				
			

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setKundenummer(int kundenummer) {
		this.kundenummer = kundenummer;
	}
}


