package logic;

import java.time.LocalDate;
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
		int difference = koerselDom.getDate().compareTo(LocalDate.now());
		if(startDestination.getAdresse()!=null && startDestination.getPostnummer()!=Double.NaN 
				&& slutDestination.getAdresse()!=null && slutDestination.getPostnummer()!=Double.NaN 
				&& difference >=0 && koerselDom.getAntalPersoner()>0 && koerselDom.getAntalPersoner()<10 
				&& koerselDom.getHjaelpemidler()>=0 && koerselDom.getAntalBagage()>=0 
				&& koerselDom.getBrugerNummer()!=Double.NaN){
			
			PrisBeregner pb = new PrisBeregner();
			pb.run();
			
			
		} else{
			
		}
		return false;
	}

	@Override
	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination,
			KoerselDom koerselDom) {
		this.koerselDom=koerselDom;
		this.startDestination=startDestination;
		this.slutDestination=slutDestination;
		//validerinformationer kommer her
		//hvis ikke valid s� kaldt en update til gui med en fejlbesked
		//hvis rigtig s�
		//beregnpris bliver kaldt og en update til gui med beregnerpris besked bliver kaldt.
		
		
		return 0;
	}

}
