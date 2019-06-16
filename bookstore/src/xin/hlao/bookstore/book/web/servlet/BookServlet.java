package xin.hlao.bookstore.book.web.servlet;

import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.book.domain.Book;
import xin.hlao.bookstore.book.service.BookService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class BookServlet extends BaseServlet {
	private BookService bs=new BookService();
	
//	查找全部书籍
	public String findallbook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		List<Book> booklist=bs.findallbook();
		request.setAttribute("booklist", booklist);
		return "f:/jsps/book/list.jsp";
	}
	
//按照分类查找书籍
	public String findbookbyc(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String cid=request.getParameter("cid");
		String cname=request.getParameter("cname");
		List<Book> booklist=bs.findbookbyc(cid);
		request.setAttribute("booklist", booklist);
		request.setAttribute("cname", cname);
		return "f:/jsps/book/list.jsp";
	}
	//根据bid来查找书本信息
	public String showbook(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
	
		String bid=request.getParameter("bid");
		Book book=bs.showbookbybid(bid);
		request.setAttribute("book", book);
		return "f:/jsps/book/desc.jsp";
	}
}
