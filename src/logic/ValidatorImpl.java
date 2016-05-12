package logic;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;

import domain.Koersel;
import domain.SlutDestination;
import domain.StartDestination;

public class ValidatorImpl implements Validator {

	StartDestination startDestination;
	SlutDestination slutDestination;
	Koersel koerselDom;
	
	@Override
	public boolean validerInformationer() {
		double difference = koerselDom.getDate().compareTo(LocalDate.now());
		
		koerselDom.getDate();
		Time nu = new Time(System.currentTimeMillis());
		
		if(startDestination.getAdresse()!=null && startDestination.getPostnummer()!=Double.NaN 
				&& slutDestination.getAdresse()!=null && slutDestination.getPostnummer()!=Double.NaN 
				&& difference >=0 && koerselDom.getAntalPersoner()>0 && koerselDom.getAntalPersoner()<10 
				&& koerselDom.getHjaelpemidler()>=0 && koerselDom.getAntalBagage()>=0 
				&& koerselDom.getBrugerNummer()!=Double.NaN){ //TODO Implementer validation af tidspunkt, fucker lidt op men lav senere.
			
			return true;
			
			
		} 
		return false;
	}

	@Override
	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination,
			Koersel koerselDom) {
		this.koerselDom=koerselDom;
		this.startDestination=startDestination;
		this.slutDestination=slutDestination;
		
		
		
		return 0;
	}

}
