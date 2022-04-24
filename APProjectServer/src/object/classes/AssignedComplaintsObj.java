package object.classes;

import java.io.Serializable;

public class AssignedComplaintsObj implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int id;
	private String fullName;
	private String email;
	private String address;
	private String tele;
	private String productService;
	private String complaintCategory;
	private String complaint;
	private int complaintPrimaryKey;
	private int customerPrimaryKey;
	
	public AssignedComplaintsObj() {
		this.id = 0;
		this.fullName = "";
		this.email = "";
		this.address = "";
		this.tele = "";
		this.productService = "";
		this.complaintCategory = "";
		this.complaint = "";
		this.complaintPrimaryKey = 0;
		this.customerPrimaryKey = 0;
	}
	
	public AssignedComplaintsObj(int id, String fullName, String email, String address, String tele, String productService, String complaintCategory, String complaint, int complaintPrimaryKey, int customerPrimaryKey) {
		this.id = id;
		this.fullName = fullName;
		this.email = email;
		this.address = address;
		this.tele = tele;
		this.productService = productService;
		this.complaintCategory = complaintCategory;
		this.complaint = complaint;
		this.complaintPrimaryKey = complaintPrimaryKey;
		this.customerPrimaryKey = customerPrimaryKey;
	}
	
	public AssignedComplaintsObj(AssignedComplaintsObj obj) {
		this.id = obj.id;
		this.fullName = obj.fullName;
		this.email = obj.email;
		this.address = obj.address;
		this.tele = obj.tele;
		this.productService = obj.productService;
		this.complaintCategory = obj.complaintCategory;
		this.complaint = obj.complaint;
		this.complaintPrimaryKey = obj.complaintPrimaryKey;
		this.customerPrimaryKey = obj.customerPrimaryKey;
	}

	public int getComplaintPrimaryKey() {
		return complaintPrimaryKey;
	}

	public void setComplaintPrimaryKey(int complaintPrimaryKey) {
		this.complaintPrimaryKey = complaintPrimaryKey;
	}

	public int getCustomerPrimaryKey() {
		return customerPrimaryKey;
	}

	public void setCustomerPrimaryKey(int customerPrimaryKey) {
		this.customerPrimaryKey = customerPrimaryKey;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getTele() {
		return tele;
	}

	public void setTele(String tele) {
		this.tele = tele;
	}

	public String getProductService() {
		return productService;
	}

	public void setProductService(String productService) {
		this.productService = productService;
	}

	public String getComplaintCategory() {
		return complaintCategory;
	}

	public void setComplaintCategory(String complaintCategory) {
		this.complaintCategory = complaintCategory;
	}

	public String getComplaint() {
		return complaint;
	}

	public void setComplaint(String complaint) {
		this.complaint = complaint;
	}

	@Override
	public String toString() {
		return "id: " + id + "\nfullName: " + fullName + "\nemail: " + email + "\naddress: " + address + "\ntele: "
				+ tele + "\nproductService: " + productService + "\ncomplaintCategory: " + complaintCategory
				+ "\ncomplaint: " + complaint + "\ncomplaintPrimaryKey: " + complaintPrimaryKey
				+ "\ncustomerPrimaryKey: " + customerPrimaryKey + "\n";
	}

}
