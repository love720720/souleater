package com.souleater.pfg.one;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;

/**
 * 
 * @author love720720@163.com
 * @date 2017年4月26日 上午9:19:14
 */
public class VariableService2 implements Runnable {

	private static Logger log = LogUtils.getLogger();
	
	private int i;// 实例变量

	public void run() {
		i = 4;
		log.info("获取i的值:" + i);
		i = 10;
		log.info("获取i * 2的值:" + i * 2);
	}
}