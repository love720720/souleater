package com.souleater.util;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import com.souleater.exp.BaseException;
import com.souleater.init.Constants;
import com.souleater.web.poJo.WebUserPoJo;

/** 
 * 通用相关的一些工具类
 * @author love720720@163.com
 * @date 2017年5月7日 上午10:32:17
 */
public class CommonUtils {
	
	/**
	 * 获取request的参数
	 * @param request
	 * @return
	 * @throws BaseException 
	 */
	public static String getRequestInfo(HttpServletRequest request) {
		if (request == null) {
			throw new NullPointerException("HttpServletRequest不可为空");
		}
		ServletInputStream sis = null;
		try {
			sis = request.getInputStream();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return getContent(sis);
	}
	
	/**
	 * 将回调地址变成微信静默授权地址
	 * 为了获取openId
	 * @param url 回调地址
	 * @param state 回调参数
	 * @return
	 */
	public static String getOauthBaseUrl(String url,String state){
		url = UrlUtils.URLEncode(url);
		String oauthBaseUrl = Constants.WX_OAUTHBASEURL;
		oauthBaseUrl = oauthBaseUrl.replace("#{redirectUrl}", url);
		oauthBaseUrl = oauthBaseUrl.replace("#{state}", state);
		return oauthBaseUrl;
	}
	
	/**
	 * 获取客户端ip
	 * @param request
	 * @return
	 */
	public static String getIp(HttpServletRequest request) {
		String ip = null;
		try {
			ip = request.getHeader("X-Forwarded-For");
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getHeader("Proxy-Client-IP");
			}
			if (StringUtils.isBlank(ip) || "unknow".equalsIgnoreCase(ip)) {
				ip = request.getHeader("WL-Proxy-Client-IP");
			}
			if (StringUtils.isBlank(ip) || "unknow".equalsIgnoreCase(ip)) {
				ip = request.getHeader("X-Real-IP");
			}
			if (StringUtils.isBlank(ip) || "unknown".equalsIgnoreCase(ip)) {
				ip = request.getRemoteAddr();
	
				if (StringUtils.equals(ip,"127.0.0.1") || StringUtils.equals(ip,"0:0:0:0:0:0:0:1")) {
					// 根据网卡获取本机配置的IP地址
					InetAddress inetAddress = InetAddress.getLocalHost();
					ip = inetAddress.getHostAddress();
				}
			}
	
			// 对于通过多个代理的情况，第一个IP为客户端真实的IP地址，多个IP按照','分割
			if (StringUtils.isNotEmpty(ip) && ip.length() > 15) {
				// "***.***.***.***".length() = 15
				if (ip.indexOf(",") > 0) {
					ip = ip.substring(0, ip.indexOf(","));
				}
			}
			
			if(StringUtils.isBlank(ip)){
				ip = "unknow";
			}
		} catch (UnknownHostException e) {
			ip = "unknow";
		}
		return ip;
	}
	
	/**
	 * 获取ServletInputStream内容
	 * @param sis
	 * @return
	 */
	public static String getContent(ServletInputStream sis){
		if(sis == null){
			throw new NullPointerException("ServletInputStream不可为空");
		}
		StringBuilder sb = new StringBuilder();
	    try {
	    	byte[] b = new byte[1024];
	    	int i = 0;
			while ((i = sis.read(b)) != -1) {
				sb.append(new String(b, 0, i));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	    return sb.toString();
	}
	
	/**
	 * 判断是否登陆
	 */
	public static boolean checkLogin(HttpServletRequest request){
		HttpSession session = request.getSession();
		Object object = session.getAttribute(Constants.WEB_USER_KEY);
		if(object == null){
			return false;
		}
		return true;
	}
	
	/**
	 * 获取web登陆用户
	 */
	public static WebUserPoJo getWebLoginUser(HttpServletRequest request){
		WebUserPoJo wupj = null;
		try {
			HttpSession session = request.getSession();
			Object object = session.getAttribute(Constants.WEB_USER_KEY);
			if(object == null){
				throw new BaseException("登陆失效 请重新登录");
			}
			wupj = (WebUserPoJo)object;
		} catch (BaseException e) {
			e.printStackTrace();
		}
		return wupj;
	}
	
	/**
	 * 获取App登陆用户的openId
	 * @param request
	 */
	public static String getAppOpenId(HttpServletRequest request){
		String openId = Constants.EMPTY;
		HttpSession session = request.getSession();
		Object object = session.getAttribute(Constants.APP_USER_KEY);
		if(object != null){
			openId = (String)object;
		}
		return openId;
	}
	
	/**
	 * 获取web登陆用户的id
	 * @param request
	 */
	public static int getWebLoginId(HttpServletRequest request){
		int id = -1;
		HttpSession session = request.getSession();
		Object object = session.getAttribute(Constants.WEB_USER_KEY);
		if(object != null){
			WebUserPoJo wupj = (WebUserPoJo)object;
			id = wupj.getId();
		}
		return id;
	}
	
	/**
	 * 获取resources下signature.jpg图片地址
	 * @return
	 */
	public static String getLogoPath(){
		return PathUtils.getWebTargetPath() + File.separator + "classes" + File.separator + Constants.QR_CODE_LOGO;
	}
	
	/**
	 * 输出到客户端
	 * @param response
	 * @param context
	 */
	public static void writerToClient(HttpServletResponse response, String context) {
		if(response == null){
			throw new NullPointerException("HttpServletResponse不可为空");
		}
		if(StringUtils.isBlank(context)){
			throw new NullPointerException("输出内容不可为空");
		}
		response.setContentType("text/html; charset=UTF-8");
		PrintWriter out = null;
		try {
			out = response.getWriter();
			out.write(context);
		} catch (IOException e) {
		}finally{
			if(out != null){
				out.flush();
				out.close();
			}
		}
	}
}