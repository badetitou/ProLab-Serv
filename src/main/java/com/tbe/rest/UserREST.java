package com.tbe.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tbe.database.FonctionnalitiesRequest;
import com.tbe.database.UsersRequest;
import com.tbe.json.User;
import com.tbe.tools.GitHubManager;
import com.tbe.tools.Mailer;

@Path("/users")
public class UserREST {

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}&{userpassword}")
	public User getUser(@PathParam("username") String username,
			@PathParam("userpassword") String password) {
		username = username.toLowerCase();
		System.out.println("GET USER " + username + " : " + password);
		return UsersRequest.getUser(username, password);
	}

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	@Path("/{username}")
	public User getUser(@PathParam("username") String username) {
		username = username.toLowerCase();
		System.out.println("GET USER " + username);
		return UsersRequest.getUser(username);
	}

	@PUT
	@Path("/{username}")
	public Response update(User user, @PathParam("username") String username) {
		int i = UsersRequest.update(user, username);
		if (i > 0) {
			return Response.status(Response.Status.OK).entity("update").build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("User already exist or no exists").build();
		}

	}

	@DELETE
	@Path("/{username}")
	public Response delete(@PathParam("username") String username) {
		int i = UsersRequest.delete(username);
		if (i > 0) {
			return Response.status(Response.Status.OK).entity("delete").build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("can't delete users : " + username).build();
		}
	}

	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public Response addUser(User user) {
		System.out.println("Post User\nusername:" + user.getUsername()
				+ "\npassword:" + user.getPassword() + "\nemail:"
				+ user.getEmail() + "\nfirstname:" + user.getFirstname()
				+ "\nlastname:" + user.getLastname());

		String username = user.getUsername().toLowerCase();
		String password = user.getPassword();

		try {
			if (!GitHubManager.userExists(username, password)) {
				Response response = Response.status(400)
						.type(MediaType.APPLICATION_JSON).build();
				return response;
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}

		String email = user.getEmail().toLowerCase();
		String firstname = user.getFirstname().substring(0, 1).toUpperCase()
				+ user.getFirstname().substring(1).toLowerCase();
		String lastname = user.getLastname().substring(0, 1).toUpperCase()
				+ user.getLastname().substring(1).toLowerCase();
		System.out.println("New user : " + username + "/" + password + "/"
				+ email + "/" + firstname + "/" + lastname);
		String result = UsersRequest.addUser(username, password, email,
				firstname, lastname);

		if (result == null) {
			Response response = Response.status(400)
					.type(MediaType.APPLICATION_JSON).build();
			return response;
		}
		Response response = Response.status(201)
				.type(MediaType.APPLICATION_JSON).entity(user).build();
		System.out.println("User Created");
		try {
			Mailer.sendMail(user.getEmail(), "ProLab - Registration",
					"Welcome to ProLab, " + user.getFirstname()
							+ " ! Check out your profile infos :\n"
							+ " - Username : "
							+ user.getUsername().toLowerCase() + "\n"
							+ " - Password : " + user.getPassword() + "\n"
							+ "\nWe hope you'll enjoy using our platform !\n"
							+ "\n\nAlexandre Beaudet\n" + "www.prolab.com\n");
		} catch (Exception e) {
			System.out.println("Mail not sent");
		}
		return response;
	}
}
