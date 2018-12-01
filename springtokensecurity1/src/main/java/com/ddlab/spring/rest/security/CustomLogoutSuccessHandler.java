package com.ddlab.spring.rest.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.WebApplicationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler { //implements LogoutHandler {//extends SimpleUrlLogoutSuccessHandler {//implements LogoutSuccessHandler {

    @Autowired
    private AuthenticationManager authenticationManager;
    
    

    @Override
    public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
    	
    	System.out.println(".....................logout- getting - called.............................."+authentication);
    	
    	HttpServletRequest httpRequest = this.getAsHttpRequest(request);
    	
    	String authToken = this.extractAuthTokenFromRequest(httpRequest);
		System.out.println("Auth Token in Logout Handler :::["+authToken+"]");
		String userName = TokenUtils.getUserNameFromToken(authToken);
		System.out.println("userName in Logout Handler :::["+userName+"]");
    	
//       String crowdToken = CrowdTokenUtil.collectCrowdToken(request);
//       if (StringUtils.isNotEmpty(crowdToken)) {
		
		
//		 try {
//		        HttpSession session = httpRequest.getSession(false);
//		        if (session != null) {
//		            session.invalidate();
//		        }
//
//		        SecurityContextHolder.clearContext();
//
//		    } catch (Exception e) {
////		        logger.log(LogLevel.INFO, "Problem logging out.");
//		    	
//		    	e.printStackTrace();
//		    }
		
		
		
		
		
		System.out.println("Authentication in logout handler---->"+authentication);
		
		
		
		
		
		
		
		
       try {
//           authenticationManager.invalidate(authToken);
    	   
//    	   SecurityContextLogoutHandler
    	   
//    	  Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//   		Object principal = authentication1.getPrincipal();
//   		if (principal instanceof String && ((String) principal).equals("anonymousUser")) {
//   			throw new WebApplicationException(401);
//   		}
//   		UserDetails userDetails = (UserDetails) principal;
//    	System.out.println("User Details in Logout filter ::::"+userDetails);   
    	   
    	   
    	   List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
			gList.add(ga);
			UserDetails userDetails = new User(userName,"user",gList);
    	   
    	   
   		TokenUtils.removeUser(userDetails);
   		
    	   
    	   
    	   
   		SecurityContextHolder.clearContext();
    	   
    	   
    	   
    	   
    	   
       } catch (Exception ex) {
           // report to user that logout action failed? or ignore it?
    	   
    	   ex.printStackTrace();
       }

//    }
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

//	@Override
//	public void logout(HttpServletRequest request,
//			HttpServletResponse response, Authentication authentication) {
//		
//		System.out.println("Authentication------->"+authentication);
//		Authentication authentication1 = SecurityContextHolder.getContext().getAuthentication();
//		System.out.println("Authentication1------->"+authentication1);
//		
//	}
}