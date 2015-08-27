package edu.scu.dp.smartcals.vm;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import edu.scu.dp.smartcals.admin.InventoryUpdateListener;
import edu.scu.dp.smartcals.admin.VMUpdateListener;
import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.constants.VMStatus;
import edu.scu.dp.smartcals.dao.interfaces.ProductDao;
import edu.scu.dp.smartcals.exception.AdminOperationsException;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.ProductModel;

/**
 * @author Aparna Ganesh Vending Machine class to perform all Vending Machine
 *         operations. Implements the methods listed from Vending Machine
 *         Interface.
 */
public abstract class VendingMachine implements InventoryUpdateListener {

	private long vendingMachineId;

	private String location;

	private VMStatus status;

	private VMLocationType locationType;

	private List<Beverage> beverages;

	private List<Candy> candies;

	private List<Snack> snacks;

	protected ProductDao productDao;

	/*
	 * Implementing Observer pattern for Admin monitoring Holds
	 * VMUpdateListeners to notify Admin when product is out of stock code
	 * change -Aparna 8/18
	 */

	private List<VMUpdateListener> vmListeners = new ArrayList<>();

	// Registering Admin as VM Listeners

	public void addListener(VMUpdateListener listener) {

		vmListeners.add(listener);
	}

	/**
	 * Notifies Admin if the product is out of Stock
	 * 
	 * @param productId
	 */
	public void notifyOutOfStock(long productId, long vmId) {

		for (VMUpdateListener listener : vmListeners) {
			listener.updateOutOfStock(vmId, productId);
		}

	}

	// ----------code change-Aparna 8/18

	public List<Beverage> getBeverages() {
		return beverages;
	}

	public void setBeverages(List<Beverage> beverages) {
		this.beverages = beverages;
	}

	public List<Candy> getCandies() {
		return candies;
	}

	public void setCandies(List<Candy> candies) {
		this.candies = candies;
	}

	public List<Snack> getSnacks() {
		return snacks;
	}

	public void setSnacks(List<Snack> snacks) {
		this.snacks = snacks;
	}

	public void buyFood() {
		// TODO Auto-generated method stub

	}

	public void payFood() {
		// TODO Auto-generated method stub

	}

	public void getNutriInfo() {
		// TODO Auto-generated method stub

	}

	public void buySmartCard() {
		// TODO Auto-generated method stub

	}

	public long getVendingMachineId() {
		return vendingMachineId;
	}

	public void setVendingMachineId(long vendingMachineId) {
		this.vendingMachineId = vendingMachineId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public VMStatus getStatus() {
		return status;
	}

	public void setStatus(VMStatus status) {
		this.status = status;
	}

	public VMLocationType getLocationType() {
		return locationType;
	}

	public void setLocationType(VMLocationType locationType) {
		this.locationType = locationType;
	}

	@Override
	public void handleAdd(long productId) {
		// Add the new product to this Vending Machine
		try {
			Product product = getProduct(productId);
			addProductToVendingMachine(product);
		}
		catch(Exception e) {
			System.out.println("Ignoring incomplete inventory add notification for Product "+ productId);
		}
	}


	@Override
	public void handleModify(long productId) {
		
		try {
			// Update the Vending machine with the modified product info
			Product product = getProduct(productId);
			
			//Get the appropriate list(Beverages | Candies | Snacks) based on the product category
			List<? extends Product> productList = getProductList(product);

			//Delete the old product from the list
			deleteProduct(productId, productList);

			//Add the new product to the list
			addProductToVendingMachine(product);
		}
		catch(AdminOperationsException e) {
			System.out.println("Ignorning incomplete inventory modify notification " + productId);
		}
		catch(Exception e) {
			System.out.println("Ignorning incomplete inventory modify notification " + productId);
		}

	}

	@Override
	public void handleDelete(long productId) {
		// Delete the product from the Vending machine
		try {
			deleteProductById(productId);
		}
		catch(Exception e) {
			System.out.println("Ignoring incomplete inventory delete notification " + productId);
		}

	}

	/**
	 * @param productId
	 */
	private boolean deleteProductById(long productId) {

		//Since product category is unknown, we need to try deleting from all product lists
		//TODO Product category should be sent to this method.
		return deleteProduct(productId, beverages) || deleteProduct(productId, snacks) || deleteProduct(productId, candies);

	}

	private Product getProduct(long productId) throws AdminOperationsException {
		ProductModel productModel = null;
		try {
			productModel = productDao.getProductById(productId);
		} catch (EmptyResultException e) {
			e.printStackTrace();
			throw new AdminOperationsException("Product not found " + productId , e);
		} catch (SQLException e) {
			e.printStackTrace();
			throw new AdminOperationsException("Error retrieving product info for " + productId + " from database", e);
		}
		VendingMachineFactory vendingMachineFactory = VendingMachineFactory
				.getFactory(locationType);
		Product product = null;
		switch (productModel.getCategory()) {
		case BEVERAGE:
			product = vendingMachineFactory.createBreverage(productModel);
			break;
		case CANDY:
			product = vendingMachineFactory.createCandy(productModel);
			break;
		case SNACK:
			product = vendingMachineFactory.createSnack(productModel);

			break;

		}
		return product;
	}

	/**
	 * Adds the product to appropriate product(Beverage | Candy | Snack) list
	 * @param product
	 */
	private void addProductToVendingMachine(Product product) {

		String category = product.getProdCategory();

		if(category.equalsIgnoreCase("BEVERAGE")) {
			beverages.add((Beverage) product);
			Collections.sort(beverages);
		}
		else if(category.equalsIgnoreCase("SNACK")) {
			snacks.add((Snack) product);
			Collections.sort(snacks);
		}
		else if(category.equalsIgnoreCase("CANDY")) {
			candies.add((Candy) product);
			Collections.sort(candies);
		}
		else {
			//do nothing
		}

	}

	private List<? extends Product> getProductList(Product product) {
		String category = product.getProdCategory();

		if(category.equalsIgnoreCase("BEVERAGE")) {
			return beverages;
		}
		else if(category.equalsIgnoreCase("SNACK")) {
			return snacks;
		}
		else if(category.equalsIgnoreCase("CANDY")) {
			return candies;
		}
		else {
			//do nothing
		}
		return null;
	}

	private boolean deleteProduct(long productId, List<? extends Product> productList) {
		Iterator<? extends Product> itr = productList.iterator();
		while(itr.hasNext()) {
			if(itr.next().getProductID() == productId) {
				itr.remove();
				return true;
			}
		}
		return false;
	}


}
