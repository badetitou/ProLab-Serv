package com.tbe.database;



	import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.tbe.json.News;
import com.tbe.json.Project;
		
	public class NewsRequest {
		/**
		pushline
		logo
		l'entreprise
		*/
		
		public static String addNews (String title, String description, Date date, String author){
			String sql = "Insert into News(name, description, url, punchline) values ('"+ title+"','"+ description+ "','"+author+"'," +date + ");";
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
		
		public static List<News> getAllNews(){
			List<News> News = new ArrayList<News>();
			try {
				Statement stmt = DataBase.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery("Select * from News");
				while (rs.next()){
					News.add(new News(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getDate("date"), rs.getString("author")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
			return News;
		}
		
		public static News getNew(String id){
			News news = null;
			String sql = "Select * from projects where id='" +id + "';";
			try {
				Statement stmt = DataBase.getConnection().createStatement();
				ResultSet rs = stmt.executeQuery(sql);
				news = new News(rs.getInt("id"), rs.getString("title"), rs.getString("description"), rs.getDate("date"), rs.getString("author"));
			} catch (SQLException e) {
				System.err.println(sql);
				e.printStackTrace();
				return news;
			}
			return news;
		}

	}

