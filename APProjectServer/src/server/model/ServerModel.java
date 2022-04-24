package server.model;

import java.io.*;
import java.net.*;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

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
import object.classes.AccountStatus;
import object.classes.AssignedComplaintResponsesObj;
import object.classes.AssignedComplaintsIdObj;
import object.classes.AssignedComplaintsObj;
import object.classes.ComplaintCategory;
import object.classes.ComplaintReportHistory;
import object.classes.ComplaintsForRepresentativeObj;
import object.classes.Credential;
import object.classes.ListOfAssignedComplaintsObj;
import object.classes.ListOfComplaintsForRepresentative;
import object.classes.ListOfOnlineEmployees;
import object.classes.OnlineEmployeesObj;
import object.classes.PaymentHistoryObj;
import object.classes.PaymentListObj;
import object.classes.ProductServices;
import object.classes.ReportIssueCategories;
import object.classes.ReportedIssueObj;
import object.classes.ReportedIssueResponseObj;
import object.classes.RepresentativeHomeTabInfoObj;
import object.classes.RepresentativeResponsesObj;
import object.classes.TechniciansObj;

public class ServerModel {
//	private String url;
	private String url_xampp;
//	private String filePath;
//	private String folderName;
	private final int PORT = 1234;
	private final int CHATPORT = 4321;
	private Connection conn;
//	private File file;
	private static volatile ServerSocket serverSocket;
	private static volatile ServerSocket serverSocketChat;
	private Socket connectionSocket;
	private String checkCustomerQuery;
	private String checkEmployeeQuery;
//	private String checkUserQuery;
	private PreparedStatement prepStatement;
	private ResultSet resultSet;
	private	SimpleDateFormat dateWithTimeFormatter; 
	private	SimpleDateFormat dateOnlyFormatter; 
    private Date date;
    private static volatile boolean serverOn;
    private Set<ClientHandler> clientThreadsHashSet = new HashSet<>();
    
    private final static Logger logger = Logger.getLogger(ServerModel.class.getName());
	
	public ServerModel() {
		initialize();
//		createFolder();
//		listenSocket();
	}
	
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
	
	private void initialize() {
		setupLogger();
//		url = "jdbc:sqlite:MicroStar_database/ap_project.db";
		url_xampp = "jdbc:mysql://localhost:3306/approject";
//		filePath = "MicroStar_database/ap_project.db";
//		folderName = "MicroStar_database";
		conn = null;
//		conn = DBConnectorFactory.getDatabaseConnection();
//		file = new File(filePath);
//		checkUserQuery = "SELECT COUNT(*) FROM credential WHERE userid = ? AND password = ? AND user_type = ?";
		checkCustomerQuery = "SELECT * "
				+ "FROM customer_login as a, customer as b "
				+ "WHERE a.customer_id = b.id AND b.customer_user_id = ? AND a.cus_password = ?";
		
		checkEmployeeQuery = "SELECT * "
				+ "FROM employee_login as a, employee as b "
				+ "WHERE a.employee_id = b.id AND b.employee_user_id = ? AND a.emp_password = ? AND b.employee_type = ?";
		
		prepStatement = null;
		resultSet = null;
		dateWithTimeFormatter = new SimpleDateFormat("dd/MM/yyyy:- hh:mm:ss aa");
		dateOnlyFormatter = new SimpleDateFormat("dd/MM/yyyy");
		serverOn = false;
		connectionSocket = null;
		
		logger.info("server started");
	}

	// create folder to store database if it doesn't exist
//	private void createFolder() {
//		if (!file.exists()) {
//			new File(folderName).mkdirs();
//			createTables();
//		}
//	}
	
	// create tables in database if they don't exist
//	private void createTables() {
//		String sql_1 = "CREATE TABLE IF NOT EXISTS customer("
//				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
//				+ "userid TEXT NOT NULL UNIQUE,"
//				+ "first_name TEXT NOT NULL,"
//				+ "last_name TEXT NOT NULL,"
//				+ "email_address TEXT NOT NULL,"
//				+ "home_address TEXT NOT NULL,"
//				+ "tele_num TEXT NOT NULL"
//				+ ");";
//		
//		String sql_2 = "CREATE TABLE IF NOT EXISTS employee("
//				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
//				+ "userid TEXT NOT NULL UNIQUE,"
//				+ "first_name TEXT NOT NULL,"
//				+ "last_name TEXT NOT NULL,"
//				+ "user_type TEXT NOT NULL"
//				+ ");";
//		
//		String sql_3 = "CREATE TABLE IF NOT EXISTS credential("
//				+ "id INTEGER PRIMARY KEY AUTOINCREMENT,"
//				+ "userid TEXT NOT NULL UNIQUE,"
//				+ "password TEXT NOT NULL,"
//				+ "user_type TEXT NOT NULL"
//				+ ");";
//		
//		
//		try {
//			conn = DriverManager.getConnection(url);
//			
//			Statement stmt_1 = conn.createStatement();
//			Statement stmt_2 = conn.createStatement();
//			Statement stmt_3 = conn.createStatement();
//			
//			stmt_1.execute(sql_1);
//			stmt_2.execute(sql_2);
//			stmt_3.execute(sql_3);
//			
//			stmt_1.close();
//			stmt_2.close();
//			stmt_3.close();
//			
//			conn.close();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		} 
//	}
	
	// change the state of the server
	public void serverState(boolean state) {

		new Thread(new Runnable() {
			@Override
			public void run() {
				if (state) {
					setupLogger();
					serverOn = true;
					try {
						// server is listening
						serverSocket = new ServerSocket(PORT);
						serverSocket.setReuseAddress(true);
						
						serverSocketChat = new ServerSocket(CHATPORT);
						serverSocketChat.setReuseAddress(true);
						
						date = new Date();
						// TODO log starting server
						System.out.println("Server has started... " + dateWithTimeFormatter.format(date));
					} catch (IOException e) {
						e.printStackTrace();
						logger.warning("IO Exception occured");
					}
					listenSocket();
				} else {
					serverOn = false;
					closeConnections();
					date = new Date();
					// TODO log stopping server
					System.out.println("server stopped... " + dateWithTimeFormatter.format(date));
					return;
				}
			}
		}).start();
	}
	
	
	// check database for credentials
	private boolean validateCredentials(Credential credObj) {
		setupLogger();
		try {
			conn = DriverManager.getConnection(url_xampp, "root", "");
			
			if (credObj.getUserType().equalsIgnoreCase("CLIENT")) {
				prepStatement = conn.prepareStatement(checkCustomerQuery);
			}
			else {
				prepStatement = conn.prepareStatement(checkEmployeeQuery);
				prepStatement.setString(3, credObj.getUserType());
			}
			
			prepStatement.setInt(1, Integer.parseInt(credObj.getUserId()));
			prepStatement.setString(2, credObj.getPassword());
			
			resultSet = prepStatement.executeQuery();
			
			if (resultSet.next()) {
				prepStatement.close();
				resultSet.close();
				return true;
			}
			
			resultSet.close();
			prepStatement.close();
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
			logger.warning("SQL exception occured");
		}
		return false;
	}
		
	private void listenSocket() {
		setupLogger();
		try { 
			while (serverOn) { 
				connectionSocket = serverSocket.accept(); 
				
				System.out.println("New client connected: " + connectionSocket.getInetAddress().getHostAddress()); 
				
				ClientHandler clientHandler = new ClientHandler(connectionSocket); 
				new Thread(clientHandler).start(); 
			} 
		} 
		catch (IOException e) { 
			// TODO error thrown from stopping server
//			e.printStackTrace(); 
		} finally {
			closeConnections();
			logger.info("connection closed");
		} 	
	}
	
	private void closeConnections() {
		setupLogger();
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				logger.warning("SQL exception occured");
			}
		}
		
		if (connectionSocket != null) {
			try {
				connectionSocket.close();
			} catch (IOException e) {
				e.printStackTrace();
				logger.warning("IOException occured");
			}
		}
		
		if (serverSocket != null) { 
			try { 
				serverSocket.close(); 
			} 
			catch (IOException e) { 
				e.printStackTrace(); 
				logger.warning("IO Exception occured");
			} 
		} 
		
		if (serverSocketChat != null) { 
			try { 
				serverSocketChat.close(); 
			} 
			catch (IOException e) { 
				e.printStackTrace(); 
				logger.warning("IO Exception occured");
			} 
		} 
	}
	
	// ClientHandler class 
	private class ClientHandler implements Runnable { 
		private final Socket clientSocket; 
		private ObjectOutputStream objOutputStream;
		private ObjectInputStream objInputStream;
		private Credential credentialObj;
		private String action;
		private String userid;
		private String userType;
		private int useridPrimaryKey;
		private OutputStream outputStream;
		private PrintWriter printWriter;
		private Socket chatSocket;
		
		// Constructor 
		public ClientHandler(Socket socket) 
		{ 
			setupLogger();
			this.clientSocket = socket;
			this.credentialObj = null;
			this.action = "";
			this.userid = "";
			this.userType = "";
			this.useridPrimaryKey = 0;
			
			this.outputStream = null;
			this.printWriter = null;
			
			this.chatSocket = null;
			
			try {
				this.objOutputStream = new ObjectOutputStream (clientSocket.getOutputStream());
				this.objInputStream = new ObjectInputStream (clientSocket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
				logger.warning("IO Exception occured");
			}
		} 
		
		private void sendObject(Object obj) {
			setupLogger();
			try {
				objOutputStream.writeObject(obj);
			} catch (IOException e) {
				e.printStackTrace();
				logger.warning("IO Exception occured");
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
		
		private void writeMessage(String message) {
			new Thread(() -> {
				printWriter.println(message);
				printWriter.flush();
			}).start();
		}
		
		private void initiateChatComponents() {
			try {
				outputStream = chatSocket.getOutputStream();
				printWriter = new PrintWriter(outputStream, true);
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		private void sendMessage(String senderUserId, String recepientUserId, String message) {
			for (ClientHandler thread : clientThreadsHashSet) {
				if (thread.userid.equalsIgnoreCase(recepientUserId)) {
					thread.writeMessage(senderUserId + " ~ " + message);
					break;
				}
			}
		}

		public void run() 
		{ 	
			setupLogger();
			quit: {
				while (serverOn) {
					if (!serverOn) {
						break quit;
					}

					try {
						action = (String) objInputStream.readObject();

						if (action.equalsIgnoreCase("LOGIN")) {
							try {
								credentialObj = (Credential) objInputStream.readObject();
							} catch (ClassNotFoundException e) {
								e.printStackTrace();
								logger.warning("Class not found exception occured");
							} catch (IOException e) {
								e.printStackTrace();
								logger.warning("IO exception occured");
							}

							boolean isCredentialValid = validateCredentials(credentialObj);
							sendObject(isCredentialValid); // send reply to login after validation

							break quit; // break to stop thread after login attempt

						} else if (action.equalsIgnoreCase("LOGOUT")) {
							System.err.println(userid + " Logged out...");
							clientThreadsHashSet.remove(this);
							break quit;
						} else if (action.equalsIgnoreCase("DASHBOARD_CONNECT")) {
							
								userid = (String) receiveObject(); // receive userid from client
								userType = (String) receiveObject(); // receive type of user from client

								Thread.currentThread().setName(userid);
								clientThreadsHashSet.add(this);
								
								try {
									chatSocket = serverSocketChat.accept();
									initiateChatComponents();
								} catch (Exception ex) {
									
								}
								
							
							new Thread(() -> {
								System.out.println("User id: " + userid);
								System.out.println("User Type: " + userType);
								
								ResultSet rs = null;
								String query = "";
								String fullName = "";
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									
									if (userType.equalsIgnoreCase("CLIENT")) {
										query = "SELECT id, UPPER(CONCAT(first_name, ' ', last_name)) As full_name "
												+ "FROM customer "
												+ "WHERE customer_user_id = ? ";
												
										prepStatement = conn.prepareStatement(query);
									}
									else {
										query = "SELECT id, UPPER(CONCAT(first_name, ' ', last_name)) As full_name "
												+ "FROM employee "
												+ "WHERE employee_user_id = ? ";
										prepStatement = conn.prepareStatement(query);
									}
									
									prepStatement.setInt(1, Integer.parseInt(userid));
									
									rs = prepStatement.executeQuery();
									
									if (rs.next()) {
										fullName = rs.getString("full_name");
										useridPrimaryKey = rs.getInt("id");
									}
									
									resultSet.close();
									prepStatement.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}

								sendObject(fullName);
							
							}).start();

						} else if (action.equalsIgnoreCase("ACCOUNT_STATUS")) {
							
							new Thread(() -> {
								ResultSet rs = null;
								PaymentListObj paymentStatusObj = null;
								PaymentHistoryObj paymentHistoryObj = null;
								AccountStatus accountStatus = new AccountStatus();
								String query = "";
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									
									query = "SELECT a.id, a.customer_id, a.amount_due, a.due_date, a.payment_status "
											+ "FROM payments_list As a, customer as b "
											+ "WHERE a.customer_id = b.id and b.customer_user_id = ?";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, Integer.parseInt(userid));
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id = rs.getInt("id");
										int customer_id = rs.getInt("customer_id");
										int amount_due = rs.getInt("amount_due");
										String due_date = rs.getString("due_date");
										String payment_status = rs.getString("payment_status");
										
										paymentStatusObj = new PaymentListObj(id, customer_id, amount_due, due_date, payment_status);
										accountStatus.setPaymentStatus(paymentStatusObj);
									}
									
									query = "SELECT a.id, a.customer_id, a.amount_paid, a.date_due, a.date_paid "
											+ "FROM payment_history As a, customer As b "
											+ "WHERE a.customer_id = b.id and b.customer_user_id = ?";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, Integer.parseInt(userid));
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id = rs.getInt("id");
										int customer_id = rs.getInt("customer_id");
										int amount_paid = rs.getInt("amount_paid");
										String date_due = rs.getString("date_due");
										String date_paid = rs.getString("date_paid");
										
										paymentHistoryObj = new PaymentHistoryObj(id, customer_id, amount_paid, date_due, date_paid);
										accountStatus.addToPaymentHistoryList(paymentHistoryObj);
									}
									
									prepStatement.close();
									rs.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}
								
								sendObject("ACCOUNT_STATUS"); // indicate to the client what to accept
								sendObject(accountStatus);

							}).start();
							
						} else if (action.equalsIgnoreCase("GET_EMPLOYEES_AVAILABLE_FOR_CHAT")) {
							new Thread(() -> {
								
								ResultSet rs = null;
								OnlineEmployeesObj onlineEmployeesObj = null;
								ListOfOnlineEmployees listOfOnlineEmployees = new ListOfOnlineEmployees();
								String query = "";
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									
									query = "SELECT employee_user_id, UPPER(CONCAT(first_name, ' ', last_name,"
											+ " ' [', employee_type, ']')) as name "
											+ "FROM employee "
											+ "WHERE employee_type = ?";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setString(1, "REPRESENTATIVE");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int emp_user_id = rs.getInt("employee_user_id");
										String name = rs.getString("name");
										
										onlineEmployeesObj = new OnlineEmployeesObj(emp_user_id, name);
										listOfOnlineEmployees.addToListOfOnlineEmployees(onlineEmployeesObj);
									}
									
									query = "SELECT DISTINCT a.employee_user_id, UPPER(CONCAT(a.first_name, ' ', a.last_name,"
											+ " ' [', a.employee_type, ']')) as name "
											+ "FROM employee as a, assigned_technician as b, "
												+ "reported_issue as c, customer as d "
											+ "WHERE a.id = b.technician_id and b.reported_issue_id = c.id "
												+ "and c.userid = d.id and d.customer_user_id = ? "
												+ "and c.completed = ? and b.live_chat = ? and a.employee_type = ?";

									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, Integer.parseInt(userid));
									prepStatement.setString(2, "NO");
									prepStatement.setString(3, "YES");
									prepStatement.setString(4, "TECHNICIAN");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int emp_user_id = rs.getInt("employee_user_id");
										String name = rs.getString("name");
										
										onlineEmployeesObj = new OnlineEmployeesObj(emp_user_id, name);
										listOfOnlineEmployees.addToListOfOnlineEmployees(onlineEmployeesObj);
									}
									
									prepStatement.close();
									rs.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}
								
								
								ListOfOnlineEmployees finalListOfOnlineEmployees = new ListOfOnlineEmployees();
								
								for (OnlineEmployeesObj obj : listOfOnlineEmployees.getOnlineEmployeesList()) {
									for (ClientHandler thread : clientThreadsHashSet) {
										if (thread.userid.equalsIgnoreCase(String.valueOf(obj.getUserId()))) {
											finalListOfOnlineEmployees.addToListOfOnlineEmployees(obj);
										}
									}
								}
								
								// indicate to the client what it is going to send
								sendObject("GET_EMPLOYEES_AVAILABLE_FOR_CHAT");
								sendObject(finalListOfOnlineEmployees);
							}).start();
							
						} else if (action.equalsIgnoreCase("REPORT_ISSUE")) {
							new Thread(() -> {
								ResultSet rs = null;
								ProductServices productServices = null;
								ComplaintCategory complaintCategory = null;
								ReportIssueCategories reportIssueCategories = new ReportIssueCategories();
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									Statement stat = conn.createStatement();
									
									rs = stat.executeQuery("SELECT * FROM product_services");
									while (rs.next()) {
										int id = rs.getInt("id");
										int product_id = rs.getInt("product_id");
										String product_name = rs.getString("product_name");

										productServices = new ProductServices(id, product_id, product_name);
										reportIssueCategories.addProductService(productServices);
									}
									
									rs = stat.executeQuery("SELECT * FROM complaint_categories");
									while (rs.next()) {
										int id = rs.getInt("id");
										int category_id = rs.getInt("category_id");
										String category_name = rs.getString("category_name");
										
										complaintCategory = new ComplaintCategory(id, category_id, category_name);
										reportIssueCategories.addComplaintCategory(complaintCategory);
									}
									
									stat.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}
								
								// indicate to the client what it is going to send
								sendObject("REPORT_ISSUE");
								sendObject(reportIssueCategories);
								
							}).start();
							
						} else if (action.equalsIgnoreCase("COMPLAINT_HISTORY")) {
							new Thread(() -> {
								ResultSet rs = null;
								ReportedIssueObj reportedIssueList = null;
								ReportedIssueResponseObj reportedIssueResponseList = null;
								ComplaintReportHistory complaintReportHistory = new ComplaintReportHistory();
								String query = "";
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									
									query = "SELECT a.id, b.product_name, c.category_name, a.complaint "
											+ "FROM reported_issue As a, product_services As b, complaint_categories As c, "
												+ "customer As d "
											+ "WHERE a.product_service_id = b.id and a.complaint_category_id = c.id "
												+ "and a.userid = d.id and d.customer_user_id = ?";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, Integer.parseInt(userid));
									
									// get reported issues associated with a user id from the reported_issue table
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										
										int id = rs.getInt("id");
										String product_name = rs.getString("product_name");
										String category_name = rs.getString("category_name");
										String complaint = rs.getString("complaint");
										
										reportedIssueList = new ReportedIssueObj(id, product_name, category_name, complaint);
										complaintReportHistory.addToReportedIssueList(reportedIssueList);
									}
									
									
									query = "SELECT a.complaint_id, b.employee_type, UPPER(CONCAT(b.first_name, ' ', b.last_name)) As full_name, a.response, a.response_date "
											+ "FROM response As a, employee As b, customer as c "
											+ "WHERE a.responder_id = b.id and a.customer_id = c.id and c.customer_user_id = ? ";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, Integer.parseInt(userid));
									
									// get responses to reported issues associated with a user id from the response table
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int complaint_id = rs.getInt("complaint_id");
										String employee_type = rs.getString("employee_type");
										String full_name = rs.getString("full_name") + " [" + employee_type + "]";
										String response = rs.getString("response");
										String response_date = rs.getString("response_date");
					
										reportedIssueResponseList = new ReportedIssueResponseObj(complaint_id, employee_type, full_name, response, response_date);
										complaintReportHistory.addToReportedIssueResponseList(reportedIssueResponseList);
									}
									
									prepStatement.close();
									rs.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}
								
								// indicate to the client what it is going to send
								sendObject("COMPLAINT_HISTORY");
								sendObject(complaintReportHistory);
							
							}).start();
						} else if (action.equalsIgnoreCase("SUBMIT_COMPLAINT")) {
							ReportIssueHibernate reportIssueHibernate = (ReportIssueHibernate) receiveObject();
							
							
							new Thread(() -> {
								reportIssueHibernate.setUserid(useridPrimaryKey);
								reportIssueHibernate.create();
							}).start();

							// TODO indicate to the client that record is submitted
//								sendObject("SUBMIT_COMPLAINT");
						} else if (action.equalsIgnoreCase("ASSIGNED_COMPLAINTS")) {
							new Thread(() -> {
								
								ResultSet rs = null;
								AssignedComplaintsObj assignedComplaints = null;
								AssignedComplaintResponsesObj assignedComplaintResponses = null;
								ListOfAssignedComplaintsObj listOfAssignedComplaints = new ListOfAssignedComplaintsObj();
								String query = "";
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									
									query = "SELECT a.id, UPPER(CONCAT(b.first_name, ' ', b.last_name)) as full_name, b.email_address, "
												+ "b.home_address, b.tele_num, c.product_name, d.category_name, e.complaint, "
												+ "e.id as complaint_pk, b.id as customer_pk "
											
											+ "FROM assigned_technician As a, customer As b, product_services As c, "
												+ "complaint_categories As d, reported_issue As e, employee as f "
											
											+ "WHERE a.reported_issue_id = e.id and e.userid = b.id and e.product_service_id = c.id "
												+ "and e.complaint_category_id = d.id and a.technician_id = f.id "
												+ "and f.employee_user_id = ? and e.completed = ? ";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, Integer.parseInt(userid));
									prepStatement.setString(2, "NO");
									
									
									// get reported issues associated with a user id from the reported_issue table
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id  = rs.getInt("id");
										String fullName = rs.getString("full_name");
										String email = rs.getString("email_address");
										String address = rs.getString("home_address");
										String tele = rs.getString("tele_num");
										String productService = rs.getString("product_name");
										String complaintCategory = rs.getString("category_name");
										String complaint = rs.getString("complaint");
										int complaintPrimaryKey = rs.getInt("complaint_pk");
										int customerPrimaryKey = rs.getInt("customer_pk");
										
										assignedComplaints = new AssignedComplaintsObj(id, fullName, email, address, tele, productService, complaintCategory, complaint, complaintPrimaryKey, customerPrimaryKey);
										listOfAssignedComplaints.addToListOfAssignedComplaints(assignedComplaints);		
									}
									
									query = "SELECT a.id, a.live_chat, b.response "
											+ "FROM assigned_technician As a, response As b, reported_issue as c "
											+ "WHERE a.technician_id = b.responder_id and a.reported_issue_id = b.complaint_id "
												+ "and a.technician_id = ? and c.completed = ?";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, useridPrimaryKey);
									prepStatement.setString(2, "NO");
									
									// get responses to reported issues associated with a user id from the response table
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id = rs.getInt("id");
										String liveChat = rs.getString("live_chat");
										String response = rs.getString("response");
										
										assignedComplaintResponses = new AssignedComplaintResponsesObj(id, liveChat, response);
										listOfAssignedComplaints.addToListOfComplaintResponses(assignedComplaintResponses);
									}
									
									prepStatement.close();
									rs.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}
												
								sendObject("ASSIGNED_COMPLAINTS");
								sendObject(listOfAssignedComplaints);
							}).start();
						} else if (action.equalsIgnoreCase("MARK_AS_COMPLETED")) {
							ReportIssueHibernate reportedIssue = new ReportIssueHibernate();
							
							int id = (Integer) receiveObject();
							
							new Thread(() -> {
								reportedIssue.setId(id);
								reportedIssue.setCompleted("YES");
								reportedIssue.updateCompletedStatus();
							}).start();
						} else if (action.equalsIgnoreCase("TECHNICIAN_RESPONSE")) {
							ReportResponseHibernate reportResponseHibernateObj = (ReportResponseHibernate) receiveObject();
							AssignedTechnicianHibernate assignedTechnicianHibernateObj = (AssignedTechnicianHibernate) receiveObject();
							
							new Thread(() -> {
								Date date = new Date();
								
								reportResponseHibernateObj.setResponderId(useridPrimaryKey);
								reportResponseHibernateObj.setResponseDate(dateOnlyFormatter.format(date));
								reportResponseHibernateObj.create();
							}).start();
							
							new Thread(() -> {
								assignedTechnicianHibernateObj.updateLiveChatStatus();
							}).start();
						} else if(action.equalsIgnoreCase("REPRESENTATIVE_HOME")) {
							new Thread(() -> {
								
								ResultSet rs = null;
								ProductServicesHibernate productServicesObj = null;
								ReportIssueHibernate reportedIssueObj = null;
								RepresentativeHomeTabInfoObj representativeHomeTabInfoObj = new RepresentativeHomeTabInfoObj();
								String query = "";
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									
									query = "SELECT * "
											+ "FROM product_services";
									
									prepStatement = conn.prepareStatement(query);
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id  = rs.getInt("id");
										int product_id = rs.getInt("product_id");
										String product_name = rs.getString("product_name");
											
										productServicesObj = new ProductServicesHibernate(id, product_id, product_name);
										representativeHomeTabInfoObj.addToProductServicesArrayList(productServicesObj);
									}
									
									query = "SELECT product_service_id "
											+ "FROM reported_issue "
											+ "WHERE completed = ?";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setString(1, "YES");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int product_service_id  = rs.getInt("product_service_id");
										
										reportedIssueObj = new ReportIssueHibernate();
										reportedIssueObj.setProductServiceId(product_service_id);
										representativeHomeTabInfoObj.addToResolvedComplaintsArrayList(reportedIssueObj);
									}
									
									prepStatement.close();
									prepStatement = conn.prepareStatement(query);
									prepStatement.setString(1, "NO");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int product_service_id  = rs.getInt("product_service_id");
										
										reportedIssueObj = new ReportIssueHibernate();
										reportedIssueObj.setProductServiceId(product_service_id);
										representativeHomeTabInfoObj.addToOutstandingComplaintsArrayList(reportedIssueObj);
									}
									
									prepStatement.close();
									rs.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}
								
								sendObject("REPRESENTATIVE_HOME");
								sendObject(representativeHomeTabInfoObj);
							}).start();
						} else if(action.equalsIgnoreCase("REPRESENTATIVE_COMPLAINTS")) {
							new Thread(() -> {
								
								ResultSet rs = null;
								ProductServicesHibernate productServicesObj = null;
								ComplaintCategoriesHibernate complaintCategoriesObj = null;
								TechniciansObj techniciansObj = null;
								ComplaintsForRepresentativeObj complaintsForRepresentativeObj = null;
								RepresentativeResponsesObj representativeResponsesObj = null;
								AssignedComplaintsIdObj assignedComplaintsIdObj = null;
								ListOfComplaintsForRepresentative listOfComplaintsForRepresentative = new ListOfComplaintsForRepresentative();
								String query = "";
								
								try {
									conn = DriverManager.getConnection(url_xampp, "root", "");
									
									// select all from product_services table
									query = "SELECT * "
											+ "FROM product_services";
									prepStatement = conn.prepareStatement(query);
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id  = rs.getInt("id");
										int product_id = rs.getInt("product_id");
										String product_name = rs.getString("product_name");
										
										productServicesObj = new ProductServicesHibernate(id, product_id, product_name);
										listOfComplaintsForRepresentative.addToListOfProductServices(productServicesObj);
									}
									//----------------------------------------
									
									// select all from complaint categories table
									query = "SELECT * "
											+ "FROM complaint_categories";
									prepStatement = conn.prepareStatement(query);
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id  = rs.getInt("id");
										int category_id = rs.getInt("category_id");
										String category_name = rs.getString("category_name");
										
										complaintCategoriesObj = new ComplaintCategoriesHibernate(id, category_id, category_name);
										listOfComplaintsForRepresentative.addToListOfComplaintCategories(complaintCategoriesObj);
									}
									//-------------------------------------------
									
									// select all technicians form employee table
									query = "SELECT id, UPPER(CONCAT(first_name, ' ', last_name)) as full_name "
											+ "FROM employee "
											+ "WHERE employee_type = ?";
									prepStatement = conn.prepareStatement(query);
									prepStatement.setString(1, "TECHNICIAN");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int id_pk  = rs.getInt("id");
										String full_name = rs.getString("full_name");
										
										techniciansObj = new TechniciansObj(id_pk, full_name);
										listOfComplaintsForRepresentative.addToListOfTechnicians(techniciansObj);
									}
									//-------------------------------------------
									
									// select all complaints that are not completed
									query = "SELECT a.id As complaint_pk, UPPER(CONCAT(b.first_name, ' ', b.last_name)) As full_name, "
												+ "b.email_address, b.home_address, b.tele_num, c.product_name, d.category_name, "
												+ "a.complaint, b.id As customer_pk, a.product_service_id, a.complaint_category_id, "
												+ "e.amount_due, e.due_date, e.payment_status "
											
											+ "FROM reported_issue As a, customer As b, product_services As c, "
												+ "complaint_categories As d, payments_list As e "
											
											+ "WHERE a.product_service_id = c.id and a.complaint_category_id = d.id "
												+ "and a.userid = b.id and e.customer_id = b.id and a.completed = ?";
									
									prepStatement = conn.prepareStatement(query);
									prepStatement.setString(1, "NO");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int complaint_pk = rs.getInt("complaint_pk");
										String full_name = rs.getString("full_name");
										String email = rs.getString("email_address");
										String address = rs.getString("home_address");
										String tele_num = rs.getString("tele_num");
										String product_name = rs.getString("product_name");
										String category_name = rs.getString("category_name");
										String complaint = rs.getString("complaint");
										int customer_pk = rs.getInt("customer_pk");
										int product_service_id = rs.getInt("product_service_id");
										int complaint_category_id = rs.getInt("complaint_category_id");
										String amount_due = rs.getString("amount_due");
										String due_date = rs.getString("due_date");
										String payment_status = rs.getString("payment_status");
										
										complaintsForRepresentativeObj = new ComplaintsForRepresentativeObj(complaint_pk, full_name, 
												email, address, tele_num, product_name, category_name, complaint, customer_pk, product_service_id,
												complaint_category_id, amount_due, due_date, payment_status);
										listOfComplaintsForRepresentative.addToComplaintsForRepresentative(complaintsForRepresentativeObj);
									}
									//--------------------------------------------
									
									// select all from product_services table
									query = "SELECT a.complaint_id, a.response "
											+ "FROM response As a, employee As b, reported_issue As c "
											+ "WHERE a.responder_id = b.id and a.complaint_id = c.id "
												+ "and b.employee_user_id = ? and c.completed = ?";
									prepStatement = conn.prepareStatement(query);
									prepStatement.setInt(1, Integer.parseInt(userid));
									prepStatement.setString(2, "NO");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int complaint_id  = rs.getInt("complaint_id");
										String response = rs.getString("response");
										
										representativeResponsesObj = new RepresentativeResponsesObj(complaint_id, response);
										listOfComplaintsForRepresentative.addToRepresentativeResponses(representativeResponsesObj);
									}
									//----------------------------------------
									
									// select all from product_services table
									query = "SELECT a.reported_issue_id "
											+ "FROM assigned_technician as a, reported_issue as b "
											+ "WHERE a.reported_issue_id = b.id and b.completed = ?";
									prepStatement = conn.prepareStatement(query);
									prepStatement.setString(1, "NO");
									
									rs = prepStatement.executeQuery();
									while (rs.next()) {
										int assignedComplaintId  = rs.getInt("reported_issue_id");
										
										assignedComplaintsIdObj = new AssignedComplaintsIdObj(assignedComplaintId);
										listOfComplaintsForRepresentative.addToListOfAssignedComplaints(assignedComplaintsIdObj);;
									}
									//----------------------------------------
									
									prepStatement.close();
									rs.close();
									conn.close();
								} catch (SQLException e) {
									e.printStackTrace();
									logger.warning("SQL exception occured");
								}
								
								sendObject("REPRESENTATIVE_COMPLAINTS");
								sendObject(listOfComplaintsForRepresentative);
							}).start();
						} else if (action.equalsIgnoreCase("REPRESENTATIVE_RESPONSE")) {
							ReportResponseHibernate reportResponseHibernateObj = (ReportResponseHibernate) receiveObject();
							
							new Thread(() -> {
								Date date = new Date();
								
								reportResponseHibernateObj.setResponderId(useridPrimaryKey);
								reportResponseHibernateObj.setResponseDate(dateOnlyFormatter.format(date));
								reportResponseHibernateObj.create();
							}).start(); 
						} else if (action.equalsIgnoreCase("ASSIGN_TECHNICIAN")) {
							AssignedTechnicianHibernate assignTechnicianObj = (AssignedTechnicianHibernate) receiveObject();
							
							new Thread(() -> {
								assignTechnicianObj.create();
							}).start(); 
						} 
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_ASSIGNED_TECHNICIAN")) {
							new Thread(() ->{
								AssignedTechnicianHibernate assignedTechnicianObj = new AssignedTechnicianHibernate();
								ArrayList<AssignedTechnicianHibernate> assignedTechnicianList = new ArrayList<>();
								assignedTechnicianList = assignedTechnicianObj.readAll();
								
								sendObject("READ_ALL_ASSIGNED_TECHNICIAN");
								sendObject(assignedTechnicianList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_ASSIGNED_TECHNICIAN")) {
							AssignedTechnicianHibernate assignedTechnicianObj = (AssignedTechnicianHibernate) receiveObject();
							
							new Thread(() -> {
								assignedTechnicianObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_ASSIGNED_TECHNICIAN")) {
							AssignedTechnicianHibernate assignedTechnicianObj = (AssignedTechnicianHibernate) receiveObject();
							
							new Thread(() -> {
								assignedTechnicianObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_ASSIGNED_TECHNICIAN")) {
							int id = (int) receiveObject();
							AssignedTechnicianHibernate assignedTechnicianObj = new AssignedTechnicianHibernate();
							assignedTechnicianObj.setId(id);
							
							new Thread(() -> {
								assignedTechnicianObj.delete();
							}).start();
						} 
						
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_COMPLAINT_CATEGORIES")) {
							new Thread(() ->{
								ComplaintCategoriesHibernate complaintCategoriesObj = new ComplaintCategoriesHibernate();
								ArrayList<ComplaintCategoriesHibernate> complaintCategoriesList = new ArrayList<>();
								complaintCategoriesList = complaintCategoriesObj.readAll();
								
								sendObject("READ_ALL_COMPLAINT_CATEGORIES");
								sendObject(complaintCategoriesList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_COMPLAINT_CATEGORY")) {
							ComplaintCategoriesHibernate complaintCategoryObj = (ComplaintCategoriesHibernate) receiveObject();
							
							new Thread(() -> {
								complaintCategoryObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_COMPLAINT_CATEGORY")) {
							ComplaintCategoriesHibernate complaintCategoryObj = (ComplaintCategoriesHibernate) receiveObject();
							
							new Thread(() -> {
								complaintCategoryObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_COMPLAINT_CATEGORY")) {
							int id = (int) receiveObject();
							ComplaintCategoriesHibernate complaintCategoryObj = new ComplaintCategoriesHibernate();
							complaintCategoryObj.setId(id);
							
							new Thread(() -> {
								complaintCategoryObj.delete();
							}).start();
						} 
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_CUSTOMER_LOGIN")) {
							new Thread(() ->{
								CustomerLoginHibernate customerLoginObj = new CustomerLoginHibernate();
								ArrayList<CustomerLoginHibernate> customerLoginList = new ArrayList<>();
								customerLoginList = customerLoginObj.readAll();
								
								sendObject("READ_ALL_CUSTOMER_LOGIN");
								sendObject(customerLoginList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_CUSTOMER_LOGIN")) {
							CustomerLoginHibernate customerLoginObj = (CustomerLoginHibernate) receiveObject();
							
							new Thread(() -> {
								customerLoginObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_CUSTOMER_LOGIN")) {
							CustomerLoginHibernate customerLoginObj = (CustomerLoginHibernate) receiveObject();
							
							new Thread(() -> {
								customerLoginObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_CUSTOMER_LOGIN")) {
							int id = (int) receiveObject();
							CustomerLoginHibernate customerLoginObj = (CustomerLoginHibernate) receiveObject();
							customerLoginObj.setId(id);
							
							new Thread(() -> {
								customerLoginObj.delete();
							}).start();
						} 
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_CUSTOMER")) {
							new Thread(() ->{
								CustomerHibernate customerObj = new CustomerHibernate();
								ArrayList<CustomerHibernate> customerList = new ArrayList<>();
								customerList = customerObj.readAll();
								
								sendObject("READ_ALL_CUSTOMER");
								sendObject(customerList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_CUSTOMER")) {
							CustomerHibernate customerObj = (CustomerHibernate) receiveObject();
							
							new Thread(() -> {
								customerObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_CUSTOMER")) {
							CustomerHibernate customerObj = (CustomerHibernate) receiveObject();
							
							new Thread(() -> {
								customerObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_CUSTOMER")) {
							int id = (int) receiveObject();
							CustomerHibernate customerObj = (CustomerHibernate) receiveObject();
							customerObj.setId(id);
							
							new Thread(() -> {
								customerObj.delete();
							}).start();
						} 
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_EMPLOYEE_LOGIN")) {
							new Thread(() ->{
								EmployeeLoginHibernate employeeLoginObj = new EmployeeLoginHibernate();
								ArrayList<EmployeeLoginHibernate> employeeLoginList = new ArrayList<>();
								employeeLoginList = employeeLoginObj.readAll();
								
								sendObject("READ_ALL_EMPLOYEE_LOGIN");
								sendObject(employeeLoginList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_EMPLOYEE_LOGIN")) {
							EmployeeLoginHibernate employeeLoginObj = (EmployeeLoginHibernate) receiveObject();
							
							new Thread(() -> {
								employeeLoginObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_EMPLOYEE_LOGIN")) {
							EmployeeLoginHibernate employeeLoginObj = (EmployeeLoginHibernate) receiveObject();
							
							new Thread(() -> {
								employeeLoginObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_EMPLOYEE_LOGIN")) {
							int id = (int) receiveObject();
							EmployeeLoginHibernate employeeLoginObj = (EmployeeLoginHibernate) receiveObject();
							employeeLoginObj.setId(id);
							
							new Thread(() -> {
								employeeLoginObj.delete();
							}).start();
						} 
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_EMPLOYEE")) {
							new Thread(() ->{
								EmployeeHibernate employeeHibernateObj = new EmployeeHibernate();
								ArrayList<EmployeeHibernate> employeeList = new ArrayList<>();
								employeeList = employeeHibernateObj.readAll();
								
								sendObject("READ_ALL_EMPLOYEE");
								sendObject(employeeList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_EMPLOYEE")) {
							EmployeeHibernate employeeHibernateObj = (EmployeeHibernate) receiveObject();
							
							new Thread(() -> {
								employeeHibernateObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_EMPLOYEE")) {
							EmployeeHibernate employeeHibernateObj = (EmployeeHibernate) receiveObject();
							
							new Thread(() -> {
								employeeHibernateObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_EMPLOYEE")) {
							int id = (int) receiveObject();
							EmployeeHibernate employeeHibernateObj = (EmployeeHibernate) receiveObject();
							employeeHibernateObj.setId(id);
							
							new Thread(() -> {
								employeeHibernateObj.delete();
							}).start();
						} 
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_PAYMENT_HISTORY")) {
							new Thread(() ->{
								PaymentHistoryHibernate paymentHistoryHibernateObj = new PaymentHistoryHibernate();
								ArrayList<PaymentHistoryHibernate> paymentHistoryList = new ArrayList<>();
								paymentHistoryList = paymentHistoryHibernateObj.readAllForEveryOne();
								
								sendObject("READ_ALL_PAYMENT_HISTORY");
								sendObject(paymentHistoryList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_PAYMENT_HISTORY")) {
							PaymentHistoryHibernate paymentHistoryHibernateObj = (PaymentHistoryHibernate) receiveObject();
							
							new Thread(() -> {
								paymentHistoryHibernateObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_PAYMENT_HISTORY")) {
							PaymentHistoryHibernate paymentHistoryHibernateObj = (PaymentHistoryHibernate) receiveObject();
							
							new Thread(() -> {
								paymentHistoryHibernateObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_PAYMENT_HISTORY")) {
							int id = (int) receiveObject();
							PaymentHistoryHibernate paymentHistoryHibernateObj = (PaymentHistoryHibernate) receiveObject();
							paymentHistoryHibernateObj.setId(id);
							
							new Thread(() -> {
								paymentHistoryHibernateObj.delete();
							}).start();
						} 
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_PAYMENT_LIST")) {
							new Thread(() -> {
								PaymentListHibernate paymentListHibernateObj = new PaymentListHibernate();
								ArrayList<PaymentListHibernate> paymentListList = new ArrayList<>();
								paymentListList = paymentListHibernateObj.readAllForEveryOne();
								
								sendObject("READ_ALL_PAYMENT_LIST");
								sendObject(paymentListList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_PAYMENT_LIST")) {
							PaymentListHibernate paymentListHibernateObj = (PaymentListHibernate) receiveObject();
							
							new Thread(() -> {
								paymentListHibernateObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_PAYMENT_LIST")) {
							PaymentListHibernate paymentListHibernateObj = (PaymentListHibernate) receiveObject();
							
							new Thread(() -> {
								paymentListHibernateObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_PAYMENT_LIST")) {
							int id = (int) receiveObject();
							PaymentListHibernate paymentListHibernateObj = (PaymentListHibernate) receiveObject();
							paymentListHibernateObj.setId(id);
							
							new Thread(() -> {
								paymentListHibernateObj.delete();
							}).start();
						} 
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_PRODUCT_SERVICES")) {
							new Thread(() -> {
								ProductServicesHibernate productServicesHibernateObj = new ProductServicesHibernate();
								ArrayList<ProductServicesHibernate> productServicesList = new ArrayList<>();
								productServicesList = productServicesHibernateObj.readAll();
								
								sendObject("READ_ALL_PRODUCT_SERVICES");
								sendObject(productServicesList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_PRODUCT_SERVICE")) {
							ProductServicesHibernate productServicesHibernateObj = (ProductServicesHibernate) receiveObject();
							
							new Thread(() -> {
								productServicesHibernateObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_PRODUCT_SERVICE")) {
							ProductServicesHibernate productServicesHibernateObj = (ProductServicesHibernate) receiveObject();
							
							new Thread(() -> {
								productServicesHibernateObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_PRODUCT_SERVICE")) {
							int id = (int) receiveObject();
							ProductServicesHibernate productServicesHibernateObj = new ProductServicesHibernate();
							productServicesHibernateObj.setId(id);
							
							new Thread(() -> {
								productServicesHibernateObj.delete();
							}).start();
						} 
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_REPORTED_ISSUES")) {
							new Thread(() -> {
								ReportIssueHibernate reportIssueHibernateObj = new ReportIssueHibernate();
								ArrayList<ReportIssueHibernate> reportedIssueList = new ArrayList<>();
								reportedIssueList = reportIssueHibernateObj.readAllForEveryOne();
								
								sendObject("READ_ALL_REPORTED_ISSUES");
								sendObject(reportedIssueList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_REPORTED_ISSUE")) {
							ReportIssueHibernate reportIssueHibernateObj = (ReportIssueHibernate) receiveObject();
							
							new Thread(() -> {
								reportIssueHibernateObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_REPORTED_ISSUE")) {
							ReportIssueHibernate reportIssueHibernateObj = (ReportIssueHibernate) receiveObject();
							
							new Thread(() -> {
								reportIssueHibernateObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_REPORTED_ISSUE")) {
							int id = (int) receiveObject();
							ReportIssueHibernate reportIssueHibernateObj = new ReportIssueHibernate();
							reportIssueHibernateObj.setId(id);
							
							new Thread(() -> {
								reportIssueHibernateObj.delete();
							}).start();
						} 
						
						
						
						
						
						
						else if(action.equalsIgnoreCase("READ_ALL_RESPONSE")) {
							new Thread(() -> {
								ReportResponseHibernate responseHibernateObj = new ReportResponseHibernate();
								ArrayList<ReportResponseHibernate> responseList = new ArrayList<>();
								responseList = responseHibernateObj.readAll();
								
								sendObject("READ_ALL_RESPONSE");
								sendObject(responseList);
							}).start();
						} else if(action.equalsIgnoreCase("INSERT_RESPONSE")) {
							ReportResponseHibernate responseHibernateObj = (ReportResponseHibernate) receiveObject();
							
							new Thread(() -> {
								responseHibernateObj.create();
							}).start();
						} else if(action.equalsIgnoreCase("UPDATE_RESPONSE")) {
							ReportResponseHibernate responseHibernateObj = (ReportResponseHibernate) receiveObject();
							
							new Thread(() -> {
								responseHibernateObj.update();
							}).start();
						} else if(action.equalsIgnoreCase("DELETE_RESPONSE")) {
							int id = (int) receiveObject();
							ReportResponseHibernate responseHibernateObj = new ReportResponseHibernate();
							responseHibernateObj.setId(id);
							
							new Thread(() -> {
								responseHibernateObj.delete();
							}).start();
						} 
						
						
						
						
						else if(action.equalsIgnoreCase("SEND_MESSAGE")) {
							String recipientUserId = (String) receiveObject();
							String message = (String) receiveObject();
							
							sendMessage(userid, recipientUserId, message);
						}
						
						
					} catch (ClassNotFoundException e) {
						e.printStackTrace();
						logger.warning("Class not found exception occured");
					} catch (IOException e) {
						// nothing is being sent to the readObject action from the client
					}
				}
			}
			
			// garbage collection
			if (clientSocket != null) {
				try {
					objOutputStream.close();
					objInputStream.close();
					clientSocket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					logger.warning("IO exception occured");
				}
			} 
			
			if (chatSocket != null) {
				try {
					outputStream.close();
					printWriter.close();
					chatSocket.close();
				} catch (IOException ex) {
					ex.printStackTrace();
					logger.warning("IO exception occured");
				}
			} 
		} 
	} 
}