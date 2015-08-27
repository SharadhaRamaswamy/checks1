/**
 * 
 */
package edu.scu.dp.smartcals.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.scu.dp.smartcals.constants.VMStatus;
import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.constants.VMStatus;
import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.VendingMachineDao;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.model.VendingMachineModel;

/**
 * Class to perform operations from Vending machine table
 * 
 * @author Aparna Ganesh
 *
 */
public class VendingMachineDaoImpl implements VendingMachineDao {

	DatabaseFactory databaseFactory;

	private static VendingMachineDao INSTANCE;

	private VendingMachineDaoImpl(DatabaseFactory databaseFactory) {

		this.databaseFactory = databaseFactory;
	}

	/**
	 * Implementation of Singleton pattern as there should be only
	 * VendingMachineDAO for the entire application
	 * 
	 * @param databaseFactory
	 * @return
	 */
	public static VendingMachineDao getInstance(DatabaseFactory databaseFactory) {
		if (INSTANCE == null) {
			INSTANCE = new VendingMachineDaoImpl(databaseFactory);
		}
		return INSTANCE;
	}

	/**
	 * Returns all the vending machines basic info.-Aparna
	 */

	@Override
	public List<VendingMachineModel> getAllVMBasicInfo()
			throws EmptyResultException, SQLException {
		PreparedStatement statement = null;
		Connection connection = databaseFactory.getConnection();
		List<VendingMachineModel> vendingMachines = new ArrayList<>();

		try {
			statement = connection
					.prepareStatement("select * from vendingmachine");

			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				vendingMachines.add(mapVmBasicRow(rs));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return vendingMachines;

	}

	/**
	 * Returns a VM with all products for a given Id-Aparna
	 */
	@Override
	public VendingMachineModel getVendingMachine(long vmId) throws EmptyResultException, SQLException {

		final String VM_SQL = "select * from vendingmachine where VendingMachineID = ?";
		Connection connection = databaseFactory.getConnection();
		PreparedStatement statement = connection.prepareStatement(VM_SQL);
		statement.setLong(1, vmId);
		ResultSet rs = statement.executeQuery();
		VendingMachineModel vmModel = null;
		if (rs.next()) {
			vmModel = mapVmBasicRow(rs);
		} else {
			throw new EmptyResultException(
					"No result found for Vending machine :" + vmId);
		}

	//Gets all products for a given VM Id
		List<ProductModel> productModels = getProductsByVMId(vmId);
		vmModel.setProductModels(productModels);

		return vmModel;

	}

	/**
	 * Returns all products for a selected VM id-Aparna
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<ProductModel> getProductsByVMId(long vmId) throws SQLException {
		PreparedStatement statement = null;
		Connection connection = databaseFactory.getConnection();
		List<ProductModel> products = new ArrayList<>();

		try {
			statement = connection
					.prepareStatement("select prod.ProductID,inven.Price,prod.ProductName,prod.Category from product prod, inventory inven  where inven.VendingMachineID=? and prod.ProductID = inven.ProductID");
			statement.setLong(1, vmId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				products.add(ProductRowMapper.mapRow(rs));
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return products;
	}

	@Override
	public VMLocationType getVendingMachineType(long vmId) throws SQLException {
		final String SQL = "select type from vendingmachine where VendingMachineID = ?";
		Connection connection = databaseFactory.getConnection();
		PreparedStatement statement = null;
		VMLocationType result = null;
		try {
			statement = connection.prepareStatement(SQL);
			statement.setLong(1, vmId);
			ResultSet rs = statement.executeQuery();
			if(rs.next()) {
				result = VMLocationType.valueOf(rs.getString("type").toUpperCase());
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			throw e;
		}
		finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}

		return result;
	}

	/**
	 * VendingMachine Row Mapper
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	private VendingMachineModel mapVmBasicRow(ResultSet rs) throws SQLException {
		VendingMachineModel vendingMachine = new VendingMachineModel();
		vendingMachine.setVendingMachineId(rs.getLong("VendingMachineID"));
		vendingMachine.setLocation(rs.getString("Location"));
		vendingMachine.setType(VMLocationType.valueOf(rs.getString("type")
				.toUpperCase()));
		vendingMachine.setStatus(VMStatus.valueOf(rs.getString("Status")
				.toUpperCase()));
		return vendingMachine;
	}

}
