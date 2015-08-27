package edu.scu.dp.smartcals.dao.impl;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import edu.scu.dp.smartcals.constants.Constants;
import edu.scu.dp.smartcals.constants.DbType;
import edu.scu.dp.smartcals.dao.interfaces.AdminLoginDao;
import edu.scu.dp.smartcals.dao.interfaces.DatabaseFactory;
import edu.scu.dp.smartcals.dao.interfaces.InventoryDao;
import edu.scu.dp.smartcals.dao.interfaces.NutritionalInfoDao;
import edu.scu.dp.smartcals.dao.interfaces.OrderHistoryDao;
import edu.scu.dp.smartcals.dao.interfaces.ProductDao;
import edu.scu.dp.smartcals.dao.interfaces.SalesSummaryDao;
import edu.scu.dp.smartcals.dao.interfaces.SmartCardDao;
import edu.scu.dp.smartcals.dao.interfaces.VendingMachineDao;
import edu.scu.dp.smartcals.exception.DatabaseInitializationException;

/**
 * @author Aparna Ganesh
 * Class to initialize databse connection once for the entire application
 */
public class DaoFactory {
	
	private static DatabaseFactory databaseFactory;
	
	/**
	 * This initializes the database factory. 
	 * This method should be called only once for the entire program
	 * @throws ClassNotFoundException
	 * @throws IOException
	 */
	public static void initialize() throws DatabaseInitializationException  {
		FileInputStream f;
		try {
			f = new FileInputStream("db.properties");
		} catch (FileNotFoundException e) {
			
			e.printStackTrace();
			throw new DatabaseInitializationException(e.getMessage());
		}
		Properties properties = new Properties();
		try {
			properties.load(f);
		} catch (IOException e1) {
			
			e1.printStackTrace();
			throw new DatabaseInitializationException(e1.getMessage());
		}
		String dbType = properties.getProperty(Constants.DB_TYPE);
		try {
			databaseFactory = DatabaseFactory.getFactory(properties,
					DbType.valueOf(dbType));
		} catch (ClassNotFoundException e) {
		
			e.printStackTrace();
			throw new DatabaseInitializationException(e.getMessage());
		}
		
	}
	/**
	 * Returns single instance of the dao
	 * @return
	 */
	public static ProductDao getProductDao(){
		return ProductDaoImpl.getInstance(databaseFactory);
	}
	
	public static VendingMachineDao getVendingMachineDao() {
		return VendingMachineDaoImpl.getInstance(databaseFactory);
	}
	
	public static NutritionalInfoDao getNutritionalInfoDao(){
		return NutritionalInfoDaoImpl.getInstance(databaseFactory);
	}
	
	public static AdminLoginDao getAdminLoginDao(){
		return AdminLoginDaoImpl.getInstance(databaseFactory);
	}
	

	public static OrderHistoryDao getOrderHistoryDao() {
		return OrderHistoryDaoImpl.getInstance(databaseFactory);
	}

	//Sharadha
	public static SmartCardDao getSmartCardDao(){
		return SmartCardDaoImpl.getInstance(databaseFactory);
	}
	
	public static InventoryDao getInventoryDao(){
		return InventoryDaoImpl.getInstance(databaseFactory);
	}
	
	//Nisha - 8/22
	public static SalesSummaryDao getSalesSummaryDao(){
		return SalesSummaryDaoImpl.getInstance(databaseFactory);
	}
	
	


}
