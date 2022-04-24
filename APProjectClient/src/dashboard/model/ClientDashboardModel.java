package dashboard.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import dashboard.controller.ClientDashboardController;
import hibernate.model.ReportIssueHibernate;
import login.controller.ClientLoginController;
import object.classes.AccountStatus;
import object.classes.ComplaintReportHistory;
import object.classes.ListOfOnlineEmployees;
import object.classes.ReportIssueCategories;

public class ClientDashboardModel {
	private final String hostname = "localhost";
	private final int PORT = 1234;
	private Socket connectionSocket;
	private ObjectOutputStream objOuputStream;
	private ObjectInputStream objInputStream;
	private String userid;
	private ClientDashboardController clientDashboardController;
	private ReportIssueCategories reportIssueCategories;
	private ComplaintReportHistory complaintReportHistory;
	private ListOfOnlineEmployees listOfOnlineEmployees;
	private AccountStatus accountStatus;
	private String username;
	private BufferedReader bufferedReader;
	private Socket chatSocket;
	private final int CHATPORT = 4321;
	private volatile boolean listenForChat;
	
	private final static Logger logger = Logger.getLogger(ClientDashboardModel.class.getName());
	
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
	
	public ClientDashboardModel(String userid) {
		this.userid = userid;
		initialize();
	}
	
	public ClientDashboardModel(ClientDashboardController obj, String userid) {
		clientDashboardController = obj;
		this.userid = userid;
		initialize();
	}

	private void initialize() {
		connectionSocket = null;
		objOuputStream = null;
		objInputStream = null;
		
		bufferedReader = null;
		chatSocket = null;
		
		complaintReportHistory = null;
		accountStatus = null;
		
		listenForChat = false;
		
		username = "";
		
		new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	createConnection();
		 		configureStreams();
		 		sendObject("DASHBOARD_CONNECT"); // indicate to the server that the dashboard is connected
		 		sendObject(userid); // send the userid to the server
		 		sendObject("CLIENT"); // send the type fo user to the server
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
					clientDashboardController.sendReceivedMessageToView(message);
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
	
	public String sendMessage(String msg) {
		try {
			objOuputStream.writeObject("Send Message");
			objOuputStream.writeObject(msg);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return new String();
	}
	
	public void receiveServerResponse() {
		setupLogger();
		new Thread(new Runnable() {
			@Override
			public void run() {
				String action = "";
				while (true) {
					try {
						action = (String) objInputStream.readObject();

						// server replies to indicate what it is sending
						if (action.equalsIgnoreCase("ACCOUNT_STATUS")) {

							accountStatus = (AccountStatus) receiveObject();
							clientDashboardController.sendAccountStatusToView(accountStatus);
							break;
						} 
						else if (action.equalsIgnoreCase("CHAT")) {
							
							break;
						} 
						else if (action.equalsIgnoreCase("REPORT_ISSUE")) {
							
							reportIssueCategories = (ReportIssueCategories) receiveObject();
							clientDashboardController.sendReportIssueCategoriesToView(reportIssueCategories);
							break;
						} 
						else if (action.equalsIgnoreCase("COMPLAINT_HISTORY")) {
							
							complaintReportHistory = (ComplaintReportHistory) receiveObject();
							clientDashboardController.sendComplaintReportHistoryToView(complaintReportHistory);
							break;
						} 			
						else if (action.equalsIgnoreCase("GET_EMPLOYEES_AVAILABLE_FOR_CHAT")) {
							
							listOfOnlineEmployees = (ListOfOnlineEmployees) receiveObject();
							clientDashboardController.sendOnlineEmployeesToView(listOfOnlineEmployees);
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
						logger.warning("IO Exception occured");
					}
				}
			}
		}).start();		
	}
		
	private void createConnection() {
		setupLogger();
		try {
			connectionSocket = new Socket(hostname, PORT);
			logger.info("Connection created on Client side");
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
			objOuputStream = new ObjectOutputStream (connectionSocket.getOutputStream());
			objInputStream = new ObjectInputStream (connectionSocket.getInputStream());
		} catch (IOException e) {
			e.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
		
	public void sendObject(Object obj) {
		setupLogger();
		try {
			objOuputStream.writeObject(obj);
		} catch (IOException ex) {
			ex.printStackTrace();
			logger.warning("IO exception occured");
		}
	}
	
	private Object receiveObject() {
		try {
			return objInputStream.readObject();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public void sendComplaintIssue(ReportIssueHibernate obj) {
		try {
			objOuputStream.writeObject(obj);
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}
	
	private void closeConnection() {
		setupLogger();
		listenForChat = false;
		try {
			if (objInputStream != null)
				objInputStream.close();
			if (objOuputStream != null)
				objOuputStream.close();
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
		logger.info("connection closed");
		new ClientLoginController();
	}

}
