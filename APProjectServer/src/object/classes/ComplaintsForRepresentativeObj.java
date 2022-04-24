package object.classes;

import java.io.Serializable;

public class ComplaintsForRepresentativeObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private int complaintPrimaryKey;
	private String fullName;
	private String emailAddress;
	private String homeAddress;
	private String teleNum;
	private String productName;
	private String categoryName;
	private String complaint;
	private int customerPrimaryKey;
	private int productServiceId;
	private int complaintCategoryId;
	private String amountDue;
	private String dueDate;
	private String paymentStatus;
	
	public ComplaintsForRepresentativeObj() {
		this.complaintPrimaryKey = 0;
		this.fullName = "";
		this.emailAddress = "";
		this.homeAddress = "";
		this.teleNum = "";
		this.productName = "";
		this.categoryName = "";
		this.complaint = "";
		this.customerPrimaryKey = 0;
		this.productServiceId = 0;
		this.complaintCategoryId = 0;
		this.amountDue = "";
		this.dueDate = "";
		this.paymentStatus = "";
	}

	public ComplaintsForRepresentativeObj(int complaintPrimaryKey, String fullName, String emailAddress,
			String homeAddress, String teleNum, String productName, String categoryName, String complaint,
			int customerPrimaryKey, int productServiceId, int complaintCategoryId,
			String amountDue, String dueDate, String paymentStatus) {
		
		this.complaintPrimaryKey = complaintPrimaryKey;
		this.fullName = fullName;
		this.emailAddress = emailAddress;
		this.homeAddress = homeAddress;
		this.teleNum = teleNum;
		this.productName = productName;
		this.categoryName = categoryName;
		this.complaint = complaint;
		this.customerPrimaryKey = customerPrimaryKey;
		this.productServiceId = productServiceId;
		this.complaintCategoryId = complaintCategoryId;
		this.amountDue = amountDue;
		this.dueDate = dueDate;
		this.paymentStatus = paymentStatus;
	}
	
	public ComplaintsForRepresentativeObj(ComplaintsForRepresentativeObj obj) {
		this.complaintPrimaryKey = obj.complaintPrimaryKey;
		this.fullName = obj.fullName;
		this.emailAddress = obj.emailAddress;
		this.homeAddress = obj.homeAddress;
		this.teleNum = obj.teleNum;
		this.productName = obj.productName;
		this.categoryName = obj.categoryName;
		this.complaint = obj.complaint;
		this.customerPrimaryKey = obj.customerPrimaryKey;
		this.productServiceId = obj.productServiceId;
		this.complaintCategoryId = obj.complaintCategoryId;
		this.amountDue = obj.amountDue;
		this.dueDate = obj.dueDate;
		this.paymentStatus = obj.paymentStatus;
	}

	public int getComplaintPrimaryKey() {
		return complaintPrimaryKey;
	}

	public void setComplaintPrimaryKey(int complaintPrimaryKey) {
		this.complaintPrimaryKey = complaintPrimaryKey;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress;
	}

	public String getTeleNum() {
		return teleNum;
	}

	public void setTeleNum(String teleNum) {
		this.teleNum = teleNum;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
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

	public int getCustomerPrimaryKey() {
		return customerPrimaryKey;
	}

	public void setCustomerPrimaryKey(int customerPrimaryKey) {
		this.customerPrimaryKey = customerPrimaryKey;
	}

	public int getProductServiceId() {
		return productServiceId;
	}

	public void setProductServiceId(int productServiceId) {
		this.productServiceId = productServiceId;
	}

	public int getComplaintCategoryId() {
		return complaintCategoryId;
	}

	public void setComplaintCategoryId(int complaintCategoryId) {
		this.complaintCategoryId = complaintCategoryId;
	}
	
	public String getAmountDue() {
		return amountDue;
	}

	public void setAmountDue(String amountDue) {
		this.amountDue = amountDue;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	@Override
	public String toString() {
		return "complaintPrimaryKey: " + complaintPrimaryKey + "\nfullName: " + fullName + "\nemailAddress: "
				+ emailAddress + "\nhomeAddress: " + homeAddress + "\nteleNum: " + teleNum + "\nproductName: "
				+ productName + "\ncategoryName: " + categoryName + "\ncomplaint: " + complaint
				+ "\ncustomerPrimaryKey: " + customerPrimaryKey + "\nproductServiceId: " + productServiceId
				+ "\ncomplaintCategoryId: " + complaintCategoryId + "\n";
	}
	
}
