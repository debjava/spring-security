package com.ddlab.rnd.spring.token.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component(value="userServices")
public class UserServicesImpl implements UserServices {
	
	@Override
	public UserDetails loadUserByUsername(String userName) {
		List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
		gList.add(ga);
		UserDetails userDetails = new User(userName,"user",gList);
		return userDetails;
	}
}
