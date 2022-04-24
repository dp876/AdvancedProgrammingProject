package object.classes;

import java.io.Serializable;
import java.util.ArrayList;

import hibernate.model.ComplaintCategoriesHibernate;
import hibernate.model.ProductServicesHibernate;

public class ListOfComplaintsForRepresentative implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	ArrayList<ProductServicesHibernate> productServicesArrayList;
	ArrayList<ComplaintCategoriesHibernate> complaintCategoriesArrayList;
	ArrayList<TechniciansObj> technicianArrayList;
	ArrayList<ComplaintsForRepresentativeObj> compliantsForRepresentativeArrayList;
	ArrayList<RepresentativeResponsesObj> representativeResponsesObjArrayList;
	ArrayList<AssignedComplaintsIdObj> assignedComplaintsIdObjArrayList;
	
	public ListOfComplaintsForRepresentative() {
		this.productServicesArrayList = new ArrayList<>();
		this.complaintCategoriesArrayList = new ArrayList<>();
		this.technicianArrayList = new ArrayList<>();
		this.compliantsForRepresentativeArrayList = new ArrayList<>();
		this.representativeResponsesObjArrayList = new ArrayList<>();
		this.assignedComplaintsIdObjArrayList = new ArrayList<>();
	}

	public ListOfComplaintsForRepresentative(ArrayList<ProductServicesHibernate> productServicesArrayList,
			ArrayList<ComplaintCategoriesHibernate> complaintCategoriesArrayList,
			ArrayList<TechniciansObj> technicianArrayList,
			ArrayList<ComplaintsForRepresentativeObj> compliantsForRepresentativeArrayList,
			ArrayList<RepresentativeResponsesObj> representativeResponsesObjArrayList,
			ArrayList<AssignedComplaintsIdObj> assignedComplaintsIdObjArrayList) {
		this.productServicesArrayList = productServicesArrayList;
		this.complaintCategoriesArrayList = complaintCategoriesArrayList;
		this.technicianArrayList = technicianArrayList;
		this.compliantsForRepresentativeArrayList = compliantsForRepresentativeArrayList;
		this.representativeResponsesObjArrayList = representativeResponsesObjArrayList;
		this.assignedComplaintsIdObjArrayList = assignedComplaintsIdObjArrayList;
	}
	
	public ListOfComplaintsForRepresentative(ListOfComplaintsForRepresentative obj) {
		this.productServicesArrayList = obj.productServicesArrayList;
		this.complaintCategoriesArrayList = obj.complaintCategoriesArrayList;
		this.technicianArrayList = obj.technicianArrayList;
		this.compliantsForRepresentativeArrayList = obj.compliantsForRepresentativeArrayList;
		this.representativeResponsesObjArrayList = obj.representativeResponsesObjArrayList;
		this.assignedComplaintsIdObjArrayList = obj.assignedComplaintsIdObjArrayList;
	}
	public void addToListOfAssignedComplaints(AssignedComplaintsIdObj obj) {
		assignedComplaintsIdObjArrayList.add(obj);
	}
	
	public void addToListOfProductServices(ProductServicesHibernate obj) {
		productServicesArrayList.add(obj);
	}
	
	public void addToListOfComplaintCategories(ComplaintCategoriesHibernate obj) {
		complaintCategoriesArrayList.add(obj);
	}

	public void addToListOfTechnicians(TechniciansObj obj) {
		technicianArrayList.add(obj);
	}
	
	public void addToComplaintsForRepresentative(ComplaintsForRepresentativeObj obj) {
		compliantsForRepresentativeArrayList.add(obj);
	}
	
	public void addToRepresentativeResponses(RepresentativeResponsesObj obj) {
		representativeResponsesObjArrayList.add(obj);
	}
	
	public ArrayList<ProductServicesHibernate> getProductServicesArrayList() {
		return productServicesArrayList;
	}

	public void setProductServicesArrayList(ArrayList<ProductServicesHibernate> productServicesArrayList) {
		this.productServicesArrayList = productServicesArrayList;
	}

	public ArrayList<ComplaintCategoriesHibernate> getComplaintCategoriesArrayList() {
		return complaintCategoriesArrayList;
	}

	public void setComplaintCategoriesArrayList(ArrayList<ComplaintCategoriesHibernate> complaintCategoriesArrayList) {
		this.complaintCategoriesArrayList = complaintCategoriesArrayList;
	}

	public ArrayList<TechniciansObj> getTechnicianArrayList() {
		return technicianArrayList;
	}

	public void setTechnicianArrayList(ArrayList<TechniciansObj> technicianArrayList) {
		this.technicianArrayList = technicianArrayList;
	}

	public ArrayList<ComplaintsForRepresentativeObj> getCompliantsForRepresentativeArrayList() {
		return compliantsForRepresentativeArrayList;
	}

	public void setCompliantsForRepresentativeArrayList(
			ArrayList<ComplaintsForRepresentativeObj> compliantsForRepresentativeArrayList) {
		this.compliantsForRepresentativeArrayList = compliantsForRepresentativeArrayList;
	}
	
	public ArrayList<RepresentativeResponsesObj> getRepresentativeResponsesObjArrayList() {
		return representativeResponsesObjArrayList;
	}

	public void setRepresentativeResponsesObjArrayList(
			ArrayList<RepresentativeResponsesObj> representativeResponsesObjArrayList) {
		this.representativeResponsesObjArrayList = representativeResponsesObjArrayList;
	}

	public ArrayList<AssignedComplaintsIdObj> getAssignedComplaintsIdObjArrayList() {
		return assignedComplaintsIdObjArrayList;
	}

	public void setAssignedComplaintsIdObjArrayList(ArrayList<AssignedComplaintsIdObj> assignedComplaintsIdObjArrayList) {
		this.assignedComplaintsIdObjArrayList = assignedComplaintsIdObjArrayList;
	}

	@Override
	public String toString() {
		return "productServicesArrayList: " + productServicesArrayList + "\ncomplaintCategoriesArrayList: "
				+ complaintCategoriesArrayList + "\ntechnicianArrayList: " + technicianArrayList
				+ "\ncompliantsForRepresentativeArrayList: " + compliantsForRepresentativeArrayList
				+ "\nrepresentativeResponsesObjArrayList: " + representativeResponsesObjArrayList
				+ "\nassignedComplaintsIdObjArrayList: " + assignedComplaintsIdObjArrayList + "\n";
	}
	
}
