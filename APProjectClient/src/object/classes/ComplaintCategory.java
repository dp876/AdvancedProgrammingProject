package object.classes;

import java.io.Serializable;

public class ComplaintCategory implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int categoryId;
	private String categoryName;
	
	public ComplaintCategory() {
		this.id = 0;
		this.categoryId = 0;
		this.categoryName = "";
	}
	
	public ComplaintCategory(int id, int categoryId, String categoryName) {
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public ComplaintCategory(ComplaintCategory obj) {
		this.id = obj.id;
		this.categoryId = obj.categoryId;
		this.categoryName = obj.categoryName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategory_id(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategory_name(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncategory id: " + categoryId + "\ncategory name: " + categoryName + "\n";
	}
	
}
