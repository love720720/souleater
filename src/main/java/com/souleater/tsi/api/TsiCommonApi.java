package com.souleater.tsi.api;

import com.souleater.init.Constants;
import com.souleater.tsi.pojo.TsiComminPoJo;
import com.souleater.tsi.send.TsiAccessToken;
import com.souleater.tsi.send.TsiJsApiTicket;
import com.souleater.tsi.send.TsiPageAccessToken;
import com.souleater.tsi.send.TsiShortUrl;
import com.souleater.tsi.send.TsiTemplateMsg;

/**
 * 公共接口方法
 * @author love720720@163.com
 * @date 2017年5月8日 下午4:14:16
 */
public class TsiCommonApi {
	
	/**
	 * 推送微信模板消息
	 * @param openId
	 * @param templateId
	 * @return
	 */
	public static String tsiTemplateMsg(String openId,String templateId,String url){
		TsiComminPoJo tcpj = new TsiComminPoJo();
		tcpj.setEntUrl(Constants.WX_API + "/cgi-bin/message/template/send");
		tcpj.setGetAugs("access_token=" + Constants.WX_ACCESSTOKEN);
		tcpj.setToUser(openId);
		tcpj.setTemplateId(templateId);
		tcpj.setUrl(url);
		String result = TsiTemplateMsg.getInstance().execute(tcpj);
		return result;
	}
	
	/**
	 * 获取微信jsApiTicket
	 * @return
	 */
	public static String tsiJsApiTicket() {
		TsiComminPoJo tcpj = new TsiComminPoJo();
		tcpj.setEntUrl(Constants.WX_API + "/cgi-bin/ticket/getticket");
		tcpj.setGetAugs("type=jsapi&access_token=" + Constants.WX_ACCESSTOKEN);
		String result = TsiJsApiTicket.getInstance().execute(tcpj);
		return result;
	}
	
	/**
	 * 获取微信网页accessToken
	 * @param appId
	 * @param appsecret
	 * @return
	 */
	public static String tsiPageAccessToken(String code){
		TsiComminPoJo tcpj = new TsiComminPoJo();
		tcpj.setEntUrl(Constants.WX_API + "/sns/oauth2/access_token");
		tcpj.setGetAugs("appid=" + Constants.WX_APPID + "&secret=" + Constants.WX_APPSECRET + "&code=" + code + "&grant_type=authorization_code");
		String result = TsiPageAccessToken.getInstance().execute(tcpj);
		return result;
	}

	/**
	 * 获取微信accessToken
	 * @param appId
	 * @param appsecret
	 * @return
	 */
	public static String tsiAccessToken(){
		TsiComminPoJo tcpj = new TsiComminPoJo();
		tcpj.setEntUrl(Constants.WX_API + "/cgi-bin/token");
		tcpj.setGetAugs("grant_type=client_credential&appid=" + Constants.WX_APPID + "&secret=" + Constants.WX_APPSECRET);
		String result = TsiAccessToken.getInstance().execute(tcpj);
		return result;
	}
	
	/**
	 * 获取短网址
	 * @param url
	 * @return
	 */
	public static String tsiShortUrl(String url) {
		TsiComminPoJo tcpj = new TsiComminPoJo();
		tcpj.setEntUrl(Constants.SHORT_API_URL);
		tcpj.setGetAugs("url=" + url);
		String result = TsiShortUrl.getInstance().execute(tcpj);
		return result;
	}

	/**
	 * 获取json格式的短网址
	 * @param url
	 * @return
	 */
	public static String tsiShortJsonUrl(String url) {
		TsiComminPoJo tcpj = new TsiComminPoJo();
		tcpj.setEntUrl(Constants.SHORT_API_URL);
		tcpj.setGetAugs("format=json&url=" + url);
		String result = TsiShortUrl.getInstance().execute(tcpj);
		return result;
	}
}