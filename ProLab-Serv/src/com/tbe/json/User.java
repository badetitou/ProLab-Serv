package com.tbe.json;

public class User {
	
	private String username;
	private String password;
	private String email;
	private String firstname;
	private String surname;
	
	//Must have empty constructor
	public User(){}
	
	public User(String username, String password, String email, String firstname, String surname){
		this.username = username;
		this.password = password;
		this.email = email;
		this.firstname = firstname;
		this.surname = surname;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	
	
}
