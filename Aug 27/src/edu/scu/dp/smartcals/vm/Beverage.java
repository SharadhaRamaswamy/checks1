package edu.scu.dp.smartcals.vm;

/**
 * Beverage class to add more properties to Beverage-for extensibility purpose
 * @author Aparna Ganesh
 *
 */
public class Beverage extends Product {

	@Override
	public String toString() {
		return "Beverage [getProductName()=" + getProductName()
				+ ", getProductID()=" + getProductID() + ", getProductPrice()="
				+ getProductPrice() + "]";
	}

	
}
