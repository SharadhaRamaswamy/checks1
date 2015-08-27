package edu.scu.dp.smartcals.vm;

import java.sql.SQLException;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.dao.interfaces.AdminLoginDao;
import edu.scu.dp.smartcals.model.AdminLoginModel;

/**
 * @author Nisha Narayanaswamy
 * 
 *         Strategy to implement security checkpoint when login attempt fails
 */

// Strategy pattern
public class FailedLoginAttemptStrategy implements LoginCheckPointStrategy {

	private AdminLoginDao adminLoginDao;
	private static final int FAILED_LOGIN_COUNT = 3;

	public FailedLoginAttemptStrategy() {
		adminLoginDao = DaoFactory.getAdminLoginDao();
	}

	@Override
	public boolean performSecurityCheck(String username) {
		try {
			AdminLoginModel adminLoginModel = adminLoginDao
					.getLoginDetails(username);
			if (adminLoginModel.getLoginAttempts() > FAILED_LOGIN_COUNT)
				return false;
			// $$$$$$ Add code to lock account in DB - add column
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return true;
	}

}
