/**
 * 
 */
package edu.scu.dp.smartcals.exception;

import java.sql.SQLException;

/**
 * @author Aparna Ganesh
 * User exception for Empty result set
 */
public class EmptyResultException extends SQLException {

	public EmptyResultException() {
		super("Result Not found");
	}

	/**
	 * @param string
	 */
	public EmptyResultException(String string) {
		super(string);
	}
}
