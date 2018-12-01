package org.codehustler.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.security.access.prepost.PreAuthorize;

import com.sun.jersey.spi.resource.Singleton;

/**
 * This is a sample REST resource which is protected by Spring Security OAuth
 *
 * @author	Alessandro Giannone
 * @version 1.0
 */
@Singleton
@Path( "/protected")
@PreAuthorize("hasRole('ROLE_OAUTH')")
@Produces( MediaType.APPLICATION_JSON )
public class ProtectedResource
{
	public ProtectedResource() {}

	/**
	 * Returns a sample piece of data which is protected by OAuth
	 *
	 * @param request
	 * @return
	 */
	@GET
	public String getOAuthProtectedData()
	{
		// This response is for simplicity. Don't build your JSON responses like this! =)
		return "{\"protected\":\"This is some OAuth protected data coming from the Provider!\"}";
	}
}
