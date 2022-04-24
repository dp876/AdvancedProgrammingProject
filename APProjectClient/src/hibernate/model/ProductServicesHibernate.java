package hibernate.model;

import java.io.Serializable;

public class ProductServicesHibernate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int productId;
	private String productName;
	
	public ProductServicesHibernate() {
		this.id = 0;
		this.productId = 0;
		this.productName = "";
	}
	
	public ProductServicesHibernate(int id, int productId, String productName) {
		this.id = id;
		this.productId = productId;
		this.productName = productName;
	}
	
	public ProductServicesHibernate(ProductServicesHibernate obj) {
		this.id = obj.id;
		this.productId = obj.productId;
		this.productName = obj.productName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
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
		return "id: " + id + "\nproductId: " + productId + "\nproductName: " + productName + "\n";
	}

}
