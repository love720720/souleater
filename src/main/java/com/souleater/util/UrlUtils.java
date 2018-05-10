package com.souleater.util;

import com.souleater.exp.BaseException;
import com.souleater.init.Constants;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;


public class UrlUtils {

	/**
	 * url编码
	 * @param input
	 * @return
	 * @throws BaseException 
	 */
	public static String URLEncode(String input) {
		if(StringUtils.isBlank(input)){
			throw new NullPointerException("待编码内容不可为空");
		}
		try {
			input = URLEncoder.encode(input, Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return input;
	}
	
	/**
	 * url解码
	 * @param input
	 * @return
	 * @throws BaseException 
	 */
	public static String URLDecode(String input) {
		if(StringUtils.isBlank(input)){
			throw new NullPointerException("待解码内容不可为空");
		}
		try {
			input = URLDecoder.decode(input, Constants.CHARSET);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return input;
	}

	/**
	 * url2次编码
	 * @param input
	 * @return
	 * @throws BaseException 
	 */
	public static String URLEncodeSecond(String input) {
		return URLEncode(URLEncode(input));
	}
	
	/**
	 * url2次解码
	 * @param input
	 * @return
	 * @throws BaseException 
	 */
	public static String URLDecodeSecond(String input) {
		return URLDecode(URLDecode(input));
	}
}