package logic;

import java.util.Date;

import domain.SlutDestination;
import domain.StartDestination;

public interface FTPController {

	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer, int brugerNummer);

	public boolean validerOplysninger(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer);
	
	public boolean accepterPris();
}
