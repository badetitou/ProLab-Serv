package com.tbe.database;

import java.sql.*;

public class DataBase {

	private static Connection c;
	private static final Integer version = 1;

	private static String strCreateUserTable = "Create table users (username char(20), password char(20), firstname char(20), surname char(20));";

	public DataBase() {
		System.out.println("Init BDD...");
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:prolab.db");
			DataBase.c = c;
			createTable();
		} catch (Exception e) {
			System.err.println(e.getClass().getName() + ": " + e.getMessage());
			System.exit(0);
		}
		System.out.println("Opened database successfully");
	}

	private void createTable() {
		System.out.println("Init Table");
		try {
			Statement stmt;
			stmt = DataBase.c.createStatement();
			stmt.executeUpdate(strCreateUserTable);
		} catch (Exception e) {
			System.out.println("Table user already exist... No problem");
		}
		System.out.println("Init Table Done");
	}

	public static Connection getConnection() {
		return c;
	}
}