package com.ddlab.rnd.spring.security;

public enum ROLETYPE {
	
	GOVT("APP_GOVT") , USER("APP_USER") , ADMIN("APP_ADMIN") , MANAGER("APP_MANAGER") , TELLER("APP_TELLER");
	
	private String role;
	
	private ROLETYPE(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return role;
	}
}
