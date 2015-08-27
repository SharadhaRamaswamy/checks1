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

import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.OrderHistoryDao;
import edu.scu.dp.smartcals.dao.interfaces.ProductDao;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.InventoryModel;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.model.VendingMachineModel;

/**
 * @author Aparna Ganesh
 * @author Sharadha Ramaswamy
 *Order History - To get best selling product
 */
public class OrderHistoryDaoImpl implements OrderHistoryDao{

	private DatabaseFactory databaseFactory;

	private static OrderHistoryDao INSTANCE;
	
	private ProductDao productDao;

	private OrderHistoryDaoImpl(DatabaseFactory databaseFactory) {
		this.databaseFactory = databaseFactory;
		productDao = ProductDaoImpl.getInstance(databaseFactory);
	}
	
	/**
	 * Implementation of Singleton pattern. There should be only one ProductDAO
	 * instance for the entire application
	 * 
	 * @param databaseFactory
	 * @return
	 */
	public static OrderHistoryDao getInstance(DatabaseFactory databaseFactory) {
		if (INSTANCE == null) {
			INSTANCE = new OrderHistoryDaoImpl(databaseFactory);
		}
		return INSTANCE;
	}

	@Override
	public List<ProductModel> getBestSellingProduct(long vmId) throws SQLException {
		PreparedStatement statement = null;
		Connection connection = databaseFactory.getConnection();
		List<ProductModel> bestSellingProducts = new ArrayList<>();
		
		try {
			statement = connection.prepareStatement("select count(*) product_count,ProductId from orderdetails group by ProductId,VendingMachineId having VendingMachineId=? order by product_count desc limit 3");
			statement.setLong(1, vmId);
			ResultSet rs = statement.executeQuery();
			 
			while (rs.next()) {
				
				long productId = rs.getLong("ProductId");
				bestSellingProducts.add(productDao.getProductById(productId));

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		return bestSellingProducts;

		
	}

	public void updateOrderTable(InventoryModel invProd,String PaymentType,long SmartCalCardNo) throws SQLException{
		PreparedStatement statement = null;
		String query;
		Connection connection = databaseFactory.getConnection();
		try {
				
				
				query = "insert into orderdetails(SmartCalCardNumber,PaymentType,SKU,LineItemPrice,LineItemQuantity,TotalAmount,OrderStatus,ProductId,VendingMachineId) values(?,?,?,?,?,?,?,?,?)";
				statement = connection.prepareStatement(query);
				statement.setLong(1,SmartCalCardNo);
				statement.setString(2,PaymentType);
				statement.setLong(3,invProd.getskuId());
				statement.setDouble(4,invProd.getProductPrice());
				statement.setInt(5,1);
				statement.setDouble(6,invProd.getProductPrice());
				statement.setString(7,"Paid");
				statement.setLong(8,invProd.getProductId());
				statement.setLong(9,invProd.getVendingMachineId());
				statement.executeUpdate();
			}
			catch (SQLException e) {
				e.printStackTrace();
				throw e;
			} 
			finally {
				DBUtils.closeStatement(statement);
				databaseFactory.closeConnection();
			}
		
	}
}
