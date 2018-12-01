package com.ddlab.spring.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserService {
	
	public UserDetails getUserByUserName(String userName);

}
