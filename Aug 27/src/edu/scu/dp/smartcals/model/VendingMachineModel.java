/**
 * 
 */
package edu.scu.dp.smartcals.model;

import java.util.List;

import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.constants.VMStatus;

/**
 * Model representing a VendingMachine DB Table
 * 
 * @author Aparna Ganesh
 *
 */
public class VendingMachineModel {

	private long vendingMachineId;

	private String location;

	private VMStatus status;
	
	private VMLocationType type;

	private List<ProductModel> productModels;

	public VMLocationType getType() {
		return type;
	}

	public void setType(VMLocationType type) {
		this.type = type;
	}

	public long getVendingMachineId() {
		return vendingMachineId;
	}

	public void setVendingMachineId(long vendingMachineId) {
		this.vendingMachineId = vendingMachineId;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public VMStatus getStatus() {
		return status;
	}

	public void setStatus(VMStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "VendingMachineModel [vendingMachineId=" + vendingMachineId
				+ ", location=" + location + ", status=" + status + ", type="
				+ type + "]";
	}

	/**
	 * @return the productModels
	 */
	public List<ProductModel> getProductModels() {
		return productModels;
	}

	/**
	 * @param productModels the productModels to set
	 */
	public void setProductModels(List<ProductModel> productModels) {
		this.productModels = productModels;
	}

	}
