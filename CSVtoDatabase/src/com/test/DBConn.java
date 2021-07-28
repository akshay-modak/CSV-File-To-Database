package com.test;

import java.sql.Connection;
import java.sql.DriverManager;
//Database Connection Class
public class DBConn {
	
	private static Connection con;
	private DBConn() {}

	public static Connection getConn() {
		try {
			Class.forName("com.mysql.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/akshay","root","root");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return con;
		}
	}
