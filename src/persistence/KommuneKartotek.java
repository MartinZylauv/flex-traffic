package persistence;

import java.sql.SQLException;

public interface KommuneKartotek {
/**
 * Used for getting a kommune based on the given postnummer
 * @param postnummer the postal number
 * @return a string of the found kommune
 * @throws SQLException if an error occurs in the database
 */
	String postnummerTilKommune(int postnummer) throws SQLException;

}
