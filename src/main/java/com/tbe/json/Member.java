package com.tbe.json;

public class Member {

	private int idMember;
	private int idProject;
	private String username;
	private int role;
	
	public Member(int idMember, int id, String username, int role){
		this.idMember = idMember;
		this.idProject = id;
		this.username = username;
		this.role = role;
	}

	public int getIdMember() {
		return idMember;
	}

	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}

	public int getIdProject() {
		return idProject;
	}

	public void setIdProject(int idProject) {
		this.idProject = idProject;
	}

	public int getRole() {
		return role;
	}

	public void setRole(int role) {
		this.role = role;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public Member(){}
}
