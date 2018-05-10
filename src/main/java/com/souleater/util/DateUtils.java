package com.souleater.util;

import org.apache.commons.lang3.StringUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

/** 
 * 日期时间相关的工具类
 * @author love720720@163.com
 * @date 2017年5月11日 下午3:44:48
 */
public class DateUtils {
	
	/**
	 * 得到今天开始时间毫秒数
	 * @return
	 */
	public static long getTodayLong(){
		Calendar calendar = Calendar.getInstance(); 
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}
	
	/**
	 * 得到明天开始时间毫秒数
	 * @return
	 */
	public static long getTomorrowLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, 1);
		return calendar.getTime().getTime();
	}
	
	/**
	 * 得到昨天开始时间毫秒数
	 * @return
	 */
	public static long getYesterdayLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.add(Calendar.DATE, -1);
		return calendar.getTime().getTime();
	}
	
	/**
	 * 得到当前周的第一天毫秒数
	 * @return
	 */
	public static long getWeekdayFirstLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.setFirstDayOfWeek(Calendar.MONDAY);
		calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		return calendar.getTime().getTime();
	}
	
	/**
	 * 得到当前月的第一天毫秒数
	 * @return
	 */
	public static long getMonthFirstLong(){
		Calendar calendar = Calendar.getInstance();
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		calendar.set(Calendar.DAY_OF_MONTH, 1);
		return calendar.getTime().getTime();
	}
	
	/**
	 * 得到当前小时
	 * @return
	 */
	public static int getHour(){
		Calendar calendar = Calendar.getInstance();
		return calendar.get(Calendar.HOUR_OF_DAY);
	}
	
	/**
	 * 获取今日自定义格式日期
	 * @param format
	 * @return
	 */
	public static String getToday(String format){
		if(StringUtils.isBlank(format)){
			throw new NullPointerException("自定义格式不可为空");
		}
		Calendar calendar = Calendar.getInstance();
		DateFormat df = new SimpleDateFormat(format);
		String today = df.format(calendar.getTime());
		return today;
	}
}