package com.souleater.tsi.send;

import com.souleater.init.Constants;
import com.souleater.tsi.pojo.TsiComminPoJo;
import com.souleater.tsi.template.TsiTemplate;

/** 
 * 获取微信jsApiTicket
 * @author love720720@163.com
 * @date 2017年5月23日 上午15:06:00
 */
public class TsiJsApiTicket extends TsiTemplate {

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
	
	private static TsiJsApiTicket instance = null;

	private TsiJsApiTicket() {
	}

	public static TsiJsApiTicket getInstance() {
		if (instance == null) {
			synchronized (TsiJsApiTicket.class) {
				if (instance == null) {
					instance = new TsiJsApiTicket();
				}
			}
		}
		return instance;
	}
}
