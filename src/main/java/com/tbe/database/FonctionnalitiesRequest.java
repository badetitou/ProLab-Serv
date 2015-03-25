package com.tbe.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Fonctionnality;
import com.tbe.json.Member;

/**
 * @author badetitou
 */
public class FonctionnalitiesRequest {

	public static int addFonctionnality(String name, String description,
			int avancement, Date deadLine) {
		String sql = "Insert into fonctionnalities(name, description, avancement, deadLine) values (?,?,?,?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setInt(3, avancement);
			stmt.setDate(4, deadLine);
			stmt.executeUpdate();
			stmt.close();
			Statement stmt2 = DataBase.getConnection().createStatement();
			ResultSet rs = stmt2
					.executeQuery("select max(id) from fonctionnalities;");
			return rs.getInt(1);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return -1;
		}
	}

	public static List<Fonctionnality> getAllfonctionnalities() {
		List<Fonctionnality> fonctionnalities = new ArrayList<Fonctionnality>();
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from fonctionnalities");
			while (rs.next()) {
				fonctionnalities.add(new Fonctionnality(rs.getInt("id"), rs
						.getString("name"), rs.getString("description"), rs
						.getInt("avancement"), rs.getDate("deadLine")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return fonctionnalities;
	}

	public static Fonctionnality getFonctionnality(int id) {
		String sql = "Select * from fonctionnalities where id=?";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			return new Fonctionnality(rs.getInt("id"), rs.getString("name"),
					rs.getString("description"), rs.getInt("avancement"),
					rs.getDate("deadline"));
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

	public static List<Member> getFonctionnalityMember(int idFonctionnality) {
		List<Member> members = new ArrayList<Member>();
		String sql = "Select members.idMember, members.idProject, members.username, members.role "
				+ "from members, tasks "
				+ "where tasks.fonctionnality=? and members.idMember=tasks.idMember;";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, idFonctionnality);
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				members.add(new Member(rs.getInt("idMember"), rs
						.getInt("idProject"), rs.getString("username"), rs
						.getInt("role")));
			}
			return members;
		} catch (Exception e) {
			return null;
		}
	}

	public static int update(Fonctionnality fonctionnality) {
		String sql = "update fonctionnalities set avancement=?,description=?,deadline=?,name=? where id=? ";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(
					sql);
			stmt.setInt(1, fonctionnality.getAvancement());
			stmt.setString(2, fonctionnality.getDescription());
			stmt.setDate(3, fonctionnality.getDeadLine());
			stmt.setString(4, fonctionnality.getName());
			stmt.setInt(5, fonctionnality.getId());
			return stmt.executeUpdate();
		} catch (Exception e) {
			return 0;
		}
	}

	public static int delete(int idFonctionnality) {
		String sql = "Delete from fonctionnalities where id=?";	
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, idFonctionnality);
			return stmt.executeUpdate();
		} catch (Exception e) {
			return 0;
		}
	}
}
