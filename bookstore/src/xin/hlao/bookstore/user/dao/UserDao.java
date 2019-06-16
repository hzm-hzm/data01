package xin.hlao.bookstore.user.dao;

import java.sql.SQLException;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.itcast.jdbc.TxQueryRunner;
import xin.hlao.bookstore.user.domain.User;

public class UserDao {

	QueryRunner qr=new TxQueryRunner();
	
//	通过账号查找用户
	public User finduserbyname(String username) {
		String sql="select * from tb_user where username=?";
		Object[] param= {username};
		try {
			return qr.query(sql,new BeanHandler<User>(User.class),param);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

//	通过邮箱查找用户
	public User finduserbyemail(String email) {
		String sql="select * from tb_user where email=?";
		Object[] param= {email};
		try {
			return qr.query(sql,new BeanHandler<User>(User.class),param);
		} catch (SQLException e) {
			throw new RuntimeException();
		}
	}

//通过验证码查找用户
	public User finduserbycode(String code) {
		String sql="select * from tb_user where code=?";
		Object[] param= {code};
		try {
			return qr.query(sql,new BeanHandler<User>(User.class), param);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

//利用uid查找该用户然后激活
	public void updatecode(String uid, boolean b) {
		String sql="update tb_user set state=? where uid=?";
		Object[] params= {b,uid};
		try {
			qr.update(sql, params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}
//添加用户
	public void adduser(User form) {
		// TODO Auto-generated method stub
		String sql="insert into tb_user values(?,?,?,?,?,?)";
		Object[] params= {form.getUid(),form.getUsername(),form.getPassword(),
				form.getEmail(),form.getCode(),form.getState()};
		try {
			qr.update(sql,params);
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}
	}

}
