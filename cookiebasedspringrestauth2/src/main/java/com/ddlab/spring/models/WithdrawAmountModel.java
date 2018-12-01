package com.ddlab.spring.models;
public class WithdrawAmountModel {
	
	private int amountWithdrawn;
	private int availableBalance;
	
	public int getAmountWithdrawn() {
		return amountWithdrawn;
	}
	public void setAmountWithdrawn(int amountWithdrawn) {
		this.amountWithdrawn = amountWithdrawn;
	}
	public int getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(int availableBalance) {
		this.availableBalance = availableBalance;
	}
}
