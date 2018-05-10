package com.souleater.tsi.send;

import com.souleater.init.Constants;
import com.souleater.tsi.pojo.TsiComminPoJo;
import com.souleater.tsi.template.TsiTemplate;

/** 
 * 获取微信accessToken
 * @author love720720@163.com
 * @date 2017年6月16日 上午10:58:00
 */
public class TsiPageAccessToken extends TsiTemplate {
	
	public String getGetAugs(Object obj) {
		TsiComminPoJo tcpj = (TsiComminPoJo)obj;
		return tcpj.getGetAugs();
	}

	public String getPostAugs(Object obj) {
		return Constants.EMPTY;
	}

	public String getEntUrl(Object obj) {
		TsiComminPoJo tcpj = (TsiComminPoJo)obj;
		return tcpj.getEntUrl();
	}
	
	private static TsiPageAccessToken instance = null;

	private TsiPageAccessToken() {
	}

	public static TsiPageAccessToken getInstance() {
		if (instance == null) {
			synchronized (TsiPageAccessToken.class) {
				if (instance == null) {
					instance = new TsiPageAccessToken();
				}
			}
		}
		return instance;
	}
}
