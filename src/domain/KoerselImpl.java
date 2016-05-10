package domain;

import java.time.LocalDate;

public class KoerselImpl implements Koersel {
	
	LocalDate dato;
	int antalPersoner;
	int antalHjaelpemidler;
	int antalBagage;
	int brugerNummer;
	String kommentar;

	@Override
	public void setDato(LocalDate dato) {
		this.dato = dato;

	}

	@Override
	public LocalDate getDate() {
		
		return dato;
	}

	@Override
	public void setAntalPersoner(int antalPersoner) {
		this.antalPersoner=antalPersoner;

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

}
