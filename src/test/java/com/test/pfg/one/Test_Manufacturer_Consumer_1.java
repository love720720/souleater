package com.test.pfg.one;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

import com.souleater.pfg.one.ConsumerService;
import com.souleater.pfg.one.ManufacturerService;

/**
 * 测试生产者、消费者线程
 * @author love720720@163.com
 * @date 2017年5月5日 上午11:33:39
 */
public class Test_Manufacturer_Consumer_1 {

	public static void main(String[] args) throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> bq = new LinkedBlockingQueue<String>(10);
 
        ManufacturerService ms1 = new ManufacturerService(bq);
        ManufacturerService ms2 = new ManufacturerService(bq);
        ManufacturerService ms3 = new ManufacturerService(bq);
        ConsumerService cs = new ConsumerService(bq);
        // 借助Executors
        ExecutorService es = Executors.newCachedThreadPool();
        // 执行生产
        es.execute(ms1);
        es.execute(ms2);
        es.execute(ms3);
        //执行消费
        es.execute(cs);
 
        Thread.sleep(10 * 1000);
        ms1.stop();
        ms2.stop();
        ms3.stop();
 
        // 退出Executor
        es.shutdown();
    }
}