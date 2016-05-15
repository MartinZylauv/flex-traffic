package logic;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.LocalDate;

import domain.SlutDestination;
import domain.StartDestination;
import sats.UnknownKommuneException;
import domain.Profil;

public interface FTPController {

	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, Date dato,
			int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer, int brugerNummer, Time tid,
			double antalKm) throws InvalidInformationException;

	

	public Beskeder accepterPris() throws SQLException;

	public Profil anmodOmProfil(long kundeNummer) throws SQLException;

	public void indtastNyeInformationer(String fuldtNavn, String email, long tlfNummer) throws SQLException;

	void getPrisTilbud(StartDestination startdestination, SlutDestination slutdestination, double km)
			throws SQLException, UnknownKommuneException;

	double getPris();

	void setPris(double pris);

	long getKundenummer();

	void setKundenummer(long kundenummer);

}
