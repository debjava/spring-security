package com.ddlab.spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class BankCustomerModel {
	
	@JsonProperty("accountNo")
	private String accountNo;
	
	@JsonProperty("userName")
	private String userName;
	
	@JsonProperty("userId")
	private String userId;
	
	public String getAccountNo() {
		return accountNo;
	}
	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
}
