package com.test.own;

import java.util.SortedMap;
import java.util.TreeMap;

import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;

import com.souleater.util.HttpUtils;
import com.souleater.util.LogUtils;
import com.souleater.util.SecurityUtils;
import com.souleater.util.UrlUtils;

/**
 * 
 *
 * @author love720720@163.com
 * @date 2017年8月9日 下午3:39:22
 */
public class Test_PageMsgPush_1 {

	private static Logger log = LogUtils.getLogger();
	
	public static void main(String[] args) {
		try {
			
			String time = String.valueOf(System.currentTimeMillis());
			String msg = "正在测试页面消息发布功能。。。";
			
			SortedMap<String, String> map = new TreeMap<String, String>();
			map.put("time", time);
			map.put("msg", msg);
			
			String sign = SecurityUtils.getSHA1(map);
			
			JSONObject js = new JSONObject();
			js.put("time", time);
			js.put("msg", msg);
			js.put("sign", sign);
			
			String url = "http://192.168.3.86/souleater/app/apmps";
			String result = HttpUtils.doPost(url, UrlUtils.URLEncodeSecond(js.toString()));
			log.info(result);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}
}