package logic;

import java.sql.Date;

import domain.Koersel;
import domain.SlutDestination;
import domain.StartDestination;

public interface Validator {

	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Koersel koerselDom);
	
	public boolean validerInformationer();
	
	public boolean validerProfilRediger(String fuldtNavn, String email, long tlfNummer) throws InvalidInformationException;
	
	public boolean validerTilbud(StartDestination startdestination, SlutDestination slutdestination, double km, Date dato) throws InvalidInformationException;

	
}
