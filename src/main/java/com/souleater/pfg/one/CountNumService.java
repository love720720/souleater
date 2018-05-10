package com.souleater.pfg.one;

import java.util.concurrent.atomic.AtomicInteger;

/** 
 * 
 * @author love720720@163.com
 * @date 2017年4月27日 下午9:44:51
 */
public class CountNumService{

	private static volatile AtomicInteger count = new AtomicInteger();
	
	public static void run() {
		count.incrementAndGet();
	}
	
	public static int getCount(){
		return count.get();
	}
}