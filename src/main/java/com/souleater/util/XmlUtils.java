package com.souleater.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

import com.google.gson.Gson;

/** 
 * xml解析相关的一些工具类
 * @author love720720@163.com
 * @date 2017年6月9日 下午2:59:35
 */
public class XmlUtils {

	/**
	 * 将字符串格式的xml转化为map
	 * key为xml标签
	 * value为内容
	 * @param xml
	 * @return
	 */
    public static Map<String, String> parseXmlMap(String xml){
    	if(StringUtils.isBlank(xml)){
    		throw new NullPointerException("待解析xml不可为空");
    	}
    	Map<String, String> map = new HashMap<String, String>();
		try {
			Document document = null;
			document = DocumentHelper.parseText(xml);
		    Element rootElement = document.getRootElement();
		    Iterator<Element> iterator = rootElement.elementIterator();
		    while(iterator.hasNext()){
		    	Element element = iterator.next();
		    	String name = element.getName();
		    	String text = element.getText();
		    	
		    	map.put(name, text);
		    }
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		return map;
    }
    
    /**
     * 将字符串格式的xml转化为json
     * @param xml
     * @return
     */
    public static String parseXmlJson(String xml){
    	Map<String, String> map = parseXmlMap(xml);
    	Gson gson = new Gson();
    	return gson.toJson(map);
    }
    
    /**
     * 将字符串格式的xml转化为指定class
     * @param xml
     * @param clazz
     * @return
     */
    public static Object parseXmlClass(String xml,Class<?> clazz){
    	String json = parseXmlJson(xml);
    	Gson gson = new Gson();
    	return gson.fromJson(json, clazz);
    }
}