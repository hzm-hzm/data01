package xin.hlao.bookstore.category.admin.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.book.service.BookService;
import xin.hlao.bookstore.category.domain.Category;
import xin.hlao.bookstore.category.service.CategoryException;
import xin.hlao.bookstore.category.service.CategoryService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AdminCategoryServlet extends BaseServlet {
   private CategoryService cs=new CategoryService();
   private BookService bs=new BookService();
	

	//管理员查找全部类别
	public String findallcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Category> categorylist=cs.findallcategory();
		request.setAttribute("categorylist",categorylist);
		return "f:/adminjsps/admin/category/list.jsp";
	}
	
	//显示详细信息
	public String desccategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cid=request.getParameter("cid");
		Category category=cs.findonecategory(cid);
		request.setAttribute("category", category);
		request.setAttribute("desc", "desc");
		return "f:/adminjsps/admin/category/desc.jsp";
	}
	
	//修改指定分类名字
	public String updatecategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cid=request.getParameter("cid");
		String cname=request.getParameter("cname");

		
		if(cname.trim().isEmpty()||cname==null) {
			request.setAttribute("desc", "desc");
			request.setAttribute("error", "不能为空！");
			return "f:/adminjsps/admin/category/desc.jsp";
		}
		
		List<Category> category=cs.findallcategory();
		cs.updatecategory(cid,cname);
		request.setAttribute("categorylist", category);
		return"f:/adminjsps/admin/category/list.jsp";
	}
	//管理员删除类别
	public String delectcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cid=request.getParameter("cid");
		try {
			cs.delectcategory(cid);
		} catch (CategoryException e) {
			request.setAttribute("msg", e.getMessage());
		}
		List<Category> categorylist=cs.findallcategory();
		request.setAttribute("categorylist", categorylist);
		return "f:/adminjsps/admin/category/list.jsp";
	}

	//管理员添加之指定类别
		public String addcategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			// TODO Auto-generated method stub
			request.setCharacterEncoding("utf-8");
			response.setContentType("text/html;charset=utf-8");
			String cname=request.getParameter("cname");
			
			if(cname.trim().isEmpty()||cname==null) {
				request.setAttribute("error", "不能为空！");
				return "f:/adminjsps/admin/category/desc.jsp";
			}
			
			Category category=new Category();
			category.setCid(CommonUtils.uuid());
			category.setCname(cname);
			category.setCount(0);
			cs.addcategory(category);
			List<Category> categorylist=cs.findallcategory();
			request.setAttribute("categorylist", categorylist);
			return "f:/adminjsps/admin/category/list.jsp";
		}
}
