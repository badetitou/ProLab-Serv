package com.tbe.rest;

import java.util.List;

import javax.ws.rs.GET;

import com.tbe.database.MembersRequest;
import com.tbe.json.Member;

public class SalonREST {

	@GET
	public String getAllMesages(){
		System.out.println("GET ALL MESSAGES yarr");
		return null;

	}
}