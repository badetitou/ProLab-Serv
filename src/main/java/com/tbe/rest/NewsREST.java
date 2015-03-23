package com.tbe.rest;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import com.tbe.database.NewsRequest;
import com.tbe.json.News;

public class NewsREST {

	@GET
	public News[] getAllnews() {
		System.out.println("GET ALL NEWS");
		List<News> news = NewsRequest.getAllNews();
		News[] m = new News[news.size()];
		for (int i = 0; i < m.length; ++i) {
			m[i] = news.get(i);
		}
		return m;
	}

	@GET
	@Path("/{idNews}")
	public News getNews(@PathParam("idNews") String id) {
		System.out.println("GET News " + id);
		return NewsRequest.getNew(id);
	}

	@POST
	public Response postNews(News news) {
		System.out.println("Post News");
		String result = NewsRequest.addNews(news.getTitle(),
				news.getDescription(), news.getDate(), news.getAuthor());
		if (result == null) {
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("Entity already exist").build();
		}
		return Response.status(Response.Status.CREATED).entity("News Created")
				.build();
	}

}
