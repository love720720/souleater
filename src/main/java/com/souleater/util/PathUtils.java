package com.souleater.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import com.souleater.init.Constants;

/** 
 * 路径相关的工具类
 * @author love720720@163.com
 * @date 2017年5月11日 下午3:41:51
 */
public class PathUtils {

	/**
	 * 获取web的target路径
	 * @return
	 */
	public static String getWebTargetPath(){
    	String path = Constants.EMPTY;
        try {
			path = PathUtils.getInstance().getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
        	
        	int index = path.indexOf("WEB-INF");
        	
        	if(index == -1){
        		index = path.indexOf("classes");
        	}
        	
        	if(index == -1){
        		index = path.indexOf("bin");
        	}
        	
        	if(index == -1){
        		throw new ArrayIndexOutOfBoundsException();
        	}
        	
        	path = path.substring(0, index);
        	if(path.startsWith("zip")){
        		//当class文件在war中时，此时返回zip:D:/...这样的路径
        		path = path.substring(4);
        	}
        	if(path.startsWith("file")){
        		//当class文件在class文件中时，此时返回file:/D:/...这样的路径    
        		path = path.substring(6);
        	}
        	if(path.startsWith("jar")){
        		//当class文件在jar文件里面时，此时返回jar:file:/D:/...这样的路径    
        		path = path.substring(10);
        	}
            path =  URLDecoder.decode(path, Constants.CHARSET);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return path;
    }
	
	/**
	 * 获取web绝对路径
	 * @return
	 */
	public static String getWebPath(){
		String path = getWebTargetPath();
		int index = path.indexOf("target");
		if(index != -1){
			path = path.substring(0, index);
		}
		return path;
	}
	
	private static PathUtils instance = null;

	private PathUtils() {
	}

	private static PathUtils getInstance() {
		if (instance == null) {
			synchronized (PathUtils.class) {
				if (instance == null) {
					instance = new PathUtils();
				}
			}
		}
		return instance;
	}
}