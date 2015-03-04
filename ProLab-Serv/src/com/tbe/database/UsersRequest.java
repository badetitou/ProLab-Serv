package com.tbe.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRequest {
	
	public static String addUser(String username, String password,
			String firstName, String surName) {
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			stmt.executeUpdate("Insert into users values ('"+ username+"','"+ password+ "','"+firstName+"','" + surName+"');");
		} catch (SQLException e) {
			return null;
		}
		return "ok";
	}
	
	public static String getAllUsers(){
		String result = "";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from users");
			while (rs.next()){
				result += rs.getString("username") + " " + rs.getString("firstname") + " " + rs.getString("surname") + "\n";
			}
		} catch (SQLException e) {
			return null;
		}
		return result;
	}

	public static String getUser(String id) {
		String result = "";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from users where id=" + id);
			while (rs.next()){
				result += rs.getString("username") + " " + rs.getString("firstname") + " " + rs.getString("surname") + "\n";
			}
		} catch (SQLException e) {
			return null;
		}
		return result;
	}
}
