package xin.hlao.bookstore.admin.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.admin.domain.Admin;
import xin.hlao.bookstore.admin.service.AdminException;
import xin.hlao.bookstore.admin.service.AdminService;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AdminServlet extends BaseServlet {
	private AdminService as=new AdminService();
	
	//管理员登陆
	public String adminlogin(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Admin admin=CommonUtils.toBean(request.getParameterMap(), Admin.class);
		String adminname=admin.getAdminname();
		String adminpassword=admin.getAdminpassword();
		if(adminname==null||adminname.trim().isEmpty()||
				adminpassword==null||adminpassword.trim().isEmpty()) {
			request.setAttribute("error", "有信息为空！");
			return"f:/adminjsps/login.jsp";
		}
		try {
			as.adminlogin(admin);
		} catch (AdminException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("admin", admin);
			return"f:/adminjsps/login.jsp";
		}
		request.getSession().setAttribute("admin", admin);
		return"f:/adminjsps/admin/main.jsp";
	}

	//管理员退出
	public String exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		request.getSession().invalidate();
		return"f:/adminjsps/login.jsp";
	}
}
