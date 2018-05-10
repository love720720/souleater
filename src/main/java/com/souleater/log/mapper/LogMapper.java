package com.souleater.log.mapper;

import com.souleater.log.poJo.LogPoJo;

/** 
 *
 * @author love720720@163.com
 * @date 2017年6月12日 下午4:50:13
 */
public interface LogMapper {

	public void insertOperateLog(LogPoJo lpj);
	
	public void insertExceptionLog(LogPoJo lpj);
}
