package com.tbe.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * 
 * @author badetitou
 *
 */
public class DataBase {

	private static Connection c;
	/* STATICS */
	private static final int MAX_USERNAME_SIZE = 20;

	private static String strCreateUserTable = "Create table if not exists users ("
			+ "username char("+ MAX_USERNAME_SIZE+ ") primary key, "
			+ "password char(20), "
			+ "email text unique, "
			+ "firstname char(20), "
			+ "lastname char(20));";

	private static String strCreateProjectTable = "Create table if not exists projects ("
			+ "id Integer primary key autoincrement, "
			+ "name char(20), "
			+ "description text, "
			+ "url char(20) unique not null, "
			+ "punchline char(50));";
	
	private static String strCreateIndex = "Create unique index if not exists duplicata on members (username collate nocase ,idProject collate nocase);";


	private static String strCreateMembersTable = 
			  "Create table if not exists members ("
			+ "idMember integer primary key autoincrement, "
			+ "username char("+ MAX_USERNAME_SIZE+ "), "
			+ "idProject integer,"
			+ "role integer, "
			+ "foreign key (username) references users(username) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "foreign key (idProject) references projects(id) ON DELETE CASCADE ON UPDATE CASCADE)";

	private static String strCreateFonctionnalitiesTable = "Create table if not exists fonctionnalities ("
			+ "id Integer primary key autoincrement, "
			+ "name char("+ MAX_USERNAME_SIZE+ "), "
			+ "description text, "
			+ "avancement int, "
			+ "deadline date, "
			+ "foreign key (name) references users(username) ON DELETE CASCADE ON UPDATE CASCADE)";

	private static String strCreateNewsTable = "Create table if not exists news ("
			+ "id Integer primary key autoincrement, "
			+ "title char("+ 20+ "), "
			+ "description text, "
			+ "date date, "
			+ "author char("+ MAX_USERNAME_SIZE+ "),"
			+ "foreign key (author) references users(username) ON DELETE CASCADE ON UPDATE CASCADE)";

	private static String strCreateTask = "Create table if not exists tasks ("
			+ "fonctionnality Integer not null," + 
			 " idMember Integer not null,"
			+ "foreign key (fonctionnality) references fonctionnalities(id) ON DELETE CASCADE ON UPDATE CASCADE,"
			+ "primary key(fonctionnality, idMember));";

	public DataBase() {
		System.out.println("Init BDD...");
		Connection c = null;
		try {
			Class.forName("org.sqlite.JDBC");
			c = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("java.io.tmpdir")
							+System.getProperty("file.separator")+"prolab.db");
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

		stmt = DataBase.c.createStatement();

		stmt.executeUpdate(strCreateUserTable);
		stmt.executeUpdate(strCreateProjectTable);
		stmt.executeUpdate(strCreateMembersTable);
		stmt.executeUpdate(strCreateFonctionnalitiesTable);
		stmt.executeUpdate(strCreateTask);
		stmt.executeUpdate(strCreateNewsTable);
		stmt.executeUpdate(strCreateIndex);
		System.out.println("Init Table Done");
	}

	public static Connection getConnection() {
		return c;
	}
}
