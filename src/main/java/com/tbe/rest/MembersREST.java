package com.tbe.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.tbe.database.MembersRequest;
import com.tbe.json.Member;
import com.tbe.json.Project;
import com.tbe.json.User;

@Path("/members")
public class MembersREST {

	@GET
	public Member[] getAllMembers() {
		System.out.println("GET ALL MEMBERS");
		List<Member> members = MembersRequest.getAllMembers();
		Member[] m = new Member[members.size()];
		for (int i = 0; i < m.length; ++i) {
			m[i] = members.get(i);
		}
		return m;
	}
	
	@GET
	@Path("/project/{idProject}")
	public User[] getProjectUser(@PathParam("idProject") int idProject){
		System.out.println("GET Project users |project:" + idProject);
		List<User> lp = MembersRequest.getProjectUser(idProject);
		User[] result = new User[lp.size()];
		for (int i = 0; i < lp.size(); ++i) {
			result[i] = lp.get(i);
		}
		return result;
	}

	@GET
	@Path("/{username}")
	public Project[] getUserProject(@PathParam("username") String username) {
		System.out.println("GET User project :username=" + username);
		List<Project> lp = MembersRequest.getUserProject(username);
		Project[] result = new Project[lp.size()];
		for (int i = 0; i < lp.size(); ++i) {
			result[i] = lp.get(i);
		}
		return result;
	}

	@POST
	public Response postMember(Member member) {
		System.out.println("Post Member");
		String result = MembersRequest.addMember(member.getUsername(),member.getIdProject(), member.getRole());
		if (result == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Entity already exist").build();
		}
		Response response = Response.status(201)
				.type(MediaType.APPLICATION_JSON).entity(member).build();
		System.out.println("Project Created");
		return response;
	}

}
