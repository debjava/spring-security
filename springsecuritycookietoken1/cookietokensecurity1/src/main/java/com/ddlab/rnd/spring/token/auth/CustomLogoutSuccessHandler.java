package com.ddlab.rnd.spring.token.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.ddlab.rnd.spring.token.services.UserServicesImpl;

public class CustomLogoutSuccessHandler implements LogoutSuccessHandler {

	@Autowired
	@Qualifier("userServices")
	private UserServicesImpl services;

	@Override
	public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		String authToken = TokenUtils.extractAuthTokenFromRequest(httpRequest);
		String userName = TokenUtils.getUserNameFromToken(authToken);

		try {
			TokenUtils.removeUser(authToken, services.loadUserByUsername(userName));
			response.setStatus(HttpServletResponse.SC_OK);
			response.getWriter().write("User logged out successfully ...");
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
}