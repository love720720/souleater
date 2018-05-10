package com.test.own;

import java.math.BigDecimal;

/** 
 *
 * @author gaowei@log56.com
 * @date 2017年7月20日 上午11:27:38
 */
public class Test_main_1 {

	public static void main(String[] args) {
		float f1 = 0.08F;
		float f2 = 0.0800000009F;
		System.out.println(BigDecimal.valueOf(f1));
		System.out.println(BigDecimal.valueOf(f2));
		System.out.println(f1 == f2);
		System.out.println(BigDecimal.valueOf(f1) == BigDecimal.valueOf(f2));
		System.out.println(Math.abs(f1) - Math.abs(f2) == 0.0F);
	}

}
