package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import domain.Koersel;
import domain.KoerselHistorikImpl;
import domain.Profil;
import domain.ProfilImpl;
import domain.SlutDestination;
import domain.StartDestination;

public class KoerselsKartotekImpl implements KoerselsKartotek {
	final static String INSERT_KOERSEL = "INSERT INTO KOERSLER( KOERSELSID, KUNDENUMMER,"
			+ " START_ADRESSE, START_POSTNUMMER, TIDSPUNKT, SLUT_ADRESSE, SLUT_POSTNUMMER,"
			+ " ANTAL_KM, BEREGNET_PRIS, GODKENDT_KØRSEL, BRUGERKOMMENTAR, ADMINSTRATIONSKOMMENTAR,"
			+ " TID_PÅ_DAGE,PERSONER,HJAELPEMIDLER,BAGAGE )VALUES ( null,? , ?,? ,? , ?,? ,? ,? ,? , ?, null,?,?,?,? )"; //TODO: tilføj de sidste par informationer der mangler ift. ssdet osv.
	
	
	final static String GET_ENKEL_TID = "SELECT * FROM KOERSLER WHERE kundenummer = ? AND tidspunkt>= ? AND tidspunkt <= ?";
	final static String GET_ENKEL ="SELECT * FROM KOERSLER WHERE kundenummer = ?";
	final static String GET_FLERE_TID= "SELECT * FROM KOERSLER WHERE tidspunkt>= ? AND tidspunkt <= ?";
	final static String GET_FLERE ="SELECT * FROM KOERSLER";

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

	@Override
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsinterval(int kundenummer, Date dato1, Date dato2)
			throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_ENKEL_TID);
		ps.setInt(1, kundenummer);
		ps.setDate(2, dato1);
		ps.setDate(3, dato2);
		ResultSet resultset = ps.executeQuery();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList();

		while (resultset.next()) {
			KoerselHistorikImpl koersel = new KoerselHistorikImpl();
			koersel.setBrugerNummer(resultset.getInt("kundenummer"));
			koersel.setStartAdresse(resultset.getString("start_adresse"));
			koersel.setStartPostnummer(resultset.getInt("start_postnummer"));
			koersel.setDato(resultset.getDate("tidspunkt"));
			koersel.setSlutAdresse(resultset.getString("slut_adresse"));
			koersel.setSlutPostnummer(resultset.getInt("slut_postnummer"));
			koersel.setAntalKm(resultset.getDouble("antal_km"));
			koersel.setPris(resultset.getDouble("beregnet_pris"));
			koersel.setKommentar(resultset.getString("brugerkommentar"));
			koersel.setTime(resultset.getTime("tid_på_dage"));
			koersel.setAntalPersoner(resultset.getInt("personer"));
			koersel.setHjaelplemidler(resultset.getInt("hjaelpemidler"));
			koersel.setAntalBagage(resultset.getInt("bagage"));
			liste.add(koersel);
			
		}

		return liste;
		

	}

	@Override
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørsler(int kundenummer) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_ENKEL);
		ps.setInt(1, kundenummer);
		ResultSet resultset = ps.executeQuery();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList();

		while (resultset.next()) {
			KoerselHistorikImpl koersel = new KoerselHistorikImpl();
			koersel.setBrugerNummer(resultset.getInt("kundenummer"));
			koersel.setStartAdresse(resultset.getString("start_adresse"));
			koersel.setStartPostnummer(resultset.getInt("start_postnummer"));
			koersel.setDato(resultset.getDate("tidspunkt"));
			koersel.setSlutAdresse(resultset.getString("slut_adresse"));
			koersel.setSlutPostnummer(resultset.getInt("slut_postnummer"));
			koersel.setAntalKm(resultset.getDouble("antal_km"));
			koersel.setPris(resultset.getDouble("beregnet_pris"));
			koersel.setKommentar(resultset.getString("brugerkommentar"));
			koersel.setTime(resultset.getTime("tid_på_dage"));
			koersel.setAntalPersoner(resultset.getInt("personer"));
			koersel.setHjaelplemidler(resultset.getInt("hjaelpemidler"));
			koersel.setAntalBagage(resultset.getInt("bagage"));
			liste.add(koersel);
			
		}

		return liste;
		
	}

	@Override
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsinterval(Date dato1, Date dato2)
			throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_FLERE_TID);
		ps.setDate(1, dato1);
		ps.setDate(2, dato2);
		
		ResultSet resultset = ps.executeQuery();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList();

		while (resultset.next()) {
			KoerselHistorikImpl koersel = new KoerselHistorikImpl();
			koersel.setBrugerNummer(resultset.getInt("kundenummer"));
			koersel.setStartAdresse(resultset.getString("start_adresse"));
			koersel.setStartPostnummer(resultset.getInt("start_postnummer"));
			koersel.setDato(resultset.getDate("tidspunkt"));
			koersel.setSlutAdresse(resultset.getString("slut_adresse"));
			koersel.setSlutPostnummer(resultset.getInt("slut_postnummer"));
			koersel.setAntalKm(resultset.getDouble("antal_km"));
			koersel.setPris(resultset.getDouble("beregnet_pris"));
			koersel.setKommentar(resultset.getString("brugerkommentar"));
			koersel.setTime(resultset.getTime("tid_på_dage"));
			koersel.setAntalPersoner(resultset.getInt("personer"));
			koersel.setHjaelplemidler(resultset.getInt("hjaelpemidler"));
			koersel.setAntalBagage(resultset.getInt("bagage"));
			liste.add(koersel);
			
		}

		return liste;
		
	}

	@Override
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørsler() throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_FLERE);
		
		ResultSet resultset = ps.executeQuery();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList();

		while (resultset.next()) {
			KoerselHistorikImpl koersel = new KoerselHistorikImpl();
			koersel.setBrugerNummer(resultset.getInt("kundenummer"));
			koersel.setStartAdresse(resultset.getString("start_adresse"));
			koersel.setStartPostnummer(resultset.getInt("start_postnummer"));
			koersel.setDato(resultset.getDate("tidspunkt"));
			koersel.setSlutAdresse(resultset.getString("slut_adresse"));
			koersel.setSlutPostnummer(resultset.getInt("slut_postnummer"));
			koersel.setAntalKm(resultset.getDouble("antal_km"));
			koersel.setPris(resultset.getDouble("beregnet_pris"));
			koersel.setKommentar(resultset.getString("brugerkommentar"));
			koersel.setTime(resultset.getTime("tid_på_dage"));
			koersel.setAntalPersoner(resultset.getInt("personer"));
			koersel.setHjaelplemidler(resultset.getInt("hjaelpemidler"));
			koersel.setAntalBagage(resultset.getInt("bagage"));
			liste.add(koersel);
		}
		return liste;
	}

	@Override
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerTidsintervalAdmin(int kundenummer, Date dato1,
			Date dato2) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørslerAdmin(int kundenummer) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerTidsintervalAdmin(Date dato1, Date dato2)
			throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørslerAdmin() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
