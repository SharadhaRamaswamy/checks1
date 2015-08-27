package edu.scu.dp.smartcals.admin;

import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JPanel;

import edu.scu.dp.smartcals.exception.DatabaseInitializationException;
import edu.scu.dp.smartcals.vm.VMController;

/**
 * @author Nisha
 *
 */
public class RevenueTableController {

	private RevenueTableModel revenueTableModel;
	private VMController vmController;

	public RevenueTableController(VMController vmController) {

		this.vmController = vmController;
		revenueTableModel = new RevenueTableModel();
		try {
			revenueTableModel.setColumnNames();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public RevenueTableModel getModel() {
		return revenueTableModel;
	}

	/**
	 * Get revenue table display option from view Model data to be populated is
	 * decided accordingly
	 * 
	 * @throws SQLException
	 */
	public void selectUserDisplayOption(String option) {
		if (option == "ALL")
			try {
				revenueTableModel.resetModel();
				revenueTableModel.setAllSalesStats("admin");				
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
		else
			try {
				revenueTableModel.resetModel();
				revenueTableModel.setVMSalesStats(Long.parseLong(option));
				
			} catch (SQLException e) {
				e.printStackTrace();
			}
	}

}
