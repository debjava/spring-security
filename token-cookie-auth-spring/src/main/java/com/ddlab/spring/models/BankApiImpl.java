package com.ddlab.spring.models;

public class BankApiImpl implements BankApi {

	@Override
	public WithdrawAmountModel withdrawAmount(String accountNo, int amount) {
		WithdrawAmountModel amountModel = new WithdrawAmountModel();
		amountModel.setAmountWithdrawn(amount);
		amountModel.setAvailableBalance(50000);
		return amountModel;
	}

	@Override
	public UserNamePasswordModel resetPasswod(BankCustomerModel customer) {
		UserNamePasswordModel unamePwdModel = new UserNamePasswordModel();
		unamePwdModel.setPassword("abcd$pass00rd");
		unamePwdModel.setUserName(customer.getUserName());
		return unamePwdModel;
	}

	@Override
	public String approveLoan(BankCustomerModel customer) {
		return "The loan account for "
				+ customer.getUserName()
				+ " has been approved and \n+ loan account has been created in the system";
	}
	
}
