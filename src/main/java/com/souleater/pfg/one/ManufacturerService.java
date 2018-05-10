package com.souleater.pfg.one;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;

/**
 * 生产者线程
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:30:33
 */
public class ManufacturerService implements Runnable {

	private static Logger log = LogUtils.getLogger();
	
	private volatile boolean isRunning = true;
	
	private BlockingQueue<String> bq;
	
	public ManufacturerService(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	public void run() {
		try {
			log.info("启动生产者线程！");
			Random random = new Random();
			
			while (isRunning) {
				int index = random.nextInt(Constants.PRODUCTS.length);
				String productInfo = Constants.PRODUCTS[index];
				String[] split = productInfo.split("_");
				String productName = split[0];
				log.info("商家[" + Constants.MANUFACTURER_NAME[index] + "]正在生产商品 请等候...");
				Thread.sleep(random.nextInt(Constants.DEFAULT_RANGE_FOR_SLEEP));
				log.info("商家将[" + productName + "]投放商场...");
				boolean b = bq.offer(productInfo, 2, TimeUnit.SECONDS);
				if (!b) {
					log.info("商家生产了一个NG产品 继续干...");
				}
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			log.info("退出生产者线程！");
		}
	}
	
	public void stop() {
		isRunning = false;
	}
}