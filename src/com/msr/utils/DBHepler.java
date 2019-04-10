package com.msr.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBHepler {
	static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost:3306/tiantian?useSSL=false&characterEncoding=utf8";
	static final String USER = "root";
	static final String PASS = "403094845";

	public static Connection getConn() {
		try {
			Class.forName(JDBC_DRIVER);
			Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
			return conn;
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public static void getClose(Connection c, Statement stmt, ResultSet rs) {
		try {
			if (stmt != null)
				stmt.close();
			if (rs != null)
				rs.close();
			if (c != null)
				c.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
