package xin.hlao.bookstore.book.admin.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.book.domain.Book;
import xin.hlao.bookstore.book.service.BookException;
import xin.hlao.bookstore.book.service.BookService;
import xin.hlao.bookstore.category.domain.Category;
import xin.hlao.bookstore.category.service.CategoryService;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.w3c.dom.ls.LSInput;


public class AdminBookServlet extends BaseServlet {
	private BookService bs=new BookService();
	private CategoryService cs=new CategoryService();
	
	//管理员查找全部书籍
	public String findallbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Book> booklist=bs.findallbook();
		request.setAttribute("booklist", booklist);
		return"f:/adminjsps/admin/book/list.jsp";
	}

	//管理员查找之指定书籍详情
	public String findonebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bid=request.getParameter("bid");
		
		String down=request.getParameter("down");
		//判定是否为查询下架书籍的请求
		if(down.equals("down")) {
			Book book=bs.down_showbookbybid(bid);
			request.setAttribute("book", book);
			request.setAttribute("down", "down");
			return"f:/adminjsps/admin/book/desc.jsp"; 
		}
		Book book=bs.showbookbybid(bid);
		request.setAttribute("book", book);
		return"f:/adminjsps/admin/book/desc.jsp"; 
}
	
	//管理员去修改指定书籍详情的页面
	public String to_updatebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bid=request.getParameter("bid");
		Book book=bs.showbookbybid(bid);
		List<Category> categorylist=cs.findallcategory();
		request.setAttribute("categorylist", categorylist);
		request.setAttribute("book", book);
		return"f:/adminjsps/admin/book/ament.jsp"; 
	}
	
	//管理员修改指定书籍
	public String updatebook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		Book book=CommonUtils.toBean(request.getParameterMap(), Book.class);
		String cid=request.getParameter("cid");
		String image=request.getParameter("image");
		book.setImage(image);
		Category category=cs.findonecategory(cid);
		book.setCategory(category);
		List<Category> categorylist=cs.findallcategory();
		
		String bname=book.getBname();
		Map<String, String> error=new HashMap<String,String>();
		if(bname.trim().isEmpty()||bname==null) {
			error.put("bname", "不能为空！");
		}
		Double price=book.getPrice();
		if(price==null) {
			error.put("price", "不能为空！");
		}else if(price<=0) {
			error.put("price", "价格不能少于0");
		}
		String author=book.getAuthor();
		if(author.trim().isEmpty()||author==null) {
			error.put("author", "不能为空！");
		}
		if(error.size()>0) {
			request.setAttribute("error", error);
			request.setAttribute("book",book);
			request.setAttribute("categorylist", categorylist);
			return "f:/adminjsps/admin/book/ament.jsp";
		}
		
		bs.updatebook(book);
		request.setAttribute("book",book);
		request.setAttribute("categorylist", categorylist);
		return "f:/adminjsps/admin/book/desc.jsp";
}
	//管理员下架指定书籍（只是改变其中一个表值导致查询不出）
	public String downbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bid=request.getParameter("bid");
		bs.downbook(bid);
		List<Book> booklist=bs.findallbook();
		request.setAttribute("booklist", booklist);
		request.setAttribute("mm", "xxxxxxxxxxxx"); 
		return "f:/adminjsps/admin/book/list.jsp"; 
	}
	
	//管理员去添加书籍界面
	public String to_addbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Category> categorylist=cs.findallcategory();
		request.setAttribute("categorylist", categorylist);
		return "f:/adminjsps/admin/book/add.jsp";
	}
	
	
	//管理员查找下架书籍
	public String down_findallbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Book> booklist=bs.down_findallbook();
		request.setAttribute("booklist", booklist);
		request.setAttribute("down", "down");
		return"f:/adminjsps/admin/book/list.jsp";
	}
	
	//管理员删除指定下架书籍
	public String delectbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bid=request.getParameter("bid");
		try {
			bs.delectbook(bid);
		} catch (BookException e) {
			request.setAttribute("msg", e.getMessage());
			request.setAttribute("down", "down");
			Book book=bs.down_showbookbybid(bid);
			request.setAttribute("book", book);
			request.setAttribute("down", "down");
			return"f:/adminjsps/admin/book/desc.jsp"; 
		}
		return down_findallbook(request, response);
	}
	
	//管理员恢复下架书籍
	public String recoverbook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String bid=request.getParameter("bid");
			bs.recoverbook(bid);
		return findallbook(request, response);
	}
	
	//管理员查找相关类别的全部书籍
	public String findallbookbycid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String cid=request.getParameter("cid");
		Map<String, Object> map=new HashMap<String,Object>();
		List<Book> booklist=bs.findbookbyc(cid);
		map.put("booklist", booklist);
		List<Book> down_booklist=bs.down_findbookbyc(cid);
		map.put("down_booklist", down_booklist);
		request.setAttribute("map", map);
		Category category=cs.findonecategory(cid);
		request.setAttribute("category", category);
		return "f:/adminjsps/admin/book/alist.jsp";
	}
}