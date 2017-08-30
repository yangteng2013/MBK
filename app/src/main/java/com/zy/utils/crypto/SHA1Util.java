package com.zy.utils.crypto;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class SHA1Util {
	/**
	 * 获取安全hash值
	 * @param file
	 * @return
	 */
	public static String getSHA1(File file) {
		if (!file.isFile()) {
			return null;
		}

		MessageDigest digest = null;
		FileInputStream in = null;
		byte buff[] = new byte[1028];
		int len;

		try {
			digest = MessageDigest.getInstance("SHA-1");
			in = new FileInputStream(file);
			while ((len = in.read(buff)) != -1) {
				digest.update(buff, 0, len);
			}
			// BigInteger integer = new BigInteger(1,digest.digest());
			// return integer.toString();
			return byte2HexFormatted(digest.digest());

		} catch (IOException e) {
			e.printStackTrace();
			return null;
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
			return null;
		} finally{
			try {
				in.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 这里是将获取到得编码进行16进制转换
	private static String byte2HexFormatted(byte[] arr) {
		StringBuilder str = new StringBuilder(arr.length * 2);
		for (int i = 0; i < arr.length; i++) {
			String h = Integer.toHexString(arr[i]);
			int l = h.length();
			if (l == 1)
				h = "0" + h;
			if (l > 2)
				h = h.substring(l - 2, l);
			str.append(h.toUpperCase());
			if (i < (arr.length - 1))
				str.append(':');
		}
		return str.toString();
	}
}
