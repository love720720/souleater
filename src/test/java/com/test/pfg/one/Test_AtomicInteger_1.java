package com.test.pfg.one;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.souleater.pfg.one.CountNumService;
import com.souleater.util.LogUtils;

/**
 * 测试原子类
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:29:16
 */
public class Test_AtomicInteger_1 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) throws InterruptedException {
		ExecutorService es = Executors.newFixedThreadPool(10);
		for (int i = 0; i < 10000; i++) {
			
			es.execute(new Runnable() {
				
				@Override
				public void run() {
					CountNumService.run();
				}
			});
		}
		
		es.shutdown();
		es.awaitTermination(2, TimeUnit.SECONDS);
		log.info(String.valueOf(CountNumService.getCount()));
	}
}