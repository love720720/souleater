package com.souleater.app.run;

import java.util.concurrent.CountDownLatch;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.souleater.init.Constants;
import com.souleater.tsi.api.TsiCommonApi;
import com.souleater.util.LogUtils;

/** 
 * 微信 accessToken线程
 * @author love720720@163.com
 * @date 2017年5月23日 上午10:55:18
 */
public class AppAccessToken implements Runnable {

	private static Logger log = LogUtils.getLogger();
	
	//凭证有效时间，单位：秒
	private int expiresIn;
	
	private CountDownLatch cdl;
	
	public AppAccessToken(CountDownLatch cdl) {
		this.cdl = cdl;
	}

	public void run() {
		log.info("线程AppAccessToken开始执行");
		try {
			for (; ;) {
				Thread.sleep(expiresIn * 1000L);
				log.info("开始获取accessToken");
				
				String result = TsiCommonApi.tsiAccessToken();
				JSONObject json = new JSONObject(result);
				if(json.has("access_token")){
					Constants.WX_ACCESSTOKEN = json.getString("access_token");
				}
				
				if(json.has("expires_in")){
					expiresIn = json.getInt("expires_in");
				}
				log.info("获取到的凭证accessToken = " + Constants.WX_ACCESSTOKEN);
				log.info("获取到的凭证有效时间expiresIn = " + expiresIn + "秒");
				
				//为保证accessToken时效 减少线程休眠时间 休眠时间减少1分钟
				//防止网络延迟等情况下 该线程获取accessToken超时
				expiresIn = expiresIn - 60;
				cdl.countDown();
				log.info("线程AppAccessToken开始进入休眠状态 预计休眠" + expiresIn / 60 + "分钟");
				log.info("结束获取accessToken");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			log.info("线程AppAccessToken结束执行");
		}
	}
}