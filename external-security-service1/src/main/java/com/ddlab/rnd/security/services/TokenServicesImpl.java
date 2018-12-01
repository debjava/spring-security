package com.ddlab.rnd.security.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.security.token.services.TokenUtils;

@Component("tokenService")
public class TokenServicesImpl implements TokenServices {

	@Autowired
	@Qualifier("authenticationManager")
	private AuthenticationManager authManager;
	
	@Autowired
	@Qualifier("userService")
	private UserServices services;
	
	@Override
	public String createToken(UserDetails userDetails) {
		return TokenUtils.createToken(userDetails);
	}
	
	@Override
	public String createToken(String userName) {
		UserDetails userDetails = services.loadUserByUsername(userName);
		return createToken(userDetails);
	}
	
	@Override
	public String getSecurityToken(String userName, String password) {
		UsernamePasswordAuthenticationToken authenticationToken =
				new UsernamePasswordAuthenticationToken(userName, password);
		System.out.println("Auth Manager---------->"+authManager);
		Authentication authentication = this.authManager.authenticate(authenticationToken);
		SecurityContextHolder.getContext().setAuthentication(authentication);
		
		return createToken(userName);
	}

}
