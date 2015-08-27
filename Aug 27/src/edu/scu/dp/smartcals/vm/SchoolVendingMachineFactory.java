
/**
 * 
 */
package edu.scu.dp.smartcals.vm;

import edu.scu.dp.smartcals.admin.AdminOperationsImpl;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.model.VendingMachineModel;

/**
 * @author Aparna Ganesh
 * Factory method pattern to return School Vending Machine, with Beverage,Snack and Candy
 */
public class SchoolVendingMachineFactory extends VendingMachineFactory {

	
	@Override
	public VendingMachine createVendingMachine(VendingMachineModel vendingMachineModel) {
		
		VendingMachine schoolVendingMachine = new SchoolVendingMachine();
		schoolVendingMachine.setVendingMachineId(vendingMachineModel.getVendingMachineId());
		schoolVendingMachine.setLocation(vendingMachineModel.getLocation());
		schoolVendingMachine.setStatus(vendingMachineModel.getStatus());
		schoolVendingMachine.setLocationType(vendingMachineModel.getType());
		
		//Aparna=08/24
		AdminOperationsImpl adminOperations = AdminOperationsImpl.getInstance();
		schoolVendingMachine.addListener(adminOperations);
		
		//add the vending machine as Inventory update listener of AdminOperations
		adminOperations.addInventoryUpdateListeners(schoolVendingMachine);
		
		return schoolVendingMachine;
	}

	
	@Override
	public Beverage createBreverage(ProductModel productModel) {
		Beverage beverageProduct = new Beverage();
		beverageProduct.setProductID(productModel.getProductId());
		beverageProduct.setProductName(productModel.getProductName());
		beverageProduct.setProductPrice(productModel.getProductPrice());
		beverageProduct.setProdCategory(productModel.getCategory().toString());
		
		return beverageProduct;
		
	}

	
	@Override
	public Candy createCandy(ProductModel productModel) {
		Candy candyProduct = new Candy();
		candyProduct.setProductID(productModel.getProductId());
		candyProduct.setProductName(productModel.getProductName());
		candyProduct.setProductPrice(productModel.getProductPrice());
		candyProduct.setProdCategory(productModel.getCategory().toString());
		return candyProduct;
	}

	
	@Override
	public Snack createSnack(ProductModel productModel) {
		Snack snackProduct = new Snack();
		snackProduct.setProductID(productModel.getProductId());
		snackProduct.setProductName(productModel.getProductName());
		snackProduct.setProductPrice(productModel.getProductPrice());
		snackProduct.setProdCategory(productModel.getCategory().toString());
		return snackProduct;
	}

	
	
}
