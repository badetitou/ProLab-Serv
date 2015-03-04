package com.tbe;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.jersey.jdkhttp.JdkHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Launcher {
	public static void main(String[] args) {
		URI baseUri = UriBuilder.fromUri("http://localhost/v1").port(9876).build();
		ResourceConfig config = new ResourceConfig(UserREST.class);
		JdkHttpServerFactory.createHttpServer(baseUri, config, true);
		System.out.println("Server started");
	}
}