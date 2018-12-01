package com.ddlab.spring.security;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

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
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;
import com.ddlab.spring.services.UserService;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response1 = null;
		System.out.println("**************AuthenticationTokenProcessingFilter************");
		try {

			response1 = (HttpServletResponse) response;
			HttpServletRequest httpRequest = this.getAsHttpRequest(request);
			String cookieValue = getCookieValueByName(httpRequest,
					"APPASSERTION");
			System.out.println("Cookie Value :::"+cookieValue);
			if (cookieValue != null) {
				cookieValue = new String(Base64.decode(cookieValue.getBytes()));
			}

			Authentication auth = SecurityContextHolder.getContext()
					.getAuthentication();
			UserDetails userDetails = null;

			if (auth == null && cookieValue == null) {
				// User has not yet logged in
				System.out.println("user has not yet logged in...........");
			} else if (auth != null && cookieValue == null) {
				userDetails = (UserDetails) auth.getPrincipal();
			} else if (auth == null && cookieValue != null) {
				//It is for the use case where use is passing APPASSERTION as cookie in Firefox rest client
				//Get the user name from the cookie token
				String cookiTokenUserName = TokenUtils.getUserNameFromToken(cookieValue);
				userDetails = userService.getUserByUserName(cookiTokenUserName);
				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource()
						.buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(
						authentication);
				boolean flag = TokenUtils.validateToken(cookieValue,userDetails);

				if (!flag) {
					response1.sendError(HttpServletResponse.SC_UNAUTHORIZED,
							"Unauthorized");
				}
			} else if (auth != null && cookieValue != null) {
				// do nothing
			}

			try {
				chain.doFilter(request, response);
			} catch (Exception e) {
				response1.sendError(HttpServletResponse.SC_UNAUTHORIZED,
						"Unauthorized");
			}

		} catch (Exception e) {
			e.printStackTrace();
			response1.sendError(HttpServletResponse.SC_UNAUTHORIZED,
					"Unauthorized");
		}
	}

	private HttpServletRequest getAsHttpRequest(ServletRequest request) {
		if (!(request instanceof HttpServletRequest)) {
			throw new RuntimeException("Expecting an HTTP request");
		}

		return (HttpServletRequest) request;
	}

	private String getCookieValueByName(HttpServletRequest httpRequest,
			String cookieName) {

		String completeCookies = httpRequest.getHeader("Cookie");
		completeCookies = completeCookies.replaceAll("\"", "");
		String cookieValue = null;
		if (completeCookies != null) {
			String[] cooks = completeCookies.split(";");
			Map<String, String> cookieMap = new HashMap<String, String>();
			for (String cookie : cooks) {
				String[] keyVal = cookie.split("=",2);
				cookieMap.put(keyVal[0].trim(), keyVal[1].trim());
			}
			cookieValue = cookieMap.get(cookieName);
		}
		return cookieValue;

	}
}