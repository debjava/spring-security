package com.ddlab.rnd.spring.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    //Person having designation more than MANAGER
    public static void approveLoan(BankTransaction bankTxn) {
        SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
//        SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
        bankTxn.approveLoan("12345");
    }

    public static void openAccount(BankTransaction bankTxn) {
//        SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
        bankTxn.openAccount("Deb");
    }
    
    public static void restPassword(BankTransaction bankTxn) {
    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
    	bankTxn.resetNetBankingPassword("customerName");
    }
    
    public static void checkAccount(BankTransaction bankTxn) {
    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
    	bankTxn.checkAccountStatus("111");
    }
    
    public static void suggestCard(BankTransaction bankTxn) {
    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
    	bankTxn.suggestCreditCard("Deb");
    }
    
    public static void approveOverDraftFacility(BankTransaction bankTxn) {
    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
    	bankTxn.approveOverDraftFacility("Deb");
    }
    
    public static void closeBank(BankTransaction bankTxn) {
    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.GOVT);
    	bankTxn.shutDownBank();
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("beans.xml");
        BankTransaction bankTxn = (BankTransaction) applicationContext.getBean("bankTxn");

        approveLoan(bankTxn);
        openAccount(bankTxn);
        restPassword(bankTxn);
        checkAccount(bankTxn);
        suggestCard(bankTxn);
        approveOverDraftFacility(bankTxn);
        //Only Govt can close the Bank
        closeBank(bankTxn);
    }
}
