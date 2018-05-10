package com.souleater.app.run;

import java.util.List;

import org.slf4j.Logger;

import com.souleater.app.poJo.AppTemplatePoJo;
import com.souleater.app.service.AppService;
import com.souleater.tsi.api.TsiCommonApi;
import com.souleater.util.LogUtils;
import com.souleater.util.PageUtils;
import com.souleater.util.SpringContextUtils;

/** 
 * 微信通知线程
 * @author love720720@163.com
 * @date 2017年5月23日 上午10:55:18
 */
public class AppStartMessage implements Runnable {

	private static Logger log = LogUtils.getLogger();
	
	private AppService appService = SpringContextUtils.getBean(AppService.class);
	
	public void run() {
		log.info("线程AppStartMessage开始执行模板消息推送");
		
		AppTemplatePoJo atpj = appService.getTemplate("APP_TEMPLATE_START");
		String templateId = atpj.getTemplateId();
		String url = atpj.getUrl();
		
		int totalCount = appService.getAppUserListCount();
		
		int count = PageUtils.COUNT_DEFAULT * 250;
		
		PageUtils.init(totalCount, count);
		
		for (int i = 0; i < PageUtils.totalPageCount; i++) {
			PageUtils.init(totalCount, count,i + 1);
			List<String> list = appService.getAppUserList(PageUtils.index, PageUtils.count);
			for (String openId : list) {
				log.info("获取的关注用户openId:" + openId);
				TsiCommonApi.tsiTemplateMsg(openId, templateId, url);
			}
		}
		log.info("线程AppStartMessage结束执行模板消息推送");
	}
}