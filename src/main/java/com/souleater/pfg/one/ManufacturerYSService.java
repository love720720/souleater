package com.souleater.pfg.one;

import java.util.Random;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;

/**
 * 生产者线程
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:30:33
 */
public class ManufacturerYSService implements Runnable {
	
	private static Logger log = LogUtils.getLogger();
	
	private SyncStack ss;
	
	private long runTime;
	
	private static long stateTime = System.currentTimeMillis();
	
	public ManufacturerYSService(SyncStack ss,final long runTime) {
		this.ss = ss;
		this.runTime = runTime;
	}

	public void run() {
		log.info("启动生产者线程！");
		
		try {
			while(!Thread.currentThread().isInterrupted()){
				if(System.currentTimeMillis() - stateTime >= runTime){
					break;
				}
				Random random = new Random();
				Thread.sleep(random.nextInt(Constants.DEFAULT_RANGE_FOR_SLEEP));
				
				int index = random.nextInt(Constants.PRODUCTS.length);
				String productInfo = Constants.PRODUCTS[index];
				String[] split = productInfo.split("_");
				String productName = split[0];
				String price = split[1];
				log.info("商家[" + Constants.MANUFACTURER_NAME[index] + "]正在生产商品 请等候...");
				BasePoJo bpj = new BasePoJo(productName,price);
				boolean b = ss.push(bpj);
				if(b){
					log.info("商家将[" + productName + "]投放商场...");
				}else{
					log.info("货架已满 商品[" + productName + "]撤回...");
				}
			}
		} catch (InterruptedException e) {
			destroy();
		} finally {
			log.info("退出生产者线程！");
		}
	}
	
	private void destroy(){
		Thread.currentThread().interrupt();
	}
}