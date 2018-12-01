package org.codehustler.resource;

import java.net.URI;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth.consumer.client.OAuthRestTemplate;
import org.springframework.stereotype.Component;

import com.sun.jersey.spi.resource.Singleton;

/**
 * Very simple REST resource which takes the credentials from the index page
 * and uses them to Sign the request to the OAuth protected API.
 * The protected API is a GET so there is no data to send to it.
 *
 * @author	Alessandro Giannone
 * @version 1.0
 */
@Singleton
@Component
@Path( "/proxy")
@Consumes( MediaType.APPLICATION_JSON )
@Produces( MediaType.APPLICATION_JSON )
public class ProxyResource
{
	private String endpointUrl = "http://localhost:8080/provider/api/protected";

	// RestTemplate specific for OAuth authenticated requests
	@Autowired
	private OAuthRestTemplate oauthRestTemplate;

	public ProxyResource()
	{
//		initSSL();
	}


	@GET
	public String fetchOAuthProtectedData()
	{
		System.out.println("***************************************************************");
		try
		{
			return oauthRestTemplate.getForObject( URI.create( endpointUrl ), String.class );
		}
		catch( Exception ex )
		{
			return ex.toString();
		}
	}


	/**
	 * The purpose of the initialisation method is to modify the SSLContext
	 * and provide it to the HttpsURLConnection class. This will allow us to
	 * make SSL calls without using a trusted certificate which for testing
	 * purposes will suffice.
	 */
	private void initSSL()
	{
		try
		{
			SSLContext sc = SSLContext.getInstance("SSL");

			HostnameVerifier hv = new HostnameVerifier() {
				public boolean verify( String urlHostName, SSLSession session ) { return true; }
			};

			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public X509Certificate[] getAcceptedIssuers() { return null; }
				public void checkClientTrusted( X509Certificate[] certs, String authType ) {}
				public void checkServerTrusted( X509Certificate[] certs, String authType ) {}
			}};

			sc.init( null, trustAllCerts, new java.security.SecureRandom() );

			SSLSocketFactory sslSocketFactory = sc.getSocketFactory();
			HttpsURLConnection.setDefaultSSLSocketFactory(sslSocketFactory);
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
		}
		catch ( Exception e )
		{
			// Do some handling
		}
	}
}
