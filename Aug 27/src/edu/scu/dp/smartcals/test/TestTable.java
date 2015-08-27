package edu.scu.dp.smartcals.test;
/*test*/
import java.awt.Color;
import java.awt.Container;
import java.sql.SQLException;
import java.util.List;
import java.util.Vector;

import javax.lang.model.element.Element;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.xml.ws.handler.MessageContext.Scope;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.interfaces.SalesSummaryDao;
import edu.scu.dp.smartcals.exception.DatabaseInitializationException;
import edu.scu.dp.smartcals.model.SalesSummaryModel;

/**
 * @author Nisha
 *
 */
public class TestTable extends JPanel {

	private JTable tblRevenue;
	private JScrollPane scrollPane;
	private static SalesSummaryDao salesSummaryDao;

	public TestTable() throws DatabaseInitializationException, SQLException {

		DaoFactory.initialize();
		salesSummaryDao = DaoFactory.getSalesSummaryDao();
		List<SalesSummaryModel> salesSummaryData;

		// get column name from DB table
		List<String> columnName = salesSummaryDao.getColumnNames();

		// get all VM data for a given user login
		// salesSummaryData = salesSummaryDao.getAllSalesStatistics("admin");

		// get VM specific data values
		 salesSummaryData = salesSummaryDao.getVMSalesStatistics(1000);

		// convert List of type SalesSUmmary to Vector of type Object
		Vector<Object> vectorSales = new Vector<Object>();

		for (SalesSummaryModel element : salesSummaryData) {
			Vector<Object> row = new Vector<Object>();
			row.addElement(element.getVendingMachineID());
			row.addElement(element.getTotalSalesAmount());
			row.addElement(element.getLastModifiedTime());
			vectorSales.addElement(row);
		}

		// convert column name to Vector type
		Vector<Object> allColumnNames = new Vector<Object>();
		for (String colName : columnName) {
			allColumnNames.addElement(colName);
		}

		// create data model
		DefaultTableModel tblModel = new DefaultTableModel(vectorSales,
				allColumnNames);

		// create table and add model to table
		tblRevenue = new JTable(tblModel);
		tblRevenue.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

		// create scrollpane and add table to it
		scrollPane = new JScrollPane(tblRevenue);

		// add scrollpane(with table) to this panel
		this.add(scrollPane);

		this.setBackground(Color.PINK);

	}

	public static void main(String[] args) {
		JFrame window = new JFrame("Test JTable");
		window.setExtendedState(JFrame.MAXIMIZED_BOTH);
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Container c = window.getContentPane();

		TestTable testTable;
		try {
			testTable = new TestTable();
			c.add(testTable);
		} catch (DatabaseInitializationException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		window.setVisible(true);
	}
}
