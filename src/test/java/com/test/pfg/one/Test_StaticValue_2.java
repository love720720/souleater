package com.test.pfg.one;

import java.io.IOException;

import com.souleater.pfg.one.VariableService2;

/**
 * 测试全局变量在多线程下的情况
 * @author love720720@163.com
 * @date 2017年5月8日 下午3:23:21
 */
public class Test_StaticValue_2 {

	public static void main(String[] args) throws IOException {
		
		//线程不安全
		VariableService2 vs2 = new VariableService2();
		for (int i = 0; i < 1000; i++){
			new Thread(vs2, "线程" + i).start();
		}
		
		//线程安全
        for (int i = 0; i < 1000; i++){
            new Thread(new VariableService2(), "线程" + i).start();
        }
	}
}