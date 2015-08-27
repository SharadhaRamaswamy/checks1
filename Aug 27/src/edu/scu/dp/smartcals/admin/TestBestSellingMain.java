
/**
 * 
 */
package edu.scu.dp.smartcals.admin;

import java.sql.SQLException;
import java.util.List;

import edu.scu.dp.smartcals.dao.impl.DaoFactory;
import edu.scu.dp.smartcals.exception.AdminOperationsException;
import edu.scu.dp.smartcals.exception.DatabaseInitializationException;
import edu.scu.dp.smartcals.vm.Product;

/**
 * @author Aparna Ganesh
 *
 */
public class TestBestSellingMain {

	public static void main(String args[]) throws SQLException, DatabaseInitializationException, AdminOperationsException {

		DaoFactory.initialize();
		AdminOperations admin = AdminOperationsImpl.getInstance();
		List<Product> products = admin.getBestSellingProduct(2000);

		for (Product product: products) {
			System.out.println("Best Selling is " + product.toString());
		}

	}
}
