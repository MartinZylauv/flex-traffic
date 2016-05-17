package domain;

import java.sql.Date;
import java.sql.Time;
import java.time.LocalDate;

public class KoerselHistorikImpl implements KoerselHistorik {

	
	Date dato;
	int antalPersoner;
	int antalHjaelpemidler;
	int antalBagage;
	int brugerNummer;
	String kommentar;
	Time tid;
	double antalKm;
	double pris;
	
	String startAdresse;
	int startPostnummer;
	String startBynavn;

	String slutAdresse;
	int slutPostnummer;
	String slutBynavn;
	
	@Override
	public void setDato(Date dato) {
		this.dato = dato;

	}

	@Override
	public Date getDato() {
		
		return dato;
	}

	@Override
	public void setAntalPersoner(int antalPersoner) {
	this.antalPersoner = antalPersoner;

	}

	@Override
	public int getAntalPersoner() {
		
		return antalPersoner;
	}

	@Override
	public void setHjaelplemidler(int antalHjaelplemidler) {
		this.antalHjaelpemidler = antalHjaelplemidler;

	}

	@Override
	public int getAntalHjaelpemidler() {
		
		return antalHjaelpemidler;
	}

	@Override
	public void setAntalBagage(int antalBagage) {
		this.antalBagage = antalBagage;

	}

	@Override
	public int getAntalBagage() {

		return antalBagage;
	}

	@Override
	public void setKommentar(String kommentar) {
		this.kommentar = kommentar;

	}

	@Override
	public String getKommentar() {
	
		return kommentar;
	}

	@Override
	public void setBrugerNummer(int brugerNummer) {
		this.brugerNummer = brugerNummer;

	}

	@Override
	public int getBrugerNummer() {
		
		return brugerNummer;
	}

	@Override
	public Time getTid() {
		
		return tid;
	}

	@Override
	public void setTime(Time tid) {
		this.tid = tid;
	}

	@Override
	public double getAntalKm() {
	
		return antalKm;
	}

	@Override
	public void setAntalKm(double antalKm) {
		this.antalKm = antalKm;

	}

	@Override
	public double getPris() {
		
		return pris;
	}

	@Override
	public void setPris(double pris) {
		this.pris = pris;

	}

	@Override
	public String getStartAdresse() {
		
		return startAdresse;
	}

	@Override
	public void setStartAdresse(String adresse) {
		this.startAdresse = adresse;

	}

	@Override
	public int getStartPostnummer() {
		
		return startPostnummer;
	}

	@Override
	public void setStartPostnummer(int postnummer) {
		this.startPostnummer = postnummer;

	}

	@Override
	public String getStartBynavn() {
		
		return startAdresse;
	}

	@Override
	public void setStartBynavn(String bynavn) {
		this.startBynavn = bynavn;

	}

	

	@Override
	public String getSlutAdresse() {
		
		return slutAdresse;
	}

	@Override
	public void setSlutAdresse(String adresse) {
		this.slutAdresse = adresse;

	}

	@Override
	public int getSlutPostnummer() {
		
		return slutPostnummer;
	}

	@Override
	public void setSlutPostnummer(int postnummer) {
		this.slutPostnummer = postnummer;

	}

	@Override
	public String getSlutBynavn() {
	
		return slutBynavn;
	}

	@Override
	public void setSlutBynavn(String bynavn) {
		this.slutBynavn = bynavn;
	}


}
