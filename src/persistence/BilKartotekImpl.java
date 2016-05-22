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
}
