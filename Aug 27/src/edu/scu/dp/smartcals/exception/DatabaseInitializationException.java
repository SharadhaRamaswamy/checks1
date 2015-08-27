/**
 * 
 */
package edu.scu.dp.smartcals.exception;

/**
 * @author Aparna Ganesh
 * User Exception for Database initialization error.
 */
public class DatabaseInitializationException extends Exception {

	/**
	 * @param message
	 */
	public DatabaseInitializationException(String message) {
		super("Error in initializing Database connection");
	}

}
