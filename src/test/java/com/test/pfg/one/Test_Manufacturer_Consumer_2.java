package com.test.pfg.one;

import org.slf4j.Logger;

import com.souleater.pfg.one.ConsumerYSService;
import com.souleater.pfg.one.ManufacturerYSService;
import com.souleater.pfg.one.SyncStack;
import com.souleater.util.LogUtils;

/**
 * 测试生产者、消费者线程
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:33:39
 */
public class Test_Manufacturer_Consumer_2 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) throws InterruptedException {
		SyncStack ss = new SyncStack();
		
		ManufacturerYSService myss = new ManufacturerYSService(ss,20000L);
		Thread t1 = new Thread(myss);
		t1.start();
		
		ConsumerYSService cyss = new ConsumerYSService(ss,30000L);
		Thread t2 = new Thread(cyss);
		t2.start();
		
		t1.join();
		t2.join();
		
		log.info("线程已完成 pushCount = " + ss.getPushCount());
		log.info("线程已完成 popCount = " + ss.getPopCount());
    }
}