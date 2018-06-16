package dao;

import java.sql.Connection;
import java.sql.DriverManager;


public class DBConnect {

	
	public static Connection getConnection() {
		Connection conn = null;

		try {
			String url = "jdbc:mysql://127.0.0.1/minhchungdb";
			Class.forName ("com.mysql.jdbc.Driver").newInstance ();
			conn = DriverManager.getConnection (url, "rumitspkt", "123123");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return conn;
	}
}
