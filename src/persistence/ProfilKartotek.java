package persistence;

import java.sql.SQLException;

import domain.Profil;

public interface ProfilKartotek {
/**
 * Method used for getting a Profil based on the given kundenummer
 * @param kundeNummer the usernumber
 * @return a profil object
 * @throws SQLException if an error occurs in the database
 */
	public Profil anmodOmProfil(long kundeNummer) throws SQLException;
	/**
	 * Method used for editing a profile
	 * @param navn the name of the profile
	 * @param email the email of the profile
	 * @param tlf the telephone number
	 * @param kundenummer the usernumber
	 * @throws SQLException if an error occurs in the database
	 */
	public void redigerProfil(String navn, String email, Long tlf, Long kundenummer) throws SQLException;
/**
 * Method used for checking if the profil is in the database
 * @param kundeNummer the given usernumber
 * @return true if it exists, false if not
 * @throws SQLException if an error occurs in the database
 */
	boolean checkProfil(long kundeNummer) throws SQLException;
	
	/**
	 * Method used for checking if the profil is admin or not
	 * @param kundeNummer the usernumber
	 * @return true if admin, false if not
	 * @throws SQLException if an error occurs in the database.
	 */
	boolean checkAdmin(long kundeNummer) throws SQLException;
}
