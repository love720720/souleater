package com.souleater.asp;

import java.lang.reflect.Method;
import java.util.concurrent.BlockingQueue;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.google.gson.Gson;
import com.souleater.log.annotation.IExceptionLog;
import com.souleater.log.annotation.IOperateLog;
import com.souleater.log.poJo.LogPoJo;
import com.souleater.log.run.OperateLog;
import com.souleater.log.service.LogService;
import com.souleater.util.CommonUtils;
import com.souleater.util.LogUtils;

/** 
 * 日志类
 * 通过注解可以直接进行日志插入
 * 直接调用processQueue.put(lpj)方法
 * 日志线程类 会进行异步日志插入
 * @author love720720@163.com
 * @date 2017年6月12日 下午4:04:57
 */
@Aspect    
@Component  
public class LogAspect {

	private static Logger log = LogUtils.getLogger();
	
	@Autowired
	private LogService logService;
	
	/**
	 * 控制层切面
	 * 主要用于记录操作日志
	 */
	@Pointcut("@annotation(com.souleater.log.annotation.IOperateLog)")
	public void operateLog() {
	}

	/**
	 * 服务层切面
	 * 主要用于记录异常日志
	 */
	@Pointcut("@annotation(com.souleater.log.annotation.IExceptionLog)")
	public void exceptionLog() {
	}

	/**
	 * 前置通知
	 * 定位控制层 写入操作日志
	 * @param joinPoint
	 */
	@Before("operateLog()")
	public void beforeMethod(JoinPoint joinPoint) {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
			
			String description = getOperateLogDescription(joinPoint); 
			String ip = CommonUtils.getIp(request);
			int userId = CommonUtils.getWebLoginId(request);
			String openId = CommonUtils.getAppOpenId(request);
			
			LogPoJo lpj = new LogPoJo();
			lpj.setUserId(userId);
			lpj.setOpenId(openId);
			lpj.setDescription(description);
			lpj.setIp(ip);
			
			BlockingQueue<LogPoJo> processQueue = OperateLog.getProcessQueue();
			processQueue.put(lpj);
		} catch (InterruptedException e) {
			log.error(e.getLocalizedMessage(),e);
		}
	}

	/**
	 * 异常通知
	 * 定位服务层 写入异常日志
	 * @param joinPoint
	 * @param exp
	 */
	@AfterThrowing(pointcut = "exceptionLog()", throwing = "exp")
	public void afterThrowingMethod(JoinPoint joinPoint, Throwable exp) {
		try {
			HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();    
			
			StringBuilder params = new StringBuilder();
			Gson gson = new Gson();
			if (joinPoint.getArgs() != null && joinPoint.getArgs().length > 0) {
				for (int i = 0; i < joinPoint.getArgs().length; i++) {
					Object[] args = joinPoint.getArgs();
					params.append(gson.toJson(args[i])).append(";");
				}
			}
			
			String method = getMethodName(getClassName(joinPoint),getMethodName(joinPoint)); 
			String description = getExceptionLogDescription(joinPoint); 
			String exception = exp.getMessage(); 
			String ip = CommonUtils.getIp(request);
			int userId = CommonUtils.getWebLoginId(request);
			String openId = CommonUtils.getAppOpenId(request);
			
			LogPoJo lpj = new LogPoJo();
			lpj.setUserId(userId);
			lpj.setOpenId(openId);
			lpj.setMethod(method);
			lpj.setDescription(description);
			lpj.setException(exception);
			lpj.setIp(ip);
			
			BlockingQueue<LogPoJo> processQueue = OperateLog.getProcessQueue();
			processQueue.put(lpj);
		} catch (InterruptedException e) {
			log.error(e.getLocalizedMessage(),e);
		}
	}
	
	private static String getMethodName(String className,String methodName){
		return className + "." + methodName + "()";
	}
	
	private static String getClassName(JoinPoint joinPoint){
		return joinPoint.getTarget().getClass().getName();
	}
	
	private static String getMethodName(JoinPoint joinPoint){
		return joinPoint.getSignature().getName();
	}
	
	/**
	 * 获取注解内容
	 * @param joinPoint
	 * @return
	 */
	private static String getOperateLogDescription(JoinPoint joinPoint) {
		String description = null;
		try {
			String className = getClassName(joinPoint);
			String methodName = getMethodName(joinPoint);
			Object[] arguments = joinPoint.getArgs();
			Class<?> targetClass = Class.forName(className);
			Method[] methods = targetClass.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class<?>[] clazz = method.getParameterTypes();
					if (clazz.length == arguments.length) {
						description = method.getAnnotation(IOperateLog.class).description();
						break;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return description;
	}
	
	/**
	 * 获取注解内容
	 * @param joinPoint
	 * @return
	 */
	private static String getExceptionLogDescription(JoinPoint joinPoint) {
		String description = null;
		try {
			String className = getClassName(joinPoint);
			String methodName = getMethodName(joinPoint);
			Object[] arguments = joinPoint.getArgs();
			Class<?> targetClass = Class.forName(className);
			Method[] methods = targetClass.getMethods();
			for (Method method : methods) {
				if (method.getName().equals(methodName)) {
					Class<?>[] clazz = method.getParameterTypes();
					if (clazz.length == arguments.length) {
						description = method.getAnnotation(IExceptionLog.class).description();
						break;
					}
				}
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return description;
	}
}