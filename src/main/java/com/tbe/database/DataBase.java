package com.tbe.database;

import java.sql.*;

public class DataBase {

	private static Connection c;
	private static final int version = 1;
	/* STATICS */
	private static final int MAX_USERNAME_SIZE = 20;

	
	
	private static String strCreateUserTable = "Create table if not exists users ("
			+ "username char("
			+ MAX_USERNAME_SIZE
			+ ") primary key, "
			+ "password char(20), "
			+ "email text unique, "
			+ "firstname char(20), "
			+ "surname char(20));";

	private static String strCreateProjectTable = "Create table if not exists projects ("
			+ "id Integer primary key autoincrement, "
			+ "name char(20), "
			+ "description text, "
			+ "url char(20) unique not null, " 
			+ "punchline char(50));";

	private static String strCreateMembersTable = "Create table if not exists members ("
			+ "username char("
			+ MAX_USERNAME_SIZE
			+ "), "
			+ "id int,"
			+ "foreign key (username) references users(username)," +
			" foreign key (id) references projects(id))";
	
	private static String strCreateFonctionnalitiesTable = "Create table if not exists fonctionnalities ("
			+ "id Integer primary key autoincrement, "
			+ "name char("
			+ MAX_USERNAME_SIZE
			+ "), "
			+ "description text, "
			+ "avancement int, "
			+ "deadline date, "
			+ "foreign key (name) references users(username))";
	
	private static String strCreateNewsTable = "Create table if not exists news ("
			+ "id Integer primary key autoincrement, "
			+ "title char("
			+ MAX_USERNAME_SIZE
			+ "), "
			+ "description text, "
			+ "date date, "
			+ "author text"
			+ "foreign key (name) references users(username))";
	
	private static String strCreateTask = "Create table if not exists tasks ("
			+ "fonctionnality Integer not null,"
			+ "username char("
			+MAX_USERNAME_SIZE
			+ "), "
			+"project Integer not null," +
			"foreign key (fonctionnality) references fonctionnalities(id)," +
			"foreign key (username) references users(username));";

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

	private void createTable() throws SQLException {
		System.out.println("Init Table");
		Statement stmt;
		// User Table
		
		stmt = DataBase.c.createStatement();
		stmt.executeUpdate(strCreateUserTable);
		stmt.executeUpdate(strCreateProjectTable);
		stmt.executeUpdate(strCreateMembersTable);
		stmt.executeUpdate(strCreateFonctionnalitiesTable);
		stmt.executeUpdate(strCreateTask);
		stmt.executeUpdate(strCreateNewsTable);
		System.out.println("Init Table Done");
	}

	public static Connection getConnection() {
		return c;
	}
}