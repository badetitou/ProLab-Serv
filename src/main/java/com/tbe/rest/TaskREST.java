package com.tbe.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.TaskRequest;
import com.tbe.json.Fonctionnality;
import com.tbe.json.Task;
import com.tbe.json.User;

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
	

	@POST
	public Response postTask(Task task) {
		System.out.println("Post Task");
		int result = TaskRequest.addTask(task.getIdMember(), task.getIdFonctionnality());
		if (result == -1) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED)
				.entity("Project Created").build();
	}

}
