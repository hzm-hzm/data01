package xin.hlao.bookstore.admin.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import cn.itcast.jdbc.TxQueryRunner;
import xin.hlao.bookstore.admin.domain.Admin;

public class AdminDao {
	  QueryRunner qr=new TxQueryRunner();


	//用账号查找
	public Admin findadminbyname(String adminname) {
		// TODO Auto-generated method stub
		String sql="select * from admin where adminname=?";
		Object[] param= {adminname};
		try {
			return qr.query(sql, new BeanHandler<>(Admin.class),param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
	  
}
