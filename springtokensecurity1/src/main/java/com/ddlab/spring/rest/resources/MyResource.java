package com.ddlab.spring.rest.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.springframework.stereotype.Component;

@Component
@Path("/myresource")
public class MyResource {
	
	@Path("something")
	@GET
	public String getSomething() {
		return "Hati-Ghoda";
	}

}
