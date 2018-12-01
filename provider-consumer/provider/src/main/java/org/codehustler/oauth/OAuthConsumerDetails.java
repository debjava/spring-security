package org.codehustler.oauth;

import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth.common.signature.SharedConsumerSecretImpl;
import org.springframework.security.oauth.common.signature.SignatureSecret;
import org.springframework.security.oauth.provider.ConsumerDetails;

/**
 * This object contains the {@link ConsumerDetails}.
 *
 * @author Alessandro Giannone
 * @version 1.0
 *
 */
public class OAuthConsumerDetails implements ConsumerDetails
{
	private static final long serialVersionUID = 1L;
	private final String consumerName;
	private final String consumerKey;
	private final SignatureSecret signatureSecret;
	private final List<GrantedAuthority> authorities;

	/**
	 * Constructor
	 *
	 * @param consumerName		The OAuth consumer name
	 * @param consumerKey		The OAuth consumer key (public key)
	 * @param signatureSecret	The OAuth secret (private key)
	 * @param authorities		The list of authorities
	 */
	public OAuthConsumerDetails( String consumerName, String consumerKey,
			String signatureSecret, List<GrantedAuthority> authorities )
	{
		this.consumerName = consumerName;
		this.consumerKey = consumerKey;
		this.signatureSecret = new SharedConsumerSecretImpl(signatureSecret);
		this.authorities = authorities;
	}


	/**
	 * Get the consumer name
	 */
	public String getConsumerName()
	{
		return consumerName;
	}


	/**
	 * Get the consumer key (public key)
	 * @return
	 */
	public String getConsumerKey()
	{
		return consumerKey;
	}


	/**
	 * Get the secret (private key)
	 */
	public SignatureSecret getSignatureSecret()
	{
		return signatureSecret;
	}


	/**
	 * Get the list of authorities
	 */
	public List<GrantedAuthority> getAuthorities()
	{
		return authorities;
	}
}
