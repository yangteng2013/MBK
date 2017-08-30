///**
// * PassWordUtil.java
// * com.csii.util
// * ──────────────────────────────────
// * 	Function:	[Add Description of Function]
// *	@Date		2013-9-14
// * ──────────────────────────────────
// * Copyright (c) 2013, L.L All Rights Reserved.
// */
//
//package com.zy.utils;
//
//import com.csii.powerenter.PEEditTextAttrSet;
//import com.heabk.mbk.R;
//
///**
// * 密码控件属性初始化工具类
//* @author 田裕杰
//*
//*/
//public class PassWordUtil {
//	/********** 密码控件设置 ***********/
//	/**
//	 * 登录密码控件属性
//	 *
//	 * @return PEEditTextAttrSet
//	 */
//	public static PEEditTextAttrSet getLoginAttrset() {
//		PEEditTextAttrSet attr=new PEEditTextAttrSet();
////		attr.name="password1";
////		attr.clearWhenOpenKbd=true;//清除输入框内容
////		attr.softkbdType=0;//软键盘类型， 初始化时指定。0：全键盘；1：数字键盘；2：全键盘与数字键盘互换模式。默认为0全键盘。
////		attr.softkbdMode=0;//设置软键盘按键DOWN时，按键是否变化。0： 变化  1： 不变化； 默认为0。
////		attr.kbdRandom=false;//设置软键盘按键位置是否随机，初始化时指定。可选值：true、false，默认选择随机（true）。
////		attr.kbdVibrator=true;//设置触屏震动, 可选值：true、false默认为false，触屏不震动
////		attr.whenMaxCloseKbd=false;//当输入长度达到maxlength是否自动关闭键盘，默认false可选值：true、false
////		attr.minLength=6;
////		attr.maxLength=10;
////		attr.encryptType=0;	//设置密码的加密类型，初始化时指定
//
//		attr.name="login";//控件名
//		attr.clearWhenOpenKbd=true;//密码键盘打开时，是否清空输入框
//		attr.softkbdType=0;//键盘类型
//		attr.softkbdMode=0;//键盘是否开启触控效果，0开启，1关闭 ，2开启，但无放大效果
//		attr.kbdRandom=false;//键盘按键顺序是否随机
//		attr.kbdVibrator=true;//触控键盘是否震动
//		attr.whenMaxCloseKbd=false;//当密码到达最大长度时是否自动关闭键盘
//		attr.minLength=6;//密码最小长度
//		attr.maxLength=10;//密码最大长度
//		attr.encryptType=0;//密码加密类型
//		attr.inScrollView=false;//密码是否在ScrollView中
//		return attr;
//	}
//
//	/**
//	 * 交易密码控件属性
//	 * @return
//	 */
//	public static PEEditTextAttrSet getTradeAttrset() {
//		// 密码键盘配置
//		PEEditTextAttrSet attr=new PEEditTextAttrSet();
//		attr.name="password2";
//		attr.clearWhenOpenKbd=true;//清除输入框内容
//		attr.softkbdType=1;//软键盘类型， 初始化时指定。0：全键盘；1：数字键盘；2：全键盘与数字键盘互换模式。默认为0全键盘。
//		attr.softkbdMode=0;//设置软键盘按键DOWN时，按键是否变化。0： 变化  1： 不变化； 默认为0。
//		attr.kbdRandom=false;//设置软键盘按键位置是否随机，初始化时指定。可选值：true、false，默认选择随机（true）。
//		attr.kbdVibrator=true;//设置触屏震动, 可选值：true、false默认为false，触屏不震动
//		attr.whenMaxCloseKbd=false;//当输入长度达到maxlength是否自动关闭键盘，默认false可选值：true、false
//		attr.minLength=6;
//		attr.maxLength=6;
//		attr.encryptType=0;	//设置密码的加密类型，初始化时指定
//		return attr;
//	}
//
//	/**
//	 * 页面密码页面所需可变密码控件属性
//	 * @param type       :键盘类型
//	 * @param random     :是否生成随机键盘
//	 * @param max        :最大输入长度
//	 * @param min        :最小输入长度
//	 * @param name       :名称,只有在一个页面使用多个控件时起区分作用.
//	 */
//	public static PEEditTextAttrSet getTradeAttrsets(short type,boolean random,int max,int min,String name) {
//		// 密码键盘配置
////		System.out.println("random"+random);
//		PEEditTextAttrSet attr=new PEEditTextAttrSet();
//		attr.name=name;
//		attr.clearWhenOpenKbd=true;//清除输入框内容
//		attr.softkbdType=type;//软键盘类型， 初始化时指定。0：全键盘； ；2：全键盘与数字键盘互换模式。默认为0全键盘。
//		attr.softkbdMode=0;//设置软键盘按键DOWN时，按键是否变化。0： 变化  1： 不变化； 默认为0。
//		attr.kbdRandom=true;//设置软键盘按键位置是否随机，初始化时指定。可选值：true、false，默认选择随机（true）。
//		attr.kbdVibrator=false;//设置触屏震动, 可选值：true、false默认为false，触屏不震动
//		attr.whenMaxCloseKbd=false;//当输入长度达到maxlength是否自动关闭键盘，默认false可选值：true、false
//		attr.minLength=min;
//		attr.maxLength=max;
//		attr.encryptType=0;	//设置密码的加密类型，初始化时指定
//		return attr;
//	}
//
//	/**
//	 * 页面所需可变密码控件属性
//	 * @param type       :键盘类型
//	 * @param random     :是否生成随机键盘
//	 * @param max        :最大输入长度
//	 * @param min        :最小输入长度
//	 * @param name       :名称,只有在一个页面使用多个控件时起区分作用.
//	 */
//	public static PEEditTextAttrSet getTradeAttrsets2(short type,boolean random,int max,int min,String name) {
//		// 密码键盘配置
//		PEEditTextAttrSet attr=new PEEditTextAttrSet();
//		attr.name=name;
//		attr.clearWhenOpenKbd=true;//清除输入框内容
//		attr.softkbdType=type;//软键盘类型， 初始化时指定。0：全键盘；1：数字键盘；2：全键盘与数字键盘互换模式。默认为0全键盘。
//		attr.softkbdMode=0;//设置软键盘按键DOWN时，按键是否变化。0： 变化  1： 不变化； 默认为0。
//		attr.kbdRandom=true;//设置软键盘按键位置是否随机，初始化时指定。可选值：true、false，默认选择随机（true）。
//		attr.kbdVibrator=true;//设置触屏震动, 可选值：true、false默认为false，触屏不震动
//		attr.whenMaxCloseKbd=false;//当输入长度达到maxlength是否自动关闭键盘，默认false可选值：true、false
//		attr.minLength=min;
//		attr.maxLength=max;
//		attr.encryptType=0;	//设置密码的加密类型，初始化时指定
//		attr.inScrollView=true;//密码是否在ScrollView中
//		return attr;
//	}
//
//}
