package edu.scu.dp.smartcals.admin;

import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.swing.table.DefaultTableModel;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.interfaces.SalesSummaryDao;
import edu.scu.dp.smartcals.exception.DatabaseInitializationException;
import edu.scu.dp.smartcals.model.SalesSummaryModel;

/**
 * @author Nisha Narayanaswamy
 * 
 *         RevenueTableModel creates and populates data in revenue table, which
 *         appears in MonitoringStationView
 *
 */
public class RevenueTableModel extends DefaultTableModel {

	private SalesSummaryDao salesSummaryDao;
	private Vector<Object> vectorSales;
	private Vector<Object> vectorAllColumnNames;

	public RevenueTableModel() {
		try {
			DaoFactory.initialize();
		} catch (DatabaseInitializationException e) {
			e.printStackTrace();
		}
		salesSummaryDao = DaoFactory.getSalesSummaryDao();
		vectorSales = new Vector<Object>();
		vectorAllColumnNames = new Vector<Object>();
	}

	/**
	 * Create data model
	 * 
	 * @return
	 * @throws SQLException
	 */
	public RevenueTableModel createAndFetchModelData() throws SQLException {
		this.setDataVector(vectorSales, vectorAllColumnNames);
		return this;
	}

	/**
	 * @return Column names of the DB table
	 * @throws SQLException
	 */
	public void setColumnNames() throws SQLException {
		// get column name from DB table
		List<String> columnName = salesSummaryDao.getColumnNames();

		// convert column name to Vector type and pass it as parameter for
		// default table model
		for (String colName : columnName) {
			vectorAllColumnNames.addElement(colName);
		}
	}

	/**
	 * Get sales statistics for all vending machines assigned to logged in user
	 * 
	 * @param username
	 *            Logged in username
	 * @return
	 * @throws SQLException
	 */
	public void setAllSalesStats(String username) throws SQLException {
		// get all VM data for a given user login
		List<SalesSummaryModel> salesSummaryData = salesSummaryDao.getAllSalesStatistics(username);

		// convert List of type SalesSUmmary to Vector of type Object and pass
		// it as parameter for
		// default table model
		for (SalesSummaryModel element : salesSummaryData) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(element.getVendingMachineID());
			row.addElement(element.getTotalSalesAmount());
			row.addElement(element.getLastModifiedTime());
			vectorSales.addElement(row);
		}

		// return vectorSales;
	}

	/**
	 * Get sales statistics for a particular vending machine
	 * 
	 * @param vendingMachineID
	 * @return
	 * @throws SQLException
	 */
	public void setVMSalesStats(long vendingMachineID) throws SQLException {
		// get all VM data for a given user login
		List<SalesSummaryModel> salesSummaryData = salesSummaryDao.getVMSalesStatistics(vendingMachineID);

		// convert List of type SalesSUmmary to Vector of type Object and pass
		// it as parameter for
		// default table model
		for (SalesSummaryModel element : salesSummaryData) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(element.getVendingMachineID());
			row.addElement(element.getTotalSalesAmount());
			row.addElement(element.getLastModifiedTime());
			vectorSales.addElement(row);
		}

		// return vectorSales;
	}

	/**
	 * Reset model to have 0 rows of data
	 */
	public void resetModel() {
		this.setRowCount(0);
		vectorSales.clear();
		
	}

}
