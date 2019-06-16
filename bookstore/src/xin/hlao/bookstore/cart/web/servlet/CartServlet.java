package xin.hlao.bookstore.cart.web.servlet;

import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.book.domain.Book;
import xin.hlao.bookstore.book.service.BookService;
import xin.hlao.bookstore.cart.domain.Cart;
import xin.hlao.bookstore.cart.domain.CartItem;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class CartServlet extends BaseServlet {
	private BookService bs=new BookService();
	
//	购物车添加商品
	public String add(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		if(cart==null) {
			request.setAttribute("msg", "你还没有登录了！");
			request.getRequestDispatcher("/jsps/user/login.jsp").forward(request, response);
		}
		
		CartItem cartitem=new CartItem();
		String bid=request.getParameter("bid");
		Book book=bs.showbookbybid(bid);
		String number=request.getParameter("number");
		Integer count=Integer.valueOf(number);
		cartitem.setCount(count);
		cartitem.setBook(book);
		cart.add(cartitem);
		return "f:/jsps/cart/list.jsp";
	}
	
//	指定删除购物车条目
	public String delect(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		String bid=request.getParameter("bid");
		cart.remove(bid);
		return "f:/jsps/cart/list.jsp";
	}
	
//把购物车清空
	public String clean(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
			
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		cart.clean();
		return "f:/jsps/cart/list.jsp";
	}
}
