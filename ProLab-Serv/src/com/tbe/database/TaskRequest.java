package com.tbe.database;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Task;

public class TaskRequest {



	public static List<Task> getAllTask() {
		return new ArrayList<Task>();
	}

	public static String addProject(String username, String project,
			String fonctionnality) {
		String sql = "insert into tasks (username, fonctionnality, project) values ('" +
				username + "'," +
				fonctionnality + "," +
				project + ");";
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
}
