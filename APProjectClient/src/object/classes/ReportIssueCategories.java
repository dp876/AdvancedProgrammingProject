package object.classes;

import java.io.Serializable;
import java.util.ArrayList;

public class ReportIssueCategories implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ComplaintCategory> complaintCategoryList;
	private ArrayList<ProductServices> productServicesList;
	
	public ReportIssueCategories() {
		complaintCategoryList = new ArrayList<>();
		productServicesList = new ArrayList<>();
	}
	
	public ReportIssueCategories(ArrayList<ComplaintCategory> ccl, ArrayList<ProductServices> psl) {
		this.complaintCategoryList = ccl;
		this.productServicesList = psl;
	}
	
	public ReportIssueCategories(ReportIssueCategories obj) {
		this.complaintCategoryList = obj.complaintCategoryList;
		this.productServicesList = obj.productServicesList;
	}
	
	public void addComplaintCategory(ComplaintCategory obj) {
		complaintCategoryList.add(obj);
	}
	
	public void addProductService(ProductServices obj) {
		productServicesList.add(obj);
	}

	public ArrayList<ComplaintCategory> getComplaintCategoryList() {
		return complaintCategoryList;
	}

	public void setComplaintCategoryList(ArrayList<ComplaintCategory> complaintCategoryList) {
		this.complaintCategoryList = complaintCategoryList;
	}

	public ArrayList<ProductServices> getProductServicesList() {
		return productServicesList;
	}

	public void setProductServicesList(ArrayList<ProductServices> productServicesList) {
		this.productServicesList = productServicesList;
	}

	@Override
	public String toString() {
		return "complaintCategoryList: " + complaintCategoryList + "\nproductServicesList: " + productServicesList
				+ "\n";
	}
	
}
