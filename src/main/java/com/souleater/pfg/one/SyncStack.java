package com.souleater.pfg.one;

import java.util.concurrent.atomic.AtomicInteger;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;

/**
 * 
 * @author love720720@163.com
 * @date 2017年5月8日 下午3:54:24
 */
public class SyncStack {

	private static Logger log = LogUtils.getLogger();
	
	private AtomicInteger index = new AtomicInteger(0);
	// 构造商品数组
	private BasePoJo[] bpjs = new BasePoJo[10];
	
	private final static long WAIT_TIME = 5000L;

	private AtomicInteger pushCount = new AtomicInteger(0);
	private AtomicInteger popCount = new AtomicInteger(0);
	
	/**
	 * 生产商品
	 * @param ppj
	 */
	public synchronized boolean push(BasePoJo bpj) {
		pushCount.incrementAndGet();
		int i = index.get();
		if (i == bpjs.length) {
			try {
				this.wait(WAIT_TIME);
				log.info("push等待" + Thread.currentThread().getName() + "|" + Thread.currentThread().getId());
			} catch (InterruptedException e) {

			} finally {
				
			}
		}
		
		if(i >= 0 && i < bpjs.length){
			bpjs[i] = bpj;
			index.incrementAndGet();
			this.notify();
			return true;
		}
		index.incrementAndGet();
		this.notify();
		return false;
	}

	/**
	 * 消费商品
	 * @return
	 */
	public synchronized BasePoJo pop() {
		BasePoJo bpj = null;
		popCount.incrementAndGet();
		int i = index.get();
		if (i == 0) {
			try {
				this.wait(WAIT_TIME);
				log.info("pop等待" + Thread.currentThread().getName() + "|" + Thread.currentThread().getId());
			} catch (InterruptedException e) {
				
			} finally {
				
			}
		}
			
		i = index.decrementAndGet();
		if(i >= 0 && i < bpjs.length){
			bpj = bpjs[i];
		}else{
			index.incrementAndGet();
		}
		this.notify();
		return bpj;
	}
	
	public int getPushCount(){
		return pushCount.get();
	}
	
	public int getPopCount(){
		return popCount.get();
	}
}