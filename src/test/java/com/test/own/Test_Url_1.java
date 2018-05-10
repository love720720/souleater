package com.test.own;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;
import com.souleater.util.UrlUtils;

/**
 * 
 *
 * @author love720720@163.com
 * @date 2017年8月9日 下午3:39:22
 */
public class Test_Url_1 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) {
		String url = "http://192.168.3.86/kyhz/kyhzLogin.action?%257B%2522loginTime%2522%253A1487813270023%252C%2522userId%2522%253A%252214891%2522%252C%2522planId%2522%253A%2522%2522%252C%2522loginType%2522%253A%25222%2522%252C%2522isWx%2522%253A%25221%2522%252C%2522sign%2522%253A%2522C301266548E45E8858DB05B3EE1B66B4%2522%257D";
		log.info(UrlUtils.URLDecodeSecond(url));
	}
}
