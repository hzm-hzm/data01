package xin.hlao.bookstore.orders.web.servlet;

import cn.itcast.commons.CommonUtils;
import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.cart.domain.Cart;
import xin.hlao.bookstore.cart.domain.CartItem;
import xin.hlao.bookstore.orders.domain.Order;
import xin.hlao.bookstore.orders.domain.OrderItem;
import xin.hlao.bookstore.orders.service.OrdersService;
import xin.hlao.bookstore.user.domain.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class OrdersServlet extends BaseServlet {
	
	private OrdersService os=new OrdersService();

	public String creat_order(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//获取session域的购物车（cart）
		Cart cart=(Cart) request.getSession().getAttribute("cart");
		//获取session域的个人信息（session_user）
		User owner=(User) request.getSession().getAttribute("session_user");
		//把购物车中的所有条目显示出来
		Collection<CartItem> cartitemlist=cart.getCartItems();
		
		//补全order对象
		
		Order order=new Order();
		order.setOid(CommonUtils.uuid());
		order.setOrdertime(new Date());
		order.setTotal(cart.getsubtotal());
		order.setState(1);
		order.setOwner(owner);
		
		
		List<OrderItem> orderitemlist=new ArrayList<>();
		//利用遍历购物车的条目一条条装进订单的条目中
		for (CartItem cartitem:cartitemlist) {
			//每便利一次创建一个订单对象
			OrderItem orderitem=new OrderItem();
			orderitem.setIid(CommonUtils.uuid());
			orderitem.setCount(cartitem.getCount());
			orderitem.setSubtotal(cartitem.getsubtotal());
			orderitem.setBook(cartitem.getBook());
			orderitem.setOrder(order);
			//一个个对象加进集合
			orderitemlist.add(orderitem);
		}
		
		order.setOrderitemlist(orderitemlist);
		
		cart.clean();
		//添加到数据库
		
		os.add_orders(order);
		//添加进去后自动清空购物车
		
		request.getSession().setAttribute("order", order);
		return "/jsps/order/desc.jsp";
	}

	
	//根据用户uid来查找该用户的总订单
	public String showitemsbyuid(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		//如果没有登录操作这步会进行阻拦
		User owner=(User) request.getSession().getAttribute("session_user");
		if(owner==null) {
			return "f:/jsps/user/login.jsp";
		}
		
		//根据用户uid来查找该用户的总订单
		List<Order> orderlist=os.finditemsbyuid(owner.getUid());
		//把用户信息加载进去
		
		for(Order order:orderlist) {
			order.setOwner(owner);
		}
		request.getSession().setAttribute("orderlist", orderlist);
		return "/jsps/order/list.jsp";
	}
	
	//根据无付款状态来利用oid去对应付款
	public String findorderbyoid(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String oid=request.getParameter("oid");
		Order order=os.findorderbyoid(oid);
		request.getSession().setAttribute("order", order);
		return"f:/jsps/order/desc.jsp";
		
	}
	
	//模拟去银行付款的页面
	public String to_orderbuy(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String oid=request.getParameter("oid");
		Order order=os.findorderbyoid(oid);
		order=CommonUtils.toBean(request.getParameterMap(), Order.class);
		String cellphone=order.getCellphone();
		String address=order.getAddress();
		if(cellphone.trim().isEmpty()||cellphone==null 
				|| address.trim().isEmpty()||address==null) {
			request.setAttribute("order", order);
			request.setAttribute("error", "有信息为空！");
			return "f:/jsps/order/desc.jsp";
		}
		os.com_order(order);
		request.setAttribute("oid", oid);
		return "f:/jsps/order/buy.jsp";
	}
	
	//模拟银行付款界面
	public String orderbuy(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String username=request.getParameter("username");
		String password=request.getParameter("password");
		String oid=request.getParameter("oid");
		System.out.println(oid);
		Map<String, String> error=new HashMap<String,String>();
		if(username.trim().isEmpty()||username==null) {
			error.put("username", "不能为空");
		}
		if(password.trim().isEmpty()||password==null) {
			error.put("password", "不能为空");
		}
		
		if(error.size()>0) {
			request.setAttribute("error", error);
			request.setAttribute("username", username);
			return"f:/jsps/order/buy.jsp";
		}
//		String oid=(String) request.getSession().getAttribute("oid");
		os.buy_updateorders(oid);
		
		return "f:/index.jsp";

	}	
	
	//改变订单状态
	public String changestatebyoid(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String oid=request.getParameter("oid");
		os.changestatebyoid(oid);
		List<Order> orderlist=os.findallorder();
		request.getSession().setAttribute("orderlist", orderlist);
		return "f:/jsps/order/list.jsp";
	}
	
	//删除订单
	public String delectbyoid(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		
		String oid=request.getParameter("oid");
		Order order=os.findorderbyoid(oid);
		os.delectbyoid(order); 
		
		List<Order> orderlist=os.findallorder();
		request.getSession().setAttribute("orderlist", orderlist);
		return "f:/jsps/order/list.jsp";
	}
}
