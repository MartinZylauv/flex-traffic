package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.Bil;
import domain.BilImpl;
import domain.KoerselHistorikImpl;

public class BilKartotekImpl {
	
	private static String GET_BILER = "SELECT * FROM biler";
	private static String GET_BIL_FRA_ID="SELECT tildelt_bil FROM koerselstildeling WHERE koersels_id =?";

	public ArrayList<Bil> getBiler() throws SQLException{
		ArrayList<Bil> biler = new ArrayList<Bil>();
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_BILER);
		ResultSet resultset = ps.executeQuery();
		

		while (resultset.next()) {
			Bil bil = new BilImpl();
			bil.setID(resultset.getInt("ID"));
			System.out.println(bil.getID());
			bil.setKapacitet(resultset.getInt("kapacitet"));
			bil.setNummerplade(resultset.getString("nummerplade"));
			biler.add(bil);
		}
		connection.close();
		ps.close();
		resultset.close();
		
		return biler;
		
	}
	
	public int getBilFromKoersel(int koersel) throws SQLException{
		DataAccessForSQL da = new DataAccessForSQL();
		Connection connection = da.getConnection();
		PreparedStatement ps = connection.prepareStatement(GET_BIL_FRA_ID);
		ps.setInt(1, koersel);
		ResultSet resultset = ps.executeQuery();
		
		int bil_id = 0;
		
		while (resultset.next()) {
			bil_id = resultset.getInt("tildelt_bil");	//vi vil kun have 1 kørsel tilbage. det er den seneste tildelte der er den valgte.
		}
		connection.close();
		ps.close();
		resultset.close();
		
		return bil_id;
	}
}
