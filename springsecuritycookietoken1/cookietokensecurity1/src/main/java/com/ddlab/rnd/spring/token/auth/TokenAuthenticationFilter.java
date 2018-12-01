package com.ddlab.rnd.spring.token.auth;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.filter.GenericFilterBean;

import com.ddlab.rnd.spring.token.services.UserServicesImpl;

public class TokenAuthenticationFilter extends GenericFilterBean {

	@Autowired
	@Qualifier("userServices")
	private UserServicesImpl services;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

		System.out.println("***************AuthenticationTokenProcessingFilter***********************");

		String authToken = TokenUtils.extractAuthTokenFromRequest(httpRequest);
		String userName = TokenUtils.getUserNameFromToken(authToken);
		System.out.println("UserName :::" + userName);

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (userName != null) {
			UserDetails userDetails = services.loadUserByUsername(userName);
			boolean validToken = TokenUtils.isTokenValid(authToken, userDetails);
			System.out.println("Is Token Valied :::" + validToken);
			if (validToken) {
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(authentication);
			}
		} else {
			SecurityContextLogoutHandler ctxLogOut = new SecurityContextLogoutHandler();
			ctxLogOut.logout(httpRequest, httpResponse, auth);
		}

		chain.doFilter(request, response);
	}

}