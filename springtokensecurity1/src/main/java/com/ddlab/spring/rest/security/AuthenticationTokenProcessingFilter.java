package com.ddlab.spring.rest.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;


public class AuthenticationTokenProcessingFilter extends GenericFilterBean
{

//	private final UserDetailsService userService;


//	public AuthenticationTokenProcessingFilter(UserDetailsService userService)
//	{
//		this.userService = userService;
//	}

//	@Override
//	public void destroy() {
//		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("Auth in destroy ---->"+authentication1);
//		super.destroy();
//	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException
	{
		System.out.println("---------------COMING--------------------------");
		
		
		
		SecurityContext sc = SecurityContextHolder.getContext();
		System.out.println("SecurityContext in filter ---->"+sc);
		
		HttpServletRequest httpRequest = this.getAsHttpRequest(request);

		String authToken = this.extractAuthTokenFromRequest(httpRequest);
		System.out.println("Auth Token :::["+authToken+"]");
		String userName = TokenUtils.getUserNameFromToken(authToken);
		System.out.println("userName :::["+userName+"]");
		
		
		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
		System.out.println("Auth in filter ---->"+authentication1);
		
		if (userName != null) {

//			UserDetails userDetails = this.userService.loadUserByUsername(userName);
			
			System.out.println("User is not null");
			List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
			gList.add(ga);
			UserDetails userDetails = new User(userName,"user",gList);
			
			
//			System.out.println("Request Action :::"+request.getParameter("action"));
//			if(request.getParameter("action") !=null &&
//			        request.getParameter("action").equals("logout")) {
//				
//				SecurityContextHolder.getContext().setAuthentication(null);
//			      SecurityContextHolder.clearContext();
//			      return;
//			    }
			
			

			if (TokenUtils.validateToken(authToken, userDetails)) {
				System.out.println("Inside to validate token...........");
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