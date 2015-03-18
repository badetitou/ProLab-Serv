package com.tbe.database;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Fonctionnality;

public class FonctionnalitiesRequest {

	public static String addFonctionnality(String name,
			String description, int avancement, Date deadLine) {
		String sql = "Insert into fonctionnalities(name, description, avancement, deadLine) values (?,?,?,?);";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setString(1, name);
			stmt.setString(2, description);
			stmt.setInt(3, avancement);
			stmt.setDate(4, deadLine);
			stmt.executeUpdate(sql);
		} catch (SQLException e) {
			e.printStackTrace();
			System.err.println(sql);
			return null;
		}
		return "ok";
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
		Fonctionnality f = null;
		String sql = "Select * from fonctionnalities where id=?";
		try {
			PreparedStatement stmt = DataBase.getConnection().prepareStatement(sql);
			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return f;
	}
}
