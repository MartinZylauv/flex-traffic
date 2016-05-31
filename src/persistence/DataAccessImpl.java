package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccessImpl {

	protected Connection connection = null;

	public DataAccessImpl() throws SQLException {
		
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydatabase", "SA", "");
		} 

	

	
	public Connection getConnection() {
		return connection;
	}

	

	


	}


