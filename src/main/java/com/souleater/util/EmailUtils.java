package com.souleater.util;

import java.security.GeneralSecurityException;
import java.util.Date;
import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.commons.lang3.StringUtils;

import com.sun.mail.util.MailSSLSocketFactory;

/**
 * 邮件相关的一些工具类
 * @author love720720@163.com
 * @date 2017年5月9日 上午9:06:32
 */
public class EmailUtils {

	private static String host = "smtp.exmail.qq.com"; // smtp服务器
	private static String port = "25"; // 端口
	private static String protocol = "smtps"; // 协议
	private static String subject = "系统测试发送"; // 邮件标题
	private static String name = ""; // 用户名 完成的邮箱
	private static String password = ""; // 密码
	private static String from = name; // 发件人地址 跟用户名一致
	private static String to = ""; // 收件人地址
	private static String cc = ""; // 抄送人地址
	private static String bcc = ""; // 密送人地址
	
	/**
	 * 邮件发送：文字
	 * 使用ssl
	 * @param content
	 * @return
	 */
	public static void sendSSL(String content) {
		try {
			verify();
			Properties prop = new Properties();
			prop.setProperty("mail.transport.protocol", protocol);
			prop.setProperty("mail.smtp.host", host);
			prop.setProperty("mail.smtp.port", port);
			prop.setProperty("mail.smtp.auth", "true");
			
			MailSSLSocketFactory sf = new MailSSLSocketFactory();
			sf.setTrustAllHosts(true);
			
			prop.put("mail.smtp.starttls.enable", "true");
			prop.put("mail.smtp.ssl.socketFactory", sf);
			Session session = Session.getDefaultInstance(prop, new MyAuthenricator(name, password));
			session.setDebug(true);
			
			MimeMessage message = new MimeMessage(session);
			message.setFrom(new InternetAddress(from));
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			message.setSubject(subject);
			message.setSentDate(new Date());
			message.setText(content);
			message.saveChanges();
			
			// 抄送
            if (StringUtils.isNoneBlank(cc)) {
            	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc));
            }
            
			// 密送
			if (StringUtils.isNoneBlank(bcc)) {
				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
			}
            
			Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 邮件发送：文字+附件
	 * 使用ssl
	 * @param content
	 * @param filePath
	 * @return
	 */
	public static void sendSSLFile(String content,String filePath) {
		try {
			verify();
			Properties prop = new Properties();
	        prop.setProperty("mail.transport.protocol", protocol);
	        prop.setProperty("mail.smtp.host", host);
	        prop.setProperty("mail.smtp.port", port);
	        prop.setProperty("mail.smtp.auth", "true");
	        
	        MailSSLSocketFactory sf = new MailSSLSocketFactory();
            sf.setTrustAllHosts(true);
            
	        prop.put("mail.smtp.starttls.enable", "true");
	        prop.put("mail.smtp.ssl.socketFactory", sf);
	        Session session = Session.getDefaultInstance(prop, new MyAuthenricator(name, password));
	        session.setDebug(true);
	        
	        BodyPart contentPart = new MimeBodyPart();
			contentPart.setText(content);
			
			FileDataSource fileds = new FileDataSource(filePath);
			BodyPart bp = new MimeBodyPart(); 
			bp.setDataHandler(new DataHandler(fileds)); 
			bp.setFileName(fileds.getName());
			
			Multipart multipart = new MimeMultipart();
			multipart.addBodyPart(contentPart);
			multipart.addBodyPart(bp);
			
	        MimeMessage message = new MimeMessage(session);
	        message.setFrom(new InternetAddress(from));
	        message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
	        message.setSubject(subject);
	        message.setSentDate(new Date());
	        message.setContent(multipart);
	        message.saveChanges();
	        
	        // 抄送
	        if (StringUtils.isNoneBlank(cc)) {
            	message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(cc)); 
            }
            
	        // 密送
 			if (StringUtils.isNoneBlank(bcc)) {
 				message.setRecipients(Message.RecipientType.BCC, InternetAddress.parse(bcc));
 			}
	     			
	        Transport.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		} catch (GeneralSecurityException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 校验参数
	 */
	private static void verify() {
		if(StringUtils.isBlank(host)){
			throw new NullPointerException("服务器地址不可为空");
		}
		if(StringUtils.isBlank(port)){
			throw new NullPointerException("服务器端口不可为空");
		}
		if(StringUtils.isBlank(protocol)){
			throw new NullPointerException("服务器协议不可为空");
		}
		if(StringUtils.isBlank(subject)){
			throw new NullPointerException("发送主题不可为空");
		}
		if(StringUtils.isBlank(name)){
			throw new NullPointerException("发件人不可为空");
		}
		if(StringUtils.isBlank(password)){
			throw new NullPointerException("发件人不可为空");
		}
		if(StringUtils.isBlank(to)){
			throw new NullPointerException("收件人地址不可为空");
		}
	}
	
	private static class MyAuthenricator extends Authenticator {
		private String u;
		private String p;

		public MyAuthenricator(String u, String p) {
			this.u = u;
			this.p = p;
		}

		@Override
		protected PasswordAuthentication getPasswordAuthentication() {
			return new PasswordAuthentication(u, p);
		}
	}
}