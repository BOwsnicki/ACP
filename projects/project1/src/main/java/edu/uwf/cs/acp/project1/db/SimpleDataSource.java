package edu.uwf.cs.acp.project1.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A simple data source for getting database connections.
 */
public class SimpleDataSource {
	/**
	 * Initializes the data source.
	 */
	public static void init() throws ClassNotFoundException {
		// Load Derby JDBC driver
		// Fully qualified driver name
		String driver = "org.apache.derby.jdbc.EmbeddedDriver";
		// Trigger class loader
		Class.forName(driver);
	}

	/**
	 * Gets a connection to the database.
	 * 
	 * @return the database connection
	 */
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:derby:myDB;create=true";
		String username = "APP";
		String password = "APP";
		return DriverManager.getConnection(url, username, password);
	}
}
