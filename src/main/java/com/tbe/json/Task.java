package com.tbe.json;

public class Task {
	private String username;
	private int idProject;
	private int idFonctionnality;
	
	public Task(){}

	public Task(String username, int idProject, int idFonctionnality) {
		this.username = username;
		this.idProject = idProject;
		this.idFonctionnality = idFonctionnality;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public int getIdFonctionnality() {
		return idFonctionnality;
	}

	public void setIdFonctionnality(int idFonctionnality) {
		this.idFonctionnality = idFonctionnality;
	};
	
}
