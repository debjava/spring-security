package com.ddlab.rnd.spring.token.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.HttpSessionRequestCache;
import org.springframework.security.web.savedrequest.RequestCache;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.util.StringUtils;

public class SuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws ServletException,
			IOException {
		System.out.println("***************MyAuthenticationSuccessHandler***********************");
		final SavedRequest savedRequest = requestCache.getRequest(request,
				response);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		if (savedRequest == null) {
			
			String cookieToken = TokenUtils.createToken(userDetails);
			System.out.println("Now cookie Token ::::"+cookieToken);
			String ecodedCookieToken = new String(
					Base64.encodeBase64(cookieToken.getBytes()));
			System.out.println("Now ecodedCookieToken ::::"+ecodedCookieToken);
			// Create the cookie
			Cookie cook = new Cookie("APPASSERTION", ecodedCookieToken);
//			Cookie cook = new Cookie("Set-Cookie", "APPASSERTION="+ecodedCookieToken);
			cook.setPath("/");
			//1 day = 24 * 60 * 60
			//five minutes (5 * 60)
			cook.setMaxAge(1 * 2 * 60);// 24 hours is 60 seconds x 60 minutes x
										// 24 hours
			cook.setVersion(1);
			
//			response.addHeader("APPASSERTION", ecodedCookieToken);
			
			response.addCookie(cook);
			
//			Cookie cook1 = new Cookie("Set-Cookie", "APPASSERTION="+ecodedCookieToken);
//			response.addCookie(cook1);
//			response.addHeader("Set-Cookie","APPASSERTION="+ecodedCookieToken);
//			response.setHeader("HATI",ecodedCookieToken);
			
//			response.addHeader("header-name", "value");
			
			System.out.println("All headers addedd ...........");
			
			clearAuthenticationAttributes(request);
//			setDefaultTargetUrl("/api/home");
//			getRedirectStrategy().sendRedirect(request, response, "/api/home");
//			setDefaultTargetUrl("/web/home");
//			getRedirectStrategy().sendRedirect(request, response, "/web/home");
			/*setDefaultTargetUrl("/home");
			getRedirectStrategy().sendRedirect(request, response, "/home");*/
			/*setDefaultTargetUrl("/api/home");
			getRedirectStrategy().sendRedirect(request, response, "/api/home");*/
			
//			setDefaultTargetUrl("/home.jsp");//working
			getRedirectStrategy().sendRedirect(request, response, "/home.jsp");//working
			
			return;
		} else {
			
		}
		final String targetUrlParameter = getTargetUrlParameter();
		System.out.println("11111111111111111111");
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils.hasText(request
						.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			clearAuthenticationAttributes(request);
			System.out.println("2222222222222222");
			return;
		}

		clearAuthenticationAttributes(request);
		System.out.println("33333333333333333");
		super.onAuthenticationSuccess(request, response, authentication);
	}

	public void setRequestCache(final RequestCache requestCache) {
		this.requestCache = requestCache;
	}

}
