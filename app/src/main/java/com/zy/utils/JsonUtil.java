package com.zy.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 *  json解析工具类（fastjson）
 * @author tyj
 * 
 * 
 */
public class JsonUtil {

	/**
	 *  把JSON文本parse为JSONArray或JSONObject
	 * @param text
	 *            json文本
	 * @return JSONArray
	 * */
	public static Object parse(String text) {
		return JSON.parse(text);
	}

	/**
	 *  把JSON文本parse成JavaBean集合
	 * @param text
	 *            json文本
	 * @param clazz
	 *            javaBean类
	 * @return list列表
	 * */
//	public static List<?> parseArray(String text, Class<?> clazz) {
//		return parseArray(text, clazz);
//	}

	/**
	 *  将JavaBean序列化为JSON文本
	 * @param object
	 *            javaBean对象
	 * @return String
	 * */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}

	/**
	 *  将JavaBean序列化为带格式的JSON文本
	 * @param object
	 *            javaBean对象
	 * @param prettyFormat
	 *            是否带格式
	 * @return String
	 * */
	public static String toJSONString(Object object, boolean prettyFormat) {
		return JSON.toJSONString(object, prettyFormat);
	}

	/**
	 *  将JavaBean转换为JSONObject或者JSONArray。
	 * @param object
	 *            javaBean对象
	 * @return Object
	 * */
	public static Object toJSON(Object object) {
		return JSON.toJSON(object);
	}

	/**
	 *  把JSON文本parse为JavaBean
	 * @param text
	 *            json文本
	 * @param clazz
	 *            javaBean类
	 * @return T
	 * */
	public static <T> T parseObject(String text, Class clazz) {
		return (T) JSON.parseObject(text, clazz);
	}

	/**
	 *  JSONArray转List<Map<String, Object>>
	 * @param jsonArr
	 *            JSONArray数据
	 * @return List<Map<String, Object>>
	 * */
	public static List<Map<String, Object>> parseJSON2List(JSONArray jsonArr) {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Iterator<Object> it = jsonArr.iterator();
		while (it.hasNext()) {
			JSONObject json2 = (JSONObject) it.next();
			list.add(parseJSON2MapO(json2));
		}
		return list;
	}

	/**
	 *  JSONObject转Map<String, String>
	 * @param json
	 *            JSONObject数据
	 * @return Map<String, String>
	 * */
	public static Map<String, String> parseJSON2Map(JSONObject json) {
		Map<String, String> map = new HashMap<String, String>();
		// 最外层解析
		for (String k : json.keySet()) {
			String v = json.getString(k);
			map.put(k.toString(), v);
		}
		return map;
	}
	/**
	 *  JSONObject转Map<String, Object>
	 * @param json
	 *            JSONObject数据
	 * @return Map<String, Object>
	 * */
	public static Map<String, Object> parseJSON2MapO(JSONObject json) {
		Map<String, Object> map = new HashMap<String, Object>();
		// 最外层解析
		for (Object k : json.keySet()) {
			Object v = json.get(k);
			// 如果内层还是数组的话，继续解析
			if (v instanceof JSONArray) {
				List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
				Iterator<Object> it = ((JSONArray) v).iterator();
				while (it.hasNext()) {
					JSONObject json2 = (JSONObject) it.next();
					list.add(parseJSON2MapO(json));
				}
				map.put(k.toString(), list);
			} else {
				map.put(k.toString(), v);
			}
		}
		return map;
	}
}
