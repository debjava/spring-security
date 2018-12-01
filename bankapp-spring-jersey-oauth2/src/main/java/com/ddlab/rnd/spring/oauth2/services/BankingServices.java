package com.ddlab.rnd.spring.oauth2.services;

import com.ddlab.rnd.spring.oauth2.entity.AccountDetails;

public interface BankingServices {
	
	public AccountDetails getAccountDetails(String userId);

}
