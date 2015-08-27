/**
 * 
 */
package edu.scu.dp.smartcals.dao.interfaces;

import java.sql.SQLException;
import java.util.List;

import edu.scu.dp.smartcals.constants.VMLocationType;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.ProductModel;
import edu.scu.dp.smartcals.model.VendingMachineModel;

/**
 * Vending Machine DAO
 * @author Aparna Ganesh
 *
 */
public interface VendingMachineDao {
	
	/**
	 * Lists all vending machines from Vending machine table
	 * @return
	 * @throws EmptyResultException
	 * @throws SQLException
	 */
	public List<VendingMachineModel> getAllVMBasicInfo() throws EmptyResultException, SQLException;
	
	
	/**
	 * Retrieve the vending machine given the vending machine ID 
	 * @param vmId
	 * @return
	 * @throws EmptyResultException
	 * @throws SQLException
	 */
	
	public VendingMachineModel getVendingMachine(long vmId) throws EmptyResultException, SQLException;
	
	
	/**
	 * Returns all products for a selected VM
	 * @param vmId
	 * @return
	 * @throws SQLException 
	 * @throws EmptyResultException 
	 */
		public List<ProductModel> getProductsByVMId(long vmId) throws SQLException;


	/**
	 * code change -Aparna -8/21
	 * Returns the vending machine type
	 * @param vmId
	 * @return
	 * @throws SQLException 
	 */
	public VMLocationType getVendingMachineType(long vmId) throws SQLException;
	

}
