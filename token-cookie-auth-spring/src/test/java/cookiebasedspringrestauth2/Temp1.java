package cookiebasedspringrestauth2;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.codec.Base64;
import org.springframework.security.crypto.codec.Hex;

import com.ddlab.spring.security.TokenUtils;

public class Temp1 {

	
	public static void main(String[] args) {
		
		String computeSignature = "APPASSERTION=YWRtaW46MTQzNTM0Njg3NzQwODpmNzZiOGM1MTcxMjFjODU4YTVhMmRhZjFjM2NhOTgzMg==";
		String[] cooks = computeSignature.split("=",2);
		for (String cookie : cooks) {
			System.out.println(cookie);
		}
	}
	
	

}
