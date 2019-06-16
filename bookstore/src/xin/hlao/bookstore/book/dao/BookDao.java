package xin.hlao.bookstore.book.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.itcast.jdbc.TxQueryRunner;
import xin.hlao.bookstore.book.domain.Book;
import xin.hlao.bookstore.category.domain.Category;

public class BookDao {
	
	 QueryRunner qr=new TxQueryRunner();
	
//	 查询全部书籍
	public List<Book> findallbook() {
		String sql="select * from book where state=1";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
	} 
//通过类别来查找书籍
	public List<Book> findbookbyc(String cid) {
		String sql="select * from book where cid=? and state=1";
		Object[] param= {cid};
		try {
		    List<Book> booklist=qr.query(sql,new BeanListHandler<Book>(Book.class),param);
		    return booklist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	}

	//通过bid来查找单本书籍的信息
	public Book showbookbybid(String bid) {
		String sql="select * from book where bid=? and state=1";
		Object[] param= {bid};
		try {
			Book book=qr.query(sql,new BeanHandler<Book>(Book.class),param);
			loadbook(book); 
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//把book库里面的cid查询出来
	private void loadbook(Book book) {
		// TODO Auto-generated method stub
		String sql="select cid from book where bid=?";
		Object[] param= {book.getBid()};
		try {
			String cid=(String) qr.query(sql,new ScalarHandler(),param);
			Category category=loadbook_category(cid);
			book.setCategory(category);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	//把book里面的category类加载完成
	private Category loadbook_category(String cid) {
		// TODO Auto-generated method stub
		String sql="select * from category where cid=?";
		Object[] param= {cid};
		try {
			return qr.query(sql,new BeanHandler<>(Category.class),param);
		} catch (SQLException e) {
			throw new RuntimeException(e); 
		}
	}
	
	//修改指定书籍
	public void updatebook(Book book) {
		// TODO Auto-generated method stub
		System.out.println(book);
		String sql="update book set bname=?,price=?,author=?,evaluate=?,cid=? where bid=?";
		Object[] params= {book.getBname(),book.getPrice(),book.getAuthor(),
				book.getEvaluate(),book.getCategory().getCid(),book.getBid()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//管理员下架指定书籍（只是改变其中一个表值导致查询不出)
	public void downbook(String bid) { 
		// TODO Auto-generated method stub
		String sql="update book set state=0 where bid=?";
		Object[] param= {bid};
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//管理员添加书籍
	public void addbook(Book book) {
		// TODO Auto-generated method stub
		String sql="insert into book values(?,?,?,?,?,?,?,1)";
		Object[] params= {book.getBid(),book.getBname(),book.getPrice(),
				book.getAuthor(),book.getImage(),book.getEvaluate(),
				book.getCategory().getCid()};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//管理员寻找下架书籍（寻找state为0的书籍）
	public List<Book> down_findallbook() {
		// TODO Auto-generated method stub
		String sql="select * from book where state=0";
		try {
			return qr.query(sql, new BeanListHandler<Book>(Book.class));
		} catch (SQLException e) {
		throw new RuntimeException(e);
		}
	}
	
	//管理员查找下架书籍详情
	public Book down_showbookbybid(String bid) {
		// TODO Auto-generated method stub
		String sql="select * from book where bid=? and state=0";
		Object[] param= {bid};
		try {
			Book book=qr.query(sql,new BeanHandler<Book>(Book.class),param);
			loadbook(book); 
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//管理员删除下架图书
	public void delectbook(String bid) {
		// TODO Auto-generated method stub
		String sql="delete from book where bid=?";
		Object[] param= {bid};
		try {
			qr.update(sql,param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	
	//管理员恢复上架（只是把state修改为1）
	public void recoverbook(String bid) {
		// TODO Auto-generated method stub
		String sql="update book set state=1 where bid=?";
		Object[] params= {bid};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	public List<Book> down_findbookbyc(String cid) {
		// TODO Auto-generated method stub
		String sql="select * from book where cid=? and state=0";
		Object[] param= {cid};
		try {
		    List<Book> booklist=qr.query(sql,new BeanListHandler<Book>(Book.class),param);
		    return booklist;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}	
	} 
}
