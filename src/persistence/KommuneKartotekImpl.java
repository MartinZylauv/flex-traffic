package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Profil;
import domain.ProfilImpl;

public class KommuneKartotekImpl {
	final static String SELECT_PROFIL = "SELECT Adresseringsnavn_1 FROM Regioner WHERE postnr = ?";

	
	public String postnummerTilKommune(int postnummer) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setDouble(1, postnummer);
		ResultSet resultset = ps.executeQuery();
		Profil profil = new ProfilImpl();
		String kommune = null;
		while(resultset.next()){
			
			kommune  = resultset.getString("Adresseringsnavn_1");
			
		}
		return kommune;
}
}
