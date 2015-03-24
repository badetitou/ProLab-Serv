package com.tbe.rest;

import javax.ws.rs.GET;

public class SalonREST {

	@GET
	public String getAllMesages() {
		System.out.println("GET ALL MESSAGES yarr");
		return null;

	}
}