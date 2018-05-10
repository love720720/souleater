package com.souleater.pfg.one;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;

/**
 * 
 * @author love720720@163.com
 * @date 2017年5月2日 上午11:29:21
 */
public class BlockingQueueService implements Runnable {

	private static Logger log = LogUtils.getLogger();
	
	private static BlockingQueue<BasePoJo> bq = new ArrayBlockingQueue<BasePoJo>(1024);
	
	public void run(){
		BasePoJo base = bq.poll();
		if(base != null){
			log.info("有线程正在执行 请稍等...");
			return;
		}
		for (int j = 0; j < 2; j++) {
			base = new BasePoJo();
			base.setName(j + Thread.currentThread().getName());
			pull(base);
		}
		try {
			for (; ;) {
				base = bq.poll(20, TimeUnit.SECONDS);
				if(base == null){
					log.info("base is null");
					Thread.currentThread().interrupt();
					break;
				}
				
				String name = base.getName();
				log.info("baseName = " + name);
			}
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
    }
	
	public void pull(BasePoJo user){
		try {
			bq.put(user);
		} catch (Exception e) {
			log.error(e.getLocalizedMessage());
		}
	}
}