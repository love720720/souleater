package com.souleater.util;

import org.apache.commons.lang3.StringUtils;
import org.json.JSONException;
import org.json.JSONObject;

import com.souleater.init.Constants;

/** 
 * json相关的一些工具类
 * @author love720720@163.com
 * @date 2017年8月9日 上午10:32:17
 */
public class JsonUtils {
	
	/**
	 * 获取json对应的值
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getNotBlankJsonValue(JSONObject json, String key) {
		String value = getJsonValue(json, key);
		if(StringUtils.isBlank(value)){
			throw new NullPointerException("JSON中[" + key + "]值不可为空");
		}
		return value;
	}
	
	/**
	 * 获取json对应的值
	 * @param json
	 * @param key
	 * @return
	 */
	public static String getJsonValue(JSONObject json, String key) {
		if(StringUtils.isBlank(key)){
			throw new NullPointerException("JSON key不可为空");
		}
		
		if(!json.has(key)){
			throw new NullPointerException("JSON中未包含[" + key + "]键");
		}
		String value = Constants.EMPTY;
		try {
			value = json.getString(key);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return value;
	}
}