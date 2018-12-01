package com.ddlab.rnd.spring.security;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
//    	ApplicationContext applicationContext =  new ClassPathXmlApplicationContext(new String[]{"beans1.xml","application-security.xml"});
    	ApplicationContext applicationContext =  new ClassPathXmlApplicationContext("beans.xml");
//    	SecurityUtil.loginAs("Deb", "pqrs", "ROLE_USER");
//    	SecurityUtil.loginAs("Deb", "pqrs", "ROLE_MANAGER");
//    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.TELLER);
    	SecurityUtil.loginAs("Deb", "pqrs", ROLETYPE.MANAGER);
    	
    	BankTransaction bankTxn = (BankTransaction) applicationContext.getBean("bankTxn");
    	bankTxn.approveLoan("12345");
//    	bankTxn.openAccount("Deb");
//    	bankTxn.checkAccountStatus("12345");
    }
}
