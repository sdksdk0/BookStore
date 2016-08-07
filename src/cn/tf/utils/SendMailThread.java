package cn.tf.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import cn.tf.domain.Customer;

public class SendMailThread extends Thread{
	
	private Customer customer;
	public SendMailThread(Customer customer){
		this.customer=customer;
	}
	
	public void run(){
		
	Properties props=new Properties();
		
		props.setProperty("mail.transport.protocol", "smtp");//规范规定的参数
		props.setProperty("mail.host", "smtp.mxhichina.com");//这里使用万网的邮箱主机
		props.setProperty("mail.smtp.auth", "true");//请求认证，不认证有可能发不出去邮件。
		
		Session session=Session.getInstance(props);
		MimeMessage message=new MimeMessage(session);
		
		try {
			message.setFrom(new InternetAddress("xingtian@tianfang1314.cn"));
			message.setRecipients(Message.RecipientType.TO, customer.getEmail());
			
			message.setSubject("来自指令汇科技书店的注册邮件");
			message.setContent("","text/html;charset=UTF-8");
			
			message.setContent("亲爱的"+customer.getUsername()+":<br/>请猛戳下面激活您的账户<a href='http://localhost:8080/BookStore/servlet/ClientServlet?op=activeCustomer&code="+customer.getCode()+"'>激活</a><br/>", "text/html;charset=UTF-8");
			message.saveChanges();
			
			Transport ts = session.getTransport();
			ts.connect("你的邮箱账号", "你的密码");
			ts.sendMessage(message, message.getAllRecipients());
			ts.close();
		} catch (AddressException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
	}

}
