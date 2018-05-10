package com.souleater.app.mapper;

import java.util.List;

import com.souleater.app.poJo.AppTemplatePoJo;
import com.souleater.app.poJo.AppUserPoJo;

/** 
 *
 * @author gaowei@log56.com
 * @date 2017年6月14日 下午3:11:47
 */
public interface AppMapper {

	public AppUserPoJo getAppUser(String openId);
	
	public void insertAppUser(String openId);
	
	public void updateAppUser(AppUserPoJo aupj);
	
	public int getAppUserListCount();

	public List<String> getAppUserList(AppUserPoJo aupj);

	public AppTemplatePoJo getTemplate(String templateKey);
}
