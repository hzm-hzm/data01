package xin.hlao.bookstore.book.service;

import java.util.List;

import xin.hlao.bookstore.book.dao.BookDao;
import xin.hlao.bookstore.book.domain.Book;
import xin.hlao.bookstore.cart.domain.CartItem;
import xin.hlao.bookstore.category.dao.CategoryDao;
import xin.hlao.bookstore.category.domain.Category;
import xin.hlao.bookstore.orders.dao.OrderDao;
import xin.hlao.bookstore.orders.domain.OrderItem;

public class BookService {
	private BookDao bd=new BookDao();
	private CategoryDao cd=new CategoryDao();
	private OrderDao od=new OrderDao();
	
	public List<Book> findallbook() {
		List<Book> booklist=bd.findallbook();
		return booklist;
	} 
	public List<Book> down_findallbook() {
		// TODO Auto-generated method stub
		return bd.down_findallbook();
	}

	public List<Book> findbookbyc(String cid) {
		List<Book> booklist=bd.findbookbyc(cid);
		return booklist; 
	}
	
	public List<Book> down_findbookbyc(String cid) {
		List<Book> booklist=bd.down_findbookbyc(cid);
		return booklist; 
	}

	public Book showbookbybid(String bid) {
		Book book=bd.showbookbybid(bid); 
		return book; 
	}

	public void updatebook(Book book) {
		// TODO Auto-generated method stub
		bd.updatebook(book);
	}

	public void downbook(String bid) {
		// TODO Auto-generated method stub
		bd.downbook(bid);  
	}

	public void addbook(Book book) {
		// TODO Auto-generated method stub
		bd.addbook(book);
	}


	public Book down_showbookbybid(String bid) {
		// TODO Auto-generated method stub
		return bd.down_showbookbybid(bid);
	}

	public void delectbook(String bid) throws BookException {
		// TODO Auto-generated method stub
		List<OrderItem> orderitem=od.finditembybid(bid);
		if(orderitem.size()!=0) throw new BookException("有用户订单存在这书籍！暂时你不能删除！");
		bd.delectbook(bid);
	}

	public void recoverbook(String bid)  {
		// TODO Auto-generated method stub
//		Book book=bd.down_showbookbybid(bid);
//		Category category=cd.findonecategory(book.getCategory().getCid());
//		if(category==null) throw new BookException("该类已经被删除，请重新选择新类别！");
		bd.recoverbook(bid);
		
	}
	
}
