package com.souleater.util;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.text.NumberFormat;

import org.apache.commons.lang3.StringUtils;

/** 
 * 数字操作相关工具类
 * @author love720720@163.com
 * @date 2017年8月31日 下午2:26:59
 */
public class NumberUtils {

	private static final String PATTERN_NORMAL = "#0.##";
	
	/**
	 * 格式化double 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param doubleValue
	 * @return
	 */
	public static String format(double doubleValue){
		return format(doubleValue, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 格式化double 不进行四舍五入
	 * 超过位数截断
	 * @param doubleValue
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String format(double doubleValue, String pattern){
		return format(doubleValue, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义格式化double
	 * 默认格式：#0.##
	 * @param doubleValue
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String format(double doubleValue, RoundingMode roundingMode){
		return format(doubleValue, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义格式化double
	 * @param doubleValue
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String format(double doubleValue, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		NumberFormat numberFormat = new DecimalFormat(pattern);
		numberFormat.setRoundingMode(roundingMode);
		return numberFormat.format(doubleValue);
	}
	
	/**
	 * 格式化float 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param floatValue
	 * @return
	 */
	public static String format(float floatValue){
		return format(floatValue, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 格式化float 不进行四舍五入
	 * 超过位数截断
	 * @param floatValue
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String format(float floatValue, String pattern){
		return format(floatValue, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义格式化float
	 * 默认格式：#0.##
	 * @param floatValue
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String format(float floatValue, RoundingMode roundingMode){
		return format(floatValue, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义格式化float
	 * @param floatValue
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String format(float floatValue, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		NumberFormat numberFormat = new DecimalFormat(pattern);
		numberFormat.setRoundingMode(roundingMode);
		return numberFormat.format(floatValue);
	}
	
	/**
	 * 加法
     * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @return
	 */
	public static String add(double doubleValue1, double doubleValue2){
		return add(doubleValue1, doubleValue2, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 加法
     * 不进行四舍五入
     * 超过位数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String add(double doubleValue1, double doubleValue2, String pattern){
		return add(doubleValue1, doubleValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义加法
	 * 默认格式：#0.##
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String add(double doubleValue1, double doubleValue2, RoundingMode roundingMode){
		return add(doubleValue1, doubleValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义加法
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String add(double doubleValue1, double doubleValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal doubleDecimal1 = BigDecimal.valueOf(doubleValue1);
		BigDecimal doubleDecimal2 = BigDecimal.valueOf(doubleValue2);
		double addValue = doubleDecimal1.add(doubleDecimal2).doubleValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 加法
	 * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @return
	 */
	public static String add(float floatValue1, float floatValue2){
		return add(floatValue1, floatValue2, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 加法
	 * 不进行四舍五入
	 * 超过位数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String add(float floatValue1, float floatValue2, String pattern){
		return add(floatValue1, floatValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义加法
	 * 默认格式：#0.##
	 * @param floatValue1
	 * @param floatValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String add(float floatValue1, float floatValue2, RoundingMode roundingMode){
		return add(floatValue1, floatValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义加法
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String add(float floatValue1, float floatValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal floatDecimal1 = BigDecimal.valueOf(floatValue1);
		BigDecimal floatDecimal2 = BigDecimal.valueOf(floatValue2);
		float addValue = floatDecimal1.add(floatDecimal2).floatValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 减法
	 * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @return
	 */
	public static String subtract(double doubleValue1, double doubleValue2){
		return subtract(doubleValue1, doubleValue2, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 减法
	 * 不进行四舍五入
	 * 超过位数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String subtract(double doubleValue1, double doubleValue2, String pattern){
		return subtract(doubleValue1, doubleValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义减法
	 * 默认格式：#0.##
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String subtract(double doubleValue1, double doubleValue2, RoundingMode roundingMode){
		return subtract(doubleValue1, doubleValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义减法
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String subtract(double doubleValue1, double doubleValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal doubleDecimal1 = BigDecimal.valueOf(doubleValue1);
		BigDecimal doubleDecimal2 = BigDecimal.valueOf(doubleValue2);
		double addValue = doubleDecimal1.subtract(doubleDecimal2).doubleValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 减法
	 * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @return
	 */
	public static String subtract(float floatValue1, float floatValue2){
		return subtract(floatValue1, floatValue2, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 减法
	 * 不进行四舍五入
	 * 超过位数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String subtract(float floatValue1, float floatValue2, String pattern){
		return subtract(floatValue1, floatValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义减法
	 * 默认格式：#0.##
	 * @param floatValue1
	 * @param floatValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String subtract(float floatValue1, float floatValue2, RoundingMode roundingMode){
		return subtract(floatValue1, floatValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义减法
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String subtract(float floatValue1, float floatValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal floatDecimal1 = BigDecimal.valueOf(floatValue1);
		BigDecimal floatDecimal2 = BigDecimal.valueOf(floatValue2);
		float addValue = floatDecimal1.subtract(floatDecimal2).floatValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 乘法
	 * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @return
	 */
	public static String multiply(double doubleValue1, double doubleValue2){
		return multiply(doubleValue1, doubleValue2, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 乘法
	 * 不进行四舍五入
	 * 超过位数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String multiply(double doubleValue1, double doubleValue2, String pattern){
		return multiply(doubleValue1, doubleValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义乘法
	 * 默认格式：#0.##
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String multiply(double doubleValue1, double doubleValue2, RoundingMode roundingMode){
		return multiply(doubleValue1, doubleValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义乘法
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String multiply(double doubleValue1, double doubleValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal doubleDecimal1 = BigDecimal.valueOf(doubleValue1);
		BigDecimal doubleDecimal2 = BigDecimal.valueOf(doubleValue2);
		double addValue = doubleDecimal1.multiply(doubleDecimal2).doubleValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 乘法
	 * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @return
	 */
	public static String multiply(float floatValue1, float floatValue2){
		return multiply(floatValue1, floatValue2, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 乘法
	 * 不进行四舍五入
	 * 超过位数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String multiply(float floatValue1, float floatValue2, String pattern){
		return multiply(floatValue1, floatValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义乘法
	 * 默认格式：#0.##
	 * @param floatValue1
	 * @param floatValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String multiply(float floatValue1, float floatValue2, RoundingMode roundingMode){
		return multiply(floatValue1, floatValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义乘法
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String multiply(float floatValue1, float floatValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal floatDecimal1 = BigDecimal.valueOf(floatValue1);
		BigDecimal floatDecimal2 = BigDecimal.valueOf(floatValue2);
		float addValue = floatDecimal1.multiply(floatDecimal2).floatValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 除法
	 * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @return
	 */
	public static String divide(double doubleValue1, double doubleValue2){
		return divide(doubleValue1, doubleValue2, PATTERN_NORMAL, RoundingMode.DOWN);
	}
	
	/**
	 * 除法
	 * 不进行四舍五入
	 * 超过位数截断
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String divide(double doubleValue1, double doubleValue2, String pattern){
		return divide(doubleValue1, doubleValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义除法
	 * 默认格式：#0.##
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String divide(double doubleValue1, double doubleValue2, RoundingMode roundingMode){
		return divide(doubleValue1, doubleValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义除法
	 * @param doubleValue1
	 * @param doubleValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String divide(double doubleValue1, double doubleValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal doubleDecimal1 = BigDecimal.valueOf(doubleValue1);
		BigDecimal doubleDecimal2 = BigDecimal.valueOf(doubleValue2);
		double addValue = doubleDecimal1.divide(doubleDecimal2, 10, BigDecimal.ROUND_HALF_DOWN).doubleValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 除法
	 * 不进行四舍五入
	 * 有小数显示小数 最多显示2位小数 超过2位小数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @return
	 */
	public static String divide(float floatValue1, float floatValue2){
		return divide(floatValue1, floatValue2, PATTERN_NORMAL);
	}
	
	/**
	 * 除法
	 * 不进行四舍五入
	 * 超过位数截断
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @return
	 */
	public static String divide(float floatValue1, float floatValue2, String pattern){
		return divide(floatValue1, floatValue2, pattern, RoundingMode.DOWN);
	}
	
	/**
	 * 自定义除法
	 * 默认格式：#0.##
	 * @param floatValue1
	 * @param floatValue2
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String divide(float floatValue1, float floatValue2, RoundingMode roundingMode){
		return divide(floatValue1, floatValue2, PATTERN_NORMAL, roundingMode);
	}
	
	/**
	 * 自定义除法
	 * @param floatValue1
	 * @param floatValue2
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 * @return
	 */
	public static String divide(float floatValue1, float floatValue2, String pattern, RoundingMode roundingMode){
		verify(pattern, roundingMode);
		BigDecimal floatDecimal1 = BigDecimal.valueOf(floatValue1);
		BigDecimal floatDecimal2 = BigDecimal.valueOf(floatValue2);
		float addValue = floatDecimal1.divide(floatDecimal2, 10, BigDecimal.ROUND_HALF_DOWN).floatValue();
		return format(addValue, pattern, roundingMode);
	}
	
	/**
	 * 内部参数校验
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 */
	protected static void verify(String pattern){
		if (StringUtils.isBlank(pattern)) {
			throw new NullPointerException("自定义格式不可为空");
		}
	}
	
	/**
	 * 内部参数校验
	 * @param pattern 自定义格式 如：#0.##、#0.00
	 * @param roundingMode 自定义精度模式 如：RoundingMode.DOWN、RoundingMode.UP {@link java.math.RoundingMode}
	 */
	protected static void verify(String pattern, RoundingMode roundingMode){
		verify(pattern);
		if (roundingMode == null) {
			throw new NullPointerException("自定义精度模式不可为空");
		}
	}
}