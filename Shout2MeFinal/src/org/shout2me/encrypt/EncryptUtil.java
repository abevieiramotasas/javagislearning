package org.shout2me.encrypt;

import java.math.BigInteger;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public class EncryptUtil {

	public static final String HASHED_DELIMITER = "|";
	private static SecureRandom random = new SecureRandom();

	public static String generateRandomString() {
		return new BigInteger(130, random).toString(32);
	}

	public static String makePassword(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}

	public static Boolean verifyPassowrd(String password, String hashed_password) {
		return BCrypt.checkpw(password, hashed_password);
	}
	

	public static String makeHashed(String value, String salt) {
		try {
			Mac mac = Mac.getInstance("HmacSHA256");
			SecretKeySpec secret = new SecretKeySpec(salt.getBytes(),
					"HmacSHA256");
			mac.init(secret);
			byte[] digest = mac.doFinal(value.getBytes());
			StringBuilder sb = new StringBuilder(digest.length * 2);
			String s = "";
			for (byte b : digest) {
				s = Integer.toHexString(b);
				if (s.length() == 1) {
					sb.append('0');
				}
				sb.append(s);
			}
			String hashed = sb.toString();
			sb = new StringBuilder();
			sb.append(value);
			sb.append(HASHED_DELIMITER);
			sb.append(hashed);
			return sb.toString();
		} catch (NoSuchAlgorithmException e) {
			return null;
		} catch (InvalidKeyException e) {
			return null;
		}
	}
}
