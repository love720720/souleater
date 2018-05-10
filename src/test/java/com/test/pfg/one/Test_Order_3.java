package com.test.pfg.one;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.souleater.pfg.one.OrderService;
import com.souleater.util.LogUtils;

/** 
 * 问题已定位解决
 * 但是无法测算耗时
 * @author love720720@163.com
 * @date 2017年4月31日 上午10:09:25
 */
public class Test_Order_3 {
	
	private static int count = 10000;
	
	private static Logger log = LogUtils.getLogger();

	private static Map<String, Integer> map = new ConcurrentHashMap<String, Integer>();

	public static void main(String[] args) throws InterruptedException {
		long startTime = System.currentTimeMillis();
		
		ExecutorService es = Executors.newFixedThreadPool(100);
		
		for (int i = 0; i < count; i++) {
			es.execute(new Runnable() {
				
				public void run() {
					int count = 1;
					String orderNo = OrderService.getOrderNo3();
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
		es.awaitTermination(5, TimeUnit.SECONDS);

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
		
		long endTime = System.currentTimeMillis();
		
		log.info("共耗时:" + (endTime - startTime) + "毫秒");
	}
}
