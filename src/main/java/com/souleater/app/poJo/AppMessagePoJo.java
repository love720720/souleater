package com.souleater.app.poJo;

/**
 *
 * @author love720720@163.com
 * @date 2017年6月9日 下午4:37:36
 */
public class AppMessagePoJo {

	private String ToUserName;//接收方帐号
	private String FromUserName;//开发者微信号
	private String CreateTime;//消息创建时间 （整型）
	private String MsgType;//消息类型
	private String Content;//回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	private String MsgId;//消息id，64位整型

	private String PicUrl;//图片链接（由系统生成）
	private String MediaId;//媒体id
	
	private String Title;//标题
	private String Description;//描述
	
	private String MusicUrl;//音乐链接
	private String HQMusicUrl;//高质量音乐链接
	private String ThumbMediaId;//缩略图的媒体id
	
	private String ArticleCount;//图文消息个数，限制为8条以内
	private String Articles;//图片链接
	private String Url;//点击图文消息跳转链接
	
	private String Event;//事件类型

	public String getToUserName() {
		return ToUserName;
	}

	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	public String getFromUserName() {
		return FromUserName;
	}

	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getMsgType() {
		return MsgType;
	}

	public void setMsgType(String msgType) {
		MsgType = msgType;
	}

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

	public String getMsgId() {
		return MsgId;
	}

	public void setMsgId(String msgId) {
		MsgId = msgId;
	}

	public String getPicUrl() {
		return PicUrl;
	}

	public void setPicUrl(String picUrl) {
		PicUrl = picUrl;
	}

	public String getMediaId() {
		return MediaId;
	}

	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}

	public String getTitle() {
		return Title;
	}

	public void setTitle(String title) {
		Title = title;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public String getMusicUrl() {
		return MusicUrl;
	}

	public void setMusicUrl(String musicUrl) {
		MusicUrl = musicUrl;
	}

	public String getHQMusicUrl() {
		return HQMusicUrl;
	}

	public void setHQMusicUrl(String hQMusicUrl) {
		HQMusicUrl = hQMusicUrl;
	}

	public String getThumbMediaId() {
		return ThumbMediaId;
	}

	public void setThumbMediaId(String thumbMediaId) {
		ThumbMediaId = thumbMediaId;
	}

	public String getArticleCount() {
		return ArticleCount;
	}

	public void setArticleCount(String articleCount) {
		ArticleCount = articleCount;
	}

	public String getArticles() {
		return Articles;
	}

	public void setArticles(String articles) {
		Articles = articles;
	}

	public String getUrl() {
		return Url;
	}

	public void setUrl(String url) {
		Url = url;
	}

	public String getEvent() {
		return Event;
	}

	public void setEvent(String event) {
		Event = event;
	}
}