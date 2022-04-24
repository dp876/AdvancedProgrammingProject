package login.model;

import java.io.*;
import java.net.*;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import dashboard.controller.*;
import login.controller.TechnicianLoginController;
import object.classes.Credential;
/*
 * This class creates a connection, verifies the information for login
 * and switch to the different dash boards for the users
 */

public class LoginModel {
	private final String hostname = "localhost";
	private final int PORT = 1234;
	private Socket connectionSocket;
	private ObjectOutputStream objOs;
	private ObjectInputStream objIs;
	
	private final static Logger logger = Logger.getLogger(LoginModel.class.getName());
	
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
	
	public LoginModel() {
		initialize();
	}
	
	private void initialize() {
		connectionSocket = null;
		objOs = null;
		objIs = null;
	}
	
	public boolean verifyCredential(Credential credObj) {
		this.createConnection();
		this.configureStreams();
		this.sendObject("LOGIN");
		this.sendObject(credObj);

		return this.receiveResponse();
	}
	
	private void createConnection() {
		setupLogger();
		try {
			connectionSocket = new Socket(hostname, PORT);
			logger.info("Connection for login model created");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.warning("Unknown Host exception occured");
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
	
	private void configureStreams() {
		setupLogger();
		try {
			objOs = new ObjectOutputStream (connectionSocket.getOutputStream());
			objIs = new ObjectInputStream (connectionSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
	
	private void sendObject(Object obj) {
		setupLogger();
		try {
			objOs.writeObject(obj);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
	
	private boolean receiveResponse() {
		setupLogger();
		boolean flag = false;
		try {
			flag = (Boolean) objIs.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.warning("Class not found exception");
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
		this.closeConnection();
		
		return flag;
	}
	
	private void closeConnection() {
		setupLogger();
		try {
			if (objIs != null)
				objIs.close();
			if (objOs != null)
				objOs.close();
			if (connectionSocket != null)
				connectionSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
	}

	public void switchToDashboard(Credential credObj) {
		if (credObj.getUserType().equalsIgnoreCase("CLIENT")) {
			new ClientDashboardController(credObj.getUserId().toString());
		} 
		else if (credObj.getUserType().equalsIgnoreCase("REPRESENTATIVE")) {
			new RepresentativeDashboardController(credObj.getUserId().toString());
		} 
		else if (credObj.getUserType().equalsIgnoreCase("TECHNICIAN")) {
			new TechnicianDashboardController(credObj.getUserId().toString());
		}
	}

}
