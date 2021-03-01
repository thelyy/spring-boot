package com.yc.wowo.util;

import java.text.SimpleDateFormat;


import java.util.Date;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "spring.mail")
public class SendEMailUtil {
	@Value("${spring.mail.username}")
	private String sendEmail;
	
	@Autowired
	private JavaMailSender mailSender;

	
	public boolean sendEmail(String recieveEmail, String code, String nickName) {
		if(StringUtil.checkNull(recieveEmail, code, nickName)) {
			return false;
		}
		
		
		try {
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(mailMessage);
			
			messageHelper.setTo(recieveEmail);
			messageHelper.setFrom(sendEmail);
			messageHelper.setSubject("窝窝注册中心");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy年MM月dd日 HH:mm:ss");
			String str = "<!DOCTYPE html><html><head><meta charset='UTF-8'></head><body><p style='font-size: 20px; font-weight: bold;' >尊敬的:"+nickName+",你好!</p>" 
					+"<p style='text-indent: 2em; font-size: 20px'>欢迎注册窝窝商城，你本次的密码是" 
					+"<span style='font-size: 30px; font-weight: bold; color: red'>"+code+"</span>,3分钟之内有效，请尽快使用!</p>"
					+"<p style='text-align: right; padding-right:20px;'>" 
					+"<a href='http://www.hyycinfo.com' style='font-size: 18px'>衡阳市源城信息公司</a></p>" 
					+"<span style='font-size: 18px; float: right; margin-right: 60px;'>"+sdf.format(new Date())+"</span></body></html>";
			
			messageHelper.setText(str, true);
			mailSender.send(mailMessage);
			return true;
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
		
	}
}
