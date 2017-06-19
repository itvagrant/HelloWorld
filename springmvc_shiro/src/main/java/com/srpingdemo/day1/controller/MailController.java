package com.srpingdemo.day1.controller;

import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/mail")
public class MailController {

	/**
	 * 发送一个简单邮件.只包含标题和文本内容。
	 * 使用SimpleMailMessage封装发件地址、收件地址、邮件主题、邮件文本内容。
	 * */
	@RequestMapping("/text")
	public String sendSimpleTextMail(){
		//1、创建一个
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		//设置邮件服务器(mail server)主机地址
		mailSender.setHost("smtp.163.com");
		
		//发送邮件的服务器的用户名和密码(实际上就是登录发送邮件服务器的用户名和密码)
		mailSender.setUsername("xxxx@163.com");
		mailSender.setPassword("123456");
		
		Properties properties=new Properties();
		//开启服务器认证，认证UserName和PassWord是否正确
		properties.put("mail.smtp.auth", "true");
		//设置超时
		properties.put("mail.smtp.timeout", "3000");
		
		mailSender.setJavaMailProperties(properties);
		
		//邮件信息对象：用于封装：发送地址、接收地址、主题、文本内容等
		SimpleMailMessage mailMessage=new SimpleMailMessage();
		//设置邮件接收的地址
		mailMessage.setTo("yyyyyy@qq.com");
		//设置邮件发送的地址
		mailMessage.setFrom("xxxxx@163.com");
		//设置邮件发送的主题
		mailMessage.setSubject("hello");
		//设置邮件发送的文本内容
		mailMessage.setText("how are you!");
		
		//发送邮件
		mailSender.send(mailMessage);
		
		return "home";
	}
	
	/**
	 * 发送HTML格式的邮件内容。
	 * 使用MimeMessage封装发件地址、收件地址、邮件主题、邮件文本内容。
	 * @throws MessagingException 
	 * */
	@RequestMapping("/html")
	public String sendHtmlMail() throws MessagingException{
		
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		//设置邮件服务器(mail server)主机地址
		mailSender.setHost("smtp.163.com");
		
		//发送邮件的服务器的用户名和密码(实际上就是登录发送邮件服务器的用户名和密码)
		mailSender.setUsername("xxxxx@163.com");
		mailSender.setPassword("123456");
		
		Properties properties=new Properties();
		//开启服务器认证，认证UserName和PassWord是否正确
		properties.put("mail.smtp.auth", "true");
		//设置超时
		properties.put("mail.smtp.timeout", "3000");
		
		mailSender.setJavaMailProperties(properties);
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
		
		mimeMessageHelper.setTo("yyyyyyy@qq.com");
		mimeMessageHelper.setFrom("xxxxxx@163.com");
		mimeMessageHelper.setSubject("Boss");
		/**
		 * 做邮箱校验肯定一个超链接给用户注册的邮箱
		 * 超链接构建：
		 * 1、URL  用户注册网站的URL
		 * 2、参数       用户名  密码  用户邮件地址  激活码    加密
		 * */
		mimeMessageHelper.setText("<h4>hahahaha</h4>", true);
		
		mailSender.send(mimeMessage);
		
		return "home";
	}
	
	
	/**
	 * 发送嵌套图片资源的邮件内容
	 * @throws MessagingException 
	 * */
	@RequestMapping(value="/img",method=RequestMethod.POST)
	public String sendImgMessage(HttpServletRequest request) throws MessagingException{
		
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		//设置邮件服务器(mail server)主机地址
		mailSender.setHost("smtp.163.com");
		
		//发送邮件的服务器的用户名和密码(实际上就是登录发送邮件服务器的用户名和密码)
		mailSender.setUsername("xxxxxx@163.com");
		mailSender.setPassword("123456");
		
		Properties properties=new Properties();
		//开启服务器认证，认证UserName和PassWord是否正确
		properties.put("mail.smtp.auth", "true");
		//设置超时
		properties.put("mail.smtp.timeout", "3000");
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		//multipart=true 表示将采用二进制流的形式发送文件内容，不对文件进行编码，这个时候可以在内容中嵌入图片资源。
		//实际上把WEB服务器上的资源上传到邮件服务器,再由邮件服务器发送到另一个邮件服务器
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		
		mimeMessageHelper.setTo("yyyyyyy@qq.com");
		mimeMessageHelper.setFrom("xxxxxxxx@163.com");
		mimeMessageHelper.setSubject("Boss");
		mimeMessageHelper.setText("<h4>this is an image</h4><br><img src=\"cid:ok\"/>", true);
		//获取本地WEB服务器上的图像资源路径
		String path=request.getSession().getServletContext().getRealPath("/resources/images/timg.jpg");
		FileSystemResource imgResource=new FileSystemResource(path);
		
		mimeMessageHelper.addInline("ok", imgResource);
		
		mailSender.send(mimeMessage);
		
		return "home";
	}
	
	
	/**
	 * 上传附件
	 * @throws MessagingException 
	 * */
	@RequestMapping(value="/attachment",method=RequestMethod.POST)
	public String sendAttachmentEmail(HttpServletRequest request) throws MessagingException{
		
		JavaMailSenderImpl mailSender=new JavaMailSenderImpl();
		
		//设置邮件服务器(mail server)主机地址
		mailSender.setHost("smtp.163.com");
		
		//发送邮件的服务器的用户名和密码(实际上就是登录发送邮件服务器的用户名和密码)
		mailSender.setUsername("xxxxxxx@163.com");
		mailSender.setPassword("123456");
		
		Properties properties=new Properties();
		//开启服务器认证，认证UserName和PassWord是否正确
		properties.put("mail.smtp.auth", "true");
		//设置超时
		properties.put("mail.smtp.timeout", "3000");
		
		MimeMessage mimeMessage = mailSender.createMimeMessage();
		
		//multipart=true 表示将采用二进制流的形式发送文件内容，不对文件进行编码，这个时候可以在内容中嵌入图片资源。
		//实际上把WEB服务器上的资源上传到邮件服务器,再由邮件服务器发送到另一个邮件服务器
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage,true);
		
		mimeMessageHelper.setTo("yyyyyy@qq.com");
		mimeMessageHelper.setFrom("xxxxxxx@163.com");
		mimeMessageHelper.setSubject("Boss");
		mimeMessageHelper.setText("<h4>this is an attachment</h4><br>", true);
		//获取本地WEB服务器上的图像资源路径
		String path=request.getSession().getServletContext().getRealPath("/resources/images/timg.jpg");
		FileSystemResource imgResource=new FileSystemResource(path);
		
		//设置附件
		mimeMessageHelper.addAttachment("timg.jpg", imgResource);
		
		mailSender.send(mimeMessage);
		
		
		return "home";
	}
	
}
