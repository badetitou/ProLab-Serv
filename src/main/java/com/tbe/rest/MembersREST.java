package com.tbe.rest;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.omg.CORBA.portable.InputStream;

import com.tbe.database.MembersRequest;
import com.tbe.json.Member;
import com.tbe.json.Project;
import com.tbe.json.User;
import com.tbe.tools.Mailer;

@Path("/members")
public class MembersREST {

	
	@DELETE
	@Path("/{idMember}")
	public Response removeMembers(@PathParam("idMember") int idMember) {
		int i = MembersRequest.removeMembers(idMember);
		if (i>0){
			return Response.status(Response.Status.OK)
					.entity("delete").build();
		}
		return Response.status(Response.Status.CREATED)
				.entity("Can't delete member").build();
	}
	

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
	@Path("/member/{idProject}")
	public Member[] getProjectMember(@PathParam("idProject") int idProject){
		System.out.println("GET Member |project:" + idProject);
		List<Member> lp = MembersRequest.getProjectMember(idProject);
		Member[] result = new Member[lp.size()];
		for (int i = 0; i < lp.size(); ++i) {
			result[i] = lp.get(i);
		}
		return result;
	}

	@GET
	@Path("/project/{idProject}")
	public User[] getProjectUser(@PathParam("idProject") int idProject) {
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
		String name = username.toLowerCase();
		System.out.println("GET User project :username=" + name);
		List<Project> lp = MembersRequest.getUserProject(name);
		Project[] result = new Project[lp.size()];
		for (int i = 0; i < lp.size(); ++i) {
			result[i] = lp.get(i);
		}
		return result;
	}
	
	@GET
	@Path("/{username}&{idProject}")
	public Member getUserProject(@PathParam("username") String username, @PathParam("idProject") int idProject) {
		String name = username.toLowerCase();
		System.out.println("GET Member id");
		return MembersRequest.getMember(name,idProject);
	}

	@POST
	public Response postMember(Member member) {
		System.out.println("Post Member");	
		String result = MembersRequest.addMember(member.getUsername().toLowerCase(),
				member.getIdProject(), member.getRole());
		if (result == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Entity already exist").build();
		}
		Response response = Response.status(201)
				.type(MediaType.APPLICATION_JSON).entity(member).build();
		System.out.println("members Created");
		try {
			//String mail = executePost(username);
			//System.out.println(mail);
			// Mailer.sendMail(mail, "Prolab  -  Project update",
			// "You've been assigned to a project !\nwww.iut.azae.net/Prolab");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}

	public static String executePost(String username) {
		String targetURL = "http://localhost:8080/v1/members/";
		String urlParameters = username;
		URL url;
		HttpURLConnection connection = null;
		try {
			// Create connection
			url = new URL(targetURL);
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("POST");
			connection.setRequestProperty("Content-Type",
					"application/x-www-form-urlencoded");

			connection.setRequestProperty("Content-Length",
					"" + Integer.toString(urlParameters.getBytes().length));
			connection.setRequestProperty("Content-Language", "en-US");

			connection.setUseCaches(false);
			connection.setDoInput(true);
			connection.setDoOutput(true);

			// Send request
			DataOutputStream wr = new DataOutputStream(
					connection.getOutputStream());
			wr.writeBytes(urlParameters);
			wr.flush();
			wr.close();

			// Get Response
			InputStream is = (InputStream) connection.getInputStream();
			BufferedReader rd = new BufferedReader(new InputStreamReader(is));
			String line;
			StringBuffer response = new StringBuffer();
			while ((line = rd.readLine()) != null) {
				response.append(line);
				response.append('\r');
			}
			rd.close();
			return response.toString();

		} catch (Exception e) {

			e.printStackTrace();
			return null;

		} finally {

			if (connection != null) {
				connection.disconnect();
			}
		}
	}

}
