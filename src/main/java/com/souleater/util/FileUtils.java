package com.souleater.util;

import com.souleater.exp.BaseException;
import com.souleater.init.Constants;
import org.apache.commons.lang3.StringUtils;
import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

/**
 * 文件相关的工具类
 * @author love720720@163.com
 * @date 2017年5月11日 下午3:44:03
 */
public class FileUtils {

	/**
	 * 根据key读取sys-config.xml文件
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getSysConfigValue(String key){
		Properties properties = FileUtils.initSysConfig();
		String value = getPropertiesValue(properties,key);
		return value;
	}

	/**
	 * 根据key读取指定配置文件
	 * @param properties
	 * @param key
	 * @return
	 */
	public static String getPropertiesValue(Properties properties,String key){
		String value = null;
		Object object = properties.get(key);
		if(object != null){
			value = (String)object;
		}
		return value;
	}

	/**
	 * 初始化系统参数配置文件
	 * @return
	 */
	public static Properties initSysConfig(){
		Properties properties = new Properties();
		FileInputStream in = null;
		try {
			String projectName = "souleater/";
			String path = PathUtils.getWebTargetPath();
			if (path.lastIndexOf(projectName) != -1) {
				path = StringUtils.removeEnd(path, projectName);
				path += "classes";
			} else if (path.indexOf("target") != -1) {
				path += "classes";
			} else {
				path += "WEB-INF" + File.separator;
			}

			String configPath = path + File.separator + "sys-config.xml";
			in = new FileInputStream(configPath);
			//sysProperties.load(in);//读取*.properties
			properties.loadFromXML(in);//读取*.xml
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return properties;
	}

	/**
	 * 获取临时文件名 根据传入目录路径和当前时间生成临时文件名
	 * @throws BaseException
	 */
	public static String createFile(String filePath, String suffix) {
		String fileName = getFileName() + (suffix.startsWith(".") ? suffix : "." + suffix);
		filePath = Constants.FILE_PATH + File.separator + filePath;
		return mkdirs(filePath,fileName);
	}

	/**
	 * 生成一个当前时间的文件名字
	 * @return
	 */
	private static synchronized String getFileName() {
		return DateUtils.getToday(Constants.FORMATYMDHMSMS);
	}

	/**
	 * 创建文件
	 * @param filePath
	 * @param fileName
	 * @throws BaseException
	 */
	private static String mkdirs(String filePath,String fileName) {
		try {
            if(StringUtils.isBlank(filePath)){
				throw new NullPointerException("待创建的文件路径不可为空");
			}
			if(StringUtils.isBlank(fileName)){
				throw new NullPointerException("待创建的文件名称不可为空");
			}

			if(StringUtils.isBlank(Constants.WEB_PATH)){
				Constants.WEB_PATH = PathUtils.getWebPath();
			}

			filePath = Constants.WEB_PATH + filePath;

			File file = new File(filePath);
			if (!file.exists() && !file.isDirectory()) {
				file.mkdirs();
			}

			filePath = filePath + File.separator + fileName;

			file = new File(filePath);
			if(!file.exists() && !file.isDirectory()){
				file.createNewFile();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return filePath;
	}
}