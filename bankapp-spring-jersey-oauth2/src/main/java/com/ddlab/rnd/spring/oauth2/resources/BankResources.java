package com.ddlab.rnd.spring.oauth2.resources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.ddlab.rnd.spring.oauth2.entity.AccountDetails;
import com.ddlab.rnd.spring.oauth2.services.BankingServices;

@Path("1/bankingservices")
public class BankResources extends BaseResource {
	
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@Autowired
	@Qualifier(value="bankingService")
	private BankingServices services;
	
	@Path("/userid")
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AccountDetails getUser(@QueryParam("id") int id) {
//		GET http://localhost:8080/bankapp-spring-jersey-oauth2/api/1/bankingservices/userid?id=123
		
		if(id == 0 )
			throw createWebappException(new IllegalArgumentException("Incorrect ID"));
		
		return services.getAccountDetails(String.valueOf(id));
	}

}
