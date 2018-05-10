package com.souleater.app.run;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.souleater.init.Constants;
import com.souleater.tsi.api.TsiCommonApi;
import com.souleater.util.LogUtils;

/** 
 * 微信网页 jsApiTicket线程
 * @author love720720@163.com
 * @date 2017年5月23日 上午15:05:18
 */
public class AppJsApiTicket implements Runnable {

	private static Logger log = LogUtils.getLogger();
	
	//凭证有效时间，单位：秒
	private int expiresIn;
	
	public void run() {
		log.info("线程AppJsApiTicket开始执行");
		try {
			for (; ;) {
				Thread.sleep(expiresIn * 1000L);
				log.info("开始获取jsApiTicket");
				
				String result = TsiCommonApi.tsiJsApiTicket();
				JSONObject json = new JSONObject(result);
				if(json.has("ticket")){
					Constants.WX_TICKET = json.getString("ticket");
				}
				
				if(json.has("expires_in")){
					expiresIn = json.getInt("expires_in");
				}
				log.info("获取到的凭证jsApiTicket = " + Constants.WX_TICKET);
				log.info("获取到的凭证有效时间expiresIn = " + expiresIn + "秒");
				
				//为保证jsApiTicket时效 减少线程休眠时间 休眠时间减少1分钟
				//防止网络延迟等情况下 该线程获取jsApiTicket超时
				expiresIn = expiresIn - 60;
				log.info("线程AppJsApiTicket开始进入休眠状态 预计休眠" + expiresIn / 60 + "分钟");
				log.info("结束获取jsApiTicket");
			}
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			log.info("线程AppJsApiTicket结束执行");
		}
	}
}