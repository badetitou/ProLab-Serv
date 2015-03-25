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
		ArrayList<Task> tasks = new ArrayList<Task>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from tasks");
			while (rs.next()) {
				tasks.add(new Task(rs.getInt("idMember"), rs
						.getInt("fonctionnality")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return tasks;
	}

	public static int addTask(int idMember, int idFonctionnality) {
		String sql = "insert into tasks (idMember, fonctionnality) values (?,?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, idMember);
			stmt.setInt(2, idFonctionnality);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return -1;
		}
	}

	public static List<Fonctionnality> getFonctionnalityFromIdProject(
			int idProject) {
		List<Fonctionnality> fonctionnalities = new ArrayList<Fonctionnality>();
		String sql = "select fonctionnalities.id, fonctionnalities.name, fonctionnalities.description, fonctionnalities.avancement, fonctionnalities.deadline "
				+ "from fonctionnalities, tasks, members "
				+ "where tasks.fonctionnality=fonctionnalities.id and tasks.idMember=members.idMember and members.idProject=? "
				+ "group by fonctionnalities.id, fonctionnalities.name, fonctionnalities.description, fonctionnalities.avancement, fonctionnalities.deadline;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, idProject);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				fonctionnalities.add(new Fonctionnality(rs.getInt("id"), rs
						.getString("name"), rs.getString("description"), rs
						.getInt("avancement"), rs.getDate("deadline")));
			}
			return fonctionnalities;
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
		}
		return null;
	}

	public static int delete(int idFonctionnality, int idMember) {
		String sql = "delete from tasks where idMember=? and fonctionnality=?";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, idMember);
			stmt.setInt(2, idFonctionnality);
			return stmt.executeUpdate();
		} catch (Exception e) {
			return 0;
		}
	}
}
