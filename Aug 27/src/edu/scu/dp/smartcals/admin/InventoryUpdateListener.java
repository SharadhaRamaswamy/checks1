/**
 * 
 */
package edu.scu.dp.smartcals.admin;

/**
 * @author Aparna Ganesh
 * Interface to listen for changes done by Admin to the inventory
 */
public interface InventoryUpdateListener {
	
	
	public void handleAdd(long productId);
	
	public void handleModify(long productId);
	
	public void handleDelete(long productId);

}
