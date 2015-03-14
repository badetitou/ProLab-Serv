package com.tbe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Member;
import com.tbe.json.Project;

public class MembersRequest {
	public static String addMember(String username, String id) {
		String sql = "Insert into members(username, id) values ( ?, ?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setInt(2, Integer.parseInt(id));
			stmt.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return null;
		}
		return "ok";
	}

	public static List<Member> getAllMembers() {
		List<Member> members = new ArrayList<Member>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from members");
			while (rs.next()) {
				members.add(new Member(rs.getInt("id"), rs
						.getString("username")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return members;
	}
	
	public static List<Project> getUserProject(String username){
		List<Project> lp = new ArrayList<Project>();
		String sql = "Select projects.id, projects.name, projects.description, projects.url, projects.punchline from members, projects where projects.id=members.id and members.username=?;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				lp.add(new Project(rs.getInt("id"), rs.getString("name"), rs.getString("description"), rs.getString("url"), rs.getString("punchline")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return lp;
	}

	public static Member getMember(String id, String username) {
		Member m = null;
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from members where id="
					+ id + " and username='" + username + "';");
			m = new Member(rs.getInt("id"), rs.getString("username"));

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return m;
	}
}
