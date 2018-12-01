package com.ddlab.rnd.spring.token.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.GenericFilterBean;

import com.ddlab.rnd.spring.token.services.BankingServicesImpl;

public class TokenAuthenticationFilter extends GenericFilterBean {

	@Autowired
	@Qualifier("bankingService")
	private BankingServicesImpl services;
	
//	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		
		System.out.println("***************AuthenticationTokenProcessingFilter***********************");
		
		String authToken = TokenUtils.extractAuthTokenFromRequest(httpRequest);
		String userName = TokenUtils.getUserNameFromToken(authToken);
		System.out.println("UserName :::"+userName);
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		
		if (userName != null) {
			UserDetails userDetails = services.loadUserByUsername(userName);
			boolean validToken = TokenUtils.isTokenValid(authToken, userDetails);
			System.out.println("Is Token Valied :::"+validToken);
			if (validToken) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
					userDetails, null, userDetails.getAuthorities());
			authentication.setDetails(new WebAuthenticationDetailsSource()
					.buildDetails(httpRequest));
			SecurityContextHolder.getContext().setAuthentication(
					authentication);
			}
		}
		else {
			SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler();
			ctxLogOut.logout(httpRequest, httpResponse, auth);
		}
		
		try {
			chain.doFilter(request, response);
		} catch (Exception e) {
			httpResponse.sendError(HttpServletResponse.SC_UNAUTHORIZED,"Unauthorized");
		}
	}


//	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
//		/* Get token from header */
//		String authToken = httpRequest.getHeader("X-Auth-Token");
//		System.out.println("Actual authToken ->"+authToken);
//		/* If token not found get it from request parameter */
//		if (authToken == null) {
//			authToken = httpRequest.getParameter("token");
//			System.out.println("Auth Token from token ->"+authToken);
//		}
//		if( authToken == null) {
//			//Get it from cookie
//			authToken = getCookieValueByName(httpRequest,"APPASSERTION");
//			System.out.println("Auth Token from Cookie ->"+authToken);
//		}
//		return authToken;
//	}
	
//	private String getCookieValueByName(HttpServletRequest httpRequest,String cookieName) {
//		String cookieValue = null;
//		String completeCookies = httpRequest.getHeader("Cookie");
//		if( completeCookies != null ) {
//			completeCookies = completeCookies.replaceAll("\"", "");
//			if (completeCookies != null) {
//				String[] cooks = completeCookies.split(";");
//				Map<String, String> cookieMap = new HashMap<String, String>();
//				for (String cookie : cooks) {
//					String[] keyVal = cookie.split("=",2);
//					cookieMap.put(keyVal[0].trim(), keyVal[1].trim());
//				}
//				cookieValue = cookieMap.get(cookieName);
//			}
//		}
//		//Decode the Cookie Token
//		if( cookieValue != null )
//			cookieValue = new String(Base64.decodeBase64(cookieValue.getBytes()));
//		return cookieValue;
//	}
}