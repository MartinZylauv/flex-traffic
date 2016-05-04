package Logic;

import java.util.Date;

import Domain.SlutDestination;
import Domain.StartDestination;

public interface Kørsel {
	
	

	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjælpemidler, int antalBagage, String kommentarer);
}
