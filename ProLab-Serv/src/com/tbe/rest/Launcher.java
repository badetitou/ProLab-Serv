package com.tbe.rest;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

import com.tbe.database.DataBase;

public class Launcher {
	public static void main(String[] args) {
		URI baseUri = UriBuilder.fromUri("http://192.168.43.75/v1").port(9876).build();
		ResourceConfig config = new ResourceConfig(UserREST.class);
		JdkHttpServerFactory.createHttpServer(baseUri, config, true);
		new DataBase();
		System.out.println("Server started");
	}
}