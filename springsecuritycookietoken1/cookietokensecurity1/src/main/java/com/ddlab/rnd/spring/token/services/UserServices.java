package com.ddlab.rnd.spring.token.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserServices {
	
	public UserDetails loadUserByUsername(String userName);

}
