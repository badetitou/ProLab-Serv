package com.tbe.database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ProjectsRequest {
	
	/**
	pushline
	logo
	l'entreprise
	*/
	
	public static String addProject (String name, String description, String url){
		String sql = "Insert into projects(name, description, url) values ('"+ name+"','"+ description+ "','"+url+"');";
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
	
	public static String getAllProject(){
		String result = "idp : name : description : url\n";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery("Select * from users");
			while (rs.next()){
				result += rs.getString("idp") + " " + rs.getString("name") + " " + rs.getString("description") + " "+ rs.getString("url")+"\n";
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
		return result;
	}
	
	public static String getProject(String id){
		String result = "";
		String sql = "Select * from projects where idp='" +id + "';";
		try {
			Statement stmt = DataBase.getConnection().createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			while (rs.next()){
				result += rs.getString("idp") + " " + rs.getString("name") + " " + rs.getString("description") + " "+ rs.getString("url")+ "\n";
			}
		} catch (SQLException e) {
			System.err.println(sql);
			e.printStackTrace();
			return null;
		}
		if (result.equals("")){
			return null;		}
		return result;
	}

}
