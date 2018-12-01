package com.ddlab.spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ddlab.spring.models.BankApi;
import com.ddlab.spring.models.BankCustomerModel;
import com.ddlab.spring.models.UserNamePasswordModel;
import com.ddlab.spring.models.WithdrawAmountModel;

@Controller
@RequestMapping(value = "/bankapi")
public class BankApiController {
	
	@Autowired
	private BankApi bankApi;
	
	@RequestMapping(value = "/withdraw/{accountNo}/{amount}", method = RequestMethod.GET, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody()
	public WithdrawAmountModel withdraw(
			@PathVariable(value = "accountNo") String accountNo,
			@PathVariable(value = "amount") Integer amount) {
		return bankApi.withdrawAmount(accountNo, amount);
	}
	
	@RequestMapping(value = "/reset", method = RequestMethod.PUT, produces = { MediaType.APPLICATION_JSON_VALUE })
	@ResponseBody()
	public UserNamePasswordModel resetPasswod(@RequestBody BankCustomerModel customer) {
		return bankApi.resetPasswod(customer);
	}
}
