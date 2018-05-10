package com.test.pfg.one;

import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;

import com.souleater.pfg.one.WorkerService;
import com.souleater.util.LogUtils;

/**
 * 测试CountDownLatch
 * @author love720720@163.com
 * @date 2017年5月8日 下午3:22:11
 */
public class Test_CountDownLatch_1 {
	
	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) throws InterruptedException {
		CountDownLatch cdl = new CountDownLatch(3);  
        WorkerService ws1 = new WorkerService("张三", 5000, cdl);
        WorkerService ws2 = new WorkerService("李四", 8000, cdl);
        WorkerService ws3 = new WorkerService("高巍", 3000, cdl);
        ws1.start(); 
        ws2.start();
        ws3.start();
        cdl.await();
        log.info("收工");
	}
}