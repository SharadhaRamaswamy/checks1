/**
 * 
 */
package edu.scu.dp.smartcals.vm;

import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.model.VendingMachineModel;

/**
 * @author Aparna Ganesh
 * Vending Machine Factory to return SchoolFactory and Hospital Factory
 */
public abstract class VendingMachineFactory {

	/**
	 * Retuns VendingMachine 
	 * @param vendingMachineModel
	 * @return
	 */
	public abstract VendingMachine createVendingMachine(VendingMachineModel vendingMachineModel);
	
	/**
	 * Method to create Beverage products
	 * @param productModel
	 * @return
	 */
	public abstract Beverage createBreverage(ProductModel productModel);
	
	/**
	 * Method to create Candy products
	 * @param productModel
	 * @return
	 */
	public abstract Candy createCandy(ProductModel productModel);

	/**
	 * Method to create Snack products
	 * @param productModel
	 * @return
	 */
	public abstract Snack createSnack(ProductModel productModel);
	
	
	/**
	 * Returns either SchoolVMFactory or HospitalVMFactory based on user requirement
	 * @param vmLocationType
	 * @return
	 */
	public static VendingMachineFactory getFactory(VMLocationType vmLocationType) {
		
		switch(vmLocationType) {
		case SCHOOL:
			return new SchoolVendingMachineFactory();
			
		
		case HOSPITAL:
			return new HospitalVendingMachineFactory();
			
		 default:
			return null;
	}
		
}
}