package xin.hlao.bookstore.orders.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.MapHandler;
import org.apache.commons.dbutils.handlers.MapListHandler;

import cn.itcast.commons.CommonUtils;
import cn.itcast.jdbc.TxQueryRunner;
import xin.hlao.bookstore.book.domain.Book;
import xin.hlao.bookstore.cart.domain.CartItem;
import xin.hlao.bookstore.orders.domain.Order;
import xin.hlao.bookstore.orders.domain.OrderItem;

public class OrderDao {
	QueryRunner qr=new TxQueryRunner();

	//添加订单
	public void add_order(Order order) {
		String sql="insert into orders values(?,?,?,?,'','',?)";
		Object[] params= {order.getOid(),order.getOrdertime(),order.getTotal(),
				order.getState(),order.getOwner().getUid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	//添加订单里的订单条目（批量添加）
	public void add_orderitem(List<OrderItem> orderitemlist) {
		String sql="insert into orderitem values(?,?,?,?,?)";
		//二维数组 object[多少行记录][记录中的信息];
		Object[][] params_s=new Object[orderitemlist.size()][];
		//通过遍历记录条数长度，一条条参数加进去，得出多条参数
		for(int i=0;i<orderitemlist.size();i++) {
			OrderItem orderitem=orderitemlist.get(i);
			//每遍历一次 对应生成一条参数
			Object[] params=new Object[]{orderitem.getIid(),orderitem.getCount(),orderitem.getSubtotal(),
					orderitem.getOrder().getOid(),orderitem.getBook().getBid()};
			params_s[i]=params;
		}
		try {
			//多条记录同时执行sql语句（batch批量）
			qr.batch(sql, params_s);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	
	
	/**
	 * 查找orders
	**/
	//第一步：利用uid来把order全部信息查找出来
	public List<Order> finditemsbyuid(String uid) {
		String sql="select * from orders where uid=?";
		Object[] param= {uid};
		try {
			List<Order> orderlist=qr.query(sql,new BeanListHandler<Order>(Order.class),param);
			for (Order order : orderlist) {
				//自己定义一个方法去加载orderitem类
				load_orderitems(order);
			}
			return orderlist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//第二步：用map方式把orderitem和book的全部信息装载起来
	//（因为查出来的数据是两个javabean加起来的，所以只能用MaplistHandler）
	private void load_orderitems(Order order) {
		String sql="select * from orderitem o,book b where o.bid=b.bid and oid=?";
		Object[] param= {order.getOid()};
		try {
			List<Map<String, Object>> maplist=qr.query(sql,new MapListHandler(), param);
			//自己定义一个方法把MapListHander转化为BeanListHander
			List<OrderItem> orderitemlist=to_orderitemlist(maplist);
			order.setOrderitemlist(orderitemlist);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//第三步：通过遍历把MapListHander转化为BeanListHander
	private List<OrderItem> to_orderitemlist(List<Map<String, Object>> maplist) {
		List<OrderItem> orderitemlist=new ArrayList<>();
		for (Map<String, Object> map : maplist) {
			//自定义一个方法去把List里的一个个对象利用map装载起来
			OrderItem orderitem=to_orderitem(map);
			orderitemlist.add(orderitem);
		}
		return orderitemlist;
	}
	
	//第四步：把一个个orderitem对象通过map装载完成
	private OrderItem to_orderitem(Map<String, Object> map) {
		OrderItem orderitem=CommonUtils.toBean(map, OrderItem.class);
		Book book=CommonUtils.toBean(map, Book.class);
		orderitem.setBook(book);
		return orderitem;
	}
	
	
	//根据oid来查找order
	public Order findorderbyoid(String oid) {
		String sql="select * from orders where oid=?";
		Object[] param= {oid};
		try {
			Order order=qr.query(sql,new BeanHandler<>(Order.class),param);
			//把里面的都加载了
			load_orderitems(order);
			return order;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//管理员查找全部订单
	public List<Order> findallorder() {
		// TODO Auto-generated method stub
		String sql="select * from orders";
		try {
			List<Order> orderlist=qr.query(sql, new BeanListHandler<>(Order.class));
			for(Order order:orderlist) {
				load_orderitems(order);
			}
			return orderlist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} 
	}
	
	//管理员根据订单状态查找订单
	public List<Order> findorderbystate(Integer state) {
		// TODO Auto-generated method stub
		String sql="select * from orders where state=?";
		Object[] param={state};
		try {
			List<Order> orderlist=qr.query(sql,new BeanListHandler<>(Order.class), param);
			for(Order order:orderlist) {
				load_orderitems(order);
			}
			return orderlist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public void updateorders(String oid) {
		// TODO Auto-generated method stub
		String sql="update orders set state=3 where oid=?";
		Object[] param= {oid};
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//补全订单信息去购买页面
	public void com_order(Order order) {
		// TODO Auto-generated method stub
		String sql="update orders set cellphone=?,address=? where oid=?";
		Object[] params= {order.getCellphone(),order.getAddress(),order.getOid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
	
	//用户付款后修改订单状态为2
	public void buy_updateorders(String oid) {
		// TODO Auto-generated method stub
		String sql="update orders set state=2 where oid=?";
		Object[] param= {oid};
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//利用bid来查找订单条目
	public List<OrderItem> finditembybid(String bid) {
		// TODO Auto-generated method stub
		String sql="select * from orderitem where bid=?";
		Object[] param= {bid};
		try {
			return qr.query(sql,new BeanListHandler<OrderItem>(OrderItem.class), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	//用户改变订单状态为4
	public void changestatebyoid(String oid) {
		// TODO Auto-generated method stub
		String sql="update orders set state=4 where oid=?";
		Object[] param= {oid};
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//删除订单里你订单条目
	public void delect_orderitem(Order order) {
		// TODO Auto-generated method stub
		System.out.println(order);
		String sql="delete from orderitem where oid=?";
		Object[] param= {order.getOid()};
		try {
			qr.update(sql,param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	//删除订单
	public void delect_order(Order order) {
		// TODO Auto-generated method stub
		String sql="delete from orders where oid=?";
		Object[] param= {order.getOid()};
		try {
			qr.update(sql,param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
}
