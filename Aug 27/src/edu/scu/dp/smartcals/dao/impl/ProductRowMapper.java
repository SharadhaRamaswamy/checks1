/**
 * 
 */
package edu.scu.dp.smartcals.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;

import edu.scu.dp.smartcals.constants.ProductCategory;
import edu.scu.dp.smartcals.model.ProductModel;

/**
 * @author Aparna Ganesh
 *
 */
public class ProductRowMapper {
	
	/**
	 * Product Row Mapper Maps every row of Resultset to Java object
	 * 
	 * @param resultSet
	 * @return
	 * @throws SQLException
	 */
	public static ProductModel mapRow(ResultSet resultSet) throws SQLException {
		ProductModel product = new ProductModel();
		product.setProductId(resultSet.getLong("ProductID"));
		product.setProductName(resultSet.getString("ProductName"));
		product.setProductPrice(resultSet.getDouble("Price"));
		product.setCategory(ProductCategory.valueOf(resultSet.getString("Category").toUpperCase()));
		

		return product;
	}

}
