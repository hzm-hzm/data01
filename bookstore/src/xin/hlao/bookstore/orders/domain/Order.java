package xin.hlao.bookstore.orders.domain;


import java.util.Date;
import java.util.List;

import xin.hlao.bookstore.user.domain.User;

//订单
public class Order {
	private String oid;
	private Date ordertime;
	private Double total;
	private Integer state;
	private String address;
	private String cellphone;
	private User owner;
	
	private List<OrderItem> orderitemlist;

	public Order() {
		super();
	}

	public Order(String oid, Date ordertime, Double total, Integer state, User owner) {
		super();
		this.oid = oid;
		this.ordertime = ordertime;
		this.total = total;
		this.state = state;
		this.owner = owner;
	}

	public String getOid() {
		return oid;
	}


	public void setOid(String oid) {
		this.oid = oid;
	}


	public Date getOrdertime() {
		return ordertime;
	}


	public void setOrdertime(Date ordertime) {
		this.ordertime = ordertime;
	}


	public Double getTotal() {
		return total;
	}


	public void setTotal(Double total) {
		this.total = total;
	}


	public Integer getState() {
		return state;
	}


	public void setState(Integer state) {
		this.state = state;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getCellphone() {
		return cellphone;
	}


	public void setCellphone(String cellphone) {
		this.cellphone = cellphone;
	}


	public List<OrderItem> getOrderitemlist() {
		return orderitemlist;
	}


	public void setOrderitemlist(List<OrderItem> orderitemlist) {
		this.orderitemlist = orderitemlist;
	}


	public User getOwner() {
		return owner;
	}




	public void setOwner(User owner) {
		this.owner = owner;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", ordertime=" + ordertime + ", total=" + total + ", state=" + state + ", address="
				+ address + ", cellphone=" + cellphone + ", owner=" + owner + "]";
	}





	
	
}
