package com.shop.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/*
 * 邮件放松工具类
 */

public class MailUtils {
	
	public static void sendMail(String to,String code){
		
		
		//1.获得连接对象
		Properties props = new Properties();
		props.setProperty("mail.host", "localhost");
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				// TODO Auto-generated method stub
				return new PasswordAuthentication("service@gm.com", "123456");			
				}
			
		});
		//2.创建邮件对象
		Message message = new MimeMessage(session);
		//3.设置发件人
		try {
			message.setFrom(new InternetAddress("service@gm.com"));
			//4.设置收件人
			message.setRecipient(RecipientType.TO, new InternetAddress(to));//抄送CC 密送BCC
			//5.设置标题
			message.setSubject("来自商城的激活邮件");
			//6.设置邮件的正文
			
			message.setContent("<h1>商城激活邮件,点下面的链接完成激活操作</h1><h3><a href='http://localhost/SSHShop/user_active.action?code="+code+"'>http://localhost/SSHShop/user_active.action?code="+code+"</a></h3>","text/html;charset=UTF-8");
			//7.发送邮件
			Transport.send(message);
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
