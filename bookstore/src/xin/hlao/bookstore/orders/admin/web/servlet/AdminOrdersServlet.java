package xin.hlao.bookstore.orders.admin.web.servlet;

import cn.itcast.servlet.BaseServlet;
import xin.hlao.bookstore.orders.domain.Order;
import xin.hlao.bookstore.orders.service.OrdersService;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



public class AdminOrdersServlet extends BaseServlet {
	private OrdersService os=new OrdersService();
	
	//管理员查找全部订单
	public String findallorders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		List<Order> orderlist=os.findallorder();
		request.getSession().setAttribute("orderlist", orderlist);
		return"f:/adminjsps/admin/order/list.jsp";
	}

	//管理员根据订状态查找订单
	public String findordersbystate(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String state0=request.getParameter("state");
		Integer state=Integer.valueOf(state0);
		List<Order> orderlist=os.findorderbystate(state);
		request.getSession().setAttribute("orderlist", orderlist);
		return"f:/adminjsps/admin/order/list.jsp";
	}
	
	//管理员发货（改变订单状态）
	public String updateorders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String oid=request.getParameter("oid");
		os.updateorders(oid);
		List<Order> orderlist=os.findallorder();
		request.getSession().setAttribute("orderlist", orderlist);
		return"f:/adminjsps/admin/order/list.jsp";
}
}