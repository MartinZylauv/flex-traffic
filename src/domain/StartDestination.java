package domain;

import java.time.LocalDate;
import java.util.Date;

public interface StartDestination {

	public String getAdresse();
	public void setAdresse(String adresse);
	public int getPostnummer();
	public void setPostnummer(int postnummer);
	public String getBynavn();
	public void setBynavn(String bynavn);
	public LocalDate getDato();
	public void setDato(LocalDate localDate);
}
