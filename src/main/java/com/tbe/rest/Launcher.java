package com.tbe.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import com.tbe.database.DataBase;

@ApplicationPath("/v1/")
public class Launcher extends Application {

	public Set<Class<?>> getClasses() {
		new DataBase();
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(ProjectsREST.class);
		s.add(UserREST.class);
		s.add(MembersREST.class);
		s.add(ChatREST.class);
		s.add(FonctionnalitiesREST.class);
		s.add(TaskREST.class);
		s.add(ChatREST.class);
		s.add(NewsREST.class);

		return s;
	}
}