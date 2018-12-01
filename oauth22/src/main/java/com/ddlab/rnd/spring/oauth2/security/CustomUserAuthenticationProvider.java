package com.ddlab.rnd.spring.oauth2.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public class CustomUserAuthenticationProvider implements AuthenticationProvider {

	@Override
	public Authentication authenticate(Authentication authentication)
			throws AuthenticationException {
		
		System.out.println("*********** CustomUserAuthenticationProvider **************");

		/*
		 * From this method, get the user details
		 * This class is tailor made, manually doing everything for 
		 * better customization
		 */
		if (authentication.getPrincipal().equals("user")
				&& authentication.getCredentials().equals("user")) {

			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			
			/*CustomUserPasswordAuthenticationToken auth = new CustomUserPasswordAuthenticationToken(
					authentication.getPrincipal(),
					authentication.getCredentials(), grantedAuthorities);*/
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorities);
			
			

			return auth;

		} else if (authentication.getPrincipal().equals("admin")
				&& authentication.getCredentials().equals("admin")) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			
			/*CustomUserPasswordAuthenticationToken auth = new CustomUserPasswordAuthenticationToken(
					authentication.getPrincipal(),
					authentication.getCredentials(), grantedAuthorities);*/
			
			
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorities);
			
			

			return auth;
		} else if (authentication.getPrincipal().equals("user1")
				&& authentication.getCredentials().equals("user1")) {
			List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
			
			
//			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
//			grantedAuthorities.add(ga);
			
			
			UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(authentication.getPrincipal(), authentication.getCredentials(), grantedAuthorities);
			
			/*CustomUserPasswordAuthenticationToken auth = new CustomUserPasswordAuthenticationToken(
					authentication.getPrincipal(),
					authentication.getCredentials(), grantedAuthorities);*/

			return auth;
		} else {
			throw new BadCredentialsException("Bad User Credentials.");
		}
	}

	@Override
	public boolean supports(Class<? extends Object> authentication) {

		return true;
	}

}
