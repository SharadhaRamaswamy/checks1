package edu.scu.dp.smartcals.vm;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.impl.ProductDaoImpl;


/**
 * @author Aparna Ganesh
 * 
 * SchoolVendingMachine class provides the implementation for a School Vending Machine
 */
public class SchoolVendingMachine extends VendingMachine {

	public SchoolVendingMachine() {
		
		productDao = DaoFactory.getProductDao();
	}

	
	

}
