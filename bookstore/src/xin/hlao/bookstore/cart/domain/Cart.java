package xin.hlao.bookstore.cart.domain;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

//购物车操作
public class Cart {
	
//	bid做Key cartitem做value
	Map<String, CartItem> cartmap=new LinkedHashMap<String,CartItem>();
	
	
//	购物车增加商品
	public void add(CartItem cartitem) {
		//判断加入前是否存在这个商品
		//存在：
		if(cartmap.containsKey(cartitem.getBook().getBid())) {
		//查找出之前存在的这个商品
		CartItem _cartitem=cartmap.get(cartitem.getBook().getBid());
		//把久的数量和需要加入的数量加起来
		_cartitem.setCount(_cartitem.getCount()+cartitem.getCount());
		//然后更新到cartmap中
		cartmap.put(cartitem.getBook().getBid(), _cartitem);
	}
	else {
		//不存在：
		//直接把现在加入的更新到cartmap中
		cartmap.put(cartitem.getBook().getBid(), cartitem);
	}
		}
	
//	计算出购物车里的总和
	public Double getsubtotal() {
		BigDecimal total=new BigDecimal("0");
		//把cartmap中的key(bid)相同的做一类相加得到小计(subtotal);
		for(CartItem e:cartmap.values()) {
			BigDecimal subtotal=new BigDecimal(e.getsubtotal()+"");
			//然后把这些计算出的小计(subtotal)再相加得出购物车里全部的总和(total)
			total=total.add(subtotal);
		}
		return total.doubleValue();
		}
	
//	显示出全部购物车条目
	public Collection<CartItem> getCartItems(){
		return cartmap.values();
	}
	
//	根据key(bid)来删除指定的购物车中的条目
	public void remove(String bid) {
		cartmap.remove(bid);
	}
	
//	直接清空购物车全部条目
	public void clean() {
		cartmap.clear();
	}
			}
