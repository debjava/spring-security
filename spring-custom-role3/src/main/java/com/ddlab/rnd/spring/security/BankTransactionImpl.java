package com.ddlab.rnd.spring.security;

public class BankTransactionImpl implements BankTransaction {

	public void approveLoan(String actNo) {
		System.out.println("The loan has been approved for the account no : "+actNo);
	}

	public void openAccount(String customerName) {
		System.out.println("Hi "+customerName+" , your account will be activated within 24 hours");
		
	}

	public void resetNetBankingPassword(String customerName) {
		System.out.println("Hi "+customerName+", Administrator has reset your internet banking password "
				+ "and an email has been sent you");
	}
	
	public void checkAccountStatus(String actNo) {
		System.out.println("You available balance is 2000 INR");
	}
	
	public void suggestCreditCard(String customerName) {
		System.out.println("Hi "+customerName+" , a life time free Titanium credit card is available for you");
	}
	
	public void approveOverDraftFacility(String customerName) {
		System.out.println("Hi "+customerName+" , a overdraft facility is approved for you");
	}
	
	public void shutDownBank() {
		System.out.println("Bank has been shutdown by Government");
	}

}
