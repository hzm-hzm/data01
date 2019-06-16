package xin.hlao.bookstore.orders.service;

import java.sql.SQLException;
import java.util.List;

import cn.itcast.jdbc.JdbcUtils;
import xin.hlao.bookstore.orders.dao.OrderDao;
import xin.hlao.bookstore.orders.domain.Order;

public class OrdersService {
	private OrderDao od=new OrderDao(); 

	public void add_orders(Order order) {
	
		try {
			//开始业务
			JdbcUtils.beginTransaction();
			
			od.add_order(order);
			od.add_orderitem(order.getOrderitemlist());

			
			//开始事务
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				//回滚事务
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
		
	}

	public List<Order> finditemsbyuid(String uid) {
		List<Order>orderlist= od.finditemsbyuid(uid);
		return orderlist;
	}

	public Order findorderbyoid(String oid) {
		Order order=od.findorderbyoid(oid);
		return order;
	}

	public List<Order> findallorder() {
		return od.findallorder();
	}

	public List<Order> findorderbystate(Integer state) {
		return od.findorderbystate(state);
	}

	public void updateorders(String oid) {
		// TODO Auto-generated method stub
		od.updateorders(oid);
	}
	
	public void buy_updateorders(String oid) {
		// TODO Auto-generated method stub
		od.buy_updateorders(oid);
	}

	public void com_order(Order order) {
		// TODO Auto-generated method stub
		od.com_order(order);
	}

	public void changestatebyoid(String oid) {
		// TODO Auto-generated method stub
		od.changestatebyoid(oid);
	}

	public void delectbyoid(Order order) {
		// TODO Auto-generated method stub
		try {
			//开始业务
			JdbcUtils.beginTransaction();
			
			od.delect_orderitem(order);
			od.delect_order(order);
			

			//开始事务
			JdbcUtils.commitTransaction();
		} catch (SQLException e) {
			try {
				//回滚事务
				JdbcUtils.rollbackTransaction();
			} catch (SQLException e1) {
				throw new RuntimeException(e);
			}
		}
	}

	
}
