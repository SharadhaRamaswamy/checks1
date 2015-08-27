/**
 * 
 */
package edu.scu.dp.smartcals.admin;

import edu.scu.dp.smartcals.constants.Constants;

/**
 * @author Aparna Ganesh
 *
 */
public class OutOfStockAlert extends Alert {

	public OutOfStockAlert() {
		alertType = AlertType.OUTOFSTOCK;
	}

	@Override
	public String getMessage() {
		
		return "Product "+getProperty(Constants.PRODUCT_ID_KEY)+" in VM "+ getProperty(Constants.VM_ID_KEY)+ " is out of Stock";
	}
	
	

}
