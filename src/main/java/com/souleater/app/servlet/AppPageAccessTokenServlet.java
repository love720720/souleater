package com.souleater.app.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.souleater.init.Constants;
import com.souleater.tsi.api.TsiCommonApi;
import com.souleater.util.LogUtils;

/** 
 * 获取微信网页accessToken
 * @author love720720@163.com
 * @date 2017年6月16日 下午2:56:44
 */
@WebServlet(name = "appPageAccessTokenServlet", urlPatterns = "/app/apats", asyncSupported = false)
public class AppPageAccessTokenServlet extends HttpServlet {
	
	private static final long serialVersionUID = -6557825723237249811L;
	
	private static Logger log = LogUtils.getLogger();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		log.info("进入获取微信网页accessToken");
		String openId = null;
		try {
			HttpSession session = request.getSession();
			Object object = session.getAttribute(Constants.APP_USER_KEY);
			if(object != null){
				openId = (String)object;
				return;
			}
			String code = request.getParameter("code");
			//String state = request.getParameter("state");//业务参数
			String result = TsiCommonApi.tsiPageAccessToken(code);
			JSONObject json = new JSONObject(result);
			if(json.has("access_token")){
				Constants.WX_PAGE_ACCESSTOKEN = json.getString("access_token");
			}
			
			if(json.has("openid")){
				openId = json.getString("openid");
			}
			
			session.setAttribute(Constants.APP_USER_KEY, openId);
		} catch (JSONException e) {
			e.printStackTrace();
		} finally {
			response.sendRedirect("index");
			log.info("离开获取微信网页accessToken");
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}
}
