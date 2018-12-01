package com.ddlab.spring.rest.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

import com.ddlab.spring.rest.services.MyServices;


public class AuthenticationTokenProcessingFilter extends GenericFilterBean
{

	@Autowired
	@Qualifier("services")
	private MyServices services;
	

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{
		
		HttpServletRequest httpRequest = this.getAsHttpRequest(request);

		String authToken = this.extractAuthTokenFromRequest(httpRequest);
//		System.out.println("Auth Token :::["+authToken+"]");
		String userName = TokenUtils.getUserNameFromToken(authToken);
//		System.out.println("userName :::["+userName+"]");
		
		
		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Auth in filter ---->"+authentication1);
		
		if (userName != null) {
			UserDetails userDetails = services.loadUserByUserName(userName);
			if (TokenUtils.validateToken(authToken, userDetails)) {
				UsernamePasswordAuthenticationToken authentication =
						new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		}

		chain.doFilter(request, response);
	}


	private HttpServletRequest getAsHttpRequest(ServletRequest request)
	{
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}

		return (HttpServletRequest) request;
	}


	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest)
	{
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");
		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
		}

		return authToken;
	}
}