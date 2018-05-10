package com.souleater.tsi.template;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;

import com.souleater.init.Constants;
import com.souleater.util.LogUtils;
import com.souleater.util.ResultUtils;

/**
 * 接口模板
 * @author love720720@163.com
 * @date 2017年5月5日 下午5:51:10
 */
public abstract class TsiTemplate {

	private final static Logger log = LogUtils.getLogger();

	/**
	 * 调用相关接口
	 * @param obj
	 * @return
	 */
	public String execute(Object obj) {
		log.info("进入调用接口模板");
		String entUrl = getEntUrl(obj);
		log.info("接口地址:" + entUrl);
		String getAugs = getGetAugs(obj);
		log.info("接口地址参数:" + getAugs);
		String postAugs = getPostAugs(obj);
		log.info("接口发送参数:" + postAugs);
		
		BufferedReader in = null;
		PrintWriter out = null;
		String result = Constants.EMPTY;
		try {
			// get参数即url地址参数
			if(StringUtils.isNotBlank(getAugs)){
				entUrl = entUrl + "?" + getAugs;
			}
			URL url = new URL(entUrl);
			URLConnection conn = url.openConnection();
			// 设置通用的请求属性
			conn.setRequestProperty("accept", "*/*");
			conn.setRequestProperty("connection", "Keep-Alive");
			conn.setRequestProperty("user-agent", "Mozilla/4.0 (compatible; MSIE 6.0; Windows NT 5.1; SV1)");
			// 设置链接、超时时间
			conn.setConnectTimeout(10000);
			conn.setReadTimeout(5000);
			// post参数
			if(StringUtils.isNotBlank(postAugs)){
				// post必须设置
				conn.setDoOutput(true);
				conn.setDoInput(true);
				
				out = new PrintWriter(conn.getOutputStream());
				out.print(postAugs);
				out.flush();
			}
			in = new BufferedReader(new InputStreamReader(conn.getInputStream(), Constants.CHARSET));
			String line;
			while ((line = in.readLine()) != null) {
				result += line;
			}
		} catch (IOException e) {
			result = ResultUtils.getFailureResult();
			e.printStackTrace();
		} finally {
			try {
				if (out != null) {
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			log.info("接口返回：" + result);
			log.info("离开调用接口模板");
		}
		return result;
	}
	
	/**
	 * 接口url请求参数
	 * @param obj
	 * @return
	 */
	public abstract String getGetAugs(Object obj);
	
	/**
	 * 接口post请求参数
	 * @param obj
	 * @return
	 */
	public abstract String getPostAugs(Object obj);

	/**
	 * 接口请求地址
	 * @param obj
	 * @return
	 */
	public abstract String getEntUrl(Object obj);
}