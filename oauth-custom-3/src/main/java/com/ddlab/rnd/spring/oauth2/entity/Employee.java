package com.ddlab.rnd.spring.oauth2.entity;

import org.codehaus.jackson.annotate.JsonProperty;

public class Employee {
	
	@JsonProperty("ID")
	private String empId ;
	
	@JsonProperty("FIRST_NAME")
	private String firstName;
	
	@JsonProperty("LAST_NAME")
	
	private String lastName;
	public String getEmpId() {
		return empId;
	}
	public void setEmpId(String empId) {
		this.empId = empId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
}
