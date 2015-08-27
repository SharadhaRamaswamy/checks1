package edu.scu.dp.smartcals.model;

/**
 * @author Sharadha Ramaswamy
 */
import edu.scu.dp.smartcals.constants.ProductCategory;

public class InventoryModel {
	
	private long productId;
	private long VendingMachineId;
	private long skuId;
	private double productPrice;
	private int qty;
	private String inventoryStatus;

	
	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public long getVendingMachineId() {
		return VendingMachineId;
	}

	public void setVendingMachineId(long VendingMachineId) {
		this.VendingMachineId = VendingMachineId;
	}

	public long getskuId() {
		return skuId;
	}

	public void setskuId(long skuId) {
		this.skuId = skuId;
	}

	public int getqty() {
		return qty;
	}

	public void setqty(int qty) {
		this.qty = qty;
	}
	
	public String getinventoryStatus() {
		return inventoryStatus;
	}

	public void setinventoryStatus(String inventoryStatus) {
		this.inventoryStatus = inventoryStatus;
	}

	

}
