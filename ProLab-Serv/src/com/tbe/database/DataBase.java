package com.tbe.database;

import java.sql.*;

public class DataBase {

	private Connection c;
	
	public DataBase() {
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:prolab.db");
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		this.c = c;
		System.out.println("Opened database successfully");
	}
}