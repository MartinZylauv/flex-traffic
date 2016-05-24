package domain;

import java.time.LocalDate;
import java.util.Date;

public interface StartDestination {

	/**
	 * @author Steffen & Martin
	 * Domainclass for constructing the start-destination of a trip, including its adress, postal code and city-name. Used in conjunction with "SlutDestination" and "Koersel"
	 * @see SlutDestination
	 * @see Koersel
	 * 
	 */
	
	public String getAdresse();
	public void setAdresse(String adresse);
	public int getPostnummer();
	public void setPostnummer(int postnummer);
	public String getBynavn();
	public void setBynavn(String bynavn);
	public LocalDate getDato();
	public void setDato(LocalDate localDate);
}
