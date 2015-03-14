package com.tbe.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.User;

public class UsersRequest {
	
	public static String addUser(String username, String password, String email,
			String firstName, String surName) {
		String sql = "Insert into users(username, password, email, firstname,surname) values ('"+ username+"','"+ password+ "','"+email+"','"+firstName+"','" + surName+"');";
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
	
	public static List<User> getAllUsers(){
		List<User> users = new ArrayList<User>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from users");
			while (rs.next()){
				users.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstname"), rs.getString("surname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		}
		return users;
	}

	public static User getUser(String username, String password) {
		User user = null;
		String sql = "Select * from users where username='" + username + "' and password='" + password + "';";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				user = new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstname"), rs.getString("surname"));
			}
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return user;
		}
		return user;
	}
}
