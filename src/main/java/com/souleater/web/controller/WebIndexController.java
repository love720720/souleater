package com.souleater.web.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.souleater.web.service.WebService;

/** 
 * web首页、登陆相关
 * @author love720720@163.com
 * @date 2017年5月24日 下午3:59:45
 */
@Controller
@RequestMapping("/web")
public class WebIndexController {
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	private WebService webService;
	
	/**
	 * 首页
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(){
		return new ModelAndView("/index");
	}
	
	/**
	 * 跳转登陆页面
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/toLogin")
	public ModelAndView toLogin(){
		return new ModelAndView("/login");
	}
	
	/**
	 * 登陆
	 * @param userName
	 * @param password
	 * @return
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public @ResponseBody String login(
			@RequestParam(value = "userName") String userName, 
			@RequestParam(value = "password") String password){
		return webService.login(userName,password,session);
	}
}
