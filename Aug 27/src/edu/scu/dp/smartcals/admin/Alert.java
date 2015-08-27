/**
 * 
 */
package edu.scu.dp.smartcals.admin;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Aparna Ganesh
 *
 */
public abstract class Alert {

	protected String message;
	protected AlertType alertType;
	
	private Map<String,String> properties = new HashMap<>();
	
	public void addProperty(String key,String value) {
		properties.put(key, value);
	}
	
	public String getProperty(String key) {
		return properties.get(key);
	}
	
	public String getMessage() {
		return message;
	}
	public AlertType getAlertType() {
		return alertType;
	}
	
}
