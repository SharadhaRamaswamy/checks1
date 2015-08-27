package edu.scu.dp.smartcals.dao.impl;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.SalesSummaryDao;
import edu.scu.dp.smartcals.model.SalesSummaryModel;

/**
 * @author Nisha Narayanaswamy
 * 
 *         Class to fetch sales statistics from SalesSummary table
 *
 */
public class SalesSummaryDaoImpl implements SalesSummaryDao {

	private DatabaseFactory databaseFactory;
	private PreparedStatement statement = null;
	private static SalesSummaryDao INSTANCE;
	private List<SalesSummaryModel> salesSummary;

	private SalesSummaryDaoImpl(DatabaseFactory databaseFactory) {
		this.databaseFactory = databaseFactory;
		salesSummary = new ArrayList<>();
	}

	/**
	 * Implementation of Singleton pattern. There should be only one
	 * SalesSummaryDAO instance for the entire application
	 * 
	 * @param databaseFactory
	 * @return
	 */
	public static SalesSummaryDao getInstance(DatabaseFactory databaseFactory) {
		if (INSTANCE == null) {
			INSTANCE = new SalesSummaryDaoImpl(databaseFactory);
		}
		return INSTANCE;
	}

	// get column names from table using result set meta data
	@Override
	public List<String> getColumnNames() throws SQLException {
		
		Connection connection = databaseFactory.getConnection();
		List<String> columnNames = new ArrayList<String>();

		statement = connection.prepareStatement("Select * from SalesSummary");
		ResultSet result = statement.executeQuery();
		ResultSetMetaData resultMetaData = result.getMetaData();

		int columns = resultMetaData.getColumnCount();
		for (int col = 1; col <= columns; col++) {
			columnNames.add(resultMetaData.getColumnName(col));
		}

		return columnNames;
	}

	//return all stats for a given user login
	@Override
	public List<SalesSummaryModel> getAllSalesStatistics(String username) throws SQLException {
		
		//clear old data
		salesSummary.clear();
		
		try {
			Connection connection = databaseFactory.getConnection();
			statement = connection
					.prepareStatement("Select * From SalesSummary "
							+ "Where VendingMachineID IN ("
								+ " Select VendingMachineId From AdministratorLogin"
								+ " Where Username = ? )" );
			statement.setString(1, username);
			ResultSet result = statement.executeQuery();
			while (result.next())
				mapRow(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}

		return salesSummary;
	}

	@Override
	public List<SalesSummaryModel> getVMSalesStatistics(long vendingMachineId)
			throws SQLException {
		
		//clear old data
		salesSummary.clear();
		
		try {
			Connection connection = databaseFactory.getConnection();

			statement = connection
					.prepareStatement("Select * from SalesSummary Where VendingMachineID IN (?)");
			statement.setLong(1, vendingMachineId);

			ResultSet result = statement.executeQuery();
			while (result.next())
				mapRow(result);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return salesSummary;
	}

	/**
	 * Map the result set data to the model object
	 * 
	 * @param result
	 * @throws SQLException
	 */
	private void mapRow(ResultSet result) throws SQLException {

		SalesSummaryModel model = new SalesSummaryModel();
		model.setVendingMachineID(result.getLong("VendingMachineID"));
		model.setTotalSalesAmount(result.getDouble("TotalSalesUSD"));
		model.setLastModifiedTime(result.getString("LastModifiedTimestamp"));

		// add model to sales summary list
		salesSummary.add(model);

	}

}
