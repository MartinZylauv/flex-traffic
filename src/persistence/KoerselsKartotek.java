package persistence;

import java.sql.SQLException;
import java.sql.Time;

import domain.Koersel;
import domain.SlutDestination;
import domain.StartDestination;

public interface KoerselsKartotek {
	
	

	public void gemKoersel(StartDestination startDestination, SlutDestination slutDestination, Koersel koersel, Time tid) throws SQLException;

}
