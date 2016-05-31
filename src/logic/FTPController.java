package logic;

import java.io.File;
import java.io.IOException;
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
/**
 * a method used for entering new information into the profil object in the database.
 * @param fuldtNavn the full name of the profil	
 * @param email the email of the profil
 * @param tlfNummer the phonenumer of the profil
 * @throws SQLException throws sqlException if an error in the database occurs
 * @throws InvalidInformationException throws InvalidInformationException if the information is invalid.
 */
	public void indtastNyeInformationer(String fuldtNavn, String email, long tlfNummer)
			throws SQLException, InvalidInformationException;
/**
 * A method used for getting a price from the given information
 * @param startdestination the starting destination
 * @param slutdestination the end destination
 * @param km the number of kilometers
 * @param dato the date of the trip
 * @throws SQLException sqlException is thrown if there is an error in the database.
 * @throws UnknownKommuneException throws unknownkommunexeption if the kommune is unknown.
 * @throws InvalidInformationException InvalidInformationException throws InvalidInformationException if the information is invalid.
 */
	void getPrisTilbud(StartDestination startdestination, SlutDestination slutdestination, double km, Date dato)
			throws SQLException, UnknownKommuneException, InvalidInformationException;

	double getPris();

	void setPris(double pris);

	long getKundenummer();

	void setKundenummer(long kundenummer);

	/**
	 * This method gets a specified users orderhistory.
	 * @param kundenummer	the usernumber
	 * @param dato1 the start date specified, can also be null
	 * @param dato2 the end date specified, can also be null
	 * @return an ArrayList with a users history of orders
	 * @throws SQLException throws if an error occurs in the database.
	 */
	public ArrayList<KoerselHistorikImpl> anmodOmBrugeresKørselHistorik(int kundenummer, Date dato1, Date dato2)
			throws SQLException;

	/**
	 * Checks if a profil exists in the database
	 * @param kundenummer usernumber
	 * @return true if yes, false if not
	 * @throws SQLException SQLException throws if an error occurs in the database.
	 */
	public boolean checkProfil(long kundenummer) throws SQLException;
	/**
	 * Checks if a profil is admin in the database
	 * @param kundenummer usernumber
	 * @return true if yes, false if not
	 * @throws SQLException SQLException throws if an error occurs in the database.
	 */
	public boolean checkAdmin(long kundenummer) throws SQLException;

	public int getBil();
/**
 * Method for setting the chosen car in this class
 * @param bil the car
 */
	public void tildelBil(int bil);
/**
 * Method used for setting an orders chosen car.
 * @param koerselhistorik gets an order history.
 * @throws SQLException SQLException SQLException throws if an error occurs in the database.
 * @throws InvalidInformationException throws if the information is invalid
 */
	public void angivKoerselTilVedligeholdelse(KoerselHistorik koerselhistorik) throws SQLException, InvalidInformationException;

	public List<Integer> getBiler() throws SQLException;
/**
 * Method for getting a car based on the koerselsid
 * @param ID order/koerselsid
 * @return and integer of the car that is set as the car
 * @throws SQLException SQLException SQLException throws if an error occurs in the database.
 */
	public Integer getBilFraID(int ID) throws SQLException;
/**
 * Method used for creating a database if the database does not exist on the local computer
 * @throws SQLException SQLException throws if an error occurs in the database.
 */
	public void createDB() throws SQLException;
	/**
	 * Method used for saving a commentary about a given order
	 * @param kommentar the commentary
	 * @param ID the id of the order
	 * @throws SQLException SQLException throws if an error occurs in the database.
	 */
	public void indtastKommentar(String kommentar, int ID) throws SQLException;
	
	public String getKommentar(int ID) throws SQLException;
/**
 * Method used for getting already driven orders
 * @param kundenummer the usernumber
 * @param dato1 the starting date
 * @param dato2 the end date
 * @return an arrayList of a history of orders already driven
 * @throws SQLException SQLException throws if an error occurs in the database.
 */
	ArrayList<KoerselHistorikImpl> anmodOmBrugeresKørselHistorikAfholdt(int kundenummer, Date dato1, Date dato2)
			throws SQLException;
	/**
	 * Method used for exporting a list of order history to csv
	 * @param koerselhistorik an order history as a list
	 * @param file the filepath
	 * @param erAdmin a boolean value if the user is admin or not. true if admin, false if not.
	 * @throws IOException 
	 */
	public void writeToCsv(ArrayList<KoerselHistorikImpl> koerselhistorik, File file, boolean erAdmin) throws IOException;
}
