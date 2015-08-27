package edu.scu.dp.smartcals.test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.scu.dp.smartcals.constants.ProductCategory;
import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.interfaces.ProductDao;
import edu.scu.dp.smartcals.dao.interfaces.VendingMachineDao;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.ProductModel;

/**
 * Junit Test file to test Database operations
 * @author Aparna Ganesh
 *
 */
public class SmartCalsDBTest {

	private static ProductDao productDao;
	
	private static VendingMachineDao vendingMachineDao;
/**
 * Test case to initialise database connection
 * @throws Exception
 */
	@BeforeClass
	public static void init() throws Exception {
		DaoFactory.initialize();
		productDao = DaoFactory.getProductDao();
		vendingMachineDao = DaoFactory.getVendingMachineDao();
		
		
	}

	/*@Test
	public void testGetProductsById() throws SQLException, EmptyResultException {
		


		ProductModel product = productDao.getProductById(101);
		assertTrue( product.getProductId() == 101);

	}*/
	/**
	 * Test case to display all products from Vending Machine table
	 * @throws SQLException
	 * @throws EmptyResultException
	 *//*
	@Test
	public void testGetAllVendingMachines() throws SQLException, EmptyResultException {
		
		
		List<VendingMachineModel> vendingMachines = vendingMachineDao.getAllVMBasicInfo();
		
		for(VendingMachineModel vmModel : vendingMachines) {
			System.out.println(vmModel);
		}
		assertTrue(vendingMachines.size() == 3);


	}
	
	/**
	 * Test case to display all products from for a given VM ID
	 * @throws SQLException
	 * @throws EmptyResultException
	 */
	@Test
	public void testInsertProduct() throws SQLException {
		
		ProductModel productModel = new ProductModel();
		productModel.setCategory(ProductCategory.SNACK);
		productModel.setProductId(500);
		productModel.setProductName("Kettle Pop");
		productModel.setProductPrice(5.99);
		
		productDao.addProduct(productModel);
		
		
	}
	
	/*@Test
	public void testGetProductsByVMId() throws SQLException, EmptyResultException {
		
		
		List<ProductModel> products = vendingMachineDao.getProductsByVMId(1000);
		
		for(ProductModel prodModel : products) {
			System.out.println(prodModel);
		}
		assertTrue(products.size() != 0);


	}*/
	
	
	

}
