package com.ddlab.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Override
	public UserDetails getUserByUserName(String userName) {
		UserDetails userDetails = null;
		//A temporary solution, it should come from database
		List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
		String password = null;
		if(userName.equals("d")) {
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
			gList.add(ga);
			password = "d";
		}
		else if(userName.equals("admin")) {
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_ADMIN");
			gList.add(ga);
			password = "admin";
		}
		else if(userName.equals("s")) {
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_STAFF");
			gList.add(ga);
			password = "s";
		}
		userDetails = new User(userName, password, gList);
		
		return userDetails;
	}

}
