package xin.hlao.tool;


import javax.mail.internet.MimeMessage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class EmailTool {
	private final Logger logger=LoggerFactory.getLogger(this.getClass());
	
	@Autowired
	private JavaMailSender sender;
	
	@Value("${spring.mail.username}")
	private  String from;
	
	//发送邮件
	public void sendEamil(String to,String subject,String content)throws Exception {
//		System.out.println(sender);
//		System.out.println(from);
		
		MimeMessage message_ =sender.createMimeMessage();
//		sender=new JavaMailSenderImpl();
//		SimpleMailMessage message=new SimpleMailMessage();
		MimeMessageHelper message = new MimeMessageHelper(message_, true);
		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(content,true);
		try {
			sender.send(message_);
			logger.info("测试邮件");
		} catch (MailException e) {
			// TODO Auto-generated catch block
			logger.error("测试发生异常！,"+e);
		}
	}
	
}
