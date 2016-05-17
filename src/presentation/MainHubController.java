package presentation;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Tab;

public class MainHubController implements Initializable {

	public MainHubController(LoggedIn loggedin) {
		this.loggedin = loggedin;
	}

	LoggedIn loggedin = null;
	int kundenummer;
	@FXML
	private Tab bestilKoersel;

	@FXML
	private Tab profilOplysninger;
	
	@FXML
	private Tab koerselshistorik;

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		try {
			System.out.println(loggedin.getKundenummer());
			UC1_gui_Controller uc1 = new UC1_gui_Controller(loggedin);
			UC23_gui_Controller uc23 = new UC23_gui_Controller(loggedin);
			UC45_gui_Controller uc45 = new UC45_gui_Controller(loggedin);
			
			FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("UC1.fxml"));
			fxmlLoader.setController(uc1); // TODO:sætter controlleren manuelt
											// så der kan kaldes en constructor
											// med brugernavnet så vi ved hvem
											// der er logget ind, hele vejen ned
											// endda.
			Parent root = (Parent) fxmlLoader.load();
			bestilKoersel.setContent(root);

			FXMLLoader fxml2Loader = new FXMLLoader(getClass().getResource("UC2-3.fxml"));
			fxml2Loader.setController(uc23);
			Parent root1 = (Parent) fxml2Loader.load();
			profilOplysninger.setContent(root1);
			
			FXMLLoader fxml3Loader = new FXMLLoader(getClass().getResource("UC4-5.fxml"));
			fxml3Loader.setController(uc45);
			Parent root2 = (Parent) fxml3Loader.load();
			koerselshistorik.setContent(root2);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setKundenummer(int kundenummer) {
		this.kundenummer = kundenummer;
	}
}
