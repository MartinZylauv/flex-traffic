package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Profil;
import domain.ProfilImpl;

public class ProfilKartotekImpl implements ProfilKartotek {
	
	//TODO tilføj et admin check. Altså bare en "erAdmin boolean" i databasen.

	final static String SELECT_PROFIL = "SELECT * FROM Profiler WHERE kundeNummer = ?";
	final static String INSERT_PROFIL = "UPDATE PROFILER SET FULDT_NAVN=?,EMAIL=?,TLF_NUMMER = ? WHERE KUNDENUMMER=?;"; // TODO
																														// refactor
																														// lige
																														// med
																														// sm�t
																														// pls

	@Override
	public Profil anmodOmProfil(long kundeNummer) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setDouble(1, kundeNummer);
		ResultSet resultset = ps.executeQuery();
		Profil profil = new ProfilImpl();

		while (resultset.next()) {
			String fuldtNavn = resultset.getString("fuldt_navn");
			String email = resultset.getString("email");
			long tlfNummer = resultset.getLong("tlf_nummer");

			profil.setKundeNummer(kundeNummer);
			profil.setFuldtNavn(fuldtNavn);
			profil.setEmail(email);
			profil.setTlfNummer(tlfNummer);
		}

		return profil;
	}

	@Override
	public void redigerProfil(String navn, String email, Long tlf, Long kundenummer) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT_PROFIL);
		ps.setString(1, navn);
		ps.setString(2, email);

		ps.setLong(3, tlf);
		ps.setLong(4, kundenummer);

		ps.executeUpdate();

	}
	
	@Override
	public boolean checkProfil(long kundeNummer) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setDouble(1, kundeNummer);
		ResultSet resultset = ps.executeQuery();
			if(resultset.next()){
				return true;
			} 
				return false;
	}

}
