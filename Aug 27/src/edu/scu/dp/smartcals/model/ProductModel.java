/**
 * 
 */
package edu.scu.dp.smartcals.model;

import edu.scu.dp.smartcals.constants.ProductCategory;

/**
 * Product Model representing Product DB Table
 * @author Aparna Ganesh
 *
 */
public class ProductModel {


	private long productId;
	
	private String productName;
	
	private ProductCategory category;
	//code change done added price- getter n setter
		private double productPrice;
	
	public ProductCategory getCategory() {
		return category;
	}

	public void setCategory(ProductCategory category) {
		this.category = category;
	}

	

	public double getProductPrice() {
		return productPrice;
	}

	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	@Override
	public String toString() {
		return "ProductModel [productId=" + productId + ", productName="
				+ productName + ", productPrice=" + productPrice + "]";
	} 
	
}
