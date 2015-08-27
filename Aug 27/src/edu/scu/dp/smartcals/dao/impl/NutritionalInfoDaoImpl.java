package edu.scu.dp.smartcals.dao.impl;

import java.sql.Array;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.NutritionalInfoDao;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.NutritionalInfoModel;

/**
 * Class to perform operations from NutritionalInfo table
 * 
 * @author Nisha Narayanaswamy
 *
 */
public class NutritionalInfoDaoImpl implements NutritionalInfoDao {

	private DatabaseFactory databaseFactory;
	private PreparedStatement statement = null;
	private NutritionalInfoModel nutriInfo;
	private static NutritionalInfoDao INSTANCE;

	private NutritionalInfoDaoImpl(DatabaseFactory databaseFactory) {
		this.databaseFactory = databaseFactory;
	}

	/**
	 * Implementation of Singleton pattern as there should be only one
	 * NutritionalInfoDao for the entire application
	 * 
	 * @param databaseFactory
	 * @return
	 */
	public static NutritionalInfoDao getInstance(DatabaseFactory databaseFactory) {
		if (INSTANCE == null) {
			INSTANCE = new NutritionalInfoDaoImpl(databaseFactory);
		}
		return INSTANCE;
	}

	@Override
	public NutritionalInfoModel getNutriInfo(long prodID) throws SQLException,
	EmptyResultException {

		Connection connection = databaseFactory.getConnection();
		try {

			statement = connection
					.prepareStatement("Select * From NutritionalInfo Where ProductID = ?");
			statement.setLong(1, prodID);
			ResultSet result = statement.executeQuery();
			if (result.next()) {
				mapRow(result);
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return nutriInfo;
	}

	@Override
	public boolean addNutriInfo(ArrayList<String> listValues)
			throws SQLException {
		Connection connection = databaseFactory.getConnection();

		statement = connection
				.prepareStatement("INSERT INTO NutritionalInfo (`ProductID`, `ServingSize`, `Calories`, `TotalFat`, "
						+ "`SaturatedFat`,`TransFat`,`Cholestrol`,`Sodium`,`TotalCarbs`,"
						+ "`DietaryFiber`,`Sugars`,`Protein`,`Iron`,`SmartTag`) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");

		// set first element prodID as "long"
		statement.setLong(1, Long.parseLong(listValues.get(0)));

		int index = 0;
		for (String element : listValues) {
			index++;
			if (index == 1)
				continue;

			if (element == null) {
				statement.setString(index, "");
			} else
				statement.setString(index, element);
		}
		int status = statement.executeUpdate();
		if (status != 1) {
			return false;
		}

		return true;
	}

	@Override
	public boolean deleteNutriInfo(long productID) throws SQLException {
		Connection connection = databaseFactory.getConnection();

		try {
			statement = connection
					.prepareStatement("DELETE FROM NutritionalInfo WHERE PRODUCTID = ?");
			statement.setLong(1, productID);
			int status = statement.executeUpdate();
			if (status != 1) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

	@Override
	public boolean updateNutriInfo(ArrayList<String> listValues)
			throws SQLException {

		Connection connection = databaseFactory.getConnection();

		statement = connection.prepareStatement("UPDATE NutritionalInfo SET "
				+ "`ProductID` = ? ," + "`ServingSize` = ? ,"
				+ "`Calories` = ? ," + "`TotalFat` = ? ,"
				+ "`SaturatedFat` = ? ," + "`TransFat` = ? ,"
				+ "`Cholestrol` = ? ," + "`Sodium` = ? ,"
				+ "`TotalCarbs` = ? ," + "`DietaryFiber` = ? ,"
				+ "`Sugars` = ? ," + "`Protein` = ? ," + "`Iron` = ? ,"
				+ "`SmartTag` = ? " + "WHERE `ProductID` = ?");

		// set first element prodID as "long"
		statement.setLong(1, Long.parseLong(listValues.get(0)));

		int index = 0;
		for (String element : listValues) {
			index++;
			if (index == 1)
				continue;

			if (element == null) {
				statement.setString(index, "");
			} else
				statement.setString(index, element);

			// set last index for prodID as "long"
			statement.setLong(15, Long.parseLong(listValues.get(0)));
		}

		int status = statement.executeUpdate();
		if (status != 1) {
			return false;
		}

		return true;
	}

	// maybe add method to get smart tags

	/**
	 * Map the result set data to the model object
	 * 
	 * @param result
	 * @throws SQLException
	 */
	private void mapRow(ResultSet result) throws SQLException {

		// set all attributes from DB result set
		nutriInfo = new NutritionalInfoModel.NutriBuilder(
				result.getLong("ProductID"), result.getString("Calories"),
				result.getString("SmartTag"))
		.servingSize(result.getString("ServingSize"))
		.totalFat(result.getString("TotalFat"))
		.saturatedFat(result.getString("SaturatedFat"))
		.transFat(result.getString("TransFat"))
		.cholestrol(result.getString("Cholestrol"))
		.sodium(result.getString("Sodium"))
		.totalCarbs(result.getString("TotalCarbs"))
		.dietaryFiber(result.getString("DietaryFiber"))
		.sugars(result.getString("Sugars"))
		.protein(result.getString("Protein"))
		.iron(result.getString("Iron")).buildNutriInfo();
	}

	@Override
	public String getSmartTag(long prodID) throws SQLException, EmptyResultException {
		Connection connection = databaseFactory.getConnection();
		String tag = "";
		try {

			statement = connection.prepareStatement("Select SmartTag From NutritionalInfo Where ProductID = ?");		
			statement.setLong(1, prodID);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				tag = result.getString("SmartTag");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return tag;
	}

	@Override
	public String getCalories(long prodId) throws SQLException,
			EmptyResultException {
		Connection connection = databaseFactory.getConnection();
		String cal = "";
		try {
			statement = connection.prepareStatement("Select Calories From NutritionalInfo Where ProductID = ?");		
			statement.setLong(1, prodId);
			ResultSet result = statement.executeQuery();
			if(result.next()){
				cal = result.getString("Calories");
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally{
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return cal;
	}
}
