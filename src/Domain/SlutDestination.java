package Domain;

import java.util.Date;

public interface SlutDestination {

	public String getAdresse();
	public void setAdresse(String adresse);
	public int getPostnummer();
	public void setPostnummer(int postnummer);
	public String getBynavn();
	public void setBynavn(String bynavn);
	public Date getDato();
	public void setDato(Date dato);
}
