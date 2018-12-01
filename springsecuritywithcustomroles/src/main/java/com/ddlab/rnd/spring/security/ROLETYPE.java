package com.ddlab.rnd.spring.security;

public enum ROLETYPE {
	
	USER("ROLE_USER") , ADMIN("ROLE_ADMIN") , MANAGER("ROLE_MANAGER") , TELLER("ROLE_TELLER");

//	USER("USER") , ADMIN("ADMIN") , MANAGER("MANAGER") , TELLER("TELLER");
	
	private String role;
	
	private ROLETYPE(String role) {
		this.role = role;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Enum#toString()
	 */
	@Override
	public String toString() {
		return role;
	}

}
