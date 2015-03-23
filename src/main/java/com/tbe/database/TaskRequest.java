package com.tbe.database;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Task;

public class TaskRequest {



	public static List<Task> getAllTask() {
		return new ArrayList<Task>();
	}

	public static int addTask(String username, int idProject, int idFonctionnality) {
		String sql = "insert into tasks (username, fonctionnality, project) values (?,?,?);";
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
}
