package com.tbe.rest;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;

import com.tbe.json.Chat;
import com.tbe.json.Salon;

@Path("/chat")
public class ChatREST {

	@GET
	@Path("/{idProjet}")
	public Salon getSalon(@PathParam("idProjet") int idProjet) {
		return Chat.salons.get(idProjet);
	}
}
