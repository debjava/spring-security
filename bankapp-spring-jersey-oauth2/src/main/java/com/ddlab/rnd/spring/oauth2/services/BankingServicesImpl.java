package com.ddlab.rnd.spring.oauth2.services;

import org.springframework.stereotype.Component;

import com.ddlab.rnd.spring.oauth2.entity.AccountDetails;

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

}
