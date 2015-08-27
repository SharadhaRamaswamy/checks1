/**
 * 
 */
package edu.scu.dp.smartcals.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.InventoryModel;
import edu.scu.dp.smartcals.model.ProductModel;

/**
 * @author Aparna Ganesh
 * @author Sharadha Ramaswamy
 */
public interface OrderHistoryDao {
	
	public List<ProductModel> getBestSellingProduct(long vmId) throws SQLException;
	public void updateOrderTable(InventoryModel invProd,String PaymentType,long SmartCalCardNo) throws SQLException;

}
