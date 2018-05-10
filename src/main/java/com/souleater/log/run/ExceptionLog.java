package com.souleater.log.run;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

import org.slf4j.Logger;

import com.souleater.log.poJo.LogPoJo;
import com.souleater.log.service.LogService;
import com.souleater.util.LogUtils;
import com.souleater.util.SpringContextUtils;

/** 
 * 异常日志线程类
 * 该线程会在系统启动之后便不停的执行
 * 当从processQueue取不到数据将进行等待 直到可以取到数据
 * @author love720720@163.com
 * @date 2017年6月13日 下午3:03:42
 */
public class ExceptionLog implements Runnable{

	private static Logger log = LogUtils.getLogger();
	
	private static BlockingQueue<LogPoJo> processQueue = new ArrayBlockingQueue<LogPoJo>(1024);
	
	private LogService logService = SpringContextUtils.getBean(LogService.class);
	
	public void run() {
		try {
			log.info("线程ExceptionLog开始执行");
			for (;;) {
				LogPoJo lpj = processQueue.take();
				logService.insertExceptionLog(lpj);
			}
		} catch (InterruptedException e) {
			log.error(e.getLocalizedMessage(),e);
		} finally {
			log.info("线程ExceptionLog结束执行");
		}
	}
	
	public static BlockingQueue<LogPoJo> getProcessQueue() {
		return processQueue;
	}
}