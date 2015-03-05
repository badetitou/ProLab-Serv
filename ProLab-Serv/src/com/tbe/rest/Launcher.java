package com.tbe.rest;

import com.tbe.database.DataBase;
import com.tbe.rest.ProjectsREST;
import com.tbe.rest.UserREST;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("/v1/")
public class Launcher extends Application {

	@Override
	public Set<Class<?>> getClasses() {
		new DataBase();
		Set<Class<?>> s = new HashSet<Class<?>>();
		s.add(ProjectsREST.class);
		s.add(UserREST.class);
		return s;
	}	
}