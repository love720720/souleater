package com.test.own;

import com.souleater.tsi.api.TsiCommonApi;

/**
 * 短网址
 * @author love720720@163.com
 * @date 2017年5月5日 下午5:12:01
 */
public class Test_ShortUrl_1 {

	public static void main(String[] args) throws Exception {
		 String url = "https://game.weixin.qq.com/cgi-bin/h5/static/smobaweekreport/index.html?ssid=1&openid=owanlsj9Q1azTDKY7i0yaGPI5_eE&partition=4040&start_date=2017-04-24";
		 
		 TsiCommonApi.tsiShortUrl(url);
	}
}
