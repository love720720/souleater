package com.souleater.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.souleater.app.mapper.AppMapper;
import com.souleater.app.poJo.AppTemplatePoJo;
import com.souleater.app.poJo.AppUserPoJo;

/** 
 *
 * @author gaowei@log56.com
 * @date 2017年6月14日 下午3:10:28
 */
@Service
public class AppService {

	@Autowired
	private AppMapper appMapper;
	
	/**
	 * 插入或更新一条关注用户
	 * @param openId
	 */
	public void insertAppUser(String openId){
		AppUserPoJo aupj = getAppUser(openId);
		if(aupj == null){
			//未关注新增关注
			appMapper.insertAppUser(openId);
			return;
		}
		//已关注
		int status = aupj.getStatus();
		if(AppUserPoJo.STATUS_WGZ == status){
			//状态未关注 更改状态为已关注
			updateAppUser(openId, AppUserPoJo.STATUS_YGZ);
		}
	}
	
	/**
	 * 根据openId获取的用户
	 * @param openId
	 * @param status
	 */
	public AppUserPoJo getAppUser(String openId){
		AppUserPoJo aupj = appMapper.getAppUser(openId);
		return aupj;
	}
	
	
	/**
	 * 根据openId修改关注状态
	 * @param openId
	 * @param status
	 */
	public void updateAppUser(String openId, int status){
		AppUserPoJo aupj = new AppUserPoJo();
		aupj.setOpenId(openId);
		aupj.setStatus(status);
		appMapper.updateAppUser(aupj);
	}
	
	/**
	 * 获取已关注的用户openId
	 * 可分页获取
	 * @param index
	 * @param count
	 * @return
	 */
	public List<String> getAppUserList(int index,int count){
		AppUserPoJo aupj = new AppUserPoJo();
		aupj.setIndex(index);
		aupj.setCount(count);
		return appMapper.getAppUserList(aupj);
	}
	
	/**
	 * 获取已关注的用户总数
	 * @return
	 */
	public int getAppUserListCount(){
		return appMapper.getAppUserListCount();
	}
	
	/**
	 * 获取微信模板消息内容
	 * @param templateKey
	 * @return
	 */
	public AppTemplatePoJo getTemplate(String templateKey){
		return appMapper.getTemplate(templateKey);
	}
}
