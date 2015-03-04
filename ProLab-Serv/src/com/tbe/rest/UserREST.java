package com.tbe.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.tbe.database.UsersRequest;

@Path("/users")
public class UserREST {

	@GET
	public String getAllUsers() {
		System.out.println("GET ALL USERS");
		return UsersRequest.getAllUsers();
	}
	
	@GET
	@Path("/{userid}")
	public String getUser(@PathParam("userid") String id){
		System.out.println("GET USER "+ id);
		return UsersRequest.getUser(id);
		
	}

	@POST
	public String addUser(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("firstname") String firstName,
			@FormParam("surename") String surName) {
		System.out.println("Post User");
		return UsersRequest.addUser(username, password, firstName, surName);
	}
}
