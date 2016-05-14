package logic;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;


import domain.SlutDestination;
import domain.StartDestination;
import domain.Profil;

public interface FTPController {

	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
		Date dato, int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer, int brugerNummer, Time tid, double antalKm) throws InvalidInformationException;

	public boolean validerOplysninger(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer);
	
	public Beskeder accepterPris() throws SQLException;
	
	public Profil anmodOmProfil(long kundeNummer) throws SQLException;
}
