package persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import domain.Bil;
import domain.Koersel;
import domain.KoerselHistorik;
import domain.KoerselHistorikImpl;
import domain.KoerselImpl;
import domain.SlutDestination;
import domain.StartDestination;

public interface KoerselsKartotek {
	
	

	public void gemKoersel(StartDestination startDestination, SlutDestination slutDestination, Koersel koersel, Time tid) throws SQLException;

	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsinterval(int kundenummer, Date dato1, Date dato2)throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørsler(int kundenummer) throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsinterval(Date dato1,Date dato2) throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørsler() throws SQLException;

	
	public void gemKommentar(String kommentar, int ID) throws SQLException;
	
	public String getKommentar(int ID) throws SQLException;
	
	

	public void godkendKoersel(int bil, KoerselHistorik koerselhistorik) throws SQLException;

	void setGodkendtKoersel(int bil, KoerselHistorik koerselhistorik) throws SQLException;

	ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsintervalAfholdt(int kundenummer, Date dato1, Date dato2)
			throws SQLException;

	ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerAfholdt() throws SQLException;

	ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsintervalAfholdt(Date dato1, Date dato2) throws SQLException;

	ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerAfholdt(int kundenummer) throws SQLException;

	}
