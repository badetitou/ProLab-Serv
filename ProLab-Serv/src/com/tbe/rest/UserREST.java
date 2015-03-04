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
	@Produces(MediaType.TEXT_PLAIN)
	public String getAllUsers() {
		return UsersRequest.getAllUsers();
	}
	
	@GET
	@Path("/{userid}")
	@Produces(MediaType.TEXT_PLAIN)
	public String getUser(@PathParam("id") String id){
		return UsersRequest.getUser(id);
		
	}

	@POST
	@Produces(MediaType.TEXT_PLAIN)
	public String addUser(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("firstname") String firstName,
			@FormParam("surName") String surName) {
		return UsersRequest.addUser(username, password, firstName, surName);
	}
}
