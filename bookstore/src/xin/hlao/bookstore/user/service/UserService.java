package xin.hlao.bookstore.user.service;

import xin.hlao.bookstore.user.dao.UserDao;
import xin.hlao.bookstore.user.domain.User;

public class UserService {
	private UserDao ud=new UserDao();
	
//	注册时的验证
	public void rerify_regist(User form) throws UserException {
		User user=ud.finduserbyname(form.getUsername());
		if(user!=null) throw new UserException("改用户已经存在！");
		user=ud.finduserbyemail(form.getEmail());
		if(user!=null) throw new UserException("改邮箱已经被注册！");
		ud.adduser(form);
	}

//点击邮箱时验证验证码
	public void rerify_code(String code) throws UserException {
		User user=ud.finduserbycode(code);
		if(user==null) throw new UserException("验证码无效！"); 
		if(user.getState()==true) throw new UserException("你已经激活过了");
		ud.updatecode(user.getUid(),true);
	}
	
//登陆时的相关验证
	public User reify_login(User form) throws UserException {
		User user=ud.finduserbyname(form.getUsername());
		if(user==null) throw new UserException("该用户名不存在");
		if (!user.getPassword().equals(form.getPassword())) throw new UserException("密码错误！");
		if(user.getState()!=true) throw new UserException("该用户还没有激活！请到邮箱激活！");
		return user;
	}
}
