package xin.hlao.bookstore.category.dao;

import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;
import org.junit.Test;

import cn.itcast.jdbc.TxQueryRunner;
import xin.hlao.bookstore.cart.domain.CartItem;
import xin.hlao.bookstore.category.domain.Category;

public class CategoryDao {
	QueryRunner qr=new TxQueryRunner();
	
//	查找全部分类
	public List<Category> findallcategory(){
		
		String sql="select * from category";  
		try { 
			List<Category> categorylist=qr.query(sql, new BeanListHandler<Category>(Category.class));
			//查询每一个类里面的书籍种类数
			String sql0="SELECT COUNT(*) FROM book WHERE cid=?";
			//通过循环一个个设置进去
			for(Category category:categorylist) {
				Object[] params= {category.getCid()};
				Number number= (Number) qr.query(sql0, new ScalarHandler(),params);
				int count=number.intValue();
				category.setCount(count);
			}
			return categorylist;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			throw new RuntimeException(); 
		}
	}
	 
	@Test 
	public void fun() {
		List<Category> cas=findallcategory();
		System.out.println(cas);
	}

	//查找指定类书籍数量
	public Integer findonecount(String cid) {
		String sql="SELECT COUNT(*) FROM book WHERE cid=?";
		Object[] params= {cid};
		try {
			Number number= (Number) qr.query(sql, new ScalarHandler(),params);
			Integer count=number.intValue();
			return count;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}
	

	//查找单个类别信息
	public Category findonecategory(String cid) {
		String sql="select * from category where cid=? ";
		Object[] param= {cid};
		try {
			return qr.query(sql, new BeanHandler<>(Category.class),param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//修改指定分类名
	public void updatecategory(String cid,String cname) {
		// TODO Auto-generated method stub
		String sql="update category set cname=? where cid=? ";
		Object[] params={cname,cid};
		try {
			 qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
		
	}

	//删除指定类别
	public void delectcategory(String cid) {
		// TODO Auto-generated method stub
		System.out.println(cid);
		String sql="delete from category where cid=? ";
		Object[] param= {cid};
		try {
			qr.update(sql, param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	//添加分类
	public void addcategory(Category category) {
		// TODO Auto-generated method stub
		String sql="insert into category values(?,?)";
		Object[] params= {category.getCid(),category.getCname()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

	
	
	
}
