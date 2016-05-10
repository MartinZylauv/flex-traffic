package domain;

import java.time.LocalDate;
import java.util.Date;

public interface Koersel {
	
	public void setDato(LocalDate dato);
	public LocalDate getDate();
	public void setAntalPersoner(int antalPersoner);
	public int getAntalPersoner();
	public void setHjaelplemidler(int antalHjaelplemidler);
	public int getHjaelpemidler();
	public void setAntalBagage(int antalBagage);
	public int getAntalBagage();
	public void setKommentar(String kommentar);
	public String getKommentar();
	public void setBrugerNummer(int brugerNummer);
	public int getBrugerNummer();

}
