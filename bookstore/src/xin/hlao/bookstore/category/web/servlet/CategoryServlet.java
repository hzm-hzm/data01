package xin.hlao.bookstore.category.web.servlet;

import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.category.domain.Category;
import xin.hlao.bookstore.category.service.CategoryService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CategoryServlet extends BaseServlet  {
	private CategoryService cs=new CategoryService();

	public String findallcategory(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;cahrset=utf-8");
		
		List<Category> categorylist=cs.findallcategory();
		request.setAttribute("categorylist", categorylist);
		return "f:/jsps/left.jsp"; 
}
}
