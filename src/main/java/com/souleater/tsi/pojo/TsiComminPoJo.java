package com.souleater.tsi.pojo;


/**
 * 公共接口pojo
 * 
 * @author love720720@163.com
 * @date 2017年5月8日 下午4:14:30
 */
public class TsiComminPoJo {

	// 接口地址、get参数
	private String entUrl;
	private String getAugs;
	// 接口参数、拼接post参数
	private String toUser;
	private String templateId;
	private String url;
	private String nextOpenId;
	private String appId;
	private String appsecret;

	public String getEntUrl() {
		return entUrl;
	}

	public void setEntUrl(String entUrl) {
		this.entUrl = entUrl;
	}

	public String getGetAugs() {
		return getAugs;
	}

	public void setGetAugs(String getAugs) {
		this.getAugs = getAugs;
	}

	public String getToUser() {
		return toUser;
	}

	public void setToUser(String toUser) {
		this.toUser = toUser;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getNextOpenId() {
		return nextOpenId;
	}

	public void setNextOpenId(String nextOpenId) {
		this.nextOpenId = nextOpenId;
	}

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppsecret() {
		return appsecret;
	}

	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}
}