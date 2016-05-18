package domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public interface KoerselHistorik {

	
	
	public void setDato(Date dato);
	public void setAntalPersoner(int antalPersoner);
	public int getAntalPersoner();
	public void setHjaelplemidler(int antalHjaelplemidler);
	public void setAntalBagage(int antalBagage);
	public int getAntalBagage();
	public void setKommentar(String kommentar);
	public String getKommentar();
	public void setBrugerNummer(int brugerNummer);
	public int getBrugerNummer();
	public Time getTid();
	public void setTime(Time tid);
	public double getAntalKm();
	public void setAntalKm(double antalKm);
	public double getPris();
	public void setPris(double pris);
	public String getStartAdresse();
	public void setStartAdresse(String adresse);
	public int getStartPostnummer();
	public void setStartPostnummer(int postnummer);
	public String getStartBynavn();
	public void setStartBynavn(String bynavn);

	public String getSlutAdresse();
	public void setSlutAdresse(String adresse);
	public int getSlutPostnummer();
	public void setSlutPostnummer(int postnummer);
	public String getSlutBynavn();
	public void setSlutBynavn(String bynavn);
	Date getDato();

	int getAntalHjaelpemidler();
	void setAdminKommentar(String adminKommentar);
	String getAdminKommentar();

	
	

}
