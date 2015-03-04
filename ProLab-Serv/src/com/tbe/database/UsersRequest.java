package com.tbe.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class UsersRequest {
	
	public static String addUser(String username, String password,
			String firstName, String surName) {
		String sql = "Insert into users(username, password, firstname,surname) values ('"+ username+"','"+ password+ "','"+firstName+"','" + surName+"');";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return null;
		}
		return "ok";
	}
	
	public static String getAllUsers(){
		String result = "id : username : firstname : surname\n";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from users");
			while (rs.next()){
				result += rs.getString("id") +" " +rs.getString("username") + " " + rs.getString("firstname") + " " + rs.getString("surname") + "\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}

	public static String getUser(String id) {
		String result = "";
		String sql = "Select * from users where id=" + id;
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				result += rs.getString("username") + " " + rs.getString("firstname") + " " + rs.getString("surname") + "\n";
			}
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return null;
		}
		return result;
	}
}
