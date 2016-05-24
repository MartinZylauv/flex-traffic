package logic;

import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import domain.SlutDestination;
import domain.StartDestination;
import sats.UnknownKommuneException;
import domain.Bil;
import domain.Koersel;
import domain.KoerselHistorik;
import domain.KoerselHistorikImpl;
import domain.KoerselImpl;
import domain.Profil;

public interface FTPController {

	/**
	 * 
	 * @author Steffen @ Martin angivInformationer, used for setting the needed
	 *         information needed for ordering a trip, construction the
	 *         "Koersel" which is a collection of these info, and the
	 *         start-slutDestination which is also a collection of some of the
	 *         informations. After this they are validated, and throws an
	 *         exception if the information is invalid.
	 * 
	 * @param startDestination
	 *            a collection of adress, postal code and city for the trip.
	 * @param slutDestination
	 *            a collection of adress, postal code and city for the trip.
	 * @param dato
	 *            the date of the trip. Must be after or on today.
	 * @param antalPersoner
	 *            number of persons for the trip.
	 * @param antalHjaelpemidler
	 *            number of aids needed for the people on the trip e.g. a
	 *            wheelchair.
	 * @param antalBagage
	 *            the number of luggage needed.
	 * @param kommentarer
	 *            a string of comments the user might have added to the trip
	 * @param brugerNummer
	 *            the usernumber of the user which is also used for logging in.
	 * @param tid
	 *            the time of the day that the trip is finding place.
	 * @param antalKm
	 *            the number of kilometers that the trip is going to be.
	 * @throws InvalidInformationException
	 *             a custom made exception, which is thrown when the information
	 *             given is invalid.
	 */
	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, Date dato,
			int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer, int brugerNummer, Time tid,
			double antalKm, boolean erAdmin) throws InvalidInformationException;

	/**
	 * A method for saving the trip in the database. Will throw a SQL exception
	 * if an error has occured. If not, it will return a message, which is
	 * generated from the enum.class "Beskeder"
	 * 
	 * @see Beskeder
	 * 
	 * @return a new instance of Beskeder.
	 * @throws SQLException
	 */

	public Beskeder accepterPris() throws SQLException;

	/**
	 * A method for getting a Profil object based on the given kundenummer
	 * @param kundeNummer	the usernumber of the current user.
	 * @return	return Profil, which is made from data from the database.
	 * @throws SQLException	throws a SQLException if an error has occured in the database.
	 */
	
	public Profil anmodOmProfil(long kundeNummer) throws SQLException;

	public void indtastNyeInformationer(String fuldtNavn, String email, long tlfNummer)
			throws SQLException, InvalidInformationException;

	void getPrisTilbud(StartDestination startdestination, SlutDestination slutdestination, double km, Date dato)
			throws SQLException, UnknownKommuneException, InvalidInformationException;

	double getPris();

	void setPris(double pris);

	long getKundenummer();

	void setKundenummer(long kundenummer);

	public ArrayList<KoerselHistorikImpl> anmodOmBrugeresKørselHistorik(int kundenummer, Date dato1, Date dato2)
			throws SQLException;

	public boolean checkProfil(long kundenummer) throws SQLException;

	public boolean checkAdmin(long kundenummer) throws SQLException;

	public int getBil();

	public void tildelBil(int bil);

	public void angivKoerselTilVedligeholdelse(KoerselHistorik koerselhistorik) throws SQLException;

	public List<Integer> getBiler() throws SQLException;

	public Integer getBilFraID(int ID) throws SQLException;

	public void createDB() throws SQLException;
	
	public void indtastKommentar(String kommentar, int ID) throws SQLException;
	
	public String getKommentar(int ID) throws SQLException;
}
