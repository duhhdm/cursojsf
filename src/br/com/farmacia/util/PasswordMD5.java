package br.com.farmacia.util;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordMD5 {
	
	public String conversaoStringMD5(String senha) {
		
		MessageDigest mDigest;
		try {
			
			mDigest = MessageDigest.getInstance("MD5");
			
			byte[] valorMD5 = mDigest.digest(senha.getBytes("UTF-8"));
			StringBuffer sb = new StringBuffer();
			for(byte b: valorMD5) {
				sb.append(Integer.toHexString((b & 0xFF) | 0x100).substring(1,3));
			}
			return sb.toString();
		}catch(NoSuchAlgorithmException e) {
			e.getStackTrace();
			return null;
		}catch(UnsupportedEncodingException e) {
			e.getStackTrace();
			return null;
		}
	}
}
