package cookiebasedspringrestauth2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Hex;

import com.ddlab.spring.security.TokenUtils;

public class Temp1 {

	public static String computeSignature(String userName, String pwd, long expires)
	{
		System.out.println("****************Compute Signature****************");
		System.out.println(userName+"-----"+pwd+"-------"+expires);
		StringBuilder signatureBuilder = new StringBuilder();
		signatureBuilder.append(userName);
		signatureBuilder.append(":");
		signatureBuilder.append(expires);
		signatureBuilder.append(":");
		signatureBuilder.append(pwd);
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
	
	public static void main(String[] args) {
		
		String computeSignature = computeSignature("d", "d", 1435257012534L);
		System.out.println(computeSignature);
		computeSignature = computeSignature("d", "d", 1435257012534L);
		System.out.println(computeSignature);
		
	}
	
	

}
