package com.ddlab.rnd.spring.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

    //Person having designation more than MANAGER
    public static void approveLoan(BankTransaction bankTxn) {
//        SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
        SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.ADMIN);
        bankTxn.approveLoan("12345");
    }

    public static void openAccount(BankTransaction bankTxn) {
        SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.USER);
        bankTxn.openAccount("Deb");
    }

    public static void main(String[] args) {
        ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("beans.xml");
        BankTransaction bankTxn = (BankTransaction) applicationContext.getBean("bankTxn");

        approveLoan(bankTxn);
        openAccount(bankTxn);
    }
}
