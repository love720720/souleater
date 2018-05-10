package com.souleater.init;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.LoggerContext;
import ch.qos.logback.classic.spi.LoggerContextListener;
import ch.qos.logback.core.spi.ContextAwareBase;
import ch.qos.logback.core.spi.LifeCycle;

import com.souleater.util.PathUtils;

/** 
 * 配置logback生成的文件位置
 * @author love720720@163.com
 * @date 2017年5月22日 上午10:56:26
 */
public class LoggerStartListener extends ContextAwareBase implements LoggerContextListener, LifeCycle {

	public void start() {
		Constants.WEB_PATH = PathUtils.getWebPath();
		System.setProperty("WEB_PATH", Constants.WEB_PATH);
	}
	
	public boolean isStarted() {
		return false;
	}

	public void stop() {
	}

	public boolean isResetResistant() {
		return false;
	}

	public void onLevelChange(Logger logger, Level level) {
	}

	public void onReset(LoggerContext loggerContext) {
	}

	public void onStart(LoggerContext loggerContext) {
	}

	public void onStop(LoggerContext loggerContext) {
	}
}