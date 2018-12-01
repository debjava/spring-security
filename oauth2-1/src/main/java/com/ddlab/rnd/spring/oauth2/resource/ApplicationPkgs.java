package com.ddlab.rnd.spring.oauth2.resource;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationPkgs extends ResourceConfig {
	
	public ApplicationPkgs() {
		
		super(EmployeeInfo.class,JacksonFeature.class);
	}

}
