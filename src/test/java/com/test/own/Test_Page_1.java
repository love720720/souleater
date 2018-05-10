package com.test.own;

import org.slf4j.Logger;

import com.souleater.util.LogUtils;
import com.souleater.util.PageUtils;

/** 
 * Java Class与反射相关的测试类
 * @author love720720@163.com
 * @date 2017年5月15日 下午2:53:04
 */
public class Test_Page_1 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) {
		PageUtils.init(53842, 25, 8, 9, "");
		
		log.info("从第" + PageUtils.index + "条开始分页");
		log.info("总条数:" + PageUtils.totalCount + "条");
		log.info("总页数:" + PageUtils.totalPageCount + "页");
		log.info("每页显示条数:" + PageUtils.count + "条");
		log.info("当前页:第" + PageUtils.pageIndex + "页");
		log.info("开始页:第" + PageUtils.firstIndex + "页");
		log.info("结束页:第" + PageUtils.lastIndex + "页");
		log.info("分页地址:" + PageUtils.fromAction);
		log.info("分页页码:" + PageUtils.getPageNums());
	}
}
