package com.ddlab.rnd.spring.token.resources;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		super(BankResources.class,JacksonFeature.class);
		
	}
}