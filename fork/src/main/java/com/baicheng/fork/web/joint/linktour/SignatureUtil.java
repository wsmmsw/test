package com.baicheng.fork.web.joint.linktour;

import java.math.BigInteger;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * 加密的一些方法
 * 
 * @author wsm 2018年4月20日下午12:51:02
 */
public class SignatureUtil {
	/**
	 * 获取data
	 */
	public static String hashPBKDF2(String algorithm, String password, String salt, int iterations, int len)
			throws Exception {
		PBEKeySpec pbeKeySpec = new PBEKeySpec(password.toCharArray(), salt.getBytes(), iterations, len * 4);
		SecretKeyFactory kFactory = SecretKeyFactory.getInstance(algorithm);
		SecretKey key = kFactory.generateSecret(pbeKeySpec);
		byte[] res = key.getEncoded();
		return toHex(res);
	}

	private static String toHex(byte[] array) {
		BigInteger bi = new BigInteger(1, array);
		String hex = bi.toString(16);
		int paddingLength = (array.length * 2) - hex.length();
		if (paddingLength > 0)
			return String.format("%0" + paddingLength + "d", 0) + hex;
		else
			return hex;
	}
	/**
	 * 
	 * @param value
	 * @param key
	 * @return
	 * @throws Exception
	 */
	public static String hash_hmac(String value, String key) throws Exception {
		byte[] keyBytes = key.getBytes();
		SecretKeySpec signingKey = new SecretKeySpec(keyBytes, "HmacSHA1");
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(signingKey);
		byte[] rawHmac = mac.doFinal(value.getBytes());
		return byte2hex(rawHmac);
	}

	private static String byte2hex(final byte[] b) {
		String hs = "";
		String stmp = "";
		for (int n = 0; n < b.length; n++) {
			stmp = (Integer.toHexString(b[n] & 0xFF));
			if(stmp.length() == 1) 
                hs = hs + "0" + stmp;
             else 
                hs=hs+stmp;  
		}
		return hs;
	}
}
