package logic;

import java.util.Date;

import domain.KoerselDom;
import domain.SlutDestination;
import domain.StartDestination;

public class KoerselImpl implements Koersel {

	StartDestination startDestination;
	SlutDestination slutDestination;
	KoerselDom koerselDom;
	
	@Override
	public boolean validerInformationer() {
		//tjek af information og logikken omkring det heri.
		return false;
	}

	@Override
	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination,
			KoerselDom koerselDom) {
		this.koerselDom=koerselDom;
		this.startDestination=startDestination;
		this.slutDestination=slutDestination;
		//validerinformationer kommer her
		//hvis ikke valid så kaldt en update til gui med en fejlbesked
		//hvis rigtig så
		//beregnpris bliver kaldt og en update til gui med beregnerpris besked bliver kaldt.
		
		
		return 0;
	}

}
