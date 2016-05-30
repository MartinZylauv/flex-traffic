package logic;

import java.sql.Date;
import java.sql.SQLException;

import domain.SlutDestination;
import domain.StartDestination;
import sats.UnknownKommuneException;

public interface PrisBeregner extends Runnable {

	@Override
	public void run();

	
	/**
	 * beregnPris is called from the run method in this class. It is used for getting the "Sats" from
	 * the external jar which is included in this project. 
	 * @param startDestination an instance of the StartDestination domain. 
	 * @param slutDestination	an instance of the SlutDestination domain.
	 * @param dato	the date of the drip
	 * @return a double with the rate of the trip.
	 * @throws UnknownKommuneException
	 * @throws SQLException
	 */
	public double beregnPris(StartDestination startDestination, SlutDestination slutDestination, Date dato)
			throws UnknownKommuneException, SQLException;

	/**
	 * A simple method used for calculating the price, given the number of kilometers, and the
	 * calculated rate earlier.
	 * @param antalKm	the number of kilometers
	 * @return	the price
	 */
	public double getPris(double antalKm);

	/**
	 * Another method used for getting the price, this time from the given kilometers used in the
	 * constructor of this class in its implementation.
	 * @return the price
	 */
	double getPris();

	/**
	 * Used for getting the state of the class.
	 * @return the state of the class.
	 */
	Tilstande getTilstand();
}
