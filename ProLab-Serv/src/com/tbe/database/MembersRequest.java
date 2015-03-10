package com.tbe.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Member;
import com.tbe.json.Project;

public class MembersRequest {
	public static String addMember(String username, String id) {
		String sql = "Insert into projects(username, id) values ('" + username
				+ "'," + id + ");";
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
