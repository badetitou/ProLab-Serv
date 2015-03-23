package com.tbe.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.FonctionnalitiesRequest;
import com.tbe.database.MembersRequest;
import com.tbe.database.TaskRequest;
import com.tbe.json.Fonctionnality;

@Path("/fonctionnalities")
public class FonctionnalitiesREST {

	@GET
	public Fonctionnality[] getAllFonctionnalities() {
		System.out.println("GET ALL Fonctionnalities");
		List<Fonctionnality> fonctionnalities = FonctionnalitiesRequest
				.getAllfonctionnalities();
		Fonctionnality[] m = new Fonctionnality[fonctionnalities.size()];
		for (int i = 0; i < m.length; ++i) {
			m[i] = fonctionnalities.get(i);
		}
		return m;
	}

	@GET
	@Path("/{idFonctionnality}")
	public Fonctionnality getFonctionnality(@PathParam("idProject") int id) {
		System.out.println("GET Fonctionnality " + id);
		return FonctionnalitiesRequest.getFonctionnality(id);
	}

	@POST
	@Path("/{username}/{idProject}")
	public Response postFonctionnality(Fonctionnality fonctionnality, @PathParam("username") String username, @PathParam("idProject") int idProject) {
		System.out.println("Post Fonctionnality");
		int id = FonctionnalitiesRequest.addFonctionnality(
				fonctionnality.getName(), fonctionnality.getDescription(), 0,
				fonctionnality.getDeadLine());
		if (id == -1) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknow Error").build();
		}
		int result = TaskRequest.addTask(username, idProject, id);
		System.out.println("ADD PRIMARY TASK");
		if (result <=0){
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknow Error").build();
		}
		return Response.status(Response.Status.CREATED)
				.entity("Fonctionnality Created").build();
	}

}
