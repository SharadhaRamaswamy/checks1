package edu.scu.dp.smartcals.test;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.interfaces.NutritionalInfoDao;
import edu.scu.dp.smartcals.exception.DatabaseInitializationException;
import edu.scu.dp.smartcals.exception.EmptyResultException;
import edu.scu.dp.smartcals.model.NutritionalInfoModel;

public class TestNutriInfo {
	
	private static NutritionalInfoDao nutriInfoDao;
	
	@BeforeClass
	public static void init() throws DatabaseInitializationException {
		DaoFactory.initialize();		
		//nutriInfoDao = new NutritionalInfoDaoImpl(dbFactory);
		nutriInfoDao = DaoFactory.getNutritionalInfoDao();
	}
	
	@Test
	public void testGetProdID() throws SQLException, EmptyResultException{
		//the productID needs to be passed from UI once the user selects one.
		//For testing purpose productId is hardcoded
		NutritionalInfoModel nutri = nutriInfoDao.getNutriInfo(103);
		System.out.println(nutri);	
		
	}
	
}
