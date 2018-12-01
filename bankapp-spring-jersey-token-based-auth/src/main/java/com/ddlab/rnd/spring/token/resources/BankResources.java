package com.ddlab.rnd.spring.token.resources;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ddlab.rnd.spring.token.entity.AccountDetails;
import com.ddlab.rnd.spring.token.entity.TokenTransfer;
import com.ddlab.rnd.spring.token.security.TokenUtils;
import com.ddlab.rnd.spring.token.services.BankingServices;

@Path("1/bankingservices")
public class BankResources extends BaseResource {
	
	@Context
	HttpServletRequest request;

	@Context
	ServletConfig servletConfig;
	
	@Autowired
	@Qualifier(value="bankingService")
	private BankingServices services;
	
	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	
	
	@Path("authenticate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password)
	{
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, password);
		System.out.println("Auth Manager---------->"+authManager);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new TokenTransfer(TokenUtils.createToken(services.loadUserByUsername(username)));
	}
	
	
	@Path("/userid")
	@GET
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public AccountDetails getUser(@QueryParam("id") int id) {
		if(id == 0 )
			throw createWebappException(new IllegalArgumentException("Incorrect ID"));
		
		return services.getAccountDetails(String.valueOf(id));
	}

}
