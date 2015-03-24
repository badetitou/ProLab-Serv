package com.tbe.json;

public class Task {
	private int idMember;
	private int idFonctionnality;
	
	public Task(){}

	public Task(int idMember, int idFonctionnality) {
		this.idMember = idMember;
		this.idFonctionnality = idFonctionnality;
	}

	public int getIdMember() {
		return idMember;
	}

	public void setIdMember(int idMember) {
		this.idMember = idMember;
	}

	public int getIdFonctionnality() {
		return idFonctionnality;
	}

	public void setIdFonctionnality(int idFonctionnality) {
		this.idFonctionnality = idFonctionnality;
	};
	
}
