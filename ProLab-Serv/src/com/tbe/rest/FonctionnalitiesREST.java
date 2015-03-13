package com.tbe.rest;

import java.sql.Date;
import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.FonctionnalitiesRequest;
import com.tbe.json.Fonctionnality;

@Path("/fonctionnalities")
public class FonctionnalitiesREST {

	@GET
	public Fonctionnality[] getAllFonctionnalities(){
		System.out.println("GET ALL Fonctionnalities");
		List<Fonctionnality> fonctionnalities = FonctionnalitiesRequest.getAllfonctionnalities();
		Fonctionnality[] m = new Fonctionnality[fonctionnalities.size()];
		for (int i = 0;i<m.length;++i){
			m[i] = fonctionnalities.get(i);
		}
		return m;
	}
	
	@GET
	@Path("/{idFonctionnality}")
	public Fonctionnality getFonctionnality(@PathParam("idProject") String id){
		System.out.println("GET Fonctionnality "+ id);
		return FonctionnalitiesRequest.getFonctionnality(id);
	}
	
	@POST
	public Response postMember(@FormParam("id") String id, @FormParam("name") String name, @FormParam("description") String description, @FormParam("avancement") String avancement, @FormParam("deadLine") Date deadLine){
		System.out.println("Post Fonctionnality");
		String result =  FonctionnalitiesRequest.addFonctionnality(id, name, description, avancement, deadLine);
		if (result == null){
	        return Response.status(Response.Status.BAD_REQUEST).entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED).entity("Fonctionnality Created").build();
	}
	
}
