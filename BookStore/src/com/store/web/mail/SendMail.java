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
 * �����û�ע�������,���ߺ�̨����ϵͳ���û�������ӣ��޸�ʱ����״̬Ϊ0���û����䣬�����Զ���������ʼ����ݵļ����ʼ�
 * @author ����
 */
public class SendMail{
	
	public static Message createMessage(Session session, String recipients,String content) throws Exception {
		MimeMessage message = new MimeMessage(session);
		message.setFrom(new InternetAddress("tan_qi@sohu.com"));
		message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipients));
		message.setSubject("�û�����");

		BodyPart text = new MimeBodyPart();
		text.setContent(content, "text/html;charset=utf-8");
		
		Multipart multipart = new MimeMultipart();
		multipart.addBodyPart(text); // װ������

		// ����ý����������message������,�����ʼ�����
		message.setContent(multipart);
		message.saveChanges();
		
		// ���ش����õ��ʼ�����
		return message;
		
	}
	
	public static void sendMail(String recipient,String content) throws Exception {
		//1���������ö���
		Properties prop = new Properties();
		InputStream in = SendMail.class.getClassLoader().getResourceAsStream("mail.properties");
		try { 
			prop.load(in);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Session session = Session.getInstance(prop);
		//session.setDebug(true); �����ʼ����͵�debugģʽ�����ڲ鿴�ʼ�����״̬
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
