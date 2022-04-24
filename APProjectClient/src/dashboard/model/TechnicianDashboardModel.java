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

import dashboard.controller.TechnicianDashboardController;
import login.controller.TechnicianLoginController;
import object.classes.ListOfAssignedComplaintsObj;

public class TechnicianDashboardModel {
	private final String hostname = "localhost";
	private final int PORT = 1234;
	private final int CHATPORT = 4321;
	private Socket connectionSocket;
	private Socket chatSocket;
	private ObjectOutputStream objOutputStream;
	private ObjectInputStream objInputStream;
	private String userid;
	private String username;
	private ListOfAssignedComplaintsObj listOfAssignedComplaints;
	private TechnicianDashboardController technicianDashboardController;
	private BufferedReader bufferedReader;
	private volatile boolean listenForChat;
	
	private final static Logger logger = Logger.getLogger(TechnicianDashboardModel.class.getName());
	
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
	
	public TechnicianDashboardModel(TechnicianDashboardController obj, String userid) {
		technicianDashboardController = obj;
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
		
		listOfAssignedComplaints = null;
		
		new Thread(new Runnable() {
		     @Override
		     public void run() {
		    	 
		    	createConnection();
		 		configureStreams();
		 		sendObject("DASHBOARD_CONNECT"); // indicate to the server that the dashboard is connected
		 		sendObject(userid); // send the userid to the server
		 		sendObject("TECHNICIAN"); // send the type of user to the server
		 		connectChatSocket();
		 		configureReader();
		 		username = (String) receiveObject(); // receive username from server
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
	
	public String getUsername() {
		return username;
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
						if (action.equalsIgnoreCase("ASSIGNED_COMPLAINTS")) {
							
							listOfAssignedComplaints = (ListOfAssignedComplaintsObj) receiveObject();
							technicianDashboardController.sendAssignedComplaintsToView(listOfAssignedComplaints);
							break;
						} 
						else if (action.equalsIgnoreCase("CHAT")) {
							
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
	
	private void listenForMessage() {
		listenForChat = true;
		new Thread(() -> {
			while (listenForChat) {
				try {
					String message = bufferedReader.readLine();
					technicianDashboardController.sendReceivedMessageToView(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	private void createConnection() {
		setupLogger();
		try {
			connectionSocket = new Socket(hostname, PORT);
			logger.info("connection created on client side for technician");
		} catch (UnknownHostException e) {
			e.printStackTrace();
			logger.warning("Unknown host exception occured");
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
	
	private void configureReader() {
		try {
			InputStream input = chatSocket.getInputStream();
			bufferedReader = new BufferedReader(new InputStreamReader(input));
		} catch (IOException e) {
			e.printStackTrace();
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
		listenForChat = false;
		setupLogger();
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
		logger.info("connection closed on client side for technician");
		new TechnicianLoginController();
	}
}
