/**
 * 
 */
package edu.scu.dp.smartcals.exception;

/**
 * @author Aparna Ganesh
 *
 */
public class OutOfStockException extends Exception {
	
	private long productId;
	
	private long vendingMachineId;
	
	public OutOfStockException(long productId, long vmId) {
		super("Product " + productId + " in Vending Machine " + vmId + " is out of stock");
	}

}
