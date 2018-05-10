package com.souleater.app.poJo;

/**
 *
 * @author love720720@163.com
 * @date 2017年6月9日 下午4:37:36
 */
public class AppTemplatePoJo {

	private int id;
	private String templateKey;//模板标识
	private String templateId;//微信模板id
	private String title;//模板消息标题
	private String description;//模板消息描述
	private String picUrl;//图片链接
	private String url;//跳转链接
	private String createTime;//媒体id
	private String lastModifTime;//标题

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTemplateKey() {
		return templateKey;
	}

	public void setTemplateKey(String templateKey) {
		this.templateKey = templateKey;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastModifTime() {
		return lastModifTime;
	}

	public void setLastModifTime(String lastModifTime) {
		this.lastModifTime = lastModifTime;
	}
}