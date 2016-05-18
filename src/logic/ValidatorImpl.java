package logic;


import java.util.Calendar;
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

		Date iDag = new Date(Calendar.getInstance().getTime().getTime()); // http://stackoverflow.com/questions/18257648/get-the-current-date-in-java-sql-date-format
		double difference = koerselDom.getDate().compareTo(iDag);

		if (startDestination.getAdresse() != null && startDestination.getPostnummer() != Double.NaN
				&& slutDestination.getAdresse() != null && slutDestination.getPostnummer() != Double.NaN
				&& difference >= 0 && koerselDom.getAntalPersoner() > 0 && koerselDom.getAntalPersoner() < 10
				&& koerselDom.getHjaelpemidler() >= 0 && koerselDom.getAntalBagage() >= 0
				&& koerselDom.getBrugerNummer() != Double.NaN) { // TODO
																	// Implementer
																	// validation
																	// af
																	// tidspunkt,
																	// fucker
																	// lidt op
																	// men lav
																	// senere. Fix med at den smider en fejlbesked

			return true;

		}
		return false;
	}

	@Override
	public double angivInformationer(StartDestination startDestination, SlutDestination slutDestination,
			Koersel koerselDom) {
		this.koerselDom = koerselDom;
		this.startDestination = startDestination;
		this.slutDestination = slutDestination;

		return 0;
	}

	@Override
	public boolean validerProfilRediger(String fuldtNavn, String email, long tlfNummer) throws InvalidInformationException {
		if(fuldtNavn.isEmpty()){
			throw new InvalidInformationException(Beskeder.NAVN_IKKE_INDTASTET.toString());
			
		} if(email.isEmpty()){
			throw new InvalidInformationException(Beskeder.EMAIL_IKKE_INDTASTET.toString());
			
		}if(tlfNummer == 0){
			throw new InvalidInformationException(Beskeder.TLF_IKKE_INDTASTET.toString());
		}
		return true;
	}

	@Override
	public boolean validerTilbud(StartDestination startdestination, SlutDestination slutdestination, double km,
			java.sql.Date dato) throws InvalidInformationException {
		if(startdestination.getAdresse().isEmpty()){
			
			throw new InvalidInformationException(Beskeder.START_ADR_MANGLER.getDescription());
		}else if(startdestination.getBynavn().isEmpty()){
			throw new InvalidInformationException(Beskeder.START_BY_MANGLER.getDescription());
		}else if(startdestination.getPostnummer() == 0){	 //TODO sæt postnummer til 0 hvis intet er tastet ind
			throw new InvalidInformationException(Beskeder.START_POSTNR_MANGLER.getDescription());
	
	}return false;
	}
	

}

