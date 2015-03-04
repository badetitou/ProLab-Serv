package com.tbe.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tbe.database.UsersRequest;

@Path("/users")
public class UserREST {

	@GET
	public String getAllUsers() {
		System.out.println("GET ALL USERS");
		return UsersRequest.getAllUsers();
	}
	
	@GET
	@Path("/{username}&{userpassword}")
	public Response getUser(@PathParam("username") String username, @PathParam("userpassword") String password){
		System.out.println("GET USER "+ username + " : " + password);
		String result =  UsersRequest.getUser(username, password);
		if (result == null){
	        return Response.status(Response.Status.BAD_REQUEST).entity("Entity already exist").build();
		}
		return Response.status(Response.Status.ACCEPTED).entity("User Accepted").build();
		
	}

	@POST
	public Response addUser(@FormParam("username") String username,
			@FormParam("password") String password,
			@FormParam("firstname") String firstName,
			@FormParam("surename") String surName) {
		System.out.println("Post User");
		String result =  UsersRequest.addUser(username, password, firstName, surName);
		if (result == null){
	        return Response.status(Response.Status.BAD_REQUEST).entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED).entity("User Created").build();
	}
}
