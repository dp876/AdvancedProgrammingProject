package object.classes;

import java.io.Serializable;

public class ProductServices implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int productId;
	private String productName;
	
	public ProductServices() {
		this.id = 0;
		this.productId = 0;
		this.productName = "";
	}
	
	public ProductServices(int id, int product_id, String product_name) {
		this.id = id;
		this.productId = product_id;
		this.productName = product_name;
	}
	
	public ProductServices(ProductServices obj) {
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

	public int getProduct_id() {
		return productId;
	}

	public void setProduct_id(int product_id) {
		this.productId = product_id;
	}

	public String getProduct_name() {
		return productName;
	}

	public void setProduct_name(String product_name) {
		this.productName = product_name;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nproduct Id: " + productId + "\nproduct name: " + productName + "\n";
	}

}
