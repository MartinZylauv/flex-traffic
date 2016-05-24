package persistence;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;

import domain.Bil;
import domain.Koersel;
import domain.KoerselHistorik;
import domain.KoerselHistorikImpl;
import domain.SlutDestination;
import domain.StartDestination;

public class KoerselsKartotekImpl implements KoerselsKartotek {
	final static String INSERT_KOERSEL = "INSERT INTO KOERSLER( KOERSELSID, KUNDENUMMER,"
			+ " START_ADRESSE, START_POSTNUMMER, TIDSPUNKT, SLUT_ADRESSE, SLUT_POSTNUMMER,"
			+ " ANTAL_KM, BEREGNET_PRIS, GODKENDT_KØRSEL, BRUGERKOMMENTAR, ADMINSTRATIONSKOMMENTAR,"
			+ " TID_PÅ_DAGE,PERSONER,HJAELPEMIDLER,BAGAGE )VALUES ( null,?,?,?,?,?,?,?,?,?,?,?,?,?,?,? )"; 
	
	
	final static String GET_ENKEL_TID = "SELECT * FROM KOERSLER WHERE kundenummer = ? AND tidspunkt>= ? AND tidspunkt <= ?";
	final static String GET_ENKEL ="SELECT * FROM KOERSLER WHERE kundenummer = ?";
	final static String GET_FLERE_TID= "SELECT * FROM KOERSLER WHERE tidspunkt>= ? AND tidspunkt <= ?";
	final static String GET_FLERE ="SELECT * FROM KOERSLER";
	final static String GODKEND_KOERSEL ="INSERT INTO koerselstildeling(tildelt_bil,koersels_id) values(?,?)";
	final static String SET_GODKENDT = "UPDATE koersler SET godkendt_kørsel = ? WHERE koerselsid = ?";
	final static String GEM_KOMMENTAR = "UPDATE koersler SET ADMINSTRATIONSKOMMENTAR = ? WHERE koerselsid = ?";
	final static String GET_KOMMENTAR = "SELECT * FROM koersler WHERE koerselsid = ?";
	
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
		ps.setString(11,  koersel.getAdminKommentar());
		ps.setTime(12, tid);
		ps.setInt(13,koersel.getAntalPersoner() );
		ps.setInt(14, koersel.getHjaelpemidler());
		ps.setInt(15,koersel.getAntalBagage());

		ps.executeUpdate();
		connection.close();
		
		ps.close();

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
		ArrayList<KoerselHistorikImpl> liste = new ArrayList<KoerselHistorikImpl>();

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
			koersel.setID(resultset.getInt("Koerselsid"));
			koersel.setErGodkendt(resultset.getBoolean("GODKENDT_KØRSEL"));

			koersel.setAdminKommentar(resultset.getString("ADMINSTRATIONSKOMMENTAR"));
			liste.add(koersel);
			
		}

		connection.close();
		resultset.close();
		ps.close();
		return liste;
		

	}

	@Override
	public ArrayList<KoerselHistorikImpl> visEnkeltBrugerKørsler(int kundenummer) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_ENKEL);
		ps.setInt(1, kundenummer);
		ResultSet resultset = ps.executeQuery();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList<KoerselHistorikImpl>();

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
			koersel.setAdminKommentar(resultset.getString("ADMINSTRATIONSKOMMENTAR"));
			koersel.setID(resultset.getInt("Koerselsid"));
			koersel.setErGodkendt(resultset.getBoolean("GODKENDT_KØRSEL"));
			liste.add(koersel);
			
		}
		
		connection.close();
		resultset.close();
		ps.close();

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
		ArrayList<KoerselHistorikImpl> liste = new ArrayList<KoerselHistorikImpl>();

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
			koersel.setAdminKommentar(resultset.getString("ADMINSTRATIONSKOMMENTAR"));
			koersel.setID(resultset.getInt("Koerselsid"));
			koersel.setErGodkendt(resultset.getBoolean("GODKENDT_KØRSEL"));
			liste.add(koersel);
			
		}
		
		connection.close();
		resultset.close();
		ps.close();

		return liste;
		
	}

	@Override
	public ArrayList<KoerselHistorikImpl> visFlereBrugerKørsler() throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_FLERE);
		
		ResultSet resultset = ps.executeQuery();
		ArrayList<KoerselHistorikImpl> liste = new ArrayList<KoerselHistorikImpl>();

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
			koersel.setAdminKommentar(resultset.getString("ADMINSTRATIONSKOMMENTAR"));
			koersel.setID(resultset.getInt("Koerselsid"));
			koersel.setErGodkendt(resultset.getBoolean("GODKENDT_KØRSEL"));
			liste.add(koersel);
		}
		
		connection.close();
		resultset.close();
		ps.close();
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

	@Override
	public void godkendKoersel(int bil, KoerselHistorik koerselhistorik) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GODKEND_KOERSEL);
		ps.setInt(1, bil);
		ps.setInt(2, koerselhistorik.getID());
		ps.executeUpdate();
		connection.close();
		ps.close();
		
		
	}
	
	@Override
	public void setGodkendtKoersel(int bil, KoerselHistorik koerselhistorik) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(SET_GODKENDT);
		ps.setBoolean(1, true);
		ps.setInt(2, koerselhistorik.getID());
		ps.executeUpdate();
		connection.close();
		ps.close();
		
		
	}

	@Override
	public void gemKommentar(String kommentar, int ID) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GEM_KOMMENTAR);
		ps.setString(1, kommentar);
		ps.setInt(2, ID);
		ps.executeUpdate();
		connection.close();
		ps.close();
	}

	@Override
	public String getKommentar(int ID) throws SQLException {
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_KOMMENTAR);
		ps.setInt(1, ID);
		
		ResultSet resultset = ps.executeQuery();
		
		String kommentar = null;
		
		while (resultset.next()) {
			kommentar = resultset.getString("ADMINSTRATIONSKOMMENTAR");
		}
		
		connection.close();
		resultset.close();
		ps.close();
		return kommentar;
}
	

}
