package login.controller;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import login.model.LoginModel;
import login.view.LoginView;
import object.classes.Credential;

public class ClientLoginController {
	private LoginModel loginModel;
	private LoginView loginView;
	
	private final static Logger logger = Logger.getLogger(ClientLoginController.class.getName());
	
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
	
	public ClientLoginController() {
		loginModel = new LoginModel();
		loginView = new LoginView(this);
	}
	
	public boolean verify(Credential credObj) {
		return loginModel.verifyCredential(credObj);
	}
	
	public void switchToDashboard(Credential credObj) {
		loginModel.switchToDashboard(credObj);
		loginView.dispose();
	}

	public static void main(String[] args) {
		setupLogger();
		try {
            UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
//            UIManager.setLookAndFeel("com.jtattoo.plaf.aluminium.AluminiumLookAndFeel");
            new ClientLoginController();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			logger.warning("Class not found exception");
		} catch (InstantiationException e) {
			e.printStackTrace();
			logger.warning("Instantiation exception occured");
		} catch (IllegalAccessException e) {
			e.printStackTrace();
			logger.warning("Illegal Access exception occured");
		} catch (UnsupportedLookAndFeelException e) {
			e.printStackTrace();
			logger.warning("Unsupported look and feel exception occured");
		} catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("An exception occured");
        }
	}

}
