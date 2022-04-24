package factories;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectorFactory {
	
	private static Connection conn = null;
	static String url = "jdbc:mysql://localhost:3306/approject";
	
	
	public static Connection getDatabaseConnection() {
		
		if (conn == null) {
			try {
				conn = DriverManager.getConnection(url, "root", "");
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return conn;
	}

}
