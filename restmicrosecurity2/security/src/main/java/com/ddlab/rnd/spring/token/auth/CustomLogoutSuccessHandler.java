package com.ddlab.rnd.spring.token.auth;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.ddlab.rnd.spring.token.services.BankingServicesImpl;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	@Qualifier("bankingService")
	private BankingServicesImpl services;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = this.getAsHttpRequest(request);
		String authToken = this.extractAuthTokenFromRequest(httpRequest);
		String userName = TokenUtils.getUserNameFromToken(authToken);

		try {

			TokenUtils.removeUser(authToken, services.loadUserByUsername(userName));

			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("User logged out successfully ...");

			// SecurityContextHolder.clearContext();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}

		return (HttpServletRequest) request;
	}

	private String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");

		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
		}

		// Also Find out from Cookie
		String cookieValue = getCookieValueByName(httpRequest, "APPASSERTION");
		System.out.println("Cookie Value :::" + cookieValue);

		return authToken;
	}

	private String getCookieValueByName(HttpServletRequest httpRequest,String cookieName) {
		String cookieValue = null;
		String completeCookies = httpRequest.getHeader("Cookie");
		if( completeCookies != null ) {
			completeCookies = completeCookies.replaceAll("\"", "");
			if (completeCookies != null) {
				String[] cooks = completeCookies.split(";");
				Map<String, String> cookieMap = new HashMap<String, String>();
				for (String cookie : cooks) {
					String[] keyVal = cookie.split("=",2);
					cookieMap.put(keyVal[0].trim(), keyVal[1].trim());
				}
				cookieValue = cookieMap.get(cookieName);
			}
		}
		return cookieValue;
	}

}