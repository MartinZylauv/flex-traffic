package logic;

import java.util.Date;

import domain.Koersel;
import domain.SlutDestination;
import domain.StartDestination;

public interface Validator {

	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination, 
			Koersel koerselDom);
	
	public boolean validerInformationer();
}
