package com.zy.utils;

import android.content.Context;

import java.lang.reflect.Field;

/**
 *  利用反射机制获取资源在项目中的id
 * */
public class RUtil {

	private static RUtil rUtil;

	public static RUtil getInstance() {
		if (rUtil == null) {
			rUtil = new RUtil();
			return rUtil;
		}
		return rUtil;
	}
	/**
	 * 获取布局文件在工程中的id
	 * */
	public int getLayoutId(Context context,String Name) {
		return getResourceId(context,"layout", Name);
	}

	/**
	 * 获取图片资源在工程中的id
	 * */
	public int getDrawableId(Context context,String Name) {
		return getResourceId(context,"drawable", Name);
	}

	/**
	 * 获取控件在工程中的id
	 * */
	public int getId(Context context,String Name) {
		return getResourceId(context,"id", Name);
	}
	/**
	 * 获取控件在工程中的id
	 * */
	public int getRawId(Context context,String Name) {
		return getResourceId(context,"raw", Name);
	}
	/**
	 * 获取控件在工程中的id
	 * */
	public int getAnimId(Context context,String Name) {
		return getResourceId(context,"anim", Name);
	}

	/**
	 * 根据资源文件名获取相应实例
	 * 
	 * */
	public int getResourceId(Context context,String xmlFile, String xmlName) {
		Class localClass = null;
		Field localField = null;
		try {
			localClass = Class.forName(context.getPackageName() + ".R$"
					+ xmlFile);
			localField = localClass.getField(xmlName);
			return Integer.parseInt(localField.get(localField.getName())
					.toString());
		} catch (ClassNotFoundException exception) {
			return 0;
		} catch (SecurityException e) {
			return 0;
		} catch (NoSuchFieldException e) {
			return 0;
		} catch (NumberFormatException e) {
			return 0;
		} catch (IllegalArgumentException e) {
			return 0;
		} catch (IllegalAccessException e) {
			return 0;
		}

	}
}
