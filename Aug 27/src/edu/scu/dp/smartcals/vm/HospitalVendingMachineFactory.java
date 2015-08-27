/**
 * 
 */
package edu.scu.dp.smartcals.vm;

import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.model.VendingMachineModel;

/**
 * Factory class to return Hospital Vending Machine
 * @author Aparna Ganesh
 *No Implementation provided so far-TODO
 */
public class HospitalVendingMachineFactory extends VendingMachineFactory {


/**
 * Factory method pattern to retun Hospital Vending Machine
 */
	@Override
	public VendingMachine createVendingMachine(VendingMachineModel vendingMachineModel) {
		VendingMachine hospitalVendingMachine = new HospitalVendingMachine();
		//TODO set id,location, status for hospital implementation
		return null;
	}

/* (non-Javadoc)
 * @see edu.scu.dp.smartcals.vm.VendingMachineFactory#createBreverage(edu.scu.dp.smartcals.model.ProductModel)
 */
@Override
public Beverage createBreverage(ProductModel productModel) {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see edu.scu.dp.smartcals.vm.VendingMachineFactory#createCandy(edu.scu.dp.smartcals.model.ProductModel)
 */
@Override
public Candy createCandy(ProductModel productModel) {
	// TODO Auto-generated method stub
	return null;
}

/* (non-Javadoc)
 * @see edu.scu.dp.smartcals.vm.VendingMachineFactory#createSnack(edu.scu.dp.smartcals.model.ProductModel)
 */
@Override
public Snack createSnack(ProductModel productModel) {
	// TODO Auto-generated method stub
	return null;
}
	}


