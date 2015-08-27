package edu.scu.dp.smartcals.vm;


/**
 * @author Aparna Ganesh
 * 
 * Product Class to hold Product attributes
 *
 */
public abstract class Product implements Comparable<Product> {
	
	private String prodCategory;
	private String productName;
	private long productID;
	private double productPrice;
	
	
	public String getProdCategory() {
		return prodCategory;
	}
	public void setProdCategory(String prodCategory) {
		this.prodCategory = prodCategory;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public long getProductID() {
		return productID;
	}
	public void setProductID(long productID) {
		this.productID = productID;
	}
	public double getProductPrice() {
		return productPrice;
	}
	public void setProductPrice(double productPrice) {
		this.productPrice = productPrice;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (productID ^ (productID >>> 32));
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (productID != other.productID)
			return false;
		return true;
	}
	@Override
	public int compareTo(Product p) {
        return (int) (productID - p.getProductID());
	}
	
	

}
