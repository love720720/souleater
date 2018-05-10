package com.souleater.util;

import java.util.SortedMap;
import java.util.TreeMap;

import com.souleater.app.poJo.AppMessagePoJo;
import com.souleater.init.Constants;

/** 
 * 微信相关的一些工具类
 * @author love720720@163.com
 * @date 2017年6月14日 上午10:32:17
 */
public class AppUtils {
	
	/**
	 * 微信图文消息内容
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param title 图文消息标题
	 * @param description 图文消息描述
	 * @param picUrl 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 * @param url 点击图文消息跳转链接
	 * @return
	 */
	public static String getWxNewsMsg(AppMessagePoJo amp){
		String url = CommonUtils.getOauthBaseUrl(amp.getUrl(), Constants.EMPTY);
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName>");
		sb.append(amp.getToUserName());
		sb.append("</ToUserName>");
		sb.append("<FromUserName>");
		sb.append(amp.getFromUserName());
		sb.append("</FromUserName>");
		sb.append("<CreateTime>");
		sb.append(System.currentTimeMillis());
		sb.append("</CreateTime>");
		sb.append("<MsgType>news</MsgType>");
		sb.append("<ArticleCount>1</ArticleCount>");
		sb.append("<Articles>");
		sb.append("<item>");
		sb.append("<Title>");
		sb.append(amp.getTitle());
		sb.append("</Title>");
		sb.append("<Description>");
		sb.append(amp.getDescription());
		sb.append("</Description>");
		sb.append("<PicUrl>");
		sb.append(amp.getPicUrl());
		sb.append("</PicUrl>");
		sb.append("<Url>");
		sb.append(url);
		sb.append("</Url>");
		sb.append("</item>");
		sb.append("</Articles>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 微信音乐消息内容
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param title 音乐标题
	 * @param description 音乐描述
	 * @param musicUrl 音乐链接
	 * @param hQMusicUrl 高质量音乐链接，WIFI环境优先使用该链接播放音乐
	 * @param thumbMediaId 缩略图的媒体id，通过素材管理中的接口上传多媒体文件，得到的id
	 * @return
	 */
	public static String getWxMusicMsg(AppMessagePoJo amp){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName>");
		sb.append(amp.getToUserName());
		sb.append("</ToUserName>");
		sb.append("<FromUserName>");
		sb.append(amp.getFromUserName());
		sb.append("</FromUserName>");
		sb.append("<CreateTime>");
		sb.append(System.currentTimeMillis());
		sb.append("</CreateTime>");
		sb.append("<MsgType>video</MsgType>");
		sb.append("<Music>");
		sb.append("<Title>");
		sb.append(amp.getTitle());
		sb.append("</Title>");
		sb.append("<Description>");
		sb.append(amp.getDescription());
		sb.append("</Description>");
		sb.append("<MusicUrl>");
		sb.append(amp.getMusicUrl());
		sb.append("</MusicUrl>");
		sb.append("<HQMusicUrl>");
		sb.append(amp.getHQMusicUrl());
		sb.append("</HQMusicUrl>");
		sb.append("<ThumbMediaId>");
		sb.append(amp.getThumbMediaId());
		sb.append("</ThumbMediaId>");
		sb.append("</Music>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 微信视频消息内容
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
	 * @param title 视频消息的标题
	 * @param description 视频消息的描述
	 * @return
	 */
	public static String getWxVideoMsg(AppMessagePoJo amp){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName>");
		sb.append(amp.getToUserName());
		sb.append("</ToUserName>");
		sb.append("<FromUserName>");
		sb.append(amp.getFromUserName());
		sb.append("</FromUserName>");
		sb.append("<CreateTime>");
		sb.append(System.currentTimeMillis());
		sb.append("</CreateTime>");
		sb.append("<MsgType>video</MsgType>");
		sb.append("<Video>");
		sb.append("<MediaId>");
		sb.append(amp.getMediaId());
		sb.append("</MediaId>");
		sb.append("<Title>");
		sb.append(amp.getTitle());
		sb.append("</Title>");
		sb.append("<Description>");
		sb.append(amp.getDescription());
		sb.append("</Description>");
		sb.append("</Video>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 微信语音消息内容
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id
	 * @return
	 */
	public static String getWxVoiceMsg(AppMessagePoJo amp){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName>");
		sb.append(amp.getToUserName());
		sb.append("</ToUserName>");
		sb.append("<FromUserName>");
		sb.append(amp.getFromUserName());
		sb.append("</FromUserName>");
		sb.append("<CreateTime>");
		sb.append(System.currentTimeMillis());
		sb.append("</CreateTime>");
		sb.append("<MsgType>voice</MsgType>");
		sb.append("<Voice>");
		sb.append("<MediaId>");
		sb.append(amp.getMediaId());
		sb.append("</MediaId>");
		sb.append("</Voice>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 微信图片消息内容
	 * @param toUserName 接收方帐号（收到的OpenID）
	 * @param fromUserName 开发者微信号
	 * @param mediaId 通过素材管理中的接口上传多媒体文件，得到的id。
	 * @return
	 */
	public static String getWxImageMsg(AppMessagePoJo amp){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName>");
		sb.append(amp.getToUserName());
		sb.append("</ToUserName>");
		sb.append("<FromUserName>");
		sb.append(amp.getFromUserName());
		sb.append("</FromUserName>");
		sb.append("<CreateTime>");
		sb.append(System.currentTimeMillis());
		sb.append("</CreateTime>");
		sb.append("<MsgType>image</MsgType>");
		sb.append("<Image>");
		sb.append("<MediaId>");
		sb.append(amp.getMediaId());
		sb.append("</MediaId>");
		sb.append("</Image>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 微信文本消息内容 接收方帐号（收到的OpenID）
	 * @param toUserName
	 * @param fromUserName 开发者微信号
	 * @param content 回复的消息内容（换行：在content中能够换行，微信客户端就支持换行显示）
	 * @return
	 */
	public static String getWxTextMsg(AppMessagePoJo amp){
		StringBuilder sb = new StringBuilder();
		sb.append("<xml>");
		sb.append("<ToUserName>");
		sb.append(amp.getToUserName());
		sb.append("</ToUserName>");
		sb.append("<FromUserName>");
		sb.append(amp.getFromUserName());
		sb.append("</FromUserName>");
		sb.append("<CreateTime>");
		sb.append(System.currentTimeMillis());
		sb.append("</CreateTime>");
		sb.append("<MsgType>text</MsgType>");
		sb.append("<Content>");
		sb.append(amp.getContent());
		sb.append("</Content>");
		sb.append("</xml>");
		return sb.toString();
	}
	
	/**
	 * 获取微信js签名
	 * @param jsapiTicket
	 * @param nonceStr
	 * @param timestamp
	 * @param url
	 * @return
	 */
	public static String getWxSignature(String nonceStr, String timestamp, String url){
		SortedMap<String, String> map = new TreeMap<String, String>();
		map.put("jsapi_ticket", Constants.WX_TICKET);
		map.put("noncestr", nonceStr);
		map.put("timestamp", timestamp);
		map.put("url", url);
		return SecurityUtils.getSHA1(map);
	}
	
	/**
	 * 获取微信接口配置信息的签名
	 * @param nonceStr
	 * @param timestamp
	 * @param url
	 * @return
	 */
	public static String getVerifySignature(String token, String timestamp, String nonce){
		String[] array = new String[] {token, timestamp, nonce};
		return SecurityUtils.getSHA1(array);
	}
}