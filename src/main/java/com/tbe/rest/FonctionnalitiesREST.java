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
	public Fonctionnality getFonctionnality(@PathParam("idProject") int id){
		System.out.println("GET Fonctionnality "+ id);
		return FonctionnalitiesRequest.getFonctionnality(id);
	}
	
	@POST
	public Fonctionnality postMember(Fonctionnality fonctionnality){
		System.out.println("Post Fonctionnality");
		String result =  FonctionnalitiesRequest.addFonctionnality(fonctionnality.getName(), fonctionnality.getDescription(), 0, fonctionnality.getDeadLine());
		if (result == null){
			return null;
		}
		return fonctionnality;
	}
	
}
