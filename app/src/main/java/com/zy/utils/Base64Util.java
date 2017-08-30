package com.zy.utils;


import com.zy.utils.crypto.Base64;

/**
 * Base64工具类
* @author 田裕杰 
*  
*/
public class Base64Util {

	/**
	 * base64解密
	 * @param str 待解密串
	 * @return 解密后串
	 */
	public static String decipheringB64(String str) {
		if(str==null||"".equals(str)) {
			return "";
		}
		String oldUser = StringUtil.reverse(str);
		String result = FormatTools.getInstance().Bytes2String(
				Base64.decode(oldUser));
		return result;
	}
	
	/**
	 * base64加密
	 * @param str 待加密的字串
	 * @return 加密后字串
	 */
	public static String encryptB64(String str) {
		if(str==null||"".equals(str)) {
			return "";
		}
		// base64加密
		String nativeUsername = Base64.encode(FormatTools
				.getInstance().String2Bytes(str));
		// 字符串反转
		nativeUsername = StringUtil.reverse(nativeUsername);
		return nativeUsername;
	}
}
