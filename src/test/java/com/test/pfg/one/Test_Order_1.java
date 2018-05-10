package com.test.pfg.one;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;

import com.souleater.pfg.one.OrderService;
import com.souleater.util.LogUtils;

/** 
 * 该线程下获取订单号 本是测试订单号在多线程并发下重复问题
 * 存在不终止的情况 存在无线循环
 * 当线程启用过多的时候 会将cpu占满
 * @author love720720@163.com
 * @date 2017年4月31日 上午10:09:25
 */
public class Test_Order_1 {

	private static int count = 10000;
	
	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		
		final Map<String, Integer> map = new HashMap<String, Integer>();
		
		ExecutorService es = Executors.newFixedThreadPool(100);
		
		for (int i = 0; i < count; i++) {
			es.execute(new Runnable() {
				
				public void run() {
					int count = 1;
					String orderNo = OrderService.getOrderNo1();
					if(map.containsKey(orderNo)){
						count = map.get(orderNo);
						count++;
					}
					map.put(orderNo,count);
					log.info("获取的订单号为：" + orderNo);
				}
			});
		}
		es.shutdown();
		
		Thread.sleep(5000);
		
		Iterator<Entry<String, Integer>> iterator = map.entrySet().iterator();
		while(iterator.hasNext()){
			Entry<String, Integer> next = iterator.next();
			String orderNo = next.getKey();
			Integer count = next.getValue();
			
			if(count > 1){
				log.info("该订单号[" + orderNo + "]重复 次数[" + count + "]");
			}
		}
		log.info("方法结束");
		map.clear();
		
		long endTime = System.currentTimeMillis();
		
		log.info("共耗时:" + (endTime - startTime) + "毫秒");
	}
}
