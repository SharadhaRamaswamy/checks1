package edu.scu.dp.smartcals.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.ProductDao;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.ProductModel;

/**
 * Class to perform operations from Product Table
 * 
 * @author Aparna Ganesh
 *
 */
public class ProductDaoImpl implements ProductDao {

	private DatabaseFactory databaseFactory;

	private static ProductDao INSTANCE;

	private ProductDaoImpl(DatabaseFactory databaseFactory) {
		this.databaseFactory = databaseFactory;
	}

	/**
	 * Implementation of Singleton pattern. There should be only one ProductDAO
	 * instance for the entire application
	 * 
	 * @param databaseFactory
	 * @return
	 */
	public static ProductDao getInstance(DatabaseFactory databaseFactory) {
		if (INSTANCE == null) {
			INSTANCE = new ProductDaoImpl(databaseFactory);
		}
		return INSTANCE;
	}

	/* code change-Aparna 08/23
	 *delete product from database
	 */
	@Override
	public void deleteProduct(long productId) throws SQLException {
	
		PreparedStatement statement = null;
		Connection connection = databaseFactory.getConnection();

		try {
			statement = connection
					.prepareStatement("delete from product where productID =?");
			statement.setLong(1, productId);
			int row = statement.executeUpdate();
		
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}
		
	}
	
	/**
	 * Returns a product for a given product ID. Connection is opened and closed
	 * for every DB operation
	 */
	@Override
	public ProductModel getProductById(long id) throws SQLException,
			EmptyResultException {

		ProductModel product = null;
		PreparedStatement statement = null;
		Connection connection = databaseFactory.getConnection();

		try {
			statement = connection
					.prepareStatement("select * from product where productID =?");
			statement.setLong(1, id);
			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				product = ProductRowMapper.mapRow(rs);
				
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
		return product;
	}
/**
 * Add product to database -Admin
 * code change-Aparna 08/23
 * @throws SQLException 
 */
	@Override
	public void addProduct(ProductModel product) throws SQLException {
		
		
		PreparedStatement statement = null;
		Connection connection = databaseFactory.getConnection();

		try {
			statement = connection
					.prepareStatement("insert into product(ProductID,ProductName,Category,Price) values(?,?,?,?) ");
			statement.setLong(1, product.getProductId());
			statement.setString(2, product.getProductName());
			statement.setString(3, product.getCategory().toString());
			statement.setDouble(4, product.getProductPrice());
			
			int rs = statement.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();
		}

	}

	@Override
	public void updateProduct(ProductModel productModel,long productId) throws SQLException {
		PreparedStatement statement = null;
		Connection connection = databaseFactory.getConnection();
		
		try {
			statement = connection
					.prepareStatement("update product set ProductName = ?,Category = ?,Price = ? where ProductID = ?");
			
			statement.setString(1, productModel.getProductName());
			statement.setString(2, productModel.getCategory().toString());
			statement.setDouble(3, productModel.getProductPrice());
			statement.setLong(4, productId);
			
			int rs = statement.executeUpdate();
			

		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} finally {
			DBUtils.closeStatement(statement);
			databaseFactory.closeConnection();

	}
	}

	@Override
	public List<ProductModel> getProductByCalorieRange(int low, int high) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<ProductModel> getProductBySmartTag(List<String> smartTags) {
		// TODO Auto-generated method stub
		return null;
	}

	

	
	

}
