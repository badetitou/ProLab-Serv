package com.tbe.database;

import java.sql.*;

public class DataBase {

	private static Connection c;
	private static final Integer version = 1;

	private static String strCreateUserTable = "Create table if not exists users (username char(20) primary key, password char(20), email text, firstname char(20), surname char(20));";

	private static String strCreateProjectTable="Create table if not exists projects (id integer primary key autoincrement, name char(20), description text, url char(20) unique not null, punchline char(50));";
	
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
		Statement stmt;
		//User Table
		try {
			stmt = DataBase.c.createStatement();
			stmt.executeUpdate(strCreateUserTable);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem creation user table : " + strCreateUserTable);
		}
		//Projects Table
		try {
			stmt = DataBase.c.createStatement();
			stmt.executeUpdate(strCreateProjectTable);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Problem creation projects table : " + strCreateUserTable);
		}
		
		System.out.println("Init Table Done");
	}

	public static Connection getConnection() {
		return c;
	}
}