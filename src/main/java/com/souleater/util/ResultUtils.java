package com.souleater.util;

import org.json.JSONException;
import org.json.JSONObject;

import com.souleater.init.Constants;

/** 
 * 通用相关的一些工具类
 * @author love720720@163.com
 * @date 2017年5月7日 上午10:32:17
 */
public class ResultUtils {
	
	private static final String RECODE = "reCode";
	private static final String REINFO = "reInfo";
	
	/**
	 * 获取ajax返回信息
	 * @return
	 */
	public static String getSuccessResult(){
		return getSuccessResult(Constants.SUCCESS_MSG);
	}
	
	/**
	 * 获取ajax返回信息
	 * @return
	 */
	public static String getSuccessResult(String reInfo){
		return getResult(Constants.SUCCESS_CODE, reInfo);
	}
	
	/**
	 * 获取ajax返回信息
	 * @return
	 */
	public static String getFailureResult(){
		return getFailureResult(Constants.FAILURE_MSG);
	}
	
	/**
	 * 获取ajax返回信息
	 * @return
	 */
	public static String getFailureResult(String reInfo){
		return getFailureResult(Constants.SYS_EXP_CODE, reInfo);
	}
	
	/**
	 * 获取ajax返回信息
	 * @return
	 */
	public static String getFailureResult(int reCode, String reInfo){
		return getResult(reCode, reInfo);
	}
	
	/**
	 * 获取ajax返回信息
	 * @return
	 */
	private static String getResult(int reCode, String reInfo){
		String result = Constants.EMPTY;
		try {
			JSONObject json = new JSONObject();
			json.put(RECODE, reCode);
			json.put(REINFO, reInfo);
			
			result = json.toString();
		} catch (JSONException e) {
			result = "{" + RECODE + ": " + Constants.SYS_EXP_CODE + "," + REINFO + ": " + Constants.FAILURE_MSG + "}";
		}
		return result;
	}
}