package com.ddlab.rnd.spring.token.resources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("1/bankingservices")
public class BankResources extends BaseResource {
	
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@Path("/userid")
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getUser(@QueryParam("id") int id) {
		if(id == 0 )
			throw createWebappException(new IllegalArgumentException("Incorrect ID"));
		return Response.ok().entity("You have successfully configured").build();
	}
	
	@Path("/info")
	@GET
	@Produces({ MediaType.TEXT_PLAIN})
	public Response getInfo() {
		return Response.ok().entity("Application configured with Spring security ...").build();
	}

}
