package edu.scu.dp.smartcals.dao.impl;
/**
 * @author Sharadha Ramaswamy 
 */
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import edu.scu.dp.smartcals.constants.ProductCategory;
import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.InventoryDao;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.InventoryModel;
import edu.scu.dp.smartcals.model.ProductModel;


/**
 * This code talks to the database and performs all operations
 *  related to inventory table.
 *  Uses: Data Access Pattern 
 */
public class InventoryDaoImpl implements InventoryDao{
	
	private DatabaseFactory databaseFactory;
	private PreparedStatement statement;

	private static InventoryDao INSTANCE;

	private InventoryDaoImpl(DatabaseFactory databaseFactory) {
		this.databaseFactory = databaseFactory;
	}

	/**
	 * Implementation of Singleton pattern. There should be only one InventoryDAO
	 * instance for the entire application
	 * 
	 * @param databaseFactory
	 * @return
	 */
	public static InventoryDao getInstance(DatabaseFactory databaseFactory) {
		if (INSTANCE == null) {
			INSTANCE = new InventoryDaoImpl(databaseFactory);
		}
		return INSTANCE;
	}

	@Override
	public InventoryModel getProductById(long id) throws SQLException,
			EmptyResultException {
		InventoryModel invProd = null;
		Connection connection = databaseFactory.getConnection();

		try {
			statement = connection
					.prepareStatement("select * from inventory where productID =?");
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				invProd = mapRow(rs); 
			} else {
				throw new EmptyResultException();
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return invProd;
	}
		
		 
	public static InventoryModel mapRow(ResultSet resultSet) throws SQLException {
				InventoryModel invProd = new InventoryModel();
				invProd.setProductPrice(resultSet.getDouble("Price"));
				invProd.setProductId(resultSet.getLong("ProductID"));
				invProd.setVendingMachineId(resultSet.getLong("VendingMachineID"));
				invProd.setskuId(resultSet.getLong("SKU"));
				invProd.setqty(resultSet.getInt("Quantity"));
				invProd.setinventoryStatus(resultSet.getString("InventoryStatus"));
				return invProd;
		}

	public void updateInventoryQty(int qty,long prodId) throws SQLException, EmptyResultException{
		Connection connection = databaseFactory.getConnection();
		String query;
		int cnt;
		try{
			query = "update inventory set Quantity = '"+qty+"' where ProductID ='"+prodId+"'";
		    statement = connection.prepareStatement(query);
		    cnt = statement.executeUpdate();
		    if(cnt == 0)
		    	System.out.println("Error");
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
	}

	@Override
	public boolean removeProductById(long id,long vmId) throws SQLException,
			EmptyResultException {
		boolean status = false;
		int cnt = 0;
		Connection connection = databaseFactory.getConnection();
		String query;
		try{
			query = "delete from inventory where ProductID = ? and VendingMachineID = ?";
			statement = connection.prepareStatement(query);
			statement.setLong(1, id);
			statement.setLong(2,vmId);
		    cnt = statement.executeUpdate();
		    if(cnt == 1)
		    	status =  true;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return status;
	}

	@Override
	public boolean addInvDetails(int id, double price, int vendMachId, int qty)
			throws SQLException, EmptyResultException {
		Connection connection = databaseFactory.getConnection();
		String query;
		int cnt = 0;
		boolean status = false;
		try {
			query = "insert into inventory(ProductID,VendingMachineID,Price,Quantity,InventoryStatus) values(?,?,?,?,?)";
			  statement = connection.prepareStatement(query);
			  statement.setInt(1,id);
			  statement.setInt(2,vendMachId);
			  statement.setDouble(3,price);
			  statement.setInt(4,qty);
			  statement.setString(5,"Available");
			  cnt = statement.executeUpdate();
			  if(cnt == 1)
				  status = true;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return status;
	}

	@Override
	public boolean modifyInvDetails(long id, double price, int vendMachId,
			int qty) throws SQLException, EmptyResultException {
		Connection connection = databaseFactory.getConnection();
		String query;
		int cnt = 0;
		boolean status = false;
		try {
			query = "update Inventory set Price = '"+price+"', Quantity = '"+qty+"', VendingMachineID = '"+vendMachId+"' where ProductID = '"+id+"'";
			 statement = connection.prepareStatement(query);
			 cnt = statement.executeUpdate();
			  if(cnt == 1)
				  status = true;
		}
		catch(SQLException e) 
		{
			e.printStackTrace();
			throw e;
		}
		finally 
		{
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return status;
	}
}