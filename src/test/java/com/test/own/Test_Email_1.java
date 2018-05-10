package com.test.own;

import com.souleater.util.EmailUtils;

/** 
 * 邮件测试类
 * @author love720720@163.com
 * @date 2017年5月9日 下午3:38:20
 */
public class Test_Email_1 {

	public static void main(String[] args) {
		EmailUtils.sendSSL("测试发送不带附件的邮件");
		//MailUtil.sendSSLFile("测试发送带附件的邮件", "D:\\test.txt");
	}
}
