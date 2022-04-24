package hibernate.model;

import java.io.Serializable;

public class ComplaintCategoriesHibernate implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private int categoryId;
	private String categoryName;
	
	public ComplaintCategoriesHibernate() {
		this.id = 0;
		this.categoryId = 0;
		this.categoryName = "";
	}

	public ComplaintCategoriesHibernate(int id, int categoryId, String categoryName) {
		this.id = id;
		this.categoryId = categoryId;
		this.categoryName = categoryName;
	}
	
	public ComplaintCategoriesHibernate(ComplaintCategoriesHibernate obj) {
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

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	@Override
	public String toString() {
		return "id: " + id + "\ncategoryId: " + categoryId + "\ncategoryName: " + categoryName + "\n";
	}
	
}
