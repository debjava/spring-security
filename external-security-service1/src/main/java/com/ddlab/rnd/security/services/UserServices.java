package com.ddlab.rnd.security.services;

import org.springframework.security.core.userdetails.UserDetails;

public interface UserServices {
	
	public UserDetails loadUserByUsername(String userName);

}
