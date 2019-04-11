package com.store.web.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 * 根据用户注册的邮箱,或者后台管理系统对用户进行添加，修改时激活状态为0的用户邮箱，发送自定义主题的邮件内容的激活邮件
 * @author 老腰
 */
public class SendMail{
	
	public static Message createMessage(Session session, String recipients,String content) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("tan_qi@sohu.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
		message.setSubject("用户激活");

		BodyPart text = new MimeBodyPart();
		text.setContent(content, "text/html;charset=utf-8");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(text); // 装载正文

		// 将多媒体对象加载入message对象中,设置邮件内容
		message.setContent(multipart);
		message.saveChanges();
		
		// 返回创建好的邮件对象
		return message;
		
	}
	
	public static void sendMail(String recipient,String content) throws Exception {
		//1、创建配置对象
		Properties prop = new Properties();
		InputStream in = SendMail.class.getClassLoader().getResourceAsStream("mail.properties");
		try { 
			prop.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Session session = Session.getInstance(prop);
		//session.setDebug(true); 开启邮件发送的debug模式，用于查看邮件发送状态
		 try {
			Transport ts = session.getTransport();
			ts.connect("smtp.sohu.com","tan_qi@sohu.com","tq6020162483");
			Message message = createMessage(session, recipient, content);
			ts.sendMessage(message, message.getAllRecipients());
		} catch (NoSuchProviderException e) {
			e.printStackTrace();
		}
		
	}

}
