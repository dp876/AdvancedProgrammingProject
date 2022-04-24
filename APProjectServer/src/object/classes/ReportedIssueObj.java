package object.classes;

import java.io.Serializable;

public class ReportedIssueObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String productService;
	private String categoryName;
	private String complaint;
	
	public ReportedIssueObj() {
		this.id = 0;
		this.productService = "";
		this.categoryName = "";
		this.complaint = "";
	}
	
	public ReportedIssueObj(int id, String productService, String categoryName, String complaint) {
		this.id = id;
		this.productService = productService;
		this.categoryName = categoryName;
		this.complaint = complaint;
	}
	
	public ReportedIssueObj(ReportedIssueObj obj) {
		this.id = obj.id;
		this.productService = obj.productService;
		this.categoryName = obj.categoryName;
		this.complaint = obj.complaint;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getProductService() {
		return productService;
	}

	public void setProductService(String productService) {
		this.productService = productService;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nproductService: " + productService + "\ncategoryName: " + categoryName + "\ncomplaint: "
				+ complaint + "\n";
	}
	
}
