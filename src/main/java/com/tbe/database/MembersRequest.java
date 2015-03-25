
package com.tbe.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Member;
import com.tbe.json.Project;
import com.tbe.json.User;

public class MembersRequest {
	public static String addMember(String username, int idProject, int role) {
		System.out.println(username);
		String sql = "Insert into members(username, idProject, role) values ( ?, ?, ?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setInt(2, idProject);
			stmt.setInt(3, role);
			stmt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
			
			System.err.println(sql);
			return null;
		}
		return "ok";
	}
	
	public static Member getMemberFromIdMember(int idMember){
		String sql = "Select * from members where idMember=?";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idMember);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return new Member(rs.getInt("idMember"), rs
						.getInt("idProject"), rs.getString("username"), rs
						.getInt("role"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;

	}

	public static List<Member> getAllMembers() {
		List<Member> members = new ArrayList<Member>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from members");
			while (rs.next()) {
				members.add(new Member(rs.getInt("idMember"), rs.getInt("idProject"), rs.getString("username"), rs.getInt("role")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return members;
	}
	
	public static List<Project> getUserProject(String username){
		List<Project> lp = new ArrayList<Project>();
		String sql = "Select projects.id, projects.name, projects.description, projects.url, projects.punchline from members, projects where projects.id=members.idProject and members.username=?;";
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

	public static List<User> getProjectUser(int idProject) {
		List<User> lp = new ArrayList<User>();
		String sql = "Select users.username, users.password, users.email, users.firstname, users.lastname from members, users where users.username=members.username and members.idProject=?;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idProject);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()){
				lp.add(new User(rs.getString("username").toLowerCase(), rs.getString("password"), rs.getString("email"), rs.getString("firstname"), rs.getString("lastname")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return lp;
	}

	public static int removeMembers(int idMember) {
		String sql = "delete from members where idMember=?";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idMember);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			return 0;
		}
	}

	public static List<Member> getProjectMember(int idProject) {
		List<Member> members = new ArrayList<Member>();
		String sql = "select * from members where idProject=?";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idProject);
			ResultSet rs = stmt.executeQuery();
			while(rs.next()){
				members.add(new Member(rs.getInt("idMember"), rs.getInt("idProject"), rs.getString("username"), rs.getInt("role")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return members;
	}

	public static Member getMember(String username, int idProject) {
		String sql = "select * from members where username=? and idProject=?";
		try{
			PreparedStatement stmt =  DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, username);
			stmt.setInt(2, idProject);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()){
				return new Member(rs.getInt("idMember"), rs.getInt("idProject"), rs.getString("username"), rs.getInt("role"));
			}
		} catch (Exception e){
			
		}
		return null;
	}
}
