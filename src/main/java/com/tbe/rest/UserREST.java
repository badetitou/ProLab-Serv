package com.tbe.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tbe.database.UsersRequest;
import com.tbe.json.User;

@Path("/users")
public class UserREST {

	@GET
    @Produces(MediaType.APPLICATION_JSON)
	public User[] getAllUsers() {
		List<User> users = UsersRequest.getAllUsers();
		User[] userTab = new User[users.size()];
		for (int i = 0;i<userTab.length;++i){
			userTab[i] = users.get(i);
		}
		return userTab;
		
	}
	
	@GET
    @Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}&{userpassword}")
	public User getUser(@PathParam("username") String username, @PathParam("userpassword") String password){
		System.out.println("GET USER "+ username + " : " + password);
		return UsersRequest.getUser(username, password);
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user){
		System.out.println("Post User");
		String result =  UsersRequest.addUser(user.getUsername(),user.getPassword(),user.getEmail(), user.getFirstname(), user.getLastname());
		if (result == null){
			Response response = Response.status(400).type(MediaType.APPLICATION_JSON).entity(user).build();
	        return response;
		}
		Response response = Response.status(201).type(MediaType.APPLICATION_JSON).entity(user).build();
		System.out.println("User Created");
		return response;
	}
}
