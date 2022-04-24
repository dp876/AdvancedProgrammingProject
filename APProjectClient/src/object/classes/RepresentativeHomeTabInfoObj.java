package object.classes;

import java.io.Serializable;
import java.util.ArrayList;

import hibernate.model.ProductServicesHibernate;
import hibernate.model.ReportIssueHibernate;

public class RepresentativeHomeTabInfoObj implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ProductServicesHibernate> productServicesArrayList;
	private ArrayList<ReportIssueHibernate> resolvedComplaintsArrayList;
	private ArrayList<ReportIssueHibernate> outstandingComplaintsArrayList;
	
	public RepresentativeHomeTabInfoObj() {
		productServicesArrayList = new ArrayList<>();
		resolvedComplaintsArrayList = new ArrayList<>();
		outstandingComplaintsArrayList = new ArrayList<>();
	}

	public RepresentativeHomeTabInfoObj(ArrayList<ProductServicesHibernate> productServicesArrayList, ArrayList<ReportIssueHibernate> resolvedComplaintsArrayList, ArrayList<ReportIssueHibernate> outstandingComplaintsArrayList) {
		this.productServicesArrayList = productServicesArrayList;
		this.resolvedComplaintsArrayList = resolvedComplaintsArrayList;
		this.outstandingComplaintsArrayList = outstandingComplaintsArrayList;
	}
	
	public RepresentativeHomeTabInfoObj(RepresentativeHomeTabInfoObj obj) {
		this.productServicesArrayList = obj.productServicesArrayList;
		this.resolvedComplaintsArrayList = obj.resolvedComplaintsArrayList;
		this.outstandingComplaintsArrayList = obj.outstandingComplaintsArrayList;
	}
	
	public void addToProductServicesArrayList(ProductServicesHibernate obj) {
		productServicesArrayList.add(obj);
	}
	
	public void addToResolvedComplaintsArrayList(ReportIssueHibernate obj) {
		resolvedComplaintsArrayList.add(obj);
	}
	
	public void addToOutstandingComplaintsArrayList(ReportIssueHibernate obj) {
		outstandingComplaintsArrayList.add(obj);
	}

	public ArrayList<ProductServicesHibernate> getProductServicesArrayList() {
		return productServicesArrayList;
	}

	public void setProductServicesArrayList(ArrayList<ProductServicesHibernate> productServicesArrayList) {
		this.productServicesArrayList = productServicesArrayList;
	}

	public ArrayList<ReportIssueHibernate> getResolvedComplaintsArrayList() {
		return resolvedComplaintsArrayList;
	}

	public void setResolvedComplaintsArrayList(ArrayList<ReportIssueHibernate> resolvedComplaintsArrayList) {
		this.resolvedComplaintsArrayList = resolvedComplaintsArrayList;
	}

	public ArrayList<ReportIssueHibernate> getOutstandingComplaintsArrayList() {
		return outstandingComplaintsArrayList;
	}

	public void setOutstandingComplaintsArrayList(ArrayList<ReportIssueHibernate> outstandingComplaintsArrayList) {
		this.outstandingComplaintsArrayList = outstandingComplaintsArrayList;
	}

	@Override
	public String toString() {
		return "productServicesArrayList: " + productServicesArrayList + "\nresolvedComplaintsArrayList: "
				+ resolvedComplaintsArrayList + "\noutstandingComplaintsArrayList: " + outstandingComplaintsArrayList
				+ "\n";
	}
	
}
