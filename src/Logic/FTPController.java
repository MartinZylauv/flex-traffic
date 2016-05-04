package Logic;

import java.util.Date;

import Domain.SlutDestination;
import Domain.StartDestination;

public interface FTPController {

	public void angivInformationer(StartDestination startDestination, SlutDestination slutDestination, Date dato, int antalPersoner, int antalHjælpemidler, int antalBagage, String kommentarer);

	public boolean validerOplysninger(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjælpemidler, int antalBagage, String kommentarer);
	
	public boolean accepterPris();
}
