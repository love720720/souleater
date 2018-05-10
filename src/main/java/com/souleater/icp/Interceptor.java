package com.souleater.icp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.souleater.util.CommonUtils;

/** 
 * 系统拦截器
 * @author love720720@163.com
 * @date 2017年5月5日 下午1:46:46
 */
public class Interceptor implements HandlerInterceptor {

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj) throws Exception {
		if(!CommonUtils.checkLogin(request)){
			String url = request.getScheme() + "://" + request.getServerName() + request.getContextPath();
			response.sendRedirect(url + "/toLogin");
			return false;
		}
		return true;
	}
	
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView mav) throws Exception {
		System.out.println();
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception e) throws Exception {
		System.out.println();
	}
}
