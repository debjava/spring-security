package com.ddlab.rnd.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface TokenServices {
	
	String getSecurityToken(String userName , String password);
	
	String createToken(UserDetails userDetails);
	
	String createToken(String userName);

}
