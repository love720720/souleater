package com.souleater.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.URL;
import java.net.URLConnection;

import org.apache.commons.lang3.StringUtils;

import com.souleater.init.Constants;


/** 
 * Http相关的一些工具类
 * @author love720720@163.com
 * @date 2017年6月14日 上午10:32:17
 */
public class HttpUtils {
	
	public static String doPost(String entUrl, String postAugs){
		BufferedReader in = null;
		PrintWriter out = null;
		String result = Constants.EMPTY;
		try {
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
		}
		return result;
	}
	
	public static String doGet(String entUrl, String getAugs){
		BufferedReader in = null;
		PrintWriter out = null;
		String result = Constants.EMPTY;
		try {
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
					out.flush();
					out.close();
				}
				if (in != null) {
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
}