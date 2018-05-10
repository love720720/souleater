package com.souleater.web.service;

import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.souleater.init.Constants;
import com.souleater.util.ResultUtils;
import com.souleater.web.mapper.WebMapper;
import com.souleater.web.poJo.WebUserPoJo;

/** 
 * 
 * @author love720720@163.com
 * @date 2017年5月25日 上午10:58:11
 */
@Service
public class WebService {

	@Autowired
	private WebMapper webMapper;
	
	/**
	 * 登陆
	 * @param userName
	 * @param password
	 * @param session 
	 * @return
	 */
	public String login(String userName, String password, HttpSession session) {
		if(StringUtils.isBlank(userName)){
			return ResultUtils.getFailureResult("请输入用户名");
		}
		if(StringUtils.isBlank(password)){
			return ResultUtils.getFailureResult("请输入密码");
		}
		
		WebUserPoJo user = webMapper.getWebUser(userName);
		
		if(user == null){
			return ResultUtils.getFailureResult("输入的用户名或密码不正确");
		}
		
		String bCryptPassword = user.getPassword();
		PasswordEncoder pe = new BCryptPasswordEncoder();
		if(!pe.matches(password, bCryptPassword)){
			return ResultUtils.getFailureResult("输入的用户名或密码不正确");
		}
		bCryptPassword = pe.encode(password);
		user.setPassword(bCryptPassword);
		webMapper.updateWebUser(user);
		user.setPassword(null);
		session.setAttribute(Constants.WEB_USER_KEY, user);
		
		return ResultUtils.getSuccessResult("登陆成功");
	}
}