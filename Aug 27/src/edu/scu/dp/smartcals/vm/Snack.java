package edu.scu.dp.smartcals.vm;

/**
 * Snack class to add more properties to Snack-for extensibility purpose
 * @author Aparna Ganesh
 *
 */
public class Snack extends Product {

	@Override
	public String toString() {
		return "Snack [getProductName()=" + getProductName()
				+ ", getProductID()=" + getProductID() + ", getProductPrice()="
				+ getProductPrice() + "]";
	}

}
