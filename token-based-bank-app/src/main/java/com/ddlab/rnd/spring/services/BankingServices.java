package com.ddlab.rnd.spring.services;

import com.ddlab.rnd.spring.token.entity.AccountDetails;
import com.ddlab.rnd.spring.token.entity.TokenTransfer;


public interface BankingServices {
	
	TokenTransfer getToken(String username, String password) ;
	
	AccountDetails getAccountDetails(String userId);

}
