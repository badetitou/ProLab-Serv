package com.tbe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Fonctionnality;
import com.tbe.json.Task;
import com.tbe.json.User;

public class TaskRequest {


	public static List<Task> getAllTask() {
		ArrayList<Task> tasks= new ArrayList<Task>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from tasks");
			while (rs.next()) {
				tasks.add(new Task(rs.getString("username"), rs.getInt("project"), rs.getInt("fonctionnality")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tasks;
	}

	public static int addTask(String username, int idProject, int idFonctionnality) {
		String sql = "insert into tasks (username, project, fonctionnality) values (?,?,?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setInt(2, idProject);
			stmt.setInt(3, idFonctionnality);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return -1;
		}
	}

	public static List<Fonctionnality> getProjectFonctionnality(int idProject) {
		List<Fonctionnality> fonctionnalities = new ArrayList<Fonctionnality>();
		String sql = "Select fonctionnalities.id, fonctionnalities.name, fonctionnalities.description, fonctionnalities.avancement, fonctionnalities.deadline " +
				"from tasks, fonctionnalities " +
				"where tasks.project=? and tasks.fonctionnality=fonctionnalities.id " +
				"group by fonctionnalities.id, fonctionnalities.name, fonctionnalities.description, fonctionnalities.avancement, fonctionnalities.deadline;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idProject);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				fonctionnalities.add(new Fonctionnality(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getInt("avancement"), rs.getDate("deadline")));
			}
			return fonctionnalities;
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("SQL:\n");
			System.err.println(sql);
			System.out.println("\n\n");
			return null;
		}
	}

	public static List<User> getUserForFonctionnality(int idFonctionnality) {
		List<User> users = new ArrayList<User>();
		String sql = "Select users.username, users.password, users.email, users.firstname, users.lastname from users, tasks where tasks.fonctionnality=? and users.username=tasks.username";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idFonctionnality);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				users.add(new User(rs.getString("username"), rs.getString("password"), rs.getString("email"), rs.getString("firstname"), rs.getString("lastname")));
			}
			return users;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return null;
		}
	}
}
