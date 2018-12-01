package org.codehustler.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.security.access.prepost.PreAuthorize;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Path("/protected")
@PreAuthorize("hasRole('ROLE_OAUTH')")
@Produces(MediaType.APPLICATION_JSON)
public class ProtectedResource {
	public ProtectedResource() {
	}

	@GET
	public String getOAuthProtectedData() {
		return "{\"protected\":\"This is some OAuth protected data coming from the Provider!\"}";
	}
}
