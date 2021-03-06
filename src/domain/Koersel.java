package domain;

import java.sql.Date;
import java.sql.Time;

public interface Koersel {
	/**
	 * @author Steffen & Martin
	 * Domainclass for constructing an ordered trip, used for saving the trip in a database.
	 * 
	 */
	public void setDato(Date dato);

	public Date getDate();

	public void setAntalPersoner(int antalPersoner);

	public int getAntalPersoner();

	public void setHjaelplemidler(int antalHjaelplemidler);

	public int getHjaelpemidler();

	public void setAntalBagage(int antalBagage);

	public int getAntalBagage();

	public void setKommentar(String kommentar);

	public String getKommentar();

	public void setAdminKommentar(String adminKommentar);

	public String getAdminKommentar();
	
	public void setBrugerNummer(int brugerNummer);

	public int getBrugerNummer();

	public Time getTid();

	public void setTime(Time tid);

	public double getAntalKm();

	public void setAntalKm(double antalKm);

	public double getPris();

	public void setPris(double pris);

}
