package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import domain.Profil;
import domain.ProfilImpl;

public class ProfilKartotekImpl implements ProfilKartotek {

	final static String SELECT_PROFIL = "SELECT * FROM Profiler WHERE kundeNummer = ?";
	final static String INSERT_PROFIL = "UPDATE profiler SET fuldt_navn=?,email=?,tlf_nummer = ? WHERE kundenummer=?;"; 

	@Override
	public Profil anmodOmProfil(long kundeNummer) throws SQLException {
		DataAccess da = new DataAccess();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setDouble(1, kundeNummer);
		ResultSet resultset = ps.executeQuery();
		Profil profil = new ProfilImpl();

		while (resultset.next()) {
			
			String fuldtNavn = resultset.getString("fuldt_navn");
			String email = resultset.getString("email");
			long tlfNummer = resultset.getLong("tlf_nummer");
			String cpr = resultset.getString("CPR");

			profil.setKundeNummer(kundeNummer);
			profil.setFuldtNavn(fuldtNavn);
			profil.setEmail(email);
			profil.setTlfNummer(tlfNummer);
			profil.setCPR(cpr);
		}
		connection.close();
		resultset.close();
		ps.close();

		return profil;
	}

	@Override
	public void redigerProfil(String navn, String email, Long tlf, Long kundenummer) throws SQLException {
		DataAccess da = new DataAccess();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT_PROFIL);
		ps.setString(1, navn);
		ps.setString(2, email);

		ps.setLong(3, tlf);
		ps.setLong(4, kundenummer);

		ps.executeUpdate();
		connection.close();
		
		ps.close();
	}
	
	@Override
	public boolean checkProfil(long kundeNummer) throws SQLException {
		DataAccess da = new DataAccess();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setLong(1, kundeNummer);
		ResultSet resultset = ps.executeQuery();
			if(resultset.next()){
				connection.close();
				resultset.close();
				ps.close();
				return true;
			} 
			connection.close();
			resultset.close();
			ps.close();
				return false;
	}

	@Override
	public boolean checkAdmin(long kundeNummer) throws SQLException {
		DataAccess da = new DataAccess();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SELECT_PROFIL);
		ps.setDouble(1, kundeNummer);
		ResultSet resultset = ps.executeQuery();
		boolean erAdmin = false;

		while (resultset.next()) {
		
			erAdmin = resultset.getBoolean("erAdmin");
			return erAdmin;
		
			
		}

		connection.close();
		resultset.close();
		ps.close();
		
		return erAdmin;
	
	}	
				
	
	

}
