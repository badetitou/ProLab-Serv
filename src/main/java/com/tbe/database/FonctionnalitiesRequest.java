package com.tbe.database;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.tbe.json.Fonctionnality;

public class FonctionnalitiesRequest {

	public static String addFonctionnality(String id, String name,
			String description, String avancement, Date deadLine) {
		String sql = "Insert into fonctionnalities(id, name, description, avancement, deadLine) values ("
				+ id
				+ ",'"
				+ name
				+ "','"
				+ description
				+ "','"
				+ avancement
				+ "',"
				+ deadLine
				+ ");";
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

	public static Fonctionnality getFonctionnality(String id) {
		Fonctionnality f = null;
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt
					.executeQuery("Select * from fonctionnalities where id="
							+ id);

		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return f;
	}
}
