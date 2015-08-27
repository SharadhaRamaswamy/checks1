package edu.scu.dp.smartcals.dao.interfaces;
/**
 * @author Sharadha Ramaswamy
 */
import java.sql.SQLException;

import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.InventoryModel;
import edu.scu.dp.smartcals.model.ProductModel;

public interface InventoryDao {

	public InventoryModel getProductById(long id) throws SQLException, EmptyResultException;

	public void updateInventoryQty(int qty,long prodId) throws SQLException, EmptyResultException;
	
	public boolean removeProductById(long id,long vmId) throws SQLException,EmptyResultException;
	
	public boolean addInvDetails(int id,double price,int vendMachId,int qty) throws SQLException,EmptyResultException;
	
	public boolean modifyInvDetails(long id,double price,int vendMachId,int qty) throws SQLException,EmptyResultException;
} 
