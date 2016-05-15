package persistence;

import java.sql.SQLException;

public interface KommuneKartotek {

	String postnummerTilKommune(int postnummer) throws SQLException;

}
