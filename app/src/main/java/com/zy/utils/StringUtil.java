package com.zy.utils;

/**
 * 字符串工具类
 * @author 田裕杰
 *
 */
public class StringUtil {

	/**
	 * 字符串反转
	 * */
	public static String reverse(String str){
		return new StringBuffer(str).reverse().toString();
	}
	/**
	 * 格式化String
	 * 例如:12345678901格式后123****8901
	 * @return String
	 */
	public static String formatString(String data) {
		if(data!=null&&!"".equals(data) && data.length()>7) {
			return data.substring(0, 3)+"****"+data.substring(data.length()-4);
		}else {
			return data;
		}
	}
	
	public static String formatString2(String data) {
		if(data!=null&&!"".equals(data)) {
			
			String  str= "";
			for (int i = 0; i < data.length()-9; i++) {
				str+="*";
			}
			return data.substring(0, 5)+str+data.substring(data.length()-4);
		}else {
			return data;
		}
	}
	public static String formatString3(String data) {
		if(data!=null&&!"".equals(data) && data.length()>9) {
			return data.substring(0, 5)+"****"+data.substring(data.length()-4);
		}else {
			return data;
		}
	}
}
