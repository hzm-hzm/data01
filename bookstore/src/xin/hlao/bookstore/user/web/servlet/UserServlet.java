package xin.hlao.bookstore.user.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.mail.Mail;
import cn.itcast.mail.MailUtils;
import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.cart.domain.Cart;
import xin.hlao.bookstore.cart.domain.CartItem;
import xin.hlao.bookstore.user.domain.User;
import xin.hlao.bookstore.user.service.UserException;
import xin.hlao.bookstore.user.service.UserService;

import java.io.IOException;
import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;





public class UserServlet extends BaseServlet {
	private UserService us=new UserService();

/**
 * 注册
 * */	
	public String regist(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8"); 
		response.setContentType("text/html;charset=utf-8");
		 
//		先把表单的传进UserBean中
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		String uid=CommonUtils.uuid(); 
		String code=CommonUtils.uuid()+CommonUtils.uuid();
		form.setUid(uid);
		form.setCode(code);
		form.setState(false);
		
		Map<String, String>error=new HashMap<String,String>();
		String username=form.getUsername();
		if(username==null||username.trim().isEmpty()) {
			error.put("username", "账号不能为空!");
		}else if(username.length()<3||username.length()>12) {
			error.put("username", "账号长度在4到11之间!");
		}
		String password=form.getPassword();
		if(password==null||password.trim().isEmpty()) {
			error.put("password", "密码不能为空!");
		}else if(password.length()<3||password.length()>12) {
			error.put("password", "密码长度在4到11之间!");
		}
		String email=form.getEmail();
		if(email==null||email.trim().isEmpty()) {
			error.put("email", "邮箱不能为空！");
		}else if(!email.matches("\\w+@\\w+\\.\\w+")){
			error.put("email", "邮箱格式有错！");
		}
		
		if(error.size()>0) {
			request.setAttribute("error", error);
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp"; 
		}
		
//		读取邮箱配置
		
		Properties pro=new Properties();
		pro.load(this.getClass().getClassLoader().getResourceAsStream("email.properties"));
		String host=pro.getProperty("host");
		String eusername=pro.getProperty("username");
		String epassword=pro.getProperty("password");
		String from=pro.getProperty("from");
		String to=form.getEmail();
		String subject=pro.getProperty("subject");
		String content=pro.getProperty("content");
		content=MessageFormat.format(content, form.getCode());
		
//		相当于登录创建会话
		Session session=MailUtils.createSession(host, eusername, epassword);
//		mail的信息
		Mail mail=new Mail(from, to, subject, content);
		
//		发送
		try {
			MailUtils.send(session, mail);
		} catch (MessagingException e1) {
			throw new RuntimeException(e1);
		}
		
		
		try {
			us.rerify_regist(form);
			request.setAttribute("msg", "注册成功！请到邮箱进行激活！");
			return "f:/jsps/msg.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return "f:/jsps/user/regist.jsp";
		}
	}

	
	/**
	 * 邮箱点击激活
	 * */		
	public String action(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String code=request.getParameter("code");
		try {
			us.rerify_code(code);
			request.setAttribute("msg", "请马上登陆");
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
		}
		return"f:/jsps/msg.jsp";
	}
	
	/**
	 * 登陆
	 * */
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
			
		User form=CommonUtils.toBean(request.getParameterMap(), User.class);
		
		Map<String,String> error=new HashMap<String,String>();
		String username=form.getUsername();
		if(username==null||username.trim().isEmpty()) {
			error.put("username", "账号不能为空!");
		}else if(username.length()<3||username.length()>12) {
			error.put("username", "账号长度在3到12之间!");
		}
		String password=form.getPassword();
		if(password==null||password.trim().isEmpty()) {
			error.put("password", "密码不能为空!");
		}else if(password.length()<3||password.length()>12) {
			error.put("password", "密码长度在3到12之间!");
		}
		if(error.size()>0) {
			request.setAttribute("error", error);
			request.setAttribute("form", form);
			return "f:/jsps/user/login.jsp";
		}
//		登陆的相关验证
		try {
			User user=us.reify_login(form);
			//登陆成功后把个人信息和购物车信息传入session域中
			request.getSession().setAttribute("session_user", user);
			request.getSession().setAttribute("cart",new Cart());
			return "f:/index.jsp";
		} catch (UserException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("form", form);
			return"f:/jsps/user/login.jsp";
		}
	}
	
	/**
	 * 退出
	 * */
	public String exit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
//		session死亡
		request.getSession().invalidate();
		return "f:/index.jsp";
	}
}
