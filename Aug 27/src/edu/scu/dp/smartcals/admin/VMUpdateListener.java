/**
 * 
 */
package edu.scu.dp.smartcals.admin;

/**
 * @author Aparna Ganesh
 * Listens for any VM updates that Admin monitors
 */
public interface VMUpdateListener {

	public void updateOutOfStock(long vmId,long productId);
}
