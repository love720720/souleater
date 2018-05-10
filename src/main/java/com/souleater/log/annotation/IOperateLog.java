package com.souleater.log.annotation;

import java.lang.annotation.*;

import com.souleater.init.Constants;

/**
 * 自定义操作日志注解
 * @author love720720@163.com
 * @date 2017年6月12日 下午4:03:54
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface IOperateLog {

	String description() default Constants.EMPTY;
}
