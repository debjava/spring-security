//package com.ddlab.rnd.spring.token.auth;
//
//import java.security.MessageDigest;
//import java.security.NoSuchAlgorithmException;
//
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.crypto.codec.Hex;
//
//public class Test1 {
//	
//	public static String computeSignature(String userDetails, long expires)
//	{
//		StringBuilder signatureBuilder = new StringBuilder();
//		signatureBuilder.append(userDetails);
//		signatureBuilder.append(":");
//		signatureBuilder.append(expires);
//		signatureBuilder.append(":");
//		signatureBuilder.append(userDetails.getPassword());
//		signatureBuilder.append(":");
//		signatureBuilder.append(TokenUtils.MAGIC_KEY);
//
//		MessageDigest digest;
//		try {
//			digest = MessageDigest.getInstance("MD5");
//		} catch (NoSuchAlgorithmException e) {
//			throw new IllegalStateException("No MD5 algorithm available!");
//		}
//
//		return new String(Hex.encode(digest.digest(signatureBuilder.toString().getBytes())));
//	}
//
//	public static void main(String[] args) {
//
//	}
//
//}
