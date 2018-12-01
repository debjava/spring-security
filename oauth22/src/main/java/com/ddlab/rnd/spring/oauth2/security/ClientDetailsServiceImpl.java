package com.ddlab.rnd.spring.oauth2.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.BaseClientDetails;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.security.oauth2.provider.NoSuchClientException;
import org.springframework.stereotype.Service;

@Service
public class ClientDetailsServiceImpl implements ClientDetailsService {

	/*
	 * From this method, get the user details
	 * This class is tailor made, manually doing everything for 
	 * better customization
	 */
	@Override
	public ClientDetails loadClientByClientId(String clientId)
			throws OAuth2Exception {
		
		System.out.println("-----------------ClientDetailsServiceImpl--------------------------");

		if (clientId.equals("client1")) {

			List<String> authorizedGrantTypes = new ArrayList<>();
			authorizedGrantTypes.add("password");
			authorizedGrantTypes.add("refresh_token");
			authorizedGrantTypes.add("client_credentials");
			
//			GrantedAuthorityImpl ga = new GrantedAuthorityImpl("ROLE_USER");
//			authorizedGrantTypes.add(ga);
			

			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId("client1");
			clientDetails.setClientSecret("client1");
			clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);
			
			
			
			
			GrantedAuthority ga = new SimpleGrantedAuthority("ROLE_USER");
			List<GrantedAuthority> gaList = new ArrayList<GrantedAuthority>();
			gaList.add(ga);
			
			clientDetails.setAuthorities(gaList);
			
			
			List<String> scopeList = new ArrayList<String>();
			scopeList.add("read");
			scopeList.add("write");
			scopeList.add("trust");
			
			clientDetails.setScope(scopeList);
			
			
			
			

			return clientDetails;

		} else if (clientId.equals("client2")) {

			List<String> authorizedGrantTypes = new ArrayList<>();
			authorizedGrantTypes.add("password");
			authorizedGrantTypes.add("refresh_token");
			authorizedGrantTypes.add("client_credentials");

			BaseClientDetails clientDetails = new BaseClientDetails();
			clientDetails.setClientId("client2");
			clientDetails.setClientSecret("client2");
			clientDetails.setAuthorizedGrantTypes(authorizedGrantTypes);

			return clientDetails;
		}

		else {
			throw new NoSuchClientException("No client with requested id: "
					+ clientId);
		}
	}

}
