package edu.scu.dp.smartcals.model;

import java.sql.Timestamp;

/**
 * @author Nisha Narayanaswamy
 * 
 * AdminLoginModel represents the AdministratorLogin table in DB
 *
 */
public class AdminLoginModel {
	
	private String username;
	private String password;
	private int loginAttempts;
	private long vendingMachineID;
	private String location;
	private Timestamp loginTimeStamp;
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getLoginAttempts() {
		return loginAttempts;
	}
	public void setLoginAttempts(int loginAttempts) {
		this.loginAttempts = loginAttempts;
	}
	public long getVendingMachineID() {
		return vendingMachineID;
	}
	public void setVendingMachineID(long vendingMachineID) {
		this.vendingMachineID = vendingMachineID;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Timestamp getLoginTimeStamp() {
		return loginTimeStamp;
	}
	public void setLoginTimeStamp(Timestamp loginTimeStamp) {
		this.loginTimeStamp = loginTimeStamp;
	}
	public void resetModel(){
		setLocation(null);
		setLoginAttempts(0);
		setLoginTimeStamp(null);
		setUsername(null);
		setPassword(null);
		setVendingMachineID(0);		
	}
	
}
