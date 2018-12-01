package com.ddlab.spring.rest.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Component;

import com.ddlab.spring.rest.security.TokenTransfer;
import com.ddlab.spring.rest.security.TokenUtils;
import com.ddlab.spring.rest.security.UserTransfer;


@Component
@Path("/user")
public class UserResource
{

//	@Autowired
//	private UserDetailsService userService;
	
	@Context 
	private HttpServletRequest servletRequest;
	
	@Context 
	private HttpServletResponse servletResponse;
	
//	private HttpServletContext servletContext;

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;


	/**
	 * Retrieves the currently logged in user.
	 * 
	 * @return A transfer containing the username and the roles.
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public UserTransfer getUser()
	{
//		System.out.println("*************************GET*****************************");
//		System.out.println("*************************GET*****************************");
//		System.out.println("*************************GET*****************************");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("authentication------------>"+authentication);
		Object principal = authentication.getPrincipal();
		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
			throw new WebApplicationException(401);
		}
		UserDetails userDetails = (UserDetails) principal;
//		System.out.println("userDetails------------>"+userDetails);
		return new UserTransfer(userDetails.getUsername(), this.createRoleMap(userDetails));
	}


	/**
	 * Authenticates a user and creates an authentication token.
	 * 
	 * @param username
	 *            The name of the user.
	 * @param password
	 *            The password of the user.
	 * @return A transfer containing the authentication token.
	 */
	@Path("authenticate")
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	public TokenTransfer authenticate(@FormParam("username") String username, @FormParam("password") String password)
	{
//		System.out.println("username--------------->"+username);
//		System.out.println("password--------------->"+password);
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(username, password);
		
		
		System.out.println("Authentication ::::"+authenticationToken);
		
		
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		
		
//		System.out.println("Current Authentication :::"+authentication);
		SecurityContextHolder.getContext().setAuthentication(authentication);

		/*
		 * Reload user as password of authentication principal will be null after authorization and
		 * password is needed for token generation
		 */
//		UserDetails userDetails = this.userService.loadUserByUsername(username);
		
		List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
		gList.add(ga);
		UserDetails userDetails = new User(username,"user",gList);
		
		
		
//		System.out.println("Now userDetails--------------->"+userDetails);
		
//		System.out.println("userService----------->"+userService);
//		System.out.println("userDetails--------------->"+userDetails);
		
		return new TokenTransfer(TokenUtils.createToken(userDetails));
	}
	
//	@Path("logout")
//	@POST
//	public Response logout() {
//		System.out.println("------------Coming to logout----------");
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
//		Object principal = authentication.getPrincipal();
//		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
//			throw new WebApplicationException(401);
//		}
//		UserDetails userDetails = (UserDetails) principal;
//		TokenUtils.removeUser(userDetails);
//		
//		SecurityContextHolder.clearContext();
//		
//		return Response.ok().build();
//	}
	
	
	
	
	
	
	
	
//	@Path("logout")
//	@POST
//	public Response logout1() {
//		System.out.println("------------Coming to logout----------");
//		
//		
////		SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler();
////		
////		ctxLogOut.setClearAuthentication(true);
////		ctxLogOut.setInvalidateHttpSession(true);
////		
//		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
////		System.out.println("Auth in logout ---->"+authentication);
////		authentication.setAuthenticated(false);
////		
////		
//		Object principal = authentication.getPrincipal();
//		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
//			throw new WebApplicationException(401);
//		}
//		UserDetails userDetails = (UserDetails) principal;
//		TokenUtils.removeUser(userDetails);
//		
//		SecurityContextHolder.clearContext();
//		
////		UsernamePasswordAuthenticationToken authenticationToken =
////				new UsernamePasswordAuthenticationToken(userDetails.getUsername(), userDetails.getPassword());
////		authenticationToken.eraseCredentials();
////		authenticationToken.setAuthenticated(false);
//		
//		
//		
////		authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
////		SecurityContextHolder.getContext().setAuthentication(authenticationToken);
//		
////		Authentication authentication1 = this.authManager.authenticate(authenticationToken);
////		SecurityContextHolder.getContext().setAuthentication(authentication);
//		
//		return Response.ok().build();
//	}
	
	//HttpServletRequest request , HttpServletResponse response
/*	@Path("logout")
	@POST
	public Response logout() {
		System.out.println("----------------- Coming to logout -------------------");
		
		
//		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest();
//		request.getSession().invalidate();
		
//		System.out.println("servletRequest----------->"+servletRequest);
		
//		System.out.println("ServletResponse----------->"+servletResponse);
		
		Cookie[] cooks = servletRequest.getCookies();
		for(Cookie cookie : cooks) {
			System.out.println("cookie--------->"+cookie);
			cookie.setPath(servletRequest.getContextPath());
	        cookie.setMaxAge(0);
	        
		}
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		System.out.println("Now auth----------->"+auth);
	      if (auth != null){    
	    	  SecurityContextLogoutHandler scl = new SecurityContextLogoutHandler();
	    	  
	    	  scl.setClearAuthentication(false);
	    	  scl.setInvalidateHttpSession(true);
	    	  scl.logout(servletRequest, servletResponse, auth);
	    	  
//	         new SecurityContextLogoutHandler().logout(servletRequest, servletResponse, auth);
	      }
	    SecurityContextHolder.getContext().setAuthentication(null);
		
	    servletRequest.getSession().invalidate();
		
//		Cookie[] cookies = request.getCookies();
//		for(Cookie cookie : cookies) {
//			cookie.setPath(request.getContextPath());
//	        cookie.setMaxAge(0);
//		}
		
	    
		
		
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
		
		
		
		
		
//		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//	      if (auth != null){    
//	         new SecurityContextLogoutHandler().logout(request, response, auth);
//	      }
//	    SecurityContextHolder.getContext().setAuthentication(null);
		
		
		return Response.ok().build();
		
	}*/


	private Map<String, Boolean> createRoleMap(UserDetails userDetails)
	{
		Map<String, Boolean> roles = new HashMap<String, Boolean>();
		for (GrantedAuthority authority : userDetails.getAuthorities()) {
			roles.put(authority.getAuthority(), Boolean.TRUE);
		}

		return roles;
	}

}