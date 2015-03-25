package com.tbe.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.FonctionnalitiesRequest;
import com.tbe.database.TaskRequest;
import com.tbe.json.Fonctionnality;
import com.tbe.json.Member;

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
	public Fonctionnality getFonctionnality(
			@PathParam("idFonctionnality") int id) {
		System.out.println("GET Fonctionnality " + id);
		return FonctionnalitiesRequest.getFonctionnality(id);
	}

	@GET
	@Path("/member/{idFonctionnality}")
	public Member[] getFonctionnalityMember(
			@PathParam("idFonctionnality") int id) {
		List<Member> members = FonctionnalitiesRequest
				.getFonctionnalityMember(id);
		Member[] m = new Member[members.size()];
		for (int i = 0; i < m.length; ++i) {
			m[i] = members.get(i);
		}
		return m;
	}

	@PUT
	public Response update(Fonctionnality fonctionnality) {
		int i = FonctionnalitiesRequest.update(fonctionnality);
		if (i > 0) {
			return Response.status(Response.Status.OK).entity("update").build();
		} else {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Fonctionnality already exist or no exists").build();
		}

	}

	@POST
	@Path("/{idMember}")
	public Response postFonctionnality(Fonctionnality fonctionnality,
			@PathParam("idMember") int idMember) {
		int id = FonctionnalitiesRequest.addFonctionnality(
				fonctionnality.getName(), fonctionnality.getDescription(), 0,
				fonctionnality.getDeadLine());
		if (id == -1) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknow Error").build();
		}
		int result = TaskRequest.addTask(idMember, id);
		System.out.println("ADD PRIMARY TASK : " + result);
		if (result <= 0) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Unknow Error").build();
		}
		return Response.status(Response.Status.CREATED)
				.entity("Fonctionnality Created").build();
	}

}
