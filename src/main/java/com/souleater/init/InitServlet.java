package com.souleater.init;

import java.util.concurrent.CountDownLatch;

import javax.servlet.ServletConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;

import com.souleater.app.run.AppAccessToken;
import com.souleater.app.run.AppJsApiTicket;
import com.souleater.app.run.AppStartMessage;
import com.souleater.log.run.ExceptionLog;
import com.souleater.log.run.OperateLog;
import com.souleater.util.FileUtils;
import com.souleater.util.LogUtils;

/** 
 * 系统初始化servlet
 * 启动顺序低于springMVC
 * 注解自启动执行初始化必须加入参数urlPatterns
 * @author love720720@163.com
 * @date 2017年5月12日 上午11:21:06
 */
@WebServlet(urlPatterns = "/init",loadOnStartup = 2)
public class InitServlet extends HttpServlet {

	private static final long serialVersionUID = -8905333042956344912L;
	
	private static Logger log = LogUtils.getLogger();
	
	public void init(ServletConfig config) {
		try {
			log.info("开始执行InitServlet初始化");
			
			String api = FileUtils.getSysConfigValue("api");
			String appId = FileUtils.getSysConfigValue("appId");
			String appsecret = FileUtils.getSysConfigValue("appsecret");
			String oauthBaseUrl = FileUtils.getSysConfigValue("oauthBaseUrl");
			String oauthUserInfoUrl = FileUtils.getSysConfigValue("oauthUserInfoUrl");
			
			oauthBaseUrl = oauthBaseUrl.replace("#{appId}", appId);
			oauthUserInfoUrl = oauthUserInfoUrl.replace("#{appId}", appId);
			
			Constants.WX_API = api;
			Constants.WX_APPID = appId;
			Constants.WX_APPSECRET = appsecret;
			Constants.WX_OAUTHBASEURL = oauthBaseUrl;
			Constants.WX_OAUTHUSERINFOURL = oauthUserInfoUrl;
			
			
			log.info("WX_API = " + api);
			log.info("WX_APPID = " + appId);
			log.info("WX_OAUTHBASEURL = " + oauthBaseUrl);
			log.info("WX_OAUTHUSERINFOURL = " + oauthUserInfoUrl);
			
			//启动微信accessToken线程
			CountDownLatch cdl = new CountDownLatch(1);
			Thread thread = new Thread(new AppAccessToken(cdl));
			thread.start();
			//必须等待accessToken获取完成
			cdl.await();
			//启动微信网页jsApiTicket线程
			thread = new Thread(new AppJsApiTicket());
			thread.start();
			//启动发送微信通知线程
			thread = new Thread(new AppStartMessage());
			thread.start();
			//启动操作日志线程
			thread = new Thread(new OperateLog());
			thread.start();
			//启动异常日志线程
			thread = new Thread(new ExceptionLog());
			thread.start();
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			log.info("结束执行InitServlet初始化");
		}
	}
}