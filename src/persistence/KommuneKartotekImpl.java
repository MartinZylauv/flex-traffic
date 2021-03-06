package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KommuneKartotekImpl implements KommuneKartotek {
	final static String SELECT_PROFIL = "SELECT Adresseringsnavn_1 FROM Regioner WHERE postnr = ?";

	@Override
	public String postnummerTilKommune(int postnummer) throws SQLException {
		DataAccess da = new DataAccess();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setInt(1, postnummer);
		ResultSet resultset = ps.executeQuery();
		String kommune = null;
		while (resultset.next()) {

			kommune = resultset.getString("Adresseringsnavn_1");
		

		}
		System.out.println(kommune);
		connection.close();
		resultset.close();
		ps.close();
			
		return kommune;
	}
}
