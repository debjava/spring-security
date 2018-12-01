package com.ddlab.rnd.spring.token.auth;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.codec.binary.Base64;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

public class TokenUtils
{

	public static final String MAGIC_KEY = "obfuscate";
	
	private static Map<UserDetails,String> tokenMap = new ConcurrentHashMap<UserDetails,String>();

	public static String createToken(UserDetails userDetails)
	{
		/* Expires in one hour */
//		long expires = System.currentTimeMillis() + 1000L * 60 * 60;
		/* Expires in 2 min */
		long expires = System.currentTimeMillis() + 1000L * 60 * 2 ;

		StringBuilder tokenBuilder = new StringBuilder();
		tokenBuilder.append(userDetails.getUsername());
		tokenBuilder.append(":");
		tokenBuilder.append(expires);
		tokenBuilder.append(":");
		tokenBuilder.append(TokenUtils.computeSignature(userDetails, expires));
		
		tokenMap.put(userDetails, tokenBuilder.toString());

		return tokenBuilder.toString();
	}


	public static String computeSignature(UserDetails userDetails, long expires)
	{
		System.out.println("Username and password");
		System.out.println(userDetails.getUsername()+"------"+userDetails.getPassword());
//		Object obj = SecurityContextHolder.getContext().getAuthentication().getCredentials();
//		System.out.println(obj);
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userDetails.getUsername());
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(userDetails.getPassword());
		signatureBuilder.append(":");
		signatureBuilder.append(TokenUtils.MAGIC_KEY);

		MessageDigest digest;
		try {
			digest = MessageDigest.getInstance("MD5");
		} catch (NoSuchAlgorithmException e) {
			throw new IllegalStateException("No MD5 algorithm available!");
		}

		return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
	}


	public static String getUserNameFromToken(String authToken)
	{
		if (null == authToken) {
			return null;
		}

		String[] parts = authToken.split(":");
		return parts[0];
	}
	
	public static boolean isTokenValid(String authToken, UserDetails userDetails)
	{
		boolean flag = true;
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		System.out.println("Expires token :::"+expires);
		String signature = parts[2];
		System.out.println("Signature-------->"+signature);
//		System.out.println("Token Map--->"+tokenMap);
		
		if (tokenMap.containsKey(userDetails)
				&& tokenMap.get(userDetails).equals(authToken)) {
			System.out.println("Yes...........");
			
			boolean tempFlag = expires > System.currentTimeMillis();
			System.out.println("Temp Flag --->"+tempFlag);
			System.out.println("Computed Signature---->"+TokenUtils.computeSignature(
							userDetails, expires));
			
			if (expires > System.currentTimeMillis()
					&& signature.equals(TokenUtils.computeSignature(
							userDetails, expires))) {
				System.out.println("222222222-Yes");
				flag = true;
			}
			else
			{
				flag = false;
			}
		}
		else {
			flag = false;
		}
		return flag;
	}

	@Deprecated
	public static boolean validateToken(String authToken, UserDetails userDetails)
	{
		boolean flag = true;
		String[] parts = authToken.split(":");
		long expires = Long.parseLong(parts[1]);
		System.out.println("Expires token :::"+expires);
		String signature = parts[2];
		System.out.println("Signature-------->"+signature);
//		System.out.println("Token Map--->"+tokenMap);
		
		if (tokenMap.containsKey(userDetails)
				&& tokenMap.get(userDetails).equals(authToken)) {
			if (expires > System.currentTimeMillis()
					&& signature.equals(TokenUtils.computeSignature(
							userDetails, expires)))
				flag = true;
			else
			{
				flag = false;
			}
		}
		else {
			flag = false;
		}
		return flag;
	}
	
	public static void removeUser(String authToken,UserDetails userDetails) {
		//Validate and then remove it.
		if(validateToken(authToken, userDetails))
			tokenMap.remove(userDetails);
	}
	
	public static void removeUser(UserDetails userDetails) {
		//Validate and then remove it.
		tokenMap.remove(userDetails);
	}
	
	
	public static String extractAuthTokenFromRequest(HttpServletRequest httpRequest) {
		/* Get token from header */
		String authToken = httpRequest.getHeader("X-Auth-Token");
		System.out.println("Actual authToken ->"+authToken);
		/* If token not found get it from request parameter */
		if (authToken == null) {
			authToken = httpRequest.getParameter("token");
			System.out.println("Auth Token from token ->"+authToken);
		}
		if( authToken == null) {
			//Get it from cookie
			authToken = getCookieValueByName(httpRequest,"APPASSERTION");
			System.out.println("Auth Token from Cookie ->"+authToken);
		}
		return authToken;
	}
	
	private static String getCookieValueByName(HttpServletRequest httpRequest,String cookieName) {
		String cookieValue = null;
		String completeCookies = httpRequest.getHeader("Cookie");
		if( completeCookies != null ) {
			completeCookies = completeCookies.replaceAll("\"", "");
			if (completeCookies != null) {
				String[] cooks = completeCookies.split(";");
				Map<String, String> cookieMap = new HashMap<String, String>();
				for (String cookie : cooks) {
					String[] keyVal = cookie.split("=",2);
					cookieMap.put(keyVal[0].trim(), keyVal[1].trim());
				}
				cookieValue = cookieMap.get(cookieName);
			}
		}
		//Decode the Cookie Token
		if( cookieValue != null )
			cookieValue = new String(Base64.decodeBase64(cookieValue.getBytes()));
		return cookieValue;
	}
	
	
	
	
	
	

//	public static boolean validateToken(String authToken, UserDetails userDetails)
//	{
//		
//		String[] parts = authToken.split(":");
//		long expires = Long.parseLong(parts[1]);
//		System.out.println("Expires token :::"+expires);
//		String signature = parts[2];
//		System.out.println("Signature-------->"+signature);
//
//		if (expires < System.currentTimeMillis()) {
//			return false;
//		}
//
//		return signature.equals(TokenUtils.computeSignature(userDetails, expires));
//	}
}