package xin.hlao.bookstore.category.service;

import java.util.List;

import org.junit.Test;

import com.sun.org.apache.regexp.internal.recompile;

import xin.hlao.bookstore.category.dao.CategoryDao;
import xin.hlao.bookstore.category.domain.Category;

public class CategoryService {
	private CategoryDao cd=new CategoryDao();
	
	public List<Category> findallcategory(){
		return cd.findallcategory();
	}

	public Category findonecategory(String cid) {
		return cd.findonecategory(cid);
	}


	public void updatecategory(String cid,String cname) {
		cd.updatecategory(cid,cname);
	}
	
	public void delectcategory(String cid) throws CategoryException {
		Integer count=cd.findonecount(cid);
		if(count>0) throw new CategoryException("改类还有书籍！请转移或删除该类书籍后继续该操作！");
		cd.delectcategory(cid);
	}

	public void addcategory(Category category) {
		// TODO Auto-generated method stub
		cd.addcategory(category);
	}
}
