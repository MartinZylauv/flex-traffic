package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBCreator {

	private static String CREATE ="CREATE TABLE IF NOT EXISTS ";
	private static String CREATE_PROFILER =CREATE+"Profiler("
			+ "kundenummer bigint IDENTITY,"
			+ "fuldt_navn varchar(255) NOT NULL,"
			+ "email varchar(255),"
			+ "tlf_nummer double NOT NULL,"
			+ "erAdmin boolean default false,"
			+ "cpr varchar(11)"
			+ ")";
	private static String CREATE_KOERSLER=CREATE+"Koersler("
			+ "KoerselsID bigint IDENTITY,"
			+ "kundenummer bigint,"
			+ "FOREIGN KEY(kundenummer) REFERENCES Profiler(kundenummer),"
			+ "start_adresse varchar(255) NOT NULL,"
			+ "start_postnummer bigint NOT NULL,"
			+ "tidspunkt Date NOT NULL,"
			+ "slut_adresse varchar(255) NOT NULL,"
			+ "slut_postnummer bigint NOT NULL,"
			+ "antal_KM double NOT NULL,"
			+ "beregnet_pris double NOT NULL,"
			+ "godkendt_kørsel boolean,"
			+ "brugerkommentar varchar(1000),"
			+ "Tid_på_dage Time,"
			+ "adminstrationskommentar varchar(1000),"
			+ "personer int,"
			+ "hjaelpemidler int,"
			+ "bagage int"
			+ ")";
	private static String CREATE_KOERSELSTILDELING=CREATE+"Koerselstildeling("
			+ "tildelt_bil bigint,"
			+ "koersels_id bigint,"
			+ "FOREIGN KEY(tildelt_bil) REFERENCES biler(id),"
			+ "FOREIGN KEY(koersels_id) REFERENCES Koersler(KoerselsID),"
			+ ")";
	private static String CREATE_BILER=CREATE+"Biler("
			+ "id bigint IDENTITY,"
			+ "kapacitet bigint,"
			+ "nummerplade varchar(7)"
			+ ")";
	
	private static String SELECT_PROFIL ="SELECT * FROM profiler";
	private static String INSERT_TESTUSER="INSERT INTO profiler (FULDT_NAVN,EMAIL,TLF_NUMMER,ERADMIN,CPR) VALUES(?,?,?,?,?)";
	private static String SELECT_BIL ="SELECT * FROM biler";
	private static String INSERT_BILER="INSERT INTO biler (KAPACITET,NUMMERPLADE) VALUES(?,?)";
	
	public void createProfilerDB() throws SQLException{
		
		DataAccess da = new DataAccess();
		Connection con = da.getConnection();
		Statement statement = con.createStatement();
		
		
		
		
		statement.executeUpdate(CREATE_PROFILER);
		statement.close();
		con.close();
		
		
		
	}
	
public void createBilerDB() throws SQLException{
		
		DataAccess da = new DataAccess();
		Connection con = da.getConnection();
		Statement statement = con.createStatement();
		statement.executeUpdate(CREATE_BILER);
		statement.close();
		con.close();
		
		
	}
public void createKoerslerDB() throws SQLException{
	
	DataAccess da = new DataAccess();
	Connection con = da.getConnection();
	Statement statement = con.createStatement();
	statement.executeUpdate(CREATE_KOERSLER);
	statement.close();
	con.close();
	
	
}
public void createKoerselstildelingDB() throws SQLException{
	
	DataAccess da = new DataAccess();
	Connection con = da.getConnection();
	Statement statement = con.createStatement();
	
	statement.executeUpdate(CREATE_KOERSELSTILDELING);
	statement.close();
	con.close();
	
	
}

public void insertProfil() throws SQLException{
	DataAccess da = new DataAccess();
	Connection con = da.getConnection();
	Statement statement = con.createStatement();
	
	ResultSet resultset = statement.executeQuery(SELECT_PROFIL);
	if(resultset.next()){
		
	} else{
		

		PreparedStatement statement1 = con.prepareStatement(INSERT_TESTUSER);
		
		statement1.setString(1, "Martin Kristensen");
		statement1.setString(2,"Martins@email.dk");
		statement1.setLong(3, 61676140);
		statement1.setBoolean(4,false);
		statement1.setString(5, "010199-1234");
		statement1.executeUpdate();
		statement1.setString(1, "Steffen Hagelskjær");
		statement1.setString(2,"steffens@email.dk");
		statement1.setLong(3, 12345678);
		statement1.setBoolean(4,true);
		statement1.setString(5, "010199-5678");
		statement1.executeUpdate();
		
	}
	statement.close();
	con.close();
}

public void insertBil() throws SQLException{
	DataAccess da = new DataAccess();
	Connection con = da.getConnection();
	Statement statement = con.createStatement();
	
	ResultSet resultset = statement.executeQuery(SELECT_BIL);
	if(resultset.next()){
		
	} else{
		

		PreparedStatement statement1 = con.prepareStatement(INSERT_BILER);
		
		statement1.setInt(1, 9);
		statement1.setString(2,"TM44055");
		statement1.executeUpdate();
		
		statement1.setInt(1, 5);
		statement1.setString(2,"AB02123");
		statement1.executeUpdate();
		
		statement1.setInt(1, 2);
		statement1.setString(2,"CB13456");
		statement1.executeUpdate();
		
		statement1.setInt(1, 6);
		statement1.setString(2,"DE25789");
		statement1.executeUpdate();
		
		
	}
	statement.close();
	con.close();
}
	
	
}
