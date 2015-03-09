package com.tbe.json;

public class Project {
	
	private Integer id;
	private String name;
	private String description;
	private String url;
	private String punchline;
	
	public Project(Integer id, String name, String description, String url, String punchline){
		this.id = id;
		this.name = name;
		this.description = description;
		this.url = url;
		this.punchline = punchline;
	}
	
	public Integer getId() {
		return id;
	}

	public String getPunchline() {
		return punchline;
	}

	public void setPunchline(String punchline) {
		this.punchline = punchline;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	//Must have empty contructor
	public Project (){}
}
