package com.tbe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Fonctionnality;
import com.tbe.json.Task;

public class TaskRequest {



	public static List<Task> getAllTask() {
		ArrayList<Task> tasks= new ArrayList<Task>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from tasks");
			while (rs.next()) {
				tasks.add(new Task(rs.getString("username"), rs.getInt("fonctionnality"), rs.getInt("project")));
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
		String sql = "Select * from tasks, fonctionnalities where tasks.project=? and tasks.fonctionnality=fonctionnalities.id";
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
			System.err.println(sql);
			return null;
		}
	}
}
