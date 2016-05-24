package domain;

import java.time.LocalDate;


public interface SlutDestination {

	/**
	 * @author Steffen & Martin
	 * Domainclass for constructing the end-destination of a trip, including its adress, postal code and city-name. Used in conjunction with "StartDestination" and "Koersel"
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

}
