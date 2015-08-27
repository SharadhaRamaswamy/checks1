package edu.scu.dp.smartcals.dao.impl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import edu.scu.dp.smartcals.constants.Constants;
import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;

/**
 * MySQlConnectionFactory to establish connection to MySQL Database. 
 * @author Aparna Ganesh
 *
 */
public class MySQLFactory extends DatabaseFactory {

	private static final String BASE_URL = "jdbc:mysql://";

	private static final String DRIVER_CLASS = "com.mysql.jdbc.Driver";

	private Connection connection;
	
	private static MySQLFactory INSTANCE;
	
	private MySQLFactory(Properties properties) throws ClassNotFoundException {

		hostname = properties.getProperty(Constants.DB_HOSTNAME);
		dbName = properties.getProperty(Constants.DB_NAME);
		username = properties.getProperty(Constants.DB_USER);
		password = properties.getProperty(Constants.DB_PASSWORD);
		connectionURL = BASE_URL + hostname + "/" + dbName;
		Class.forName(DRIVER_CLASS);

	}
	
	/**
	 * Returns SINGLE instance of MySQLFactory.Implementation of Singleton pattern.
	 * There should be only one MYSQL factory for the entire application
	 * @param properties
	 * @return
	 * @throws ClassNotFoundException 
	 */
	public static DatabaseFactory getInstance(Properties properties) throws ClassNotFoundException {
		if (INSTANCE == null) {
			INSTANCE = new MySQLFactory(properties);
		}
		return INSTANCE;
	}


	/**
	 * Returns a Connection object if exists else creates a new connection
	 */
	@Override
	public Connection getConnection() throws SQLException {

		//if (connection == null) {

			connection = DriverManager.getConnection(connectionURL, username,
					password);
		//}

		return connection;
	}
	
	public void closeConnection() {
		if(connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			connection = null;
		}
		
	}

	
}
