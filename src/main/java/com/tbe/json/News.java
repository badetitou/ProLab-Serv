package com.tbe.json;

import java.sql.Date;

public class News {

	private int id;
	private String title;
	private String description;
	private Date date;
	private String author;
	
	public News(int id, String title, String description, Date date, String author){
		this.id = id;
		this.title = title;
		this.description = description;
		this.date = date;
		this.author = author;
	}

	
	public News(){}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getTitle() {
		return title;
	}


	public void setTitle(String title) {
		this.title = title;
	}


	public String getDescription() {
		return description;
	}


	public void setDescription(String description) {
		this.description = description;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public String getAuthor() {
		return author;
	}


	public void setAuthor(String author) {
		this.author = author;
	}
}