package com.ddlab.rnd.spring.token.auth;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.ddlab.rnd.spring.token.entity.TokenTransfer;
import com.ddlab.rnd.spring.token.services.BankingServices;

@Path("1/authservices")
public class AuthenticationResource {
	
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
		Authentication authentication = authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		return new TokenTransfer(TokenUtils.createToken(services.loadUserByUsername(username)));
	}

}
