package com.ddlab.spring.rest.security;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

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

	public static boolean validateToken(String authToken, UserDetails userDetails)
	{
		boolean flag = true;
		System.out.println("Token Map--->"+tokenMap);
		if( tokenMap.containsKey(userDetails)) {
			
			String[] parts = authToken.split(":");
			long expires = Long.parseLong(parts[1]);
			System.out.println("Expires token :::"+expires);
			String signature = parts[2];
			System.out.println("Signature-------->"+signature);
			
			if( expires > System.currentTimeMillis() && signature.equals(TokenUtils.computeSignature(userDetails, expires)) )
				flag = true;
			else
			{
				flag = false;
			}
//			if (expires < System.currentTimeMillis()) {
//				flag = false;
//			}
//			
//			if (signature.equals(TokenUtils.computeSignature(userDetails, expires)))
//				flag = true;
			
		}
		else {
			flag = false;
		}

		

		return flag;
	}
	
	public static void removeUser(UserDetails userDetails) {
		tokenMap.remove(userDetails);
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