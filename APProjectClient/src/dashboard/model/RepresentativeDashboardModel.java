package dashboard.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import dashboard.controller.RepresentativeDashboardController;
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
import login.controller.RepresentativeLoginController;
import object.classes.ListOfComplaintsForRepresentative;
import object.classes.RepresentativeHomeTabInfoObj;

public class RepresentativeDashboardModel {
	private final String hostname = "localhost";
	private final int PORT = 1234;
	private Socket connectionSocket;
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;
	private String userid;
	private String username;
	private RepresentativeDashboardController representativeDashboardController;
	private RepresentativeHomeTabInfoObj representativeHomeTabInfoObj;
	private ListOfComplaintsForRepresentative listOfComplaintsForRepresentative;
	private ArrayList<AssignedTechnicianHibernate> assignedTechnicianList;
	private ArrayList<ComplaintCategoriesHibernate> complaintCategoriesList;
	private ArrayList<CustomerLoginHibernate> customerLoginList;
	private ArrayList<CustomerHibernate> customerList;
	private ArrayList<EmployeeLoginHibernate> employeeLoginList;
	private ArrayList<EmployeeHibernate> employeeList;
	private ArrayList<PaymentHistoryHibernate> paymentHistoryList;
	private ArrayList<PaymentListHibernate> paymentListList;
	private ArrayList<ProductServicesHibernate> productServicesList;
	private ArrayList<ReportIssueHibernate> reportedIssueList;
	private ArrayList<ReportResponseHibernate> responseList;
	private BufferedReader bufferedReader;
	private Socket chatSocket;
	private final int CHATPORT = 4321;
	private volatile boolean listenForChat;
	
	private final static Logger logger = Logger.getLogger(RepresentativeDashboardModel.class.getName());
	
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
	
	public RepresentativeDashboardModel(RepresentativeDashboardController obj, String userid) {
		this.representativeDashboardController = obj;
		this.userid = userid;
		initialize();
	}
	
	private void initialize() {
		connectionSocket = null;
		objOutputStream = null;
		objInputStream = null;
		
		bufferedReader = null;
		chatSocket = null;
		
		listenForChat = false;
		
		username = "";
		
		representativeHomeTabInfoObj = null;
		listOfComplaintsForRepresentative = null;
		assignedTechnicianList = null;
		complaintCategoriesList = null;
		customerLoginList = null;
		customerList = null;
		employeeLoginList = null;
		employeeList = null;
		paymentHistoryList = null;
		paymentListList = null;
		productServicesList = null;
		reportedIssueList = null;
		responseList = null;
		
		new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	createConnection();
		 		configureStreams();
		 		sendObject("DASHBOARD_CONNECT"); // indicate to the server that the dashboard is connected
		 		sendObject(userid); // send the userid to the server
		 		sendObject("REPRESENTATIVE"); // send the type of user to the server
		 		connectChatSocket();
		 		configureReader();
		 		username = (String) receiveObject();
		     }
		}).start();
		
		TimerTask task = new TimerTask() {
	        public void run() {
	        	listenForMessage();
	        }
	    };
	    Timer timer = new Timer("Timer");
	    
	    long delay = 3000L;
	    timer.schedule(task, delay);
	}
	
	private void connectChatSocket() {
		try {
			chatSocket = new Socket(hostname, CHATPORT);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void listenForMessage() {
		listenForChat = true;
		new Thread(() -> {
			while (listenForChat) {
				try {
					String message = bufferedReader.readLine();
					representativeDashboardController.sendReceivedMessageToView(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void configureReader() {
		try {
			InputStream input = chatSocket.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(input));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public String getUsername() {
		return username;
	}
	
	public void receiveServerResponse() {
		setupLogger();
		new Thread(new Runnable() {
			@SuppressWarnings("unchecked")
			@Override
			public void run() {
				String action = "";
				while (true) {
					try {
						action = (String) objInputStream.readObject();

						// server replies to indicate what it is sending
						if (action.equalsIgnoreCase("REPRESENTATIVE_HOME")) {
							
							representativeHomeTabInfoObj = (RepresentativeHomeTabInfoObj) receiveObject();
							representativeDashboardController.sendHomeTabInfoToView(representativeHomeTabInfoObj);
							break;
						} 
						else if (action.equalsIgnoreCase("REPRESENTATIVE_COMPLAINTS")) {
							
							listOfComplaintsForRepresentative = (ListOfComplaintsForRepresentative) receiveObject();
							representativeDashboardController.sendComplaintTabInfoToView(listOfComplaintsForRepresentative);
							break;
						} 
						else if (action.equalsIgnoreCase("READ_ALL_ASSIGNED_TECHNICIAN")) {
							
							assignedTechnicianList = (ArrayList<AssignedTechnicianHibernate>) receiveObject();
							representativeDashboardController.sendListOfAssignedTechnicianToView(assignedTechnicianList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_COMPLAINT_CATEGORIES")) {
							
							complaintCategoriesList = (ArrayList<ComplaintCategoriesHibernate>) receiveObject();
							representativeDashboardController.sendListOfComplaintCategoriesToView(complaintCategoriesList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_CUSTOMER_LOGIN")) {
							
							customerLoginList = (ArrayList<CustomerLoginHibernate>) receiveObject();
							representativeDashboardController.sendListOfCustomerLoginToView(customerLoginList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_CUSTOMER")) {
							
							customerList = (ArrayList<CustomerHibernate>) receiveObject();
							representativeDashboardController.sendListOfCustomerToView(customerList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_EMPLOYEE_LOGIN")) {
							
							employeeLoginList = (ArrayList<EmployeeLoginHibernate>) receiveObject();
							representativeDashboardController.sendListOfEmployeeLoginToView(employeeLoginList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_EMPLOYEE")) {
							
							employeeList = (ArrayList<EmployeeHibernate>) receiveObject();
							representativeDashboardController.sendListOfEmployeeToView(employeeList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_PAYMENT_HISTORY")) {
							
							paymentHistoryList = (ArrayList<PaymentHistoryHibernate>) receiveObject();
							representativeDashboardController.sendListOfPaymentHistoryToView(paymentHistoryList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_PAYMENT_LIST")) {
							
							paymentListList = (ArrayList<PaymentListHibernate>) receiveObject();
							representativeDashboardController.sendListOfPaymentListToView(paymentListList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_PRODUCT_SERVICES")) {
							
							productServicesList = (ArrayList<ProductServicesHibernate>) receiveObject();
							representativeDashboardController.sendListOfProductServicesToView(productServicesList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_REPORTED_ISSUES")) {
							
							reportedIssueList = (ArrayList<ReportIssueHibernate>) receiveObject();
							representativeDashboardController.sendListOfReportedIssuesToView(reportedIssueList);
							break;
						}
						else if (action.equalsIgnoreCase("READ_ALL_RESPONSE")) {
							
							responseList = (ArrayList<ReportResponseHibernate>) receiveObject();
							representativeDashboardController.sendListOfResponsesToView(responseList);
							break;
						}
						else {
							break;
						}

					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						logger.warning("Class not found exception occured");
					} catch (IOException e) {
						e.printStackTrace();
						logger.warning("IO exception occured");
					}
				}
			}
		}).start();		
	}
	
	
	private void createConnection() {
		setupLogger();
		try {
			connectionSocket = new Socket(hostname, PORT);
			logger.info("connection created on client side for representative");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.warning("Unkown host exception occured");
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
	
	private void configureStreams() {
		setupLogger();
		try {
			objOutputStream = new ObjectOutputStream (connectionSocket.getOutputStream());
			objInputStream = new ObjectInputStream (connectionSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
		
	public void sendObject(Object obj) {
		setupLogger();
		try {
			objOutputStream.writeObject(obj);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
	
	private Object receiveObject() {
		setupLogger();
		try {
			return objInputStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.warning("Class not found exception occured");
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
		return null;
	}
	
	private void closeConnection() {
		setupLogger();
		listenForChat = false;
		try {
			if (objInputStream != null)
				objInputStream.close();
			if (objOutputStream != null)
				objOutputStream.close();
			if (connectionSocket != null)
				connectionSocket.close();
			if (bufferedReader != null)
				bufferedReader.close();
			if (chatSocket != null)
				chatSocket.close();
			
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
	
	// send action to server to terminate client thread
	public void terminateThreadOnServer() {
		sendObject("LOGOUT"); 		
	}
	
	public void returnToLoginScreen() {
		setupLogger();
		closeConnection();
		logger.info("connection closed on client side for representative");
		new RepresentativeLoginController();
	}

}
