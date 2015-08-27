package edu.scu.dp.smartcals.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import edu.scu.dp.smartcals.model.SalesSummaryModel;

/**
 * @author Nisha Narayanaswamy
 * 
 * 
 */
public interface SalesSummaryDao {
	
	public List<String> getColumnNames() throws SQLException;
	
	public List<SalesSummaryModel> getAllSalesStatistics(String username) throws SQLException ;
	
	public List<SalesSummaryModel> getVMSalesStatistics(long vendingMachineId) throws SQLException ;



}
