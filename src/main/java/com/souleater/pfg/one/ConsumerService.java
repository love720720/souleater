package com.souleater.pfg.one;

import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;

/**
 * 消费者线程
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:30:42
 */
public class ConsumerService implements Runnable {
	
	private static Logger log = LogUtils.getLogger();
	
	private BlockingQueue<String> bq;
	
	public ConsumerService(BlockingQueue<String> bq) {
		this.bq = bq;
	}

	public void run() {
		try {
			log.info("启动消费者线程！");
			Random random = new Random();
			
			for(; ;) {
				String consumerName = Constants.CONSUMER_NAME[random.nextInt(Constants.CONSUMER_NAME.length)];
				log.info(consumerName + "正在商场选购商品...");
				String productInfo = bq.poll(5, TimeUnit.SECONDS);
				if (null == productInfo) {
					log.info("商场断货了 倒闭了");
					break;
				}
				String[] split = productInfo.split("_");
				String productName = split[0];
				String price = split[1];
				log.info(consumerName + "将商品[" + productName + "]购买下来了 共花费" + price);
				Thread.sleep(random.nextInt(Constants.DEFAULT_RANGE_FOR_SLEEP));
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
			Thread.currentThread().interrupt();
		} finally {
			log.info("退出消费者线程！");
		}
	}
}