package com.ddlab.spring.security;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.GenericFilterBean;

public class AuthenticationTokenProcessingFilter extends GenericFilterBean {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		HttpServletResponse response1 = null;
		try {

			response1 = (HttpServletResponse) response;
			HttpServletRequest httpRequest = this.getAsHttpRequest(request);
			String cookieValue = getCookieValueByName(httpRequest,
					"APPASSERTION");
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
				String user = "d";
				List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
				GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
				gList.add(ga);
				userDetails = new User(user, "d", gList);

				UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
						userDetails, null, userDetails.getAuthorities());
				authentication.setDetails(new WebAuthenticationDetailsSource()
						.buildDetails(httpRequest));
				SecurityContextHolder.getContext().setAuthentication(
						authentication);

				boolean flag = TokenUtils.validateToken(cookieValue,
						userDetails);

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
		String cookieValue = null;
		if (completeCookies != null) {
			String[] cooks = completeCookies.split(";");
			Map<String, String> cookieMap = new HashMap<String, String>();
			for (String cookie : cooks) {
				String[] keyVal = cookie.split("=");
				cookieMap.put(keyVal[0].trim(), keyVal[1].trim());
			}
			cookieValue = cookieMap.get(cookieName);
		}
		return cookieValue;

	}
}