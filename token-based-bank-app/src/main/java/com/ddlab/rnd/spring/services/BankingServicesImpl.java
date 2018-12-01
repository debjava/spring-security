package com.ddlab.rnd.spring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import com.ddlab.rnd.security.services.TokenServices;
import com.ddlab.rnd.spring.token.entity.AccountDetails;
import com.ddlab.rnd.spring.token.entity.TokenTransfer;

@Component("bankingService")
public class BankingServicesImpl implements BankingServices {
	
	@Autowired
	@Qualifier("tokenService")
	private	TokenServices tokenService;

	@Override
	public TokenTransfer getToken(String username, String password) {
		return new TokenTransfer( tokenService.getSecurityToken(username, password));
	}

	@Override
	public AccountDetails getAccountDetails(String userId) {
		AccountDetails actDetails = new AccountDetails();
		actDetails.setUserId(String.valueOf(userId));
		actDetails.setAvlBalance(2000L);
		actDetails.setFirstName("Debadatta");
		actDetails.setLastName("Mishra");
		
		return actDetails;
	}

}
