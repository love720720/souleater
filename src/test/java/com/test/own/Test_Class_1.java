package com.test.own;

import org.slf4j.Logger;

import com.souleater.util.ClassUtils;
import com.souleater.util.LogUtils;
import com.test.own.poJo.TestPoJo;

/** 
 * Java Class与反射相关的测试类
 * @author love720720@163.com
 * @date 2017年5月15日 下午2:53:04
 */
public class Test_Class_1 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) {
		TestPoJo tpj = new TestPoJo();
		ClassUtils.setMethodValue(null, "field1", "高巍1");
		
		log.info("获取field1：" + tpj.getField1());
		
		TestPoJo copy = new TestPoJo();
		copy.setField2("高巍2");
		
		ClassUtils.copyProperties(tpj, copy);
		
		log.info("获取field2：" + tpj.getField2());
	}
}
