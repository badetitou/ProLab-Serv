package com.tbe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.xml.crypto.Data;

import com.tbe.json.Project;

public class ProjectsRequest {

	/**
	 * pushline logo l'entreprise
	 */

	public static int addProject(String name, String description, String url,
			String punchline) {
		String sql = "Insert into projects(name, description, url, punchline) values (?,?,?,?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setString(3, url);
			stmt.setString(4, punchline);
			stmt.execute();
			stmt.close();
			Statement stmt2 = DataBase.getConnection().createStatement();
			ResultSet rs = stmt2.executeQuery("select last_insert_rowid();");
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return -1;
		}
	}

	public static List<Project> getAllProject() {
		List<Project> projects = new ArrayList<Project>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from projects");
			while (rs.next()) {
				projects.add(new Project(rs.getInt("id"), rs.getString("name"),
						rs.getString("description"), rs.getString("url"), rs
								.getString("punchline")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return projects;
	}

	public static Project getProject(int id) {
		Project project = null;
		String sql = "Select * from projects where id=?;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			project = new Project(rs.getInt("id"), rs.getString("name"),
					rs.getString("description"), rs.getString("url"),
					rs.getString("punchline"));
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return project;
		}
		return project;
	}

	public static int getId (String url) {
		Project project = null;
		String sql = "Select * from projects where url=?;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setString(1, url);
			ResultSet rs = stmt.executeQuery();
			project = new Project(rs.getInt("id"), rs.getString("name"),
					rs.getString("description"), rs.getString("url"),
					rs.getString("punchline"));
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return project.getId();
		}
		return project.getId();
	}

	public static int update(int id, String name, String punchline,String description, String url) {
		String sql = "update projects set name=?, punchline=?,description=?,url=? where id=?;";
		try{
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, punchline);
			stmt.setString(3, description);
			stmt.setString(4, url);
			stmt.setInt(5, id);
			return stmt.executeUpdate();
		} catch (Exception e){
			e.printStackTrace();
			return 0;
		}
	}

	public static int delete(int idProject) {
		String sql = "delete from projects where id=?";
		try{
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idProject);
			return stmt.executeUpdate();
		} catch (Exception e){
			return 0;
		}
	}

}
