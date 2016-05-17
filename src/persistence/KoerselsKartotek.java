package persistence;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import domain.Koersel;
import domain.KoerselImpl;
import domain.SlutDestination;
import domain.StartDestination;

public interface KoerselsKartotek {
	
	

	public void gemKoersel(StartDestination startDestination, SlutDestination slutDestination, Koersel koersel, Time tid) throws SQLException;

	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsinterval(int kundenummer, Date dato1, Date dato2)throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørsler(int kundenummer) throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsinterval(Date dato1,Date dato2) throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørsler() throws SQLException;

	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsintervalAdmin(int kundenummer,Date  dato1,Date dato2)throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerAdmin(int kundenummer)throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsintervalAdmin(Date dato1,Date dato2) throws SQLException;
	
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerAdmin()throws SQLException;
	
	
}
