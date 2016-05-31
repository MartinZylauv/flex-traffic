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
	
	
/**
 * Saves the user's information as a trip once the system has validated the information.
 * @param startdestination Information about the specified starting destination for the trip.
 * @param slutdestination Information about the specified ending destination for the trip.
 * @param koersel A specific booked trip in the system.
 * @param tid Information about the specified time for the trip.
 * @throws SQLException Throws an exception if an unknown error occurs in the database.
 */
	public void gemKoersel(StartDestination startDestination, SlutDestination slutDestination, Koersel koersel, Time tid) throws SQLException;
/**
 * Shows a specific user's booked trips between two dates.
 * @param kundenummer The user's assigned user ID.
 * @param dato1 The first date assigned for searching in a time interval.
 * @param dato2 The second date assigned for searching in a time interval.
 * @return Returns trips based on the specified information.
 * @throws SQLException Throws an exception if an unknown error occurs in the database.
 */
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsinterval(int kundenummer, Date dato1, Date dato2)throws SQLException;
	/**
	 * Shows a specific user's booked trips.
	 * @param kundenummer The user's assigned user ID.
	 * @return Returns trips based on the specified information.
	 * @throws SQLException Throws an exception if an unknown error occurs in the database.
	 */
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørsler(int kundenummer) throws SQLException;
	/**
	 * Shows every booked trip between two dates.
	 * @param dato1 The first date assigned for searching in a time interval.
	 * @param dato2 The second date assigned for searching in a time interval.
	 * @return Returns trips based on the specified information.
	 * @throws SQLException Throws an exception if an unknown error occurs in the database.
	 */
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsinterval(Date dato1,Date dato2) throws SQLException;
	/**
	 * Shows every booked trip in the system.
	 * @return Returns trips based on the specified information.
	 * @throws SQLException Throws an exception if an unknown error occurs in the database.
	 */
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørsler() throws SQLException;

	/**
	 * Saves an admin's comment on a specified, booked trip.
	 * @param kommentar Information of text for an admin's message.
	 * @param ID The trip's specified ID.
	 * @throws SQLException Throws an exception if an unknown error occurs in the database.
	 */
	public void gemKommentar(String kommentar, int ID) throws SQLException;
	/**
	 * Displays the admin's comment on a specified, booked trip.
	 * @param ID The trip's specified ID.
	 * @return Returns the message based on the selected trip.
	 * @throws SQLException Throws an exception if an unknown error occurs in the database.
	 */
	public String getKommentar(int ID) throws SQLException;
	
	
/**
 * Accepts a booked trip in the system.
 * @param bil Information about which car is selected.
 * @param koerselhistorik The selected trip containing all data for the trip.
 * @throws SQLException Throws an exception if an unknown error occurs in the database.
 */
	public void godkendKoersel(int bil, KoerselHistorik koerselhistorik) throws SQLException;
/**
 * Accepts a booked trip in the system.
 * @param bil Information about which car is selected.
 * @param koerselhistorik The selected trip containing all data for the trip.
 * @throws SQLException Throws an exception if an unknown error occurs in the database.
 */
	void setGodkendtKoersel(int bil, KoerselHistorik koerselhistorik) throws SQLException;
/**
 * Shows a specific user's held trips between two dates.
 * @param kundenummer The user's assigned user ID.
 * @param dato1 The first date assigned for searching in a time interval.
 * @param dato2 The second date assigned for searching in a time interval.
 * @return
 * @throws SQLException
 */
	ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsintervalAfholdt(int kundenummer, Date dato1, Date dato2)
			throws SQLException;
/**
 * Shows every held trip in the system.
 * @return Returns trips based on the specified information.
 * @throws SQLException Throws an exception if an unknown error occurs in the database.
 */
	ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerAfholdt() throws SQLException;
/**
 * Shows all user's held trips between two dates.
 * @param dato1 The first date assigned for searching in a time interval.
 * @param dato2 The second date assigned for searching in a time interval.
 * @return Returns trips based on the specified information.
 * @throws SQLException Throws an exception if an unknown error occurs in the database.
 */
	ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsintervalAfholdt(Date dato1, Date dato2) throws SQLException;
/**
 * Shows a specific user's held trips.
 * @param kundenummer The user's assigned user ID.
 * @return Returns trips based on the specified information.
 * @throws SQLException Throws an exception if an unknown error occurs in the database.
 */
	ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerAfholdt(int kundenummer) throws SQLException;

	}
