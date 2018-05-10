package com.souleater.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * log相关的工具类
 * @author love720720@163.com
 * @date 2017年5月8日 下午4:15:41
 */
public class LogUtils {

	public static Logger getLogger(){
		String className = Thread.currentThread().getStackTrace()[2].getClassName();
		return LoggerFactory.getLogger(className);
	}
}
