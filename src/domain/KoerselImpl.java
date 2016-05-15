package domain;

import java.sql.Date;
import java.sql.Time;


public class KoerselImpl implements Koersel {

	Date dato;
	int antalPersoner;
	int antalHjaelpemidler;
	int antalBagage;
	int brugerNummer;
	String kommentar;
	Time tid;
	double antalKm;
	double pris;

	@Override
	public void setDato(Date dato) {
		this.dato = dato;
	}

	@Override
	public Date getDate() {
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
	public int getHjaelpemidler() {
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
}
