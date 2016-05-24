package domain;

public interface Profil {

	/**
	 * @author Steffen & Martin Domainclass for constructing a users profile,
	 *         used for getting and setting it from the database, and showing it
	 *         in the gui
	 * 
	 */

	public long getKundeNummer();

	public void setKundeNummer(long kundeNummer);

	public String getFuldtNavn();

	public void setFuldtNavn(String fuldtNavn);

	public String getEmail();

	public void setEmail(String email);

	public long getTlfNummer();

	public void setTlfNummer(long tlfNummer);

	public void setCPR(String cpr);

	public String getCPR();
}
