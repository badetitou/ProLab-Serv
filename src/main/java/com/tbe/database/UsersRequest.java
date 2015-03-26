package com.tbe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.User;

public class UsersRequest {

	public static String addUser(String username, String password,
			String email, String firstName, String lastname) {
		String sql = "Insert into users(username, password, email, firstname,lastname) values (?,?,?,?,?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			stmt.setString(3, email);
			stmt.setString(4, firstName);
			stmt.setString(5, lastname);

			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return null;
		}
		return "ok";
	}

	public static List<User> getAllUsers() {
		List<User> users = new ArrayList<User>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from users");
			while (rs.next()) {
				users.add(new User(rs.getString("username"), rs
						.getString("password"), rs.getString("email"), rs
						.getString("firstname"), rs.getString("lastname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return users;
		}
		return users;
	}

	public static User getUser(String username, String password) {
		User user = null;
		String sql = "Select * from users where username=? and password=?;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				user = new User(rs.getString("username"),
						rs.getString("password"), rs.getString("email"),
						rs.getString("firstname"), rs.getString("lastname"));
			}
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return user;
		}
		return user;
	}

	public static User getUser(String username) {
		User user = null;
		String sql = "Select * from users where username=?;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			return new User(rs.getString("username"), rs.getString("password") ,rs.getString("email"), rs.getString("firstname"),rs.getString("lastname"));
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return user;
		}
	}

	public static int update(User user, String username) {
		String sql = "update users set username=?, email=?,password=?,firstname=?, lastname=? where id=?;";
		try{
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setString(2, user.getEmail());
			stmt.setString(3, user.getPassword());
			stmt.setString(4, user.getFirstname());
			stmt.setString(5, user.getLastname());
			stmt.setString(6, user.getUsername());
			return stmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public static int delete(String username) {
		String sql = "delete from users where username=?";
		try{
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			return stmt.executeUpdate();
		} catch (Exception e){
			return 0;
		}
	}
}
