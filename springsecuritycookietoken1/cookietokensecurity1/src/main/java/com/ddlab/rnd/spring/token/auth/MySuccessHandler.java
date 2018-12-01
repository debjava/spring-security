package com.ddlab.rnd.spring.token.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
		System.out.println("---------MySuccessHandler-----------");
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		System.out.println("UserDetails :::"+userDetails);
		User authUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		System.out.println("authUser :::"+authUser);
		
		String cookieToken = TokenUtils.createToken(userDetails);
		System.out.println("Now cookie Token ::::"+cookieToken);
		String ecodedCookieToken = new String(
				Base64.encodeBase64(cookieToken.getBytes()));
		System.out.println("Now ecodedCookieToken ::::"+ecodedCookieToken);
		Cookie cook = new Cookie("APPASSERTION", ecodedCookieToken);
		cook.setPath("/");
		//1 day = 24 * 60 * 60
		//five minutes (5 * 60)
		//cook.setMaxAge(1 * 2 * 60);
		cook.setMaxAge(60);// 60 seconds
		cook.setVersion(1);
		
		response.addCookie(cook);
		
		getRedirectStrategy().sendRedirect(request, response, "/home.jsp");//working
		
	}
}
