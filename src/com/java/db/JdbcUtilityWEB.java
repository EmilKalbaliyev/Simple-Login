package com.java.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtilityWEB {
	private static Connection con;
	private static Properties props;

	public static Connection getConn() throws ClassNotFoundException,
			SQLException {

		// Check if the props is successfully retreived from DatabaseInitializer if null then db sucks
		if (props == null){
			System.out.println("Error: DB properties were not read successfully.");
			return null;
		}

		// Step 1: Register Driver (not needed in JDBC 4.0 (Java SE 6)
		Class.forName(props.getProperty("DB_DRIVER_CLASS"));

		// Step 2: Establish Connection with DB:
		if (con == null) {
			con = DriverManager.getConnection(props.getProperty("DB_URL"),
					props.getProperty("DB_USERNAME"),
					props.getProperty("DB_PASSWORD"));
		}

		return con;
	}

	public static void releaseConn() throws SQLException {
		if (con != null) {
			con.close();
			con = null;
		}
	}

	public static void setProps(Properties p) {
		props = p; //this method will be called only once
	}
}
