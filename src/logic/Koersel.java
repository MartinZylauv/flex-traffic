package logic;

import java.util.Date;

import domain.KoerselDom;
import domain.SlutDestination;
import domain.StartDestination;

public interface Koersel {

	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			KoerselDom koerselDom);
	
	public boolean validerInformationer();
}
