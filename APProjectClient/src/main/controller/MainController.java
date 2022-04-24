package main.controller;


import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;

import javax.swing.UIManager;

import main.model.MainModel;
import main.view.MainView;

public class MainController {
	private MainModel mm;
	private MainView mv;
	
	private final static Logger logger = Logger.getLogger(MainController.class.getName());
	
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
	
	public MainController() {
		this.mm =new MainModel();
		this.mv= new MainView(this);
	}
	
	public MainController(MainModel mm, MainView mv) {
		this.mm = mm;
		this.mv = mv;
	}
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		setupLogger();
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.graphite.GraphiteLookAndFeel");
            new MainController();
		}
        catch (Exception ex) {
            ex.printStackTrace();
            logger.warning("An exception occured");
        }
	}

}
