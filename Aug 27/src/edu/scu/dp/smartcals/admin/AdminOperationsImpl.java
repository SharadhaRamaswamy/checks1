/**
 * 
 */
package edu.scu.dp.smartcals.admin;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import edu.scu.dp.smartcals.constants.Constants;
import edu.scu.dp.smartcals.constants.ProductCategory;
import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.interfaces.InventoryDao;
import edu.scu.dp.smartcals.dao.interfaces.NutritionalInfoDao;
import edu.scu.dp.smartcals.dao.interfaces.OrderHistoryDao;
import edu.scu.dp.smartcals.dao.interfaces.ProductDao;
import edu.scu.dp.smartcals.dao.interfaces.VendingMachineDao;
import edu.scu.dp.smartcals.exception.AdminOperationsException;
import edu.scu.dp.smartcals.model.InventoryModel;
import edu.scu.dp.smartcals.model.NutritionalInfoModel;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.vm.Beverage;
import edu.scu.dp.smartcals.vm.Candy;
import edu.scu.dp.smartcals.vm.Product;
import edu.scu.dp.smartcals.vm.Snack;
import edu.scu.dp.smartcals.vm.VendingMachineFactory;

/**
 * @author Aparna Ganesh Admin operations implementations
 */
public class AdminOperationsImpl implements AdminOperations, VMUpdateListener {

	/**
	 * Code change-Aparna - 8/18 Added Alert to notify Monitoring Station View
	 */
	private List<AlertListener> alertListeners;
	
	private Set<InventoryUpdateListener> inventoryUpdateListeners;

	private OrderHistoryDao orderHistoryDao;

	private VendingMachineDao vendingMachineDao;
	
	private InventoryDao invDao;
	
	private static AdminOperationsImpl INSTANCE;

	// code change-Aparna 08/23
	private ProductDao productDao;
	
	//nisha - 8/24
	private NutritionalInfoDao nutriDao;

	private AdminOperationsImpl() {
		alertListeners = new ArrayList<>();
		inventoryUpdateListeners = new HashSet<>();
		orderHistoryDao = DaoFactory.getOrderHistoryDao();
		vendingMachineDao = DaoFactory.getVendingMachineDao();
		invDao = DaoFactory.getInventoryDao();
		// code change-Aparna 08/23
		productDao = DaoFactory.getProductDao();
		
		//nisha - 8/24
		nutriDao = DaoFactory.getNutritionalInfoDao();

	}
	
	public static AdminOperationsImpl getInstance() {
		if(INSTANCE == null) {
			INSTANCE = new AdminOperationsImpl();
		}
		return INSTANCE;
	}

	public void addAlertListeners(AlertListener alertListener) {

		alertListeners.add(alertListener);

	}

	public void sendOutOfStockAlert(long vmId, long productId) {

		Alert alert = new OutOfStockAlert();

		alert.addProperty(Constants.PRODUCT_ID_KEY, productId + "");
		alert.addProperty(Constants.VM_ID_KEY, vmId + "");

		for (AlertListener alertListener : alertListeners) {
			alertListener.update(alert);
		}
	}

	// -------------code change-Aparna 8/18

	
	// code change-Aparna 08/23
	/**
	 * Add new product-Admin
	 * 
	 * @throws SQLException
	 */

	@Override
	public void addNewProduct(Product product) throws SQLException {

		// get from Product and set to ProductModel and send it to DB
		ProductModel productModel = new ProductModel();
		productModel.setCategory(ProductCategory.valueOf(product.getProdCategory().toUpperCase()));
		productModel.setProductId(product.getProductID());
		productModel.setProductName(product.getProductName());
		productModel.setProductPrice(product.getProductPrice());

		productDao.addProduct(productModel);
		
	}

	//code change-Aparna -08/24 similar to Add product
	
	@Override
	public void updateProduct(Product product, long productId) throws SQLException {
		// get from Product and set to ProductModel and send it to DB
				ProductModel productModel = new ProductModel();
				productModel.setProductId(productId);
				productModel.setCategory(ProductCategory.valueOf(product.getProdCategory().toUpperCase()));
				productModel.setProductName(product.getProductName());
				productModel.setProductPrice(product.getProductPrice());

				productDao.updateProduct(productModel, productId);
				
				//send notification 
				notifyInventoryModified(productId);
		
	}

	/*
	 * delete product-Admin
	 * code change-Aparna 08/23
	 */
	@Override
	public void deleteProduct(long productId) throws AdminOperationsException {
		try {
			productDao.deleteProduct(productId);
		} catch (SQLException e) {
			
			e.printStackTrace();
			throw new AdminOperationsException("Error deleteting product with product ID "+ productId,e);
		}
		
		//send delete notification
		notifyInventoryDeleted(productId);
		
	}
	
	//code change-Aparna 08/24
	@Override
	public ProductModel getProduct(long productId) throws AdminOperationsException {
		ProductModel productModel = null;
		try {
			productModel = productDao.getProductById(productId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw new AdminOperationsException("Product "+ productId + " not found",e);
		}
		return productModel;
	}
	
	//----------------------------------------------
	@Override
	public void updateOutOfStock(long vmId, long productId) {
		// notify MonitoringStationView
		System.out.println("The product " + productId + " for the VM " + vmId
				+ " is out of Stock");
		sendOutOfStockAlert(vmId, productId);
	}

	/**
	 * Returns best selling products
	 * 
	 * @throws SQLException
	 */
	@Override
	public List<Product> getBestSellingProduct(long vmId)
			throws AdminOperationsException {

		List<Product> products = new ArrayList<>();
		List<ProductModel> productModels;
		VMLocationType type;

		try {
			productModels = orderHistoryDao.getBestSellingProduct(vmId);
			type = vendingMachineDao.getVendingMachineType(vmId);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminOperationsException(
					"Error getting best selling product for VM : " + vmId, e);
		}

		VendingMachineFactory vendingMachineFactory = VendingMachineFactory
				.getFactory(type);

		for (ProductModel productModel : productModels) {

			switch (productModel.getCategory()) {
			case BEVERAGE:
				Beverage beverage = vendingMachineFactory
						.createBreverage(productModel);
				products.add(beverage);

				break;
			case CANDY:
				Candy candy = vendingMachineFactory.createCandy(productModel);
				products.add(candy);
				break;
			case SNACK:
				Snack snack = vendingMachineFactory.createSnack(productModel);
				products.add(snack);
				break;

			}
		}

		return products;
	}

	//nisha - 8/24
	@Override
	public NutritionalInfoModel searchNutriInfo(long productId) throws SQLException {
		NutritionalInfoModel nutriInfo = nutriDao.getNutriInfo(productId);
		return nutriInfo;
		
	}

	@Override
	public boolean addNewNutriInfo(ArrayList<String> dataValues) throws SQLException {
		
		return nutriDao.addNutriInfo(dataValues);
		
	}
	
	
	@Override
	public boolean updateNewNutriInfo(ArrayList<String> dataValues) throws SQLException {
		
		return nutriDao.updateNutriInfo(dataValues);
		
	}

	@Override
	public boolean deleteNutriInfo(long productID) throws SQLException {
		
		return nutriDao.deleteNutriInfo(productID);
	}//end - nisha - 8/24

	
	@Override
	public void addInventoryUpdateListeners(
			InventoryUpdateListener invUpdateListener) {
		inventoryUpdateListeners.add(invUpdateListener);
		
	}
	
	@Override
	public InventoryModel searchInventory(long prodId)
	{
		InventoryModel invProductData = null;
		try {
			invProductData = invDao.getProductById(prodId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return invProductData;
	}
	
	@Override
	public boolean addInventoryData(int prodId,double price,int vendMachId,int qty)
	{
		boolean res = false;
		try {
			res = invDao.addInvDetails(prodId,price,vendMachId,qty);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send product added notification to all inventory update listeners
		notifyInventoryAdded(prodId);

		return res;
	}
	
	@Override
	public boolean modifyInventory(long prodId,double price,int vendMachId,int qty)
	{
		boolean res = false;
		try {
			res = invDao.modifyInvDetails(prodId,price,vendMachId,qty);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send product modify notification to all inventory update listeners
		notifyInventoryModified(prodId);
		
		return res;
	}

	
	
	public boolean deleteInventory(long prodId,long vmId){
		boolean status = false;
		try {
			status = invDao.removeProductById(prodId,vmId);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//send product deleted notification to all inventory update listeners
		notifyInventoryDeleted(prodId);
		
		return status;
	}
	
	
	private void notifyInventoryAdded(final long productId) {
		for(InventoryUpdateListener listener : inventoryUpdateListeners) {
			listener.handleAdd(productId);
		}
	}
	
	private void notifyInventoryModified(final long productId) {
		for(InventoryUpdateListener listener : inventoryUpdateListeners) {
			listener.handleModify(productId);
		}
	}
	private void notifyInventoryDeleted(final long productId) {
		for(InventoryUpdateListener listener : inventoryUpdateListeners) {
			listener.handleDelete(productId);
		}
	}

}
