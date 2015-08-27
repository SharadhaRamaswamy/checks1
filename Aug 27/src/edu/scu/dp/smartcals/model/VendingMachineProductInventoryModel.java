/**
 * 
 */
package edu.scu.dp.smartcals.model;

import edu.scu.dp.smartcals.constants.InventoryStatus;

/**
 * Model representing Vending Machine Inventory table
 * @author Aparna Ganesh
 *
 */
public class VendingMachineProductInventoryModel {

	private long vendingMachineId;
	
	private long productId;
	
	private int quantity;
	
	private InventoryStatus status;
	

	public long getVendingMachineId() {
		return vendingMachineId;
	}

	public void setVendingMachineId(long vendingMachineId) {
		this.vendingMachineId = vendingMachineId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public InventoryStatus getStatus() {
		return status;
	}

	public void setStatus(InventoryStatus status) {
		this.status = status;
	}
}
