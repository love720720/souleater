package com.souleater.util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.lang3.StringUtils;

import com.souleater.init.Constants;

/** 
 * 订单号相关工具类
 * @author love720720@163.com
 * @date 2017年7月25日 下午2:20:13
 */
public class OrderUtils {
	
	private static ConcurrentLinkedQueue<Integer> queue = new ConcurrentLinkedQueue<Integer>();

	private static ReadWriteLock lock = new ReentrantReadWriteLock();
	
    public static final int MAX = 1000;

    static{
        for (int i = 1; i <= MAX; i++) {
            queue.offer(i);
        }
    }

    public static String getSequence() {
        Integer i = queue.poll();
        queue.offer(i);
        String sequence = String.format("%4d", i).replace(" ", "0");  
        return sequence;
    }

    public static String getOrderNo(String prefix) {
    	lock.writeLock().lock();
    	String orderNo = null;
    	try {
    		DateFormat df = new SimpleDateFormat(Constants.FORMATYMDHMSMS);
    		Date date = new Date();
    		String formatData = df.format(date);
    		if(StringUtils.isNotBlank(prefix)){
    			orderNo = prefix + formatData + getSequence();
    			return orderNo;
    		}
    		
    		orderNo = formatData + getSequence();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			lock.writeLock().unlock();
		}
        return orderNo;
    }
}