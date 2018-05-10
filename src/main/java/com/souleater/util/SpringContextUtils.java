package com.souleater.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 获取spring上下文相关工具类
 * @author love720720@163.com
 * @date 2017年6月13日 下午3:48:02
 */
public class SpringContextUtils implements ApplicationContextAware{
	
	private static ApplicationContext ac = null;

	/**
	 * 注入spring上下文
	 */
	public void setApplicationContext(ApplicationContext ac) throws BeansException {
		SpringContextUtils.ac = ac;
	}

	/**
	 * 获取spring注入类
	 * @param clazz
	 * @return
	 */
	public static <T> T getBean(Class<T> clazz) {
		return ac.getBean(clazz);
	}
}