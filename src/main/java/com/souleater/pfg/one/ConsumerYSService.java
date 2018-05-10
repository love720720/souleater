package com.souleater.pfg.one;

import java.util.Random;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;

/**
 * 消费者线程
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:30:42
 */
public class ConsumerYSService implements Runnable {
	
	private static Logger log = LogUtils.getLogger();
	
	private SyncStack ss;

	private long runTime;

	private final static long stateTime = System.currentTimeMillis();
	
	public ConsumerYSService(SyncStack ss,final long runTime) {
		this.ss = ss;
		this.runTime = runTime;
	}

	public void run() {
		log.info("启动消费者线程！");
		
		try {
			while(!Thread.currentThread().isInterrupted()){
				if(System.currentTimeMillis() - stateTime >= runTime){
					break;
				}
				Random random = new Random();
				Thread.sleep(random.nextInt(Constants.DEFAULT_RANGE_FOR_SLEEP));
				
				int index = random.nextInt(Constants.CONSUMER_NAME.length);
				String consumerName = Constants.CONSUMER_NAME[index];
				log.info(consumerName + "正在商场选购商品...");
				BasePoJo bpj = ss.pop();
				if(bpj == null){
					log.info("商场断货了 继续等待...");
					continue;
				}
				log.info(consumerName + "将商品[" + bpj.getName() + "]购买下来了 共花费" + bpj.getPrice());
			}
		} catch (InterruptedException e) {
			destroy();
		} finally {
			log.info("退出消费者线程！");
		}
	}
	
	private void destroy(){
		Thread.currentThread().interrupt();
	}

	public void stop() {
		destroy();
	}
}