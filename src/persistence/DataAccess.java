package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataAccess {

	protected Connection connection = null;

	public DataAccess() throws SQLException {
		
			connection = DriverManager.getConnection("jdbc:hsqldb:hsql://localhost/mydatabase", "SA", "");
		} 

	

	
	public Connection getConnection() {
		return connection;
	}

	

	


	}


