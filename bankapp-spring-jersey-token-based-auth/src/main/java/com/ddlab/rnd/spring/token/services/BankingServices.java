package com.ddlab.rnd.spring.token.services;

import org.springframework.security.core.userdetails.UserDetails;

import com.ddlab.rnd.spring.token.entity.AccountDetails;

public interface BankingServices {
	
	public AccountDetails getAccountDetails(String userId);
	
	public UserDetails loadUserByUsername(String userName);

}
