package xin.hlao.bookstore.cart.domain;

import java.math.BigDecimal;

import xin.hlao.bookstore.book.domain.Book;

//购物车
public class CartItem {
	private Book book;
	private Integer count;
	
	public CartItem() {
		super();
	}

	public CartItem(Book book, Integer count) {
		super();
		this.book = book;
		this.count = count;
	}
//	计算出单本书的总和费用
	public double getsubtotal() {
		BigDecimal b1=new BigDecimal(book.getPrice()+"");
		BigDecimal b2=new BigDecimal(count+"");
//		价钱 * 数量
		return b1.multiply(b2).doubleValue();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Override
	public String toString() {
		return "CartItem [book=" + book + ", count=" + count + "]";
	}
	
}
