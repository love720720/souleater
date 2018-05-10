package com.souleater.util;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.commons.lang3.StringUtils;

/** 
 * Java Class与反射相关的一些工具类
 * @author love720720@163.com
 * @date 2017年5月11日 下午3:44:35
 */
public class ClassUtils {

    /**
     * 反射设置set方法
     * @param obj 需要反射的对象
     * @param key 对象的字段
     * @param value 需要设置的value
     */
    public static void setMethodValue(Object obj,String key,String value){
		try {
			if(obj == null){
				throw new NullPointerException("待反射的对象不可为空");
			}
			if(StringUtils.isBlank(key)){
				throw new NullPointerException("待反射的字段不可为空");
			}
			Class<? extends Object> clazz = obj.getClass();
			PropertyDescriptor pd = new PropertyDescriptor(key,clazz);
			Method method = pd.getWriteMethod();
			method.invoke(obj,value);
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    }
	
	/**
     * 复制对象属性值
     * copy的字段必须存在于obj当中 即字段名称相同
     * @param obj 合并的对象
     * @param copy 复制的对象
     * @throws Exception
     */
    public static void copyProperties(Object obj,Object copy) {
    	try {
    		if(obj == null){
    			throw new NullPointerException("待复制对象不可为空");
    		}
    		if(obj == null || copy == null){
    			throw new NullPointerException("被复制对象不可为空");
    		}
    		Class<? extends Object> clazzObj = obj.getClass();
    		Class<? extends Object> clazzCopy = copy.getClass();
    		//获取一个公开的字段 不进行copy
    		Field[] noFields = clazzObj.getFields();
    		
    		Field[] fields = clazzCopy.getDeclaredFields();

    		A:for (Field field : fields) {
    			String name = field.getName();
    			
    			for (Field noField : noFields) {
    				if(noField.getName().equals(name)){
    					continue A;
    				}
    			}
    			
    			PropertyDescriptor pd = new PropertyDescriptor(name,clazzCopy);
    			Method method = pd.getReadMethod();
    			Object value = method.invoke(copy);
    			if(value == null){
    				continue;
    			}
    			pd = new PropertyDescriptor(name,clazzObj);
    			method = pd.getWriteMethod();
    			method.invoke(obj,value);
    		}
		} catch (IntrospectionException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
    }
}
