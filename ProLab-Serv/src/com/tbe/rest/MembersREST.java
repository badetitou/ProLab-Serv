package com.tbe.rest;

import java.util.List;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.MembersRequest;
import com.tbe.json.Member;

public class MembersREST {
	
	@GET
	public Member[] getAllMembers(){
		System.out.println("GET ALL MEMBERS");
		List<Member> members = MembersRequest.getAllMembers();
		Member[] m = new Member[members.size()];
		for (int i = 0;i<m.length;++i){
			m[i] = members.get(i);
		}
		return m;
	}
	
	@GET
	@Path("/{idMember}/{username}")
	public Member getMember(@PathParam("idMember") String id,@PathParam("username") String username){
		System.out.println("GET Member "+ id);
		return MembersRequest.getMember(id, username);
	}
	
	@POST
	public Response postMember(@FormParam("username") String username, @FormParam("id") String id){
		System.out.println("Post Member");
		String result =  MembersRequest.addMember(username, id);
		if (result == null){
	        return Response.status(Response.Status.BAD_REQUEST).entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED).entity("Member Created").build();
	}
	
}
