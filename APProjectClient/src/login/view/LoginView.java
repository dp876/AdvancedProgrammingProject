package login.view;

import javax.swing.JFrame;
import javax.swing.JTextField;

import org.jdesktop.xswingx.BuddySupport;
import org.jdesktop.xswingx.PromptSupport;

import login.controller.*;
import main.controller.MainController;
import net.java.balloontip.BalloonTip;
import net.java.balloontip.positioners.RightAbovePositioner;
import net.java.balloontip.styles.EdgedBalloonStyle;
import object.classes.Credential;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.border.LineBorder;
import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

/*
 * THis class holds the GUI for the login 
 */
public class LoginView extends JFrame implements ActionListener{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextField txtUserId;
	private JPasswordField txtPassword;
	private JLabel lblUserIcon;
	private JLabel lblPasswordIcon;
	private JButton btnLogin;
	private JButton btnBack;
	private JLabel lblNewLabel;
	private String userType;
	private ClientLoginController clientLoginController;
	private TechnicianLoginController technicianLoginController;
	private RepresentativeLoginController representativeLoginController;
	private BalloonTip balloonTip;

	/**
	 * Create the application.
	 */
	public LoginView() {
		setTitle("Login");
		initialize(" ");
		launch();
	}
	
	public LoginView(ClientLoginController obj) {
		clientLoginController = obj;
		setTitle("Client Login");
		initialize("CLIENT");
		launch();
	}
	
	public LoginView(RepresentativeLoginController obj) {
		representativeLoginController = obj;
		setTitle("Representative Login");
		initialize("REPRESENTATIVE");
		launch();
	}
	
	public LoginView(TechnicianLoginController obj) {
		technicianLoginController = obj;
		setTitle("Technician Login");
		initialize("TECHNICIAN");
		launch();
	}
	
	private void launch() {
		addComponentsToWindow();
		setWindowProperties();
		registerListeners();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(String userType) {
		this.userType = userType;
		
		txtUserId = new JTextField();
		txtUserId.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtUserId.setBounds(216, 104, 300, 35);
		
		lblUserIcon = new JLabel("");
		lblUserIcon.setFocusable(false);
		lblUserIcon.setIcon(new ImageIcon(LoginView.class.getResource("/Images/icons8-user-25.png")));
		lblUserIcon.setBounds(216, 196, 35, 35);
		
		txtPassword = new JPasswordField();
		txtPassword.setFont(new Font("Tahoma", Font.PLAIN, 15));
		txtPassword.setBounds(216, 150, 300, 35);
		
		lblPasswordIcon = new JLabel("");
		lblPasswordIcon.setFocusable(false);
		lblPasswordIcon.setIcon(new ImageIcon(LoginView.class.getResource("/Images/icons8-lock-25.png")));
		lblPasswordIcon.setBounds(261, 196, 35, 35);
		
		
		btnLogin = new JButton("Login");
		btnLogin.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnLogin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnLogin.setFocusable(false);
		btnLogin.setBounds(376, 196, 140, 35);
		
		btnBack = new JButton("Back");
		btnBack.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnBack.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnBack.setFocusable(false);
		btnBack.setBounds(216, 196, 140, 35);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setBorder(new LineBorder(new Color(0, 0, 0)));
		lblNewLabel.setFocusable(false);
		lblNewLabel.setIcon(new ImageIcon(LoginView.class.getResource("/Images/micro-star cablevision logo - 127.png")));
		lblNewLabel.setBounds(75, 104, 127, 127);
	}
	
	private void addComponentsToWindow() {
		this.getContentPane().add(txtUserId);
		this.getContentPane().add(txtPassword);
		this.getContentPane().add(btnLogin);
		this.getContentPane().add(btnBack);
		this.getContentPane().add(lblNewLabel);
		this.getContentPane().add(lblUserIcon);
		this.getContentPane().add(lblPasswordIcon);
		
		BuddySupport.addLeft(lblUserIcon, txtUserId);
		PromptSupport.setPrompt("UserID", txtUserId);
		
		BuddySupport.addLeft(lblPasswordIcon, txtPassword);
		PromptSupport.setPrompt("Password", txtPassword);
	}
	
	private void setWindowProperties() {
		this.setResizable(false);
		this.setBounds(100, 100, 600, 400);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.getContentPane().setLayout(null);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
	
	private void registerListeners() {
		// switch focus to password field if enter was pressed in the user id field
		txtUserId.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					txtPassword.requestFocus();
				}
			}
		});
		
		// call login function if enter was pressed while in the password field
		txtPassword.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					loginToDashboard();
				}
			}
		});
		
		// call login function if login button was pressed
		btnLogin.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				loginToDashboard();
			}
		});
		
		btnBack.addActionListener(this);
	}
	
	
	private void loginToDashboard() {
		// displays balloon tip to indicate that credentials are required
		if (txtUserId.getText().isBlank() || String.valueOf(txtPassword.getPassword()).isBlank()) {
			if (txtUserId.getText().isBlank()) {
				balloonTip = new BalloonTip(txtUserId, "Required", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
				balloonTip.setPositioner(new RightAbovePositioner(35, 5));
			}
			
			if (String.valueOf(txtPassword.getPassword()).isBlank()) {
				balloonTip = new BalloonTip(txtPassword, "Required", new EdgedBalloonStyle(Color.WHITE, Color.BLUE), true);
				balloonTip.setPositioner(new RightAbovePositioner(35, 5));
			}
			return; // return if credential fields are empty
		}
		
		// login to associated dash board if credential is valid for each user
		boolean isValid = false;
		Credential credObj = new Credential(txtUserId.getText(), String.valueOf(txtPassword.getPassword()), userType);
		
		if (userType.equalsIgnoreCase("CLIENT")) {
			isValid = clientLoginController.verify(credObj);
			
			if (isValid) {
				clientLoginController.switchToDashboard(credObj);
			}
		} else if (userType.equalsIgnoreCase("REPRESENTATIVE")) {
			isValid = representativeLoginController.verify(credObj);
			
			if (isValid) {
				representativeLoginController.switchToDashboard(credObj);
			}
		} else if (userType.equalsIgnoreCase("TECHNICIAN")) {
			isValid = technicianLoginController.verify(credObj);
			
			if (isValid) {
				technicianLoginController.switchToDashboard(credObj);
			}
		}
		
		// message dialog for invalid credentials
		if (!isValid) {
			JOptionPane.showMessageDialog(null, "Invalid UserID or Password", "InValid Credential",
					JOptionPane.INFORMATION_MESSAGE);
		} 
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource()==btnBack) {
			this.dispose();
			MainController.main(null);
		}
	}
}
