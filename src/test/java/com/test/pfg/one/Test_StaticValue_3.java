package com.test.pfg.one;

import java.io.IOException;

import com.souleater.pfg.one.VariableService3;

/**
 * 测试内部变量在多线程下的情况
 * @author love720720@163.com
 * @date 2017年5月8日 下午3:23:27
 */
public class Test_StaticValue_3 {

	public static void main(String[] args) throws IOException {
		
		//线程安全
		VariableService3 vs3 = new VariableService3();
		for (int i = 0; i < 1000; i++){
			new Thread(vs3, "线程" + i).start();
		}
		
		//线程安全
        for (int i = 0; i < 1000; i++){
            new Thread(new VariableService3(), "线程" + i).start();
        }
	}
}