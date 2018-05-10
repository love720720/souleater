package com.souleater.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import com.souleater.app.poJo.AppMessagePoJo;
import com.souleater.app.poJo.AppTemplatePoJo;
import com.souleater.app.poJo.AppUserPoJo;
import com.souleater.app.service.AppService;
import com.souleater.exp.BaseException;
import com.souleater.init.Constants;
import com.souleater.util.AppUtils;
import com.souleater.util.CommonUtils;
import com.souleater.util.FileUtils;
import com.souleater.util.LogUtils;
import com.souleater.util.SpringContextUtils;
import com.souleater.util.XmlUtils;

/** 
 * 用于首次接入微信开发平台的验证
 * 以及消息接口
 * @author love720720@163.com
 * @date 2017年5月23日 下午2:56:44
 */
@Component
@WebServlet(name = "appMessageServlet", urlPatterns = "/app/ams", asyncSupported = false)
public class AppMessageServlet extends HttpServlet {
	
	private static final long serialVersionUID = 2833302153577213748L;
	
	private static Logger log = LogUtils.getLogger();
	
	private AppService appService = SpringContextUtils.getBean(AppService.class);
	
	/**
	 * 开发平台的验证
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("进入微信接口配置信息appMessageServlet");
		String result = Constants.EMPTY;
		try {
			String timestamp = request.getParameter("timestamp");
			String nonce = request.getParameter("nonce");
			String signature = request.getParameter("signature");
			String echostr = request.getParameter("echostr");
			String token = FileUtils.getSysConfigValue("token");
			
			log.info("获取的timestamp = " + timestamp);
			log.info("获取的nonce = " + nonce);
			log.info("获取的echostr = " + echostr);
			log.info("获取的token = " + token);
			log.info("获取的signature = " + signature);
			
			String verifySignature = AppUtils.getVerifySignature(token, timestamp, nonce);
			log.info("验证的signature = " + verifySignature);
			if(!StringUtils.equals(signature, verifySignature)){
				throw new BaseException("微信接口配置信息验证失败");
			}
			result = echostr;
			log.info("微信接口配置信息验证成功");
		} catch (BaseException e) {
			e.printStackTrace();
		} finally {
			CommonUtils.writerToClient(response, result);
			log.info("离开微信接口配置信息appMessageServlet");
		}
	}

	/**
	 * 消息
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServletInputStream sis = request.getInputStream();
		String xml = CommonUtils.getContent(sis);
		AppMessagePoJo amp = (AppMessagePoJo) XmlUtils.parseXmlClass(xml, AppMessagePoJo.class);
		String msgType = amp.getMsgType();
		String toUserName = amp.getToUserName();
		String fromUserName = amp.getFromUserName();
		
		//将消息直接返回给发送者
		amp.setToUserName(fromUserName);
		amp.setFromUserName(toUserName);
		
		//事件消息
		if(StringUtils.equals(msgType, "event")){
			String event = amp.getEvent();
			switch (event) {
				case "subscribe":
					//subscribe(订阅)
					appService.insertAppUser(fromUserName);
					
					AppTemplatePoJo atpj = appService.getTemplate("APP_TEMPLATE_ATTENTION");
					
					amp.setTitle(atpj.getTitle());
					amp.setDescription(atpj.getDescription());
					amp.setPicUrl(atpj.getPicUrl());
					amp.setUrl(atpj.getUrl());
					xml = AppUtils.getWxNewsMsg(amp);
					log.info(xml);
					CommonUtils.writerToClient(response, xml);
					break;
				
				case "unsubscribe":
					//unsubscribe(取消订阅)
					appService.updateAppUser(fromUserName,AppUserPoJo.STATUS_WGZ);
					break;
				
				default:
					break;
			}
			return;
		}
		
		//普通消息
		amp.setContent("您的消息已经收到！");
		xml = AppUtils.getWxTextMsg(amp);
		log.info(xml);
		CommonUtils.writerToClient(response, xml);
		return;
	}
}
