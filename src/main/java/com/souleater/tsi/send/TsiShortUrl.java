package com.souleater.tsi.send;

import com.souleater.init.Constants;
import com.souleater.tsi.pojo.TsiComminPoJo;
import com.souleater.tsi.template.TsiTemplate;

/**
 * 短网址生成
 * @author love720720@163.com
 * @date 2017年5月8日 下午4:14:55
 */
public class TsiShortUrl extends TsiTemplate{

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
	
	private static TsiShortUrl instance = null;

	private TsiShortUrl() {
	}

	public static TsiShortUrl getInstance() {
		if (instance == null) {
			synchronized (TsiShortUrl.class) {
				if (instance == null) {
					instance = new TsiShortUrl();
				}
			}
		}
		return instance;
	}
}