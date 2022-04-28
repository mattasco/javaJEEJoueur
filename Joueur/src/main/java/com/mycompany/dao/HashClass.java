package com.mycompany.dao;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HashClass {
	public static String sha1(String input) {
		String sha1String = null;
		
		try {
			MessageDigest msgDigest = MessageDigest.getInstance("SHA-1");
			msgDigest.update(input.getBytes("UTF-8"), 0, input.length());
			byte[] sha1 = msgDigest.digest(input.getBytes());
			sha1String = String.format("%032X", new BigInteger(1, sha1));
		} catch (UnsupportedEncodingException | NoSuchAlgorithmException e) {
				Logger.getLogger(HashClass.class.getName()).log(Level.SEVERE, null, e);
			}
	return sha1String;
	}
}

