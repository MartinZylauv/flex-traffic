package domain;

import java.time.LocalDate;
import java.util.Date;

public class StartDestinationImpl implements StartDestination {

	String adresse;
	int postnummer;
	String bynavn;
	LocalDate localDate;

	@Override
	public String getAdresse() {

		return adresse;
	}

	@Override
	public void setAdresse(String adresse) {
		this.adresse = adresse;

	}

	@Override
	public int getPostnummer() {

		return postnummer;
	}

	@Override
	public void setPostnummer(int postnummer) {
		this.postnummer = postnummer;

	}

	@Override
	public String getBynavn() {

		return bynavn;
	}

	@Override
	public void setBynavn(String bynavn) {
		this.bynavn = bynavn;

	}

	@Override
	public LocalDate getDato() {

		return localDate;
	}

	@Override
	public void setDato(LocalDate localDate) {
		this.localDate = localDate;

	}

}
