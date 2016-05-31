package persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Bil;

public interface BilKartotek {
	/**
	 * Method for getting all available cars.
	 * @return Returns all available cars.
	 * @throws SQLException Throws an exception if an unknown error occurs in the database.
	 */
	public ArrayList<Bil> getBiler() throws SQLException;
	/**
	 * Shows which car is assigned to a trip.
	 * @param koersel A specific booked trip in the system.
	 * @return Returns the assigned car.
	 * @throws SQLException Throws an exception if an unknown error occurs in the database.
	 */
	public int getBilFromKoersel(int koersel) throws SQLException;
	
}
