package com.souleater.pfg.one;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

import org.slf4j.Logger;

import com.souleater.init.Constants;
import com.souleater.util.LogUtils;

/**
 * 
 * @author love720720@163.com
 * @date 2017年5月8日 下午3:54:35
 */
public class WorkerService extends Thread{

	private static Logger log = LogUtils.getLogger();
	
	private String workerName;
	private int workTime;
	private CountDownLatch cdl;

	public WorkerService(String workerName, int workTime, CountDownLatch cdl) {
		this.workerName = workerName;
		this.workTime = workTime;
		this.cdl = cdl;
	}

	public void run() {
		DateFormat df = new SimpleDateFormat(Constants.FORMAT_YMDHMSMS);
		log.info(workerName + "->>开始工作：" + df.format(new Date()));
		doWork();
		log.info(workerName + "->>完成工作：" + df.format(new Date()));
		cdl.countDown();

	}

	private void doWork() {
		try {
			Thread.sleep(workTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}