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

	public ArrayList<KoerselImpl> visEnkeltBrugerKørslerTidsinterval(int kundenummer, Date dato1, Date dato2)throws SQLException;
	
	public ArrayList<KoerselImpl> visEnkeltBrugerKørsler(int kundenummer) throws SQLException;
	
	public ArrayList<KoerselImpl> visFlereBrugerKørslerTidsinterval(Date dato1,Date dato2) throws SQLException;
	
	public ArrayList<KoerselImpl> visFlereBrugerKørsler() throws SQLException;

	public ArrayList<KoerselImpl> visEnkeltBrugerKørslerTidsintervalAdmin(int kundenummer,Date  dato1,Date dato2)throws SQLException;
	
	public ArrayList<KoerselImpl> visEnkeltBrugerKørslerAdmin(int kundenummer)throws SQLException;
	
	public ArrayList<KoerselImpl> visFlereBrugerKørslerTidsintervalAdmin(Date dato1,Date dato2) throws SQLException;
	
	public ArrayList<KoerselImpl> visFlereBrugerKørslerAdmin()throws SQLException;
	
	
}
