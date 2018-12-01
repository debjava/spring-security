package com.ddlab.rnd.spring.token.entity;

import java.io.IOException;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

@XmlRootElement(name = "AccountDetails")
@XmlAccessorType(XmlAccessType.FIELD)
public class AccountDetails {

	@XmlElement(name = "firstName")
	@JsonProperty("firstName")
	private String firstName;

	@XmlElement(name = "lastName")
	@JsonProperty("lastName")
	private String lastName;

	@XmlElement(name = "userId")
	@JsonProperty("userId")
	private String userId;

	@XmlElement(name = "availableBalance")
	@JsonProperty("availableBalance")
	private long avlBalance;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public long getAvlBalance() {
		return avlBalance;
	}

	public void setAvlBalance(long avlBalance) {
		this.avlBalance = avlBalance;
	}

	public String toJSON() {
		ObjectMapper mapper = new ObjectMapper();
		try {
			return mapper.writeValueAsString(this);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (JsonMappingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}

}
