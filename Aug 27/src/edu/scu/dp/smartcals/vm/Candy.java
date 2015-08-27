package edu.scu.dp.smartcals.vm;

/**
 * Candy class to add more properties to Candy-for extensibility purpose
 * @author Aparna Ganesh
 *
 */
public class Candy extends Product {

	@Override
	public String toString() {
		return "Candy [getProductName()=" + getProductName()
				+ ", getProductID()=" + getProductID() + ", getProductPrice()="
				+ getProductPrice() + "]";
	}

}
