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
																	// senere.

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

}
