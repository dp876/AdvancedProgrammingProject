package dashboard.controller;

import java.util.ArrayList;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dashboard.model.RepresentativeDashboardModel;
import dashboard.view.RepresentativeDashboardView;
import hibernate.model.AssignedTechnicianHibernate;
import hibernate.model.ComplaintCategoriesHibernate;
import hibernate.model.CustomerHibernate;
import hibernate.model.CustomerLoginHibernate;
import hibernate.model.EmployeeHibernate;
import hibernate.model.EmployeeLoginHibernate;
import hibernate.model.PaymentHistoryHibernate;
import hibernate.model.PaymentListHibernate;
import hibernate.model.ProductServicesHibernate;
import hibernate.model.ReportIssueHibernate;
import hibernate.model.ReportResponseHibernate;
import object.classes.ListOfComplaintsForRepresentative;
import object.classes.RepresentativeHomeTabInfoObj;

public class RepresentativeDashboardController {
	private RepresentativeDashboardView representativeDashboardView;
	private RepresentativeDashboardModel representativeDashboardModel;
	private final static Logger logger = Logger.getLogger(RepresentativeDashboardController.class.getName());
	
	private static void setupLogger() {
        LogManager.getLogManager().reset();
        
        ConsoleHandler ch = new ConsoleHandler();
        logger.addHandler(ch);

        try {
            FileHandler fh = new FileHandler("Log.log", true);
            logger.addHandler(fh);
        } catch (java.io.IOException e) {            
            logger.log(Level.SEVERE, "File logger not working.", e);
        }
         
    }
	
	public RepresentativeDashboardController(String userid) {
		representativeDashboardModel = new RepresentativeDashboardModel(this, userid);
		representativeDashboardView = new RepresentativeDashboardView(this);
	}

	public void returnToLoginScreen() {
		representativeDashboardModel.terminateThreadOnServer();	
		representativeDashboardView.dispose();
		representativeDashboardModel.returnToLoginScreen();
	}
	
	public void sendObjectToModel(Object obj) {
		representativeDashboardModel.sendObject(obj);
	}
	
	public void sendQueryToModel(Object obj) {
		representativeDashboardModel.sendObject(obj);
		representativeDashboardModel.receiveServerResponse();
	}
	
	public void sendHomeTabInfoToView(RepresentativeHomeTabInfoObj representativeHomeTabInfoObj) {
		representativeDashboardView.populateHomeTab(representativeHomeTabInfoObj);
	}
	
	public void sendComplaintTabInfoToView(ListOfComplaintsForRepresentative listOfComplaintsForRepresentative) {
		representativeDashboardView.populateComplaintTab(listOfComplaintsForRepresentative);
	}
	
	public String getUsernameFromModel() {
		return representativeDashboardModel.getUsername();
	}
	
	public void sendListOfAssignedTechnicianToView(ArrayList<AssignedTechnicianHibernate> assignedTechnicianList) {
		representativeDashboardView.sendListOfAssignedTechnicianToInternalFrame(assignedTechnicianList);
	}
	
	public void sendListOfComplaintCategoriesToView(ArrayList<ComplaintCategoriesHibernate> complaintCategoriesList) {
		representativeDashboardView.sendListOfComplaintCategoriesToInternalFrame(complaintCategoriesList);
	}
	
	public void sendListOfCustomerLoginToView(ArrayList<CustomerLoginHibernate> customerLoginList) {
		representativeDashboardView.sendListOfCustomerLoginToInternalFrame(customerLoginList);
	}
	
	public void sendListOfCustomerToView(ArrayList<CustomerHibernate> customerList) {
		representativeDashboardView.sendListOfCustomerToInternalFrame(customerList);
	}
	
	public void sendListOfEmployeeLoginToView(ArrayList<EmployeeLoginHibernate> employeeLoginList) {
		representativeDashboardView.sendListOfEmployeeLoginToInternalFrame(employeeLoginList);
	}
	
	public void sendListOfEmployeeToView(ArrayList<EmployeeHibernate> employeeList) {
		representativeDashboardView.sendListOfEmployeeToInternalFrame(employeeList);
	}
	
	public void sendListOfPaymentHistoryToView(ArrayList<PaymentHistoryHibernate> paymentHistoryList) {
		representativeDashboardView.sendListOfPaymentHistoryToInternalFrame(paymentHistoryList);
	}
	
	public void sendListOfPaymentListToView(ArrayList<PaymentListHibernate> paymentListList) {
		representativeDashboardView.sendListOfPaymentListToInternalFrame(paymentListList);
	}
	
	public void sendListOfProductServicesToView(ArrayList<ProductServicesHibernate> productServicesList) {
		representativeDashboardView.sendListOfProductServicesToInternalFrame(productServicesList);
	}
	
	public void sendListOfReportedIssuesToView(ArrayList<ReportIssueHibernate> reportedIssueList) {
		representativeDashboardView.sendListOfReportedIssuesToInternalFrame(reportedIssueList);
	}
	
	public void sendListOfResponsesToView(ArrayList<ReportResponseHibernate> responseList) {
		representativeDashboardView.sendListOfResponsesToInternalFrame(responseList);
	}
	
	public void sendReceivedMessageToView(String message) {
		representativeDashboardView.displayMessageOnView(message);
	}
	
	public static void main(String[] args) {
		setupLogger();
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            new RepresentativeDashboardController("0000");
            logger.info("Rep dashboard controller created");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.warning("Class not found exception occured");
		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.warning("Instantiation exception occured");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.warning("Illegal Access exception occured");
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			logger.warning("Unsupported Look and feel exception occured");
		} catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("An exception occured");
        }
	}

}
