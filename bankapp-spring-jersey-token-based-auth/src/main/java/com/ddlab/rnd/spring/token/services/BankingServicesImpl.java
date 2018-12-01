package com.ddlab.rnd.spring.token.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.spring.token.entity.AccountDetails;

@Component(value="bankingService")
public class BankingServicesImpl implements BankingServices {

	@Override
	public AccountDetails getAccountDetails(String userId) {
		AccountDetails actDetails = new AccountDetails();
		actDetails.setUserId(String.valueOf(userId));
		actDetails.setAvlBalance(2000L);
		actDetails.setFirstName("Debadatta");
		actDetails.setLastName("Mishra");
		
		return actDetails;
	}
	
	@Override
	public UserDetails loadUserByUsername(String userName) {
		List<GrantedAuthority> gList = new ArrayList<GrantedAuthority>();
		GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
		gList.add(ga);
		UserDetails userDetails = new User(userName,"user",gList);
		return userDetails;
	}

}
