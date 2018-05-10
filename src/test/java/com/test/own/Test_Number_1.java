package com.test.own;

import java.math.RoundingMode;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;
import com.souleater.util.NumberUtils;

/** 
 * Java Class与反射相关的测试类
 * @author love720720@163.com
 * @date 2017年5月15日 下午2:53:04
 */
public class Test_Number_1 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) {
		log.info("format = " + NumberUtils.format(0.999));
		log.info("format = " + NumberUtils.format(-0.999));
		log.info("format = " + NumberUtils.format(0.999F));
		log.info("format = " + NumberUtils.format(0.999, "#0.00"));
		log.info("format = " + NumberUtils.format(0.999, "#0.00", RoundingMode.HALF_UP));
		log.info("format = " + NumberUtils.format(-0.999F, "#0.##", RoundingMode.HALF_UP));
		
		
		log.info("加法等于 = " + NumberUtils.add(-11117920.281, 11117920.181));
		log.info("减法等于 = " + NumberUtils.subtract(0, 17920.181));
		log.info("乘法等于 = " + NumberUtils.multiply(0, 1711111111920.181));
		log.info("除法等于 = " + NumberUtils.divide(16, 3));
	}
}
