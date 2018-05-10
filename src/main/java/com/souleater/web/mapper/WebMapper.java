package com.souleater.web.mapper;

import com.souleater.web.poJo.WebUserPoJo;

/** 
 *
 * @author love720720@163.com
 * @date 2017年5月25日 上午11:26:29
 */
public interface WebMapper {

	public WebUserPoJo getWebUser(String userName);

	public void updateWebUser(WebUserPoJo user);

}
