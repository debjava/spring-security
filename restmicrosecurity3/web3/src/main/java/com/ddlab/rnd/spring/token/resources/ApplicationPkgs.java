package com.ddlab.rnd.spring.token.resources;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.ddlab.rnd.spring.token.auth.AuthenticationResource;

public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		super(BankResources.class,JacksonFeature.class,AuthenticationResource.class);
		
	}
}