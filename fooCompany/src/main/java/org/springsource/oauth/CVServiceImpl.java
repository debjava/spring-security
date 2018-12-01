package org.springsource.oauth;

import java.net.URI;

import org.springframework.security.oauth2.client.OAuth2RestTemplate;

//import org.springframework.security.oauth2.consumer.OAuth2RestTemplate;
//import org.springframework.security.oauth2.consumer.token.OAuth2ClientTokenServices;

public class CVServiceImpl implements CVService {

	private String cvURL = null;

	private OAuth2RestTemplate cvRestTemplate;
	
//	private OAuth2ClientTokenServices tokenServices;
	
	public String getCVContent() {
		
//		System.out.println("************************************************");
//		System.out.println("cvRestTemplate.getResource().getClientId()--->"+cvRestTemplate.getResource());
//		System.out.println("cvRestTemplate.getResource().getClientId()--->"+cvRestTemplate.getResource().getClientId());
//		System.out.println("cvRestTemplate.getResource().getClientId()--->"+cvRestTemplate.getResource().getAccessTokenUri());
//		System.out.println("cvRestTemplate.getResource().getClientId()--->"+cvRestTemplate.getResource().getBearerTokenName());
//		System.out.println("cvRestTemplate.getResource().getClientId()--->"+cvRestTemplate.getResource().getGrantType());
//		
		byte[] content = (getCvRestTemplate().getForObject(URI.create(cvURL), byte[].class));
		return new String(content);
		
		
//		return "OKKKKKKKKKK";
	}
	
	public String getCvURL() {
		return cvURL;
	}
	public void setCvURL(String cvURL) {
		this.cvURL = cvURL;
	}
	
	
	
	
	public OAuth2RestTemplate getCvRestTemplate() {
		return cvRestTemplate;
	}
	public void setCvRestTemplate(OAuth2RestTemplate cvRestTemplate) {
		this.cvRestTemplate = cvRestTemplate;
	}
	
	
//	public OAuth2ClientTokenServices getTokenServices() {
//		return tokenServices;
//	}
//	
//	
//	public void setTokenServices(OAuth2ClientTokenServices tokenServices) {
//		this.tokenServices = tokenServices;
//	}

	
	
}
