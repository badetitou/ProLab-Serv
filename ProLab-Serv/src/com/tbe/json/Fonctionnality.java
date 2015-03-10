package com.tbe.json;

import java.util.Date;

public class Fonctionnality {
	
	private Integer id;
	private String name;
	private String description;
	private Integer avancement;
	private Date deadLine;
	
	public Fonctionnality(Integer id, String name, String description, Integer avancement, Date deadLine){
		this.id = id;
		this.name = name;
		this.description = description;
		this.avancement = avancement;
		this.deadLine = deadLine;
	}

	public Date getDeadLine() {
		return deadLine;
	}

	public void setDeadLine(Date deadLine) {
		this.deadLine = deadLine;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getAvancement() {
		return avancement;
	}

	public void setAvancement(Integer avancement) {
		this.avancement = avancement;
	}

	public Fonctionnality(){}
	
}