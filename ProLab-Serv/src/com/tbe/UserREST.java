package com.tbe;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/users")
public class UserREST {
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String addPlainText() {
		return "hello";
	}
}
