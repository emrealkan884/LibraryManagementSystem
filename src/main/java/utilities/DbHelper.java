package utilities;

import java.sql.Connection;
import java.sql.DriverManager;

public class DbHelper {
	static String userName="UserLibrary";
	static String password="Emre.1439";
	static String dbUrl="jdbc:oracle:thin:@localhost:1521:stajdb";
	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(dbUrl,userName,password);
		return con;
	}
}
