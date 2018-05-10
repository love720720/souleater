package com.souleater.log.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souleater.log.mapper.LogMapper;
import com.souleater.log.poJo.LogPoJo;

/** 
 *
 * @author love720720@163.com
 * @date 2017年6月12日 下午4:44:20
 */
@Service
public class LogService {

	@Autowired
	private LogMapper logMapper;
	
	public void insertOperateLog(LogPoJo lpj){
		logMapper.insertOperateLog(lpj);
	}
	
	public void insertExceptionLog(LogPoJo lpj){
		logMapper.insertExceptionLog(lpj);
	}
}
