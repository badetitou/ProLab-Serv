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
				tasks.add(new Task(rs.getInt("idMember"), rs.getInt("fonctionnality")));
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
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idMember);
			stmt.setInt(2, idFonctionnality);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return -1;
		}
	}
}
