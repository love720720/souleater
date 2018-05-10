package com.souleater.pfg.one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import com.souleater.init.Constants;

/** 
 * 生成毫秒级订单号
 * @author love720720@163.com
 * @date 2017年4月31日 上午9:55:23
 */
public class OrderService {

	private static Set<String> set = new HashSet<String>();
	
	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
	public static String getOrderNo5() {
		lock.writeLock().lock();
		String orderNo = null;
		try {
			DateFormat df = new SimpleDateFormat(Constants.FORMATYMDHMSMS);
			orderNo = df.format(new Date());
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		return orderNo;
	}
	
	public static String getOrderNo4() {
		lock.writeLock().lock();
		String orderNo = null;
		try {

			DateFormat df = new SimpleDateFormat(Constants.FORMATYMDHMSMS);
			orderNo = df.format(new Date());

			while (set.contains(orderNo)) {
				orderNo = df.format(new Date());
			}

			set.add(orderNo);
			
			Thread.sleep(1);
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
		return orderNo;
	}
	
	public static String getOrderNo3(){
		String orderNo = null;
		try{
			lock.writeLock().lock();
			DateFormat df = new SimpleDateFormat(Constants.FORMATYMDHMSMS);
			orderNo = df.format(new Date());
			for(;;){
				if(set.contains(orderNo)){
					orderNo = df.format(new Date());
					continue;
				}
				
				set.add(orderNo);
				break;
			}
		}finally{
			lock.writeLock().unlock();
		}
		return orderNo;
	}
	
	public static String getOrderNo2(){
		DateFormat df = new SimpleDateFormat(Constants.FORMATYMDHMSMS);
		String orderNo = df.format(new Date());
		
		for(;;){
			if(set.contains(orderNo)){
				orderNo = df.format(new Date());
				continue;
			}
			
			set.add(orderNo);
			break;
		}
		return orderNo;
	}
	
	public static String getOrderNo1(){
		DateFormat df = new SimpleDateFormat(Constants.FORMATYMDHMSMS);
		String orderNo = df.format(new Date());
		
		while(true){
			if(set.contains(orderNo)){
				orderNo = df.format(new Date());
				continue;
			}
			
			set.add(orderNo);
			break;
		}
		return orderNo;
	}
}