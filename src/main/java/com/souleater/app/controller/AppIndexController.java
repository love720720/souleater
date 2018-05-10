package com.souleater.app.controller;

import org.slf4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.souleater.log.annotation.IOperateLog;
import com.souleater.util.LogUtils;


/** 
 * app
 * @author love720720@163.com
 * @date 2017年5月24日 下午3:59:45
 */
@Controller
@RequestMapping("/app")
public class AppIndexController {

	private static Logger log = LogUtils.getLogger();
	
	/**
	 * index
	 * @return
	 */
	@IOperateLog(description = "index")
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public ModelAndView index(){
		log.info("/app/index");
		ModelAndView mav = new ModelAndView("/app/index");
		return mav;
	}
	
	/**
	 * dwr
	 * @return
	 */
	@IOperateLog(description = "dwr")
	@RequestMapping(value = "/dwr", method = RequestMethod.GET)
	public ModelAndView dwr(){
		log.info("/app/dwr");
		ModelAndView mav = new ModelAndView("/app/dwr");
		return mav;
	}
}
