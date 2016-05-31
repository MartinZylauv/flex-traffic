package logic;

import java.sql.Date;

import domain.Koersel;
import domain.SlutDestination;
import domain.StartDestination;

public interface Validator {
/**
 * This method handles the information needed for validating the specified information.
 * @param startDestination Information about the specified starting destination for the trip.
 * @param slutDestination Information about the specified ending destination for the trip.
 * @param koerselDom An instance of the domain object Koersel.
 */
	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Koersel koerselDom);
	/**
	 * This method handles the information specified above.
	 * @return Returns a true/false based on specified information.
	 */
	public boolean validerInformationer();
	/**
	 * Validates the information specified for editing your profile.
	 * @param fuldtNavn Information about the user's full name.
	 * @param email Information about the user's email address.
	 * @param tlfNummer Information about the user's telephone number.
	 * @return Returns a true/false based on specified information.
	 * @throws InvalidInformationException Casts an exception if the specified information is incorrect.
	 */
	public boolean validerProfilRediger(String fuldtNavn, String email, long tlfNummer) throws InvalidInformationException;
	/**
	 * This method handles the information specified for getting a price from the system.
	 * @param startdestination Information about the specified starting destination for the trip.
	 * @param slutdestination Information about the specified ending destination for the trip.
	 * @param km Information about the specified kilometers traveled for the trip.
	 * @param dato Information about the date set for the trip.
	 * @return Returns a true/false based on specified information.
	 * @throws InvalidInformationException Casts an exception if the specified information is incorrect.
	 */
	public boolean validerTilbud(StartDestination startdestination, SlutDestination slutdestination, double km, Date dato) throws InvalidInformationException;

	
}
