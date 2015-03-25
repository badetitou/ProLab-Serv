package com.tbe.rest;

import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.MembersRequest;
import com.tbe.database.TaskRequest;
import com.tbe.json.Fonctionnality;
import com.tbe.json.Member;
import com.tbe.json.Task;

@Path("/task")
public class TaskREST {

	@GET
	public Task[] getAllTasks() {
		System.out.println("GET ALL tasks");
		List<Task> tasks = TaskRequest.getAllTask();
		Task[] p = new Task[tasks.size()];
		for (int i = 0; i < p.length; ++i) {
			p[i] = tasks.get(i);
		}
		return p;
	}
	
	@GET
	@Path("/allfonctionnalities/{idMember}")
	public Fonctionnality[] getAllFonctionnalities(@PathParam("idMember") int idMember){
		Member member = MembersRequest.getMemberFromIdMember(idMember);
		if (member == null){
			return null;
		}
		List<Fonctionnality> fonctionnalities = TaskRequest.getFonctionnalityFromIdProject(member.getIdProject());
		Fonctionnality[] p = new Fonctionnality[fonctionnalities.size()];
		for (int i = 0; i < p.length; ++i) {
			p[i] = fonctionnalities.get(i);
		}
		return p;
	}
	
	@GET
	@Path("/allProjectFonctionnalities/{idproject}")
	public Fonctionnality[] getProjectFonctionnalities(@PathParam("idProject") int idProject){
		List<Fonctionnality> fonctionnalities = TaskRequest.getFonctionnalityFromIdProject(idProject);
		Fonctionnality[] p = new Fonctionnality[fonctionnalities.size()];
		for (int i = 0; i < p.length; ++i) {
			p[i] = fonctionnalities.get(i);
		}
		return p;
	}
	
	@DELETE
	@Path("/{idFonctionnality}&{idMember}")
	public Response delete(@PathParam("idFonctionnality") int idFonctionnality, @PathParam("idMember") int idMember) {
		System.out.println("\nidFonctionnality:" + idFonctionnality + "\nidMember:" + idMember);
		int result = TaskRequest.delete(idFonctionnality, idMember);
		if (result > 0) {
			return Response.status(Response.Status.OK)
					.entity("delete").build();
		}
		return Response.status(Response.Status.CREATED)
				.entity("Can't delete task").build();
	}

	@POST
	public Response postTask(Task task) {
		System.out.println("Post Task\nidFonc:" + task.getIdFonctionnality() + "\nidMember" + task.getIdMember());
		int result = TaskRequest.addTask(task.getIdMember(), task.getIdFonctionnality());
		
		if (result == -1) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED)
				.entity("Project Created").build();
	}

}
