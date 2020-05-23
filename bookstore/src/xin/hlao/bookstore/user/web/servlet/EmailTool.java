package xin.hlao.bookstore.user.web.servlet;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;

public class EmailTool {
		public static Session createSession(String host, final String username, final String password) {
			Properties prop = new Properties();
			prop.setProperty("mail.host", host);// 指定主机
			prop.setProperty("mail.smtp.auth", "true");// 指定验证为true
			prop.setProperty("mail.smtp.port", "465");//设置端口为465
			prop.setProperty("mail.smtp.socketFactory.port", "465");
			prop.setProperty("mail.smtp.ssl.enable", "true");

			// 创建验证器
			System.out.println(prop);
			Authenticator auth = new Authenticator() {
				public PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication(username, password);
				}
			};
			
			// 获取session对象
			return Session.getInstance(prop, auth);
		}
}
