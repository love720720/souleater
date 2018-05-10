package com.souleater.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.souleater.init.Constants;
import com.souleater.util.AppUtils;
import com.souleater.util.CommonUtils;
import com.souleater.util.LogUtils;
import com.souleater.util.SecurityUtils;

/** 
 * 获取微信jsSdk一些必要的信息
 * @author love720720@163.com
 * @date 2017年5月23日 下午2:56:44
 */
@WebServlet(name = "appJsSdkServlet", urlPatterns = "/app/ajss", asyncSupported = false)
public class AppJsSdkServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6557825723237249811L;
	
	private static Logger log = LogUtils.getLogger();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("进入微信获取JsSdk参数信息appJsSdkServlet");
		String result = Constants.EMPTY;
		try {
			String url = request.getHeader("Referer");
			String timestamp = String.valueOf(System.currentTimeMillis() / 1000);
			String nonceStr = SecurityUtils.getSHA1(timestamp);
			String signature = AppUtils.getWxSignature(nonceStr, timestamp, url);
			
			JSONObject json = new JSONObject();
			json.put("appId", Constants.WX_APPID);
			json.put("timestamp", timestamp);
			json.put("nonceStr", nonceStr);
			json.put("signature", signature);
			
			log.info("appId = " + Constants.WX_APPID);
			log.info("timestamp = " + timestamp);
			log.info("nonceStr = " + nonceStr);
			log.info("signature = " + signature);
			
			result = json.toString();
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			CommonUtils.writerToClient(response, result);
			log.info("离开微信获取JsSdk参数信息appJsSdkServlet");
		}
	}
}
