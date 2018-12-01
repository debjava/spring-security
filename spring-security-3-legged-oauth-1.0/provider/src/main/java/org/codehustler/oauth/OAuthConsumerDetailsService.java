package org.codehustler.oauth;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth.common.OAuthException;
import org.springframework.security.oauth.provider.ConsumerDetails;
import org.springframework.security.oauth.provider.ConsumerDetailsService;

/**
 * This provides the OAuth process with the relevant {@link ConsumerDetails}.
 * The data is currently hard coded, but can easily be modified to retrieve
 * your data from a database.
 *
 * @author Alessandro Giannone
 * @version 1.0
 *
 */
public class OAuthConsumerDetailsService implements ConsumerDetailsService
{
	// TODO: The details below are hard coded. You should use the consumerKey to lookup those values in your database.
	String consumerName = "John";
	String consumerKey = "3a4393c3da1a4e316ee66c0cc61c71";
	String consumerSecret = "fe1372c074185b19c309964812bb8f3f2256ba514aea8a318f05f9d703d524b8";

	public OAuthConsumerDetailsService() {}


	@Override
	public ConsumerDetails loadConsumerByConsumerKey( String consumerKey ) throws OAuthException
	{
		if( consumerKey == null )
			throw new OAuthException("No credentials found for the consumer key [" + consumerKey + "]");

		if( !consumerKey.equals( this.consumerKey ) )
			throw new OAuthException("No credentials found for the consumer key [" + consumerKey + "]");

		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add( new SimpleGrantedAuthority("ROLE_OAUTH") );

		return new OAuthConsumerDetails(
				consumerName,
				consumerKey,
				consumerSecret,
				authorities );
	}

}
