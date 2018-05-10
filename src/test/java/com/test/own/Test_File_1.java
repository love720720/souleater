package com.test.own;

import org.slf4j.Logger;

import com.souleater.util.FileUtils;
import com.souleater.util.LogUtils;

/** 
 * 文件相关测试类
 * @author love720720@163.com
 * @date 2017年5月23日 上午9:49:31
 */
public class Test_File_1 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) {
		String value = FileUtils.getSysConfigValue("appId");
		log.info("appId = " + value);
	}
}
