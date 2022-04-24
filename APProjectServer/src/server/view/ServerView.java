package server.view;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;
import java.awt.Cursor;
import server.controller.ServerController;

public class ServerView extends JFrame implements ActionListener {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JButton btnStartServer;
	private JButton btnStopServer;
//	private int xBoundaryOffset;
//	private int yBoundaryOffset;
	private ServerController serverController;
	
	/**
	 * Create the frame.
	 */
	public ServerView(ServerController obj) {
		this.serverController = obj;	
		initializeComponents();
		addComponentsToWindow();
		setWindowProperties();
		registerActionListeners();
	}
	
	private void initializeComponents() {
		btnStartServer = new JButton("Start Server");
		btnStartServer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStartServer.setIcon(new ImageIcon(ServerView.class.getResource("/Images/icons8-play-20.png")));
		btnStartServer.setFocusable(false);
		btnStartServer.setBounds(96, 30, 160, 40);
		
		btnStopServer = new JButton("Stop Server");
		btnStopServer.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnStopServer.setIcon(new ImageIcon(ServerView.class.getResource("/Images/icons8-stop-20.png")));
		btnStopServer.setFocusable(false);
		btnStopServer.setBounds(96, 70, 160, 40);
		btnStopServer.setEnabled(false);
		
//		xBoundaryOffset = 5;
//		yBoundaryOffset = 50;
	}
	
	private void addComponentsToWindow() {
		getContentPane().add(btnStartServer);
		getContentPane().add(btnStopServer);
	}
	
	private void setWindowProperties() {
		getContentPane().setLayout(null);
		this.setTitle("Micro-Star Server");
		this.setResizable(false);
		this.setBounds(100, 100, 359, 185);
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		this.setLocation(5, 5);
//		setStartLocation();
		this.setVisible(true);
		this.setAlwaysOnTop(true);
	}
	
	private void registerActionListeners() {
		btnStartServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStartServer.setEnabled(false);
				btnStopServer.setEnabled(true);
				serverController.serverState(true);
			}
		});
		
		btnStopServer.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				btnStartServer.setEnabled(true);
				btnStopServer.setEnabled(false);
				serverController.serverState(false);
			}
		});
		
		this.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				shutdown();
			}
		});
	}
	
	private void shutdown() {
		int option = JOptionPane.showConfirmDialog(this, "Are you sure?", "Shutdown and Exit", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
		
		 if(option == JOptionPane.YES_OPTION){  
			 dispose();
			 System.exit(0);
		 }  
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
//	private void setStartLocation() {
//		this.pack();
//		GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//		GraphicsDevice defaultScreen = ge.getDefaultScreenDevice();
//		Rectangle rect = defaultScreen.getDefaultConfiguration().getBounds();
//		int x = (int) rect.getMaxX() - this.getWidth();
//		int y = (int) rect.getMaxY() - this.getHeight();
//		this.setLocation(x - xBoundaryOffset, y - yBoundaryOffset);
//	}
	
//	@Override // placeholder for actual content
//    public Dimension getPreferredSize() {
//        return new Dimension(359, 185);
//    }
}
