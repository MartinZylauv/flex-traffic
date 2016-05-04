package Logic;

import java.util.Date;

public interface FTPController {

	public angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjælpemidler, int antalBagage, String kommentarer);

	public boolean validerOplysninger(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjælpemidler, int antalBagage, String kommentarer);
	
	public boolean accepterPris();
}
