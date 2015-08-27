package edu.scu.dp.smartcals.vm;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;


/**
 * @author Aparna Ganesh
 * Hospital Vending Machine class
 *
 */
public class HospitalVendingMachine extends VendingMachine {


	public HospitalVendingMachine() {
		productDao = DaoFactory.getProductDao();
	}
}
