package com.tbe.rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.ProjectsRequest;

@Path("/projects")
public class ProjectsREST {

	@GET
	public String getAllProjects(){
		System.out.println("GET ALL PROJECTS");
		return ProjectsRequest.getAllProject();
	}
	
	@GET
	@Path("/{idproject}")
	public String getProject(@PathParam("idproject") String idp){
		System.out.println("GET Project "+ idp);
		String result =  ProjectsRequest.getProject(idp);
		if (result == null){
	        return null;
		}
		return result;
	}
	
	@POST
	public Response postProject(@FormParam("name") String name, @FormParam("description") String description, @FormParam("url") String url){
		System.out.println("Post Project");
		String result =  ProjectsRequest.addProject(name, description, url);
		if (result == null){
	        return Response.status(Response.Status.BAD_REQUEST).entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED).entity("Project Created").build();
	}
	
}
