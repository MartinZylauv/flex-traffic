package Logic;

import java.util.Date;

public interface Kørsel {

	public angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Date dato, int antalPersoner, int antalHjælpemidler, int antalBagage, String kommentarer);
}
