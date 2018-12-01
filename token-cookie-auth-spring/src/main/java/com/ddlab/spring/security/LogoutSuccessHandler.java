package com.ddlab.spring.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

public class LogoutSuccessHandler extends SimpleUrlLogoutSuccessHandler {

	@Override
	public void onLogoutSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		Cookie cook = new Cookie("APPASSERTION", "someJunkValue");
		cook.setPath("/");
		cook.setMaxAge(0);// 24 hours is 60 seconds x 60 minutes x 24 hours
		cook.setVersion(1);
		response.addCookie(cook);

		// do whatever you want
		super.onLogoutSuccess(request, response, authentication);
	}

}