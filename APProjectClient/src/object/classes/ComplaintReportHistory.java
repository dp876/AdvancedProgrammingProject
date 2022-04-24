package object.classes;

import java.io.Serializable;
import java.util.ArrayList;


public class ComplaintReportHistory implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private ArrayList<ReportedIssueObj> reportedIssueList;
	private ArrayList<ReportedIssueResponseObj> reportedIssueResponseList;
	
	public ComplaintReportHistory() {
		this.reportedIssueList  = new ArrayList<>();
		this.reportedIssueResponseList = new ArrayList<>();
	}
	
	public ComplaintReportHistory(ArrayList<ReportedIssueObj> repIssueObj, ArrayList<ReportedIssueResponseObj> repRespObj) {
		this.reportedIssueList = repIssueObj;
		this.reportedIssueResponseList = repRespObj;
	}
	
	public ComplaintReportHistory(ComplaintReportHistory obj) {
		this.reportedIssueList = obj.reportedIssueList;
		this.reportedIssueResponseList = obj.reportedIssueResponseList;
	}
	
	public void addToReportedIssueList(ReportedIssueObj obj) {
		this.reportedIssueList.add(obj);
	}
	
	public void addToReportedIssueResponseList(ReportedIssueResponseObj obj) {
		this.reportedIssueResponseList.add(obj);
	}

	public ArrayList<ReportedIssueObj> getReportedIssueList() {
		return reportedIssueList;
	}

	public void setReportedIssueList(ArrayList<ReportedIssueObj> reportedIssueList) {
		this.reportedIssueList = reportedIssueList;
	}

	public ArrayList<ReportedIssueResponseObj> getReportedIssueResponseList() {
		return reportedIssueResponseList;
	}

	public void setReportedIssueResponseList(ArrayList<ReportedIssueResponseObj> reportedIssueResponseList) {
		this.reportedIssueResponseList = reportedIssueResponseList;
	}

	@Override
	public String toString() {
		return "reportIssueList: " + reportedIssueList + "\nreportResponseList: " + reportedIssueResponseList + "\n";
	}
	
}
