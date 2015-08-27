
/**
 * 
 */
package edu.scu.dp.smartcals.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import edu.scu.dp.smartcals.exception.AdminOperationsException;
import edu.scu.dp.smartcals.model.InventoryModel;
import edu.scu.dp.smartcals.model.NutritionalInfoModel;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.vm.Product;
import edu.scu.dp.smartcals.vm.VendingMachine;

/**
 * @author Aparna Ganesh
 * Interface to define Admin operations
 */
public interface AdminOperations {
	
	/**
	 * API to search product inventory for a given Product ID
	 * @param prodId
	 * @return
	 */
	public InventoryModel searchInventory(long prodId);


	/**
	 * API to modify product inventory 
	 * @param productId
	 * @param price
	 * @param vmId
	 * @param quantity
	 * @return
	 */
	public boolean modifyInventory(long productId,double price, int vmId, int quantity);
	
	/**
	 * API to add new product to the inventory
	 * @param productId
	 * @param price
	 * @param vmId
	 * @param quantity
	 * @return
	 */
	public boolean addInventoryData(int productId,double price,int vmId,int quantity);
	
	/**
	 * API to delete product data from the inventory
	 * @param prodId
	 * @param vmId 
	 * @return
	 */
	public boolean deleteInventory(long prodId, long vmId);
	
	//code change-Aparna 08/24
	
	/**
	 * API to get Product data
	 * @param productId
	 * @return
	 * @throws AdminOperationsException
	 */
	public ProductModel getProduct(long productId) throws AdminOperationsException;
	
	
	//code change-Aparna 08/24
	
	/**
	 * API to register for alert notifications
	 * @param alertListener
	 */
	 public void addAlertListeners(AlertListener alertListener);
	 
	 /**
	  * API to register for inventory update notifications
	  * @param invUpdateListener
	  */
	 public void addInventoryUpdateListeners(InventoryUpdateListener invUpdateListener);
	 
	 //code change-Aparna 08/25
	 /**
	  * API to update Product info( Price,category,Price)	
	  * @param product
	  * @param productId
	  * @throws SQLException
	  */
	 public void updateProduct(Product product,long productId) throws SQLException;
	//---------------------------------------------------
	/**
	 * code change-Aparna 08/23
	 * Add new product -By Admin
	 * @param product
	 * @throws SQLException 
	 */
	public void addNewProduct(Product product) throws SQLException;
	
	//code change-Aparna 08/23
	
	public void deleteProduct(long productId) throws AdminOperationsException;
	
	public List<Product> getBestSellingProduct(long vmId) throws AdminOperationsException;
	
	//nisha - 8/24
	public NutritionalInfoModel searchNutriInfo(long productId) throws SQLException;
	
	public boolean addNewNutriInfo(ArrayList<String> dataValues) throws SQLException;
	
	public boolean updateNewNutriInfo(ArrayList<String> dataValues) throws SQLException;
	
	public boolean deleteNutriInfo(long productID) throws SQLException;

	
	
}


