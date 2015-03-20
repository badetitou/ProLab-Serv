package com.tbe.rest;

import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.skife.jdbi.v2.DBI;
import org.sqlite.SQLiteDataSource;

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
		return s;
	}	
}