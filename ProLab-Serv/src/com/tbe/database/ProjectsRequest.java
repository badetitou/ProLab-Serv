package com.tbe.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Project;

public class ProjectsRequest {
	
	/**
	pushline
	logo
	l'entreprise
	*/
	
	public static String addProject (String name, String description, String url, String punchline){
		String sql = "Insert into projects(name, description, url, punchline) values ('"+ name+"','"+ description+ "','"+url+"','" +punchline + "');";
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
	
	public static List<Project> getAllProject(){
		List<Project> projects = new ArrayList<Project>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from projects");
			while (rs.next()){
				projects.add(new Project(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("url"), rs.getString("punchline")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return projects;
	}
	
	public static Project getProject(String id){
		Project project = null;
		String sql = "Select * from projects where id='" +id + "';";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			project = new Project(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("url"), rs.getString("punchline"));
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return project;
		}
		return project;
	}

}
