package edu.scu.dp.smartcals.model;

import java.sql.Timestamp;

/**
 * @author Nisha Narayanaswamy
 * 
 *         SalesSummaryModel represents the SalesSummary table in DB
 *
 */
public class SalesSummaryModel {

	private long vendingMachineID;
	private double totalSalesAmount;
	private String lastModifiedTime;

	public long getVendingMachineID() {
		return vendingMachineID;
	}

	public void setVendingMachineID(long vendingMachineID) {
		this.vendingMachineID = vendingMachineID;
	}

	public double getTotalSalesAmount() {
		return totalSalesAmount;
	}

	public void setTotalSalesAmount(double totalSalesAmount) {
		this.totalSalesAmount = totalSalesAmount;
	}

	public String getLastModifiedTime() {
		return lastModifiedTime;
	}

	public void setLastModifiedTime(String lastModifiedTime) {
		this.lastModifiedTime = lastModifiedTime;
	}

	public void resetModel() {
		this.vendingMachineID = 0;
		this.totalSalesAmount = 0;
		this.lastModifiedTime = null;
	}

}
