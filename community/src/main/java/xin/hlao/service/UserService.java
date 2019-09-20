package xin.hlao.service;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import xin.hlao.bean.User;
import xin.hlao.service.exception.MyException;

public interface UserService {
	
//	查找一个用户
	public User findOneUser(String uid);
	
	//添加一个用户
	public void addOneUser(User user) throws MyException;
	
//	通过邮箱查找用户
	public List<User> findUserByEmail(String email);
	
//  通过电话查找用户
	public List<User> findUserByphone(String phone);


//	修改某个用户的信息
	public void updateUser(User user,String picture);
	
//  修改某个用户的激活状态
	public void updateUserState(String uid) throws MyException;

//	验证用户登录
	public void verifyUserLogin(@Valid User user) throws MyException;
	
}
