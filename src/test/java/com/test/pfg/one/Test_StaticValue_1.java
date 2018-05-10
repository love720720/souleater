package com.test.pfg.one;

import java.io.IOException;

import com.souleater.pfg.one.VariableService1;

/**
 * 测试全局静态变量在多线程下的情况
 * @author love720720@163.com
 * @date 2017年5月8日 下午3:52:32
 */
public class Test_StaticValue_1 {

	public static void main(String[] args) throws IOException {
		
		//线程不安全
		VariableService1 vs1 = new VariableService1();
		for (int i = 0; i < 1000; i++){
			new Thread(vs1, "线程" + i).start();
		}
		
		//线程不安全
        for (int i = 0; i < 1000; i++){
            new Thread(new VariableService1(), "线程" + i).start();
        }
	}
}