package edu.scu.dp.smartcals.dao.interfaces;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import edu.scu.dp.smartcals.constants.DbType;
import edu.scu.dp.smartcals.dao.impl.MySQLFactory;

/**
 * Database Factory to support different DB types. 
 * Abstract Factory pattern used for this implementation. 
 * @author Aparna Ganesh
 *
 */

public abstract class DatabaseFactory {

	protected String hostname;

	protected String dbName;

	protected String username;

	protected String password;

	protected String connectionURL;
	
	/**
	 * Returns a Database Connection object if exists else creates a new connection
	 */
	public abstract Connection getConnection() throws SQLException;
	
	public abstract void closeConnection();

	public static DatabaseFactory getFactory(Properties properties, DbType type) throws ClassNotFoundException {

		switch (type) {

		case MYSQL:
			return MySQLFactory.getInstance(properties);
			
		default:
			return null;

		}
		

	}

}
