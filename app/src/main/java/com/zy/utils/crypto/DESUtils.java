package com.zy.utils.crypto;

import android.util.Base64;

import java.security.Key;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

/**
 * @author zy DES：算法DES要求密钥长度为64位密钥, 有效密钥56位。64bits=8*8*1，即8个ascii字符。
 *         DESede：算法DESede要求的密钥位数为192位，即192bits=64*3=8*8*3，即24个ascii字符。
 *         Blowfish：算法Blowfish要求密钥长度为8--448字位，即8--448(bits)。即：1个到56个ascii字符
 */
public class DESUtils {

	private static final String CHARSET = "UTF-8";
	private static final String DES = "DES";
	private static final String KEY = "CSIINCBK";
	private static SecretKey secretkey = null;

	private static Key getKey() {

		if (secretkey == null) {
			byte[] bb = null;
			try {
				bb = KEY.getBytes(CHARSET);
				secretkey = new SecretKeySpec(bb, DES);
			} catch (Exception e) {

			}
		}
		return secretkey;
	}

	/**
	 * DESede加密
	 * 
	 * @param key
	 *            密钥 24个ascii字符。
	 * @throws Exception
	 */
	public static String encrypt(String source, String key) {
		String s = null;
		byte[] target = null;
		try {
			Key secretkey = new SecretKeySpec(key.getBytes(CHARSET), "DESede");
			Cipher cipher;

			cipher = Cipher.getInstance("DESede");

			cipher.init(Cipher.ENCRYPT_MODE, secretkey);
			byte[] center = source.getBytes(CHARSET);
			target = cipher.doFinal(center);
			s = Base64.encodeToString(target, Base64.DEFAULT);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return s;
	}

	/**
	 * DESede解密
	 * 
	 * @param key
	 *            密钥 24个ascii字符。
	 * @throws Exception
	 */
	public static String decrypt(String source, String key) throws Exception {
		String s = null;
		byte[] dissect = null;

		byte[] center = Base64.decode(source.getBytes(CHARSET), Base64.DEFAULT);
		Key secretkey = new SecretKeySpec(key.getBytes(CHARSET), "DESede");
		Cipher cipher = Cipher.getInstance("DESede");
		cipher.init(Cipher.DECRYPT_MODE, secretkey);
		dissect = cipher.doFinal(center);
		s = new String(dissect, CHARSET);

		return s;
	}

	/**
	 * 加密
	 */
	public static String encrypt(String source) {
		String s = null;
		byte[] target = null;
		try {
			byte[] center = source.getBytes(CHARSET);
			Key key = getKey();
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.ENCRYPT_MODE, key);
			target = cipher.doFinal(center);
			s = Base64.encodeToString(target, Base64.DEFAULT);
		} catch (Exception e) {
		}
		return s;
	}

	/**
	 * 解密
	 */
	public static String decrypt(String source) {
		String s = null;
		byte[] dissect = null;
		try {
			byte[] center = Base64.decode(source.getBytes(CHARSET), Base64.DEFAULT);
			Key key = getKey();
			Cipher cipher = Cipher.getInstance(DES);
			cipher.init(Cipher.DECRYPT_MODE, key);
			dissect = cipher.doFinal(center);
			s = new String(dissect, CHARSET);
		} catch (Exception e) {
		}
		return s;
	}
}
