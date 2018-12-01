package org.codehustler.oauth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

/**
 * We need to setup a dummy {@link AuthenticationEntryPoint} so that
 * Spring Security generates a Security Filter Chain which the OAuth
 * package requires in order to setup the Consumer.
 *
 * @author Alessandro Giannone
 * @version 1.0
 */
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint
{
	public void commence( HttpServletRequest request, HttpServletResponse response, AuthenticationException failure )
			throws IOException, ServletException {}
}