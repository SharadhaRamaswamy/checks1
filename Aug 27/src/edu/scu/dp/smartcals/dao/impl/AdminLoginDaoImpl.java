
package edu.scu.dp.smartcals.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.scu.dp.smartcals.dao.interfaces.AdminLoginDao;
import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.model.AdminLoginModel;

/**
 * @author Nisha Narayanaswamy
 * 
 *         Class to fetch user credentials from AdministratorLogin table
 *
 */
public class AdminLoginDaoImpl implements AdminLoginDao {

	private DatabaseFactory databaseFactory;
	private PreparedStatement statement = null;
	private AdminLoginModel adminLogin;
	private static AdminLoginDao INSTANCE;

	// Hidden construtor since we are using Singleton design pattern
	private AdminLoginDaoImpl(DatabaseFactory databaseFactory) {
		this.databaseFactory = databaseFactory;
	}

	/**
	 * Implementation of Singleton pattern as there should be only one
	 * AdminLoginDao for the entire application
	 * 
	 * @param databaseFactory
	 * @return
	 */
	public static AdminLoginDao getInstance(DatabaseFactory databaseFactory) {
		if (INSTANCE == null)
			INSTANCE = new AdminLoginDaoImpl(databaseFactory);
		return INSTANCE;
	}

	@Override
	public AdminLoginModel validateLogin(String username, String password)
			throws SQLException {
		try {
			Connection connection = databaseFactory.getConnection();
			statement = connection
					.prepareStatement("Select * From AdministratorLogin Where UserName = ? And Password = sha(?)");
			statement.setString(1, username);
			statement.setString(2, password);
			ResultSet result = statement.executeQuery();

			if (result.next() && (result != null)) {
				// if result set not null then login credentials match
				if(adminLogin != null)
					adminLogin.resetModel();
				mapRow(result);
			} else {
				System.out.println("Login attempt failed! Please try again.");
				// clear any old model data
				// adminLogin.resetModel();

				// $$$$$$$$$ Code to write failed attempt to DB pending $$$$$$$$
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return adminLogin;
	}

	// start - Nisha - 8/17
	/* (non-Javadoc)
	 * When login successful, reset the Failed login count in DB, update the recent login timestamp in DB
	 */
	@Override
	public void setLastLoginTime(String username) throws SQLException {
		
		try {
			Connection connection = databaseFactory.getConnection();
			statement = connection
					.prepareStatement("Update AdministratorLogin Set LoginTimeStamp = CURRENT_TIMESTAMP, LoginAttempts = 0 Where UserName = ?");
			statement.setString(1, username);
			int updateStatus = statement.executeUpdate();
			// $$$$$$$$$$$$$$ add logger fucntionality here $$$$$$$$$$$$
			/*if (updateStatus != 1)
				System.out.println("Could not udpate DB with login timestamp");*/
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
	}

	@Override
	public void setLoginFailedAttempt(String username) throws SQLException {
		try {
			Connection connection = databaseFactory.getConnection();
			statement = connection
					.prepareStatement("Update AdministratorLogin Set LoginAttempts = LoginAttempts + 1, LoginTimeStamp = CURRENT_TIMESTAMP Where UserName = ?");
			statement.setString(1, username);
			int updateStatus = statement.executeUpdate();

			// $$$$$$$$$$$$$$$ add logger fucntionality here $$$$$$$$$$
			if (updateStatus != 1)
				System.out.println("Could not udpate DB");

			// $$$$$$$$$$$$$$$ apply Strategy pattern to this code $$$$$$$$$$

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
	}

	@Override
	public AdminLoginModel getLoginDetails(String username) throws SQLException {
		try{
			Connection connection = databaseFactory.getConnection();
			statement = connection.prepareStatement("Select * From AdministratorLogin Where UserName = ?");
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while(result.next()){
				//clear old result before populating
				/*if(adminLogin != null)
					adminLogin.resetModel();*/
				//populate the AdminLogin Model
				mapRow(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();

		} finally{
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return adminLogin;
	}
	// end - Nisha - 8/17

	/**
	 * Map the result set data to the model object
	 * 
	 * @param result
	 * @throws SQLException
	 */
	private void mapRow(ResultSet result) throws SQLException {

		// set all attributes from DB result set
		adminLogin = new AdminLoginModel();
		adminLogin.setUsername(result.getString("UserName"));
		//adminLogin.setPassword(result.getString("Password"));
		adminLogin.setLoginAttempts(result.getInt("LoginAttempts"));
		adminLogin.setVendingMachineID(result.getInt("VendingMachineID"));
		adminLogin.setLocation(result.getString("Location"));
		adminLogin.setLoginTimeStamp(result.getTimestamp("LoginTimeStamp"));

	}

}
