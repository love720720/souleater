package com.souleater.exp;

/** 
 * 自定义异常
 * @author love720720@163.com
 * @date 2017年5月12日 上午11:18:38
 */
public class BaseException extends Exception{

	private static final long serialVersionUID = 4695644772580341874L;

	public BaseException() {
		super();
	}

	public BaseException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public BaseException(String message, Throwable cause) {
		super(message, cause);
	}

	public BaseException(String message) {
		super(message);
	}

	public BaseException(Throwable cause) {
		super(cause);
	}
}