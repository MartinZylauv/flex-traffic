package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Profil;
import domain.ProfilImpl;

public class ProfilKartotekImpl implements ProfilKartotek {

	final static String SELECT_PROFIL = "SELECT * FROM Profiler WHERE kundeNummer = ?";

	@Override
	public Profil anmodOmProfil(double kundeNummer) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setDouble(1, kundeNummer);
		ResultSet resultset = ps.executeQuery();
		Profil profil = new ProfilImpl();
		
		while(resultset.next()){
			String fuldtNavn = resultset.getString("fuldt_navn");
			String email = resultset.getString("email");
			double tlfNummer = resultset.getDouble("tlf_nummer");
			
			profil.setKundeNummer(kundeNummer);
			profil.setFuldtNavn(fuldtNavn);
			profil.setEmail(email);
			profil.setTlfNummer(tlfNummer);
		}
		
		
		
		
		
		
		return profil;
	}

}
