package edu.scu.dp.smartcals.vm;

import java.util.List;

/**
 * @author Aparna Ganesh
 * @author Nisha Narayanaswamy
 * Interface to perfom Vending Machine operations
 * TODO Differentiate somewhere using some crieteria for School VM and Hospital VM
 *
 */
public interface VendingMachineTemp {
	
	public void buyFood();
	public void payFood();
	public void getNutriInfo();
	public List<Product> searchProductByCalorie();
	public List<Product> searchProductBySmartTag();
	public void buySmartCard();

}
