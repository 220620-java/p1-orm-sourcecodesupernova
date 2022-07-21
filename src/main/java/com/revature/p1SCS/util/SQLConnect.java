package com.revature.p1SCS.util;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQLConnect {
	private static SQLConnect sqlConnect;

	private SQLConnect() {

	}

	public static synchronized SQLConnect getSQLConnect() {
		if (sqlConnect == null) {
			sqlConnect = new SQLConnect();
		}
		return sqlConnect;
	}

	public Connection getConnection() {
		/*Local Variables*/
		Connection conn = null;
		String url, username, password;
		
		/*Function*/
		try {
			Class.forName("org.postgresql.Driver");
			url = System.getenv("REV_P1_DBURL");
			username = System.getenv("REV_P1_DBUN");
			password = System.getenv("REV_P1_DBPW");
			conn = DriverManager.getConnection(url, username, password);
		}
		catch (Exception e) {
			//TODO Exception Logger
		}
		return conn;
	}
}
