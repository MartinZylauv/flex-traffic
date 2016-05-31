package persistence;

import java.sql.SQLException;
import java.util.ArrayList;

import domain.Bil;

public interface BilKartotek {
	
	public ArrayList<Bil> getBiler() throws SQLException;
	
	public int getBilFromKoersel(int koersel) throws SQLException;
	
}
