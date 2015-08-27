package edu.scu.dp.smartcals.dao.interfaces;

import java.sql.SQLException;

import edu.scu.dp.smartcals.model.AdminLoginModel;

/**
 * @author Nisha Narayanaswamy
 * 
 * Administrator Login DAO
 */
public interface AdminLoginDao {
	
	public AdminLoginModel validateLogin(String username, String password) throws SQLException;
	
	public AdminLoginModel getLoginDetails(String username) throws SQLException;
	
	public void setLastLoginTime(String username) throws SQLException;
	
	public void setLoginFailedAttempt(String username) throws SQLException;

}
