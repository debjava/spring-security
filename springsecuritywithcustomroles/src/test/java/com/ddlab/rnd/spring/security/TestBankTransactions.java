package com.ddlab.rnd.spring.security;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;

public class TestBankTransactions {
	
	private static BankTransaction bankTxn = null;
	
	@BeforeClass
	public static void setup() {
		@SuppressWarnings("resource")
		ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("beans.xml");
		bankTxn = (BankTransaction) applicationContext.getBean("bankTxn");
	}
	
	
	/**
	 * Loan can not be approved by Normal user
	 */
	@Test(expected=AccessDeniedException.class)
	public void testApproveLoanByNormalUser() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
		bankTxn.approveLoan("12345");
	}
	
	/**
	 * Loan can be approved by Admin
	 */
	@Test
	public void testApproveLoanByAdmin() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
		bankTxn.approveLoan("12345");
	}
	
	/**
	 * Loan can not be approved by Teller
	 */
	@Test(expected=AccessDeniedException.class)
	public void testApproveLoanByTeller() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
		bankTxn.approveLoan("12345");
	}
	
	/**
	 * Loan can be approved by Manager
	 */
	@Test
	public void testApproveLoanByManager() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
		bankTxn.approveLoan("12345");
	}
	
	/**
	 * Loan can not be approved by unknown user
	 */
	@Test(expected = AuthenticationCredentialsNotFoundException.class)
	public void testApproveLoanAnonymously() {
		SecurityUtil.logout();
		System.out.println("----->"+SecurityContextHolder.getContext().getAuthentication());
		bankTxn.approveLoan("12345");
	}
	
	//--------------------
	
	//All bank employee suggest a customer for a free credit card
	@Test
	public void testSuggestForCreditCardLoanByManager() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
		bankTxn.suggestCreditCard("John Abraham");
	}
	
	@Test
	public void testSuggestForCreditCardLoanByAdmin() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
		bankTxn.suggestCreditCard("John Abraham");
	}
	
	@Test
	public void testSuggestForCreditCardLoanByTeller() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
		bankTxn.suggestCreditCard("John Abraham");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testSuggestForCreditCardLoanByUser() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
		bankTxn.suggestCreditCard("John Abraham");
	}
	
	//Overdraft facility can only be approved by Bank Manager only and not by any other
	
	@Test(expected=AccessDeniedException.class)
	public void testOverDraftFacilityByUser() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
		bankTxn.approveOverDraftFacility("John Abraham");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testOverDraftFacilityByTeller() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
		bankTxn.approveOverDraftFacility("John Abraham");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testOverDraftFacilityByAdmin() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
		bankTxn.approveOverDraftFacility("John Abraham");
	}
	
	@Test
	public void testOverDraftFacilityByManager() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
		bankTxn.approveOverDraftFacility("John Abraham");
	}
	
	//A user can open account but bank employees should not open in the same bank
	
	@Test
	public void testOpenAccountByUser() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
		bankTxn.openAccount("John Abraham");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testOpenAccountByAdmin() {
		SecurityUtil.logout();
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
		bankTxn.openAccount("John Abraham");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testOpenAccountByManager() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
		bankTxn.openAccount("John Abraham");
	}
	
//	@Test(expected=AccessDeniedException.class)
	public void testOpenAccountByTeller() {
		SecurityUtil.logout();
//		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
		bankTxn.openAccount("John Abraham");
	}
	
	//Rest user's Internet banking password
	
	@Test(expected=AccessDeniedException.class)
	public void testResetNetBankingPasswordByTeller() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
		bankTxn.resetNetBankingPassword("John Abraham");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testResetNetBankingPasswordByUser() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
		bankTxn.resetNetBankingPassword("John Abraham");
	}
	
	@Test
	public void testResetNetBankingPasswordByManager() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
		bankTxn.resetNetBankingPassword("John Abraham");
	}
	
	@Test
	public void testResetNetBankingPasswordByAdmin() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
		bankTxn.resetNetBankingPassword("John Abraham");
	}
	
	//Account status can be checked by Bank Manager or Teller
	
	@Test
	public void testCheckAccountStatusByAdmin() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
		bankTxn.checkAccountStatus("12345");
	}
	
	@Test
	public void testCheckAccountStatusByManager() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
		bankTxn.checkAccountStatus("12345");
	}
	
	@Test
	public void testCheckAccountStatusByTeller() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
		bankTxn.checkAccountStatus("12345");
	}
	
	@Test(expected=AccessDeniedException.class)
	public void testCheckAccountStatusByUser() {
		SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
		bankTxn.checkAccountStatus("12345");
	}

}
