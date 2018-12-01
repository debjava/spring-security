package com.ddlab.spring.rest.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface MyServices {
	
	public UserDetails loadUserByUserName(String userName) ;

}
