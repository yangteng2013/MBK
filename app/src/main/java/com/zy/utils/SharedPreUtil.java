/**   
* @Title: SharedPreUtil.java 
* @Package cn.com.csii.util 
* @author A18ccms A18ccms_gmail_com
* @date 2015-3-12 下午2:43:25 
* @version V1.0   
*/
package com.zy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

/**
 * SharedPreferences工具类
 * @author 田裕杰
 * 
 */
public class SharedPreUtil {

	/**
	 * sharedpreferences 存储文件名
	 * */
	private static final String SPConfig = "app_config";
	private SharedPreferences preferences;
	
	public SharedPreUtil(Context context){
		preferences = context.getSharedPreferences(SPConfig, Context.MODE_PRIVATE);
	}
	/**
	 * 获取存储的boolean值（如果取不到则返回false）
	 * @param key
	 * @return boolean
	 * */
	public boolean getToggleState(String key) {
		return preferences.getBoolean(key, false);
	}
	/**
	 * 存储Boolean到本地
	 * @param key
	 * @param state
	 */
	public void setToggleState(String key, boolean state) {
		preferences.edit().putBoolean(key, state).commit();
	}
	/**
	 * 获取存储的String值（如果取不到则返回""字符串）
	 *  @param key
	 * @return String
	 * */
	public String getToggleString(String key) {
		return preferences.getString(key, "");
	}
	/**
	 * 存储String到本地
	 * @param key
	 * @param value
	 * */
	public void setToggleString(String key, String value) {
		preferences.edit().putString(key, value).commit();
	}
	/**
	 * 获取存储的int值（如果取不到则返回-1）
	 * @param key
	 * @return int
	 * */
	public int getToggleInt(String key) {
		return preferences.getInt(key, -1);
	}
	/**
	 * 存储int到本地
	 * @param key
	 * @param value
	 * */
	public void setToggleInt(String key, int value) {
		preferences.edit().putInt(key, value).commit();
	}
	
	/**
	 * 获取Editor对象
	 * @return Editor
	 */
	public Editor getToggleEdit() {
		return preferences.edit();
	}
	
}
