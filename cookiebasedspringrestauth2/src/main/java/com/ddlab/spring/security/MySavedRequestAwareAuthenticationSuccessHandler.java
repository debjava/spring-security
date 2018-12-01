package com.ddlab.spring.security;

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

public class MySavedRequestAwareAuthenticationSuccessHandler extends
		SimpleUrlAuthenticationSuccessHandler {

	private RequestCache requestCache = new HttpSessionRequestCache();

	@Override
	public void onAuthenticationSuccess(final HttpServletRequest request,
			final HttpServletResponse response,
			final Authentication authentication) throws ServletException,
			IOException {
		final SavedRequest savedRequest = requestCache.getRequest(request,
				response);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
		if (savedRequest == null) {
			String cookieToken = TokenUtils.createToken(userDetails);
			String ecodedCookieToken = new String(
					Base64.encodeBase64(cookieToken.getBytes()));
			// Create the cookie
			Cookie cook = new Cookie("APPASSERTION", ecodedCookieToken);
			cook.setPath("/");
			cook.setMaxAge(1 * 2 * 60);// 24 hours is 60 seconds x 60 minutes x
										// 24 hours
			cook.setVersion(1);
			response.addCookie(cook);
			clearAuthenticationAttributes(request);
			setDefaultTargetUrl("/api/home");
			getRedirectStrategy().sendRedirect(request, response, "/api/home");
			return;
		} else {
		}
		final String targetUrlParameter = getTargetUrlParameter();
		if (isAlwaysUseDefaultTargetUrl()
				|| (targetUrlParameter != null && StringUtils.hasText(request
						.getParameter(targetUrlParameter)))) {
			requestCache.removeRequest(request, response);
			clearAuthenticationAttributes(request);
			return;
		}

		clearAuthenticationAttributes(request);

		super.onAuthenticationSuccess(request, response, authentication);
	}

	public void setRequestCache(final RequestCache requestCache) {
		this.requestCache = requestCache;
	}

}
