package com.ddlab.rnd.spring.security;

public enum ROLETYPE {
	
	GOVT("ROLE_GOVT") , USER("ROLE_USER") , ADMIN("ROLE_ADMIN") , MANAGER("ROLE_MANAGER") , TELLER("ROLE_TELLER");
	
	private String role;
	
	private ROLETYPE(String role) {
		this.role = role;
	}
	
	@Override
	public String toString() {
		return role;
	}
}
