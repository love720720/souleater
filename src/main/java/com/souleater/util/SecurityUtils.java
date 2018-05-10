package com.souleater.util;

import java.security.MessageDigest;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.SortedMap;

import org.apache.commons.lang3.StringUtils;

import com.souleater.init.Constants;

/**
 * 签名相关工具类
 * @author love720720@163.com
 * @date 2017年5月23日 下午3:22:52
 */
public class SecurityUtils {

	/**
	 * 进行SHA-1签名
	 * 传入一个数组 根据排序进行签名
	 * @param args
	 * @return
	 */
	public static String getSHA1(final String ... args) {
		int len;
		if (args == null || (len = args.length) <= 0) {
			throw new NullPointerException("待签名内容不可为空");
		}
		StringBuffer sb = new StringBuffer();
		Arrays.sort(args);
		for (int i = 0; i < len; i++) {
			sb.append(args[i]);
		}
		return getSHA1(sb.toString());
	}
	
	/**
	 * 进行SHA-1签名
	 * 传入排序map 根据排序进行签名
	 * @param map
	 * @return
	 */
	public static String getSHA1(final SortedMap<String, String> map) {
		if (map == null || map.size() <= 0) {
			throw new NullPointerException("待签名内容不可为空");
		}
		StringBuffer sb = new StringBuffer();
		Iterator<Entry<String, String>> iterator = map.entrySet().iterator();
		while (iterator.hasNext()) {
			Entry<String, String> next = iterator.next();
			String key = next.getKey();
			String value = next.getValue();
			sb.append(key + "=" + value + "&");
		}
		return getSHA1(StringUtils.removeEnd(sb.toString(), "&"));
	}

	/**
	 * 对字符串进行SHA-1签名
	 * @param inputString
	 * @return
	 */
	public static String getSHA1(final String inputString) {
		String result = Constants.EMPTY;
		if (StringUtils.isBlank(inputString)) {
			throw new NullPointerException("待签名内容不可为空");
		}
		try {
			MessageDigest sha1 = MessageDigest.getInstance("SHA-1");
			sha1.update(inputString.getBytes("GBK"));

			byte[] digest = sha1.digest();
			StringBuffer hexstr = new StringBuffer();
			String shaHex = Constants.EMPTY;
			for (int i = 0; i < digest.length; i++) {
				shaHex = Integer.toHexString(digest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexstr.append(0);
				}
				hexstr.append(shaHex);
			}
			result = hexstr.toString().toUpperCase();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
}