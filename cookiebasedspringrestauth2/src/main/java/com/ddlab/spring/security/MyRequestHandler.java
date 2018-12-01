package com.ddlab.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;

public class MyRequestHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		
		System.out.println("************************************************");
		System.out.println("************************************************");
		System.out.println("************************************************");
		final SavedRequest savedRequest = requestCache.getRequest(request, response);
		
		
		 Cookie[] cookies  = request.getCookies();
		 for( Cookie cook : cookies ) {
			 System.out.println(cook.getName()+"------"+cook.getValue());
			 
//			 response.
		 }
		
		 if (savedRequest == null) {
	        	System.out.println("savesRequest is not null..............................");
	        	Cookie cook = new Cookie("MyCook", "ABCD");
	        	response.addCookie(cook);
	        	
	        	
	        	clearAuthenticationAttributes(request);
	            setDefaultTargetUrl("/api/home");
	            getRedirectStrategy().sendRedirect(request, response, "/api/home");
		 }
//		 else {
//			 
//			 System.out.println("Coming to second time...............");
//			 Cookie[] cookies  = request.getCookies();
//			 for( Cookie cook : cookies ) {
//				 System.out.println(cook.getName()+"------"+cook.getValue());
//			 }
//		 }
		
		
		
		
		
		
		
//		super.onAuthenticationSuccess(request, response, authentication);
	}

}
