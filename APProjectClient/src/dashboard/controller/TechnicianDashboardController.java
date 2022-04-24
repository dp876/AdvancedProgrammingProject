package dashboard.controller;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import dashboard.model.TechnicianDashboardModel;
import dashboard.view.TechnicianDashboardView;
import object.classes.ListOfAssignedComplaintsObj;

public class TechnicianDashboardController {
	private TechnicianDashboardView technicianDashboardView;
	private TechnicianDashboardModel technicianDashboardModel;
	
	private final static Logger logger = Logger.getLogger(TechnicianDashboardController.class.getName());
	
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
	
	public TechnicianDashboardController(String userid) {
		technicianDashboardModel = new TechnicianDashboardModel(this, userid);
		technicianDashboardView = new TechnicianDashboardView(this);
	}
	
	public void returnToLoginScreen() {
		technicianDashboardModel.terminateThreadOnServer();
		technicianDashboardView.dispose();
		technicianDashboardModel.returnToLoginScreen();
	}
	
	public String getUsernameFromModel() {
		return technicianDashboardModel.getUsername();
	}
		
	public void sendQueryToModel(Object obj) {
		technicianDashboardModel.sendObject(obj);
		technicianDashboardModel.receiveServerResponse();
	}
	
	public void sendAssignedComplaintsToView(ListOfAssignedComplaintsObj listOfAssignedComplaints) {
		technicianDashboardView.populateAssignedComplaints(listOfAssignedComplaints);
	}
	
	public void sendObjectToModel(Object obj) {
		technicianDashboardModel.sendObject(obj);
	}
	
	public void sendReceivedMessageToView(String message) {
		technicianDashboardView.displayMessageOnView(message);
	}
	
	public static void main(String[] args) {
		setupLogger();
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");
			new TechnicianDashboardController("0000");
			logger.info("Technician dashboard controller created");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.warning("Class not found exception occured");
		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.warning("Instantiation exception occured");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.warning("Illegal Accesss exception occured");
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			logger.warning("Unsupported look and feel exception occured");
		} catch (Exception e) {
            e.printStackTrace();
            logger.warning("An exception occured");
        }
	}

}
