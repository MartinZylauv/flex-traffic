package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Time;
import domain.Koersel;
import domain.SlutDestination;
import domain.StartDestination;

public class KoerselsKartotekImpl implements KoerselsKartotek {
	final static String INSERT_KOERSEL = "INSERT INTO KOERSLER( KOERSELSID, KUNDENUMMER,"
			+ " START_ADRESSE, START_POSTNUMMER, TIDSPUNKT, SLUT_ADRESSE, SLUT_POSTNUMMER,"
			+ " ANTAL_KM, BEREGNET_PRIS, GODKENDT_KØRSEL, BRUGERKOMMENTAR, ADMINSTRATIONSKOMMENTAR,"
			+ " TID_PÅ_DAGE,PERSONER,HJAELPEMIDLER,BAGAGE )VALUES ( null,? , ?,? ,? , ?,? ,? ,? ,? , ?, null,?,?,?,? )"; //TODO: tilføj de sidste par informationer der mangler ift. ssdet osv.

	@Override
	public void gemKoersel(StartDestination startDestination, SlutDestination slutDestination, Koersel koersel,
			Time tid) throws SQLException {

		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(INSERT_KOERSEL);
		ps.setInt(1, koersel.getBrugerNummer());
		ps.setString(2, startDestination.getAdresse());
		ps.setLong(3, startDestination.getPostnummer());
		ps.setDate(4, koersel.getDate());
		ps.setString(5, slutDestination.getAdresse());
		ps.setLong(6, slutDestination.getPostnummer());
		ps.setDouble(7, koersel.getAntalKm());
		ps.setDouble(8, koersel.getPris());
		ps.setBoolean(9, false);
		ps.setString(10, koersel.getKommentar());
		ps.setTime(11, tid);
		ps.setInt(12,koersel.getAntalPersoner() );
		ps.setInt(13, koersel.getHjaelpemidler());
		ps.setInt(14,koersel.getAntalBagage());

		ps.executeUpdate();

	}

}
