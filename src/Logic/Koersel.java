package Logic;

import java.util.Date;

import Domain.SlutDestination;
import Domain.StartDestination;

public interface Koersel {
	
	

	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjaelpemidler, int antalBagage, String kommentarer);
}
