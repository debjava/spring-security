package com.ddlab.spring.models;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;

public interface BankApi {
	
	@PreAuthorize ("hasRole('ROLE_USER')")
	public WithdrawAmountModel withdrawAmount(String accountNo,int amount);
	
	@Secured({"ROLE_ADMIN"})
	public UserNamePasswordModel resetPasswod(BankCustomerModel customer); 
	
	@PreAuthorize ("hasRole('ROLE_STAFF')")
	public String approveLoan(BankCustomerModel customer);

}
