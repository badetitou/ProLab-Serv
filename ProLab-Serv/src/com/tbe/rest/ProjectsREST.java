package com.tbe.rest;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.ProjectsRequest;
import com.tbe.json.Project;

@Path("/projects")
public class ProjectsREST {

	@GET
	public Project[] getAllProjects(){
		System.out.println("GET ALL PROJECTS");
		List<Project> projects = ProjectsRequest.getAllProject();
		Project[] p = new Project[projects.size()];
		for (int i = 0;i<p.length;++i){
			p[i] = projects.get(i);
		}
		return p;
	}
	
	@GET
	@Path("/{idproject}")
	public Project getProject(@PathParam("idproject") String id){
		System.out.println("GET Project "+ id);
		return ProjectsRequest.getProject(id);
	}
	
	@POST
	public Response postProject(@FormParam("name") String name, @FormParam("description") String description, @FormParam("url") String url, @FormParam("punchline") String punchline){
		System.out.println("Post Project");
		String result =  ProjectsRequest.addProject(name, description, url, punchline);
		if (result == null){
	        return Response.status(Response.Status.BAD_REQUEST).entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED).entity("Project Created").build();
	}
	
}
