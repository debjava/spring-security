package org.codehustler.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.resource.Singleton;

@Singleton
@Component
@Path("/proxy")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProxyResource {
	private String endpointUrl = "http://localhost:8080/provider/api/protected";

	@Autowired
	private OAuthRestTemplate oauthRestTemplate;

	public ProxyResource() {
	}

	@GET
	public String fetchOAuthProtectedData() {
		try {
			return oauthRestTemplate.getForObject(URI.create(endpointUrl),
					String.class);
		} catch (Exception ex) {
			return ex.toString();
		}
	}

}
