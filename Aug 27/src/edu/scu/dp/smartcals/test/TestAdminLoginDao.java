package edu.scu.dp.smartcals.test;

import java.sql.SQLException;

import org.junit.BeforeClass;
import org.junit.Test;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.interfaces.AdminLoginDao;
import edu.scu.dp.smartcals.exception.DatabaseInitializationException;
import edu.scu.dp.smartcals.model.AdminLoginModel;

//test admin dao 
public class TestAdminLoginDao {

	private static AdminLoginDao adminLoginDao;

	@BeforeClass
	public static void init() throws DatabaseInitializationException {
		DaoFactory.initialize();
		adminLoginDao = DaoFactory.getAdminLoginDao();
	}

	@Test
	public void testAdminDetails() throws SQLException {
		//valid user
		AdminLoginModel adminLoginModel = adminLoginDao.validateLogin("admin", "test123");
		System.out.println(adminLoginModel.getUsername());
		System.out.println(adminLoginModel.getPassword());

		//incorrect user test
		adminLoginModel = adminLoginDao.validateLogin("nisha", "test123");
		System.out.println(adminLoginModel.getUsername());
		System.out.println(adminLoginModel.getPassword());


	}
}
