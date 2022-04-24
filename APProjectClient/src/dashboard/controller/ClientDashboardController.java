package dashboard.controller;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dashboard.model.ClientDashboardModel;
import dashboard.view.ClientDashboardView;
import hibernate.model.ReportIssueHibernate;
import object.classes.AccountStatus;
import object.classes.ComplaintReportHistory;
import object.classes.ListOfOnlineEmployees;
import object.classes.ReportIssueCategories;

public class ClientDashboardController {
	private ClientDashboardView clientDashboardView;
	private ClientDashboardModel clientDashboardModel;
	public String username = "";
	
	private final static Logger logger = Logger.getLogger(ClientDashboardController.class.getName());
	
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
	
	public void setUserName(String username) {
		this.username = username;
	}
	
	public ClientDashboardController(String userid) {
		clientDashboardModel = new ClientDashboardModel(this, userid);
		clientDashboardView = new ClientDashboardView(this);
	}
	
	public void returnToLoginScreen() {
		clientDashboardModel.terminateThreadOnServer();
		clientDashboardView.dispose();
		clientDashboardModel.returnToLoginScreen();
	}
	
	public void sendObjectToModel(Object obj) {
		clientDashboardModel.sendObject(obj);
	}
	
	public void sendMessageToModel(String msg) {
		clientDashboardModel.sendMessage(msg);
	}
	
	public void sendQueryToModel(Object obj) {
		clientDashboardModel.sendObject(obj);
		clientDashboardModel.receiveServerResponse();
	}
		
	public void sendAccountStatusToView(AccountStatus accountStatus) {
		clientDashboardView.populateAccountStatus(accountStatus);
	}
	
	public void sendReportIssueCategoriesToView(ReportIssueCategories obj) {
		clientDashboardView.populateReportIssueCategories(obj);
	}
	
	public void sendComplaintToModel(ReportIssueHibernate reportIssue) {
		clientDashboardModel.sendComplaintIssue(reportIssue);
	}
	
	public void sendComplaintReportHistoryToView(ComplaintReportHistory complaintReportHistory) {
		clientDashboardView.populateComplaintReportHistory(complaintReportHistory);
	}
	
	public String getUsernameFromModel() {
		return clientDashboardModel.getUsername();
	}
	
	public void sendOnlineEmployeesToView(ListOfOnlineEmployees listOfOnlineEmployees) {
		clientDashboardView.populateOnlineEmployees(listOfOnlineEmployees);
	}
	
	public void sendReceivedMessageToView(String message) {
		clientDashboardView.displayMessageOnView(message);
	}
	
	public static void main(String[] args) {
		setupLogger();
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
            new ClientDashboardController("00000");
            logger.info("Client Dashboard controller created");
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
