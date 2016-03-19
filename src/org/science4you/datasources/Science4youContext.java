package org.science4you.datasources;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Science4youContext {

	private final static String connectionName = "jdbc:oracle:thin:@s4ora.science4you.org:1521:s2orcl";
	private final static String username = "s2web";
	private final static String password = "s2web";
	
	public static Connection getConnection() {
		
		try {

			Class.forName("oracle.jdbc.driver.OracleDriver");

		} catch (ClassNotFoundException e) {

			e.printStackTrace();
		}

		Connection connection = null;

		try {
			
			connection = DriverManager.getConnection(connectionName, username, password);
			
		} catch (SQLException e) {

			e.printStackTrace();
		}
		
		return connection;
	}
}
