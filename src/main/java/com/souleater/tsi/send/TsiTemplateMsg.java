package com.souleater.tsi.send;

import org.json.JSONException;
import org.json.JSONObject;

import com.souleater.init.Constants;
import com.souleater.tsi.pojo.TsiComminPoJo;
import com.souleater.tsi.template.TsiTemplate;
import com.souleater.util.CommonUtils;
import com.souleater.util.DateUtils;

/** 
 * 微信模板消息推送
 * @author love720720@163.com
 * @date 2017年5月23日 上午10:58:00
 */
public class TsiTemplateMsg extends TsiTemplate {
	
	public String getGetAugs(Object obj) {
		TsiComminPoJo tcpj = (TsiComminPoJo)obj;
		return tcpj.getGetAugs();
	}
	
	public String getPostAugs(Object obj) {
		TsiComminPoJo tcpj = (TsiComminPoJo)obj;
		String toUser = tcpj.getToUser();
		String templateId = tcpj.getTemplateId();
		String url = tcpj.getUrl();
		url = CommonUtils.getOauthBaseUrl(url, Constants.EMPTY);
		
		JSONObject json = new JSONObject();
		try {
			json.put("touser", toUser);
			json.put("template_id", templateId);
			json.put("url", url);
			JSONObject path = new JSONObject();
			path.put("value", Constants.WEB_PATH);
			path.put("color", "#173177");
			
			JSONObject date = new JSONObject();
			date.put("value", DateUtils.getToday(Constants.FORMAT_YMDHMSMS));
			date.put("color", "#173177");
			
			JSONObject msg = new JSONObject();
			msg.put("value", "点击进行查看");
			msg.put("color", "#173177");
			
			JSONObject data = new JSONObject();
			data.put("path", path);
			data.put("date", date);
			data.put("msg", msg);
			json.put("data", data);
		} catch (JSONException e) {
			e.printStackTrace();
		}
		return json.toString();
	}

	public String getEntUrl(Object obj) {
		TsiComminPoJo tcpj = (TsiComminPoJo)obj;
		return tcpj.getEntUrl();
	}
	
	private static TsiTemplateMsg instance = null;

	private TsiTemplateMsg() {
	}

	public static TsiTemplateMsg getInstance() {
		if (instance == null) {
			synchronized (TsiTemplateMsg.class) {
				if (instance == null) {
					instance = new TsiTemplateMsg();
				}
			}
		}
		return instance;
	}
}
