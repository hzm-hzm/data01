package xin.hlao.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.omg.CORBA.UserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xin.hlao.bean.User;
import xin.hlao.bean.UserExample;
import xin.hlao.bean.UserExample.Criteria;
import xin.hlao.dao.UserMapper;
import xin.hlao.service.UserService;
import xin.hlao.service.exception.MyException;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserMapper userMapper;

	@Override
	public User findOneUser(String uid) {
		// TODO Auto-generated method stub
		return userMapper.selectByPrimaryKey(uid);
	}

	@Override
	public void addOneUser(User user) throws MyException {
		// TODO Auto-generated method stub
//		System.err.println(user);
		List<User> users=findUserByEmail(user.getEmail());
		if(users.size()!=0)throw new MyException("email,已经被注册了！");
		users=findUserByphone(user.getPhone());
		if(users.size()!=0)throw new MyException("phone,已经被注册了！");

		userMapper.insert(user);	
	}

	@Override
	public List<User> findUserByEmail(String email) {
		// TODO Auto-generated method stub
		UserExample example=new UserExample();
		Criteria criterria=example.createCriteria();
		criterria.andEmailEqualTo(email);
		return userMapper.selectByExample(example) ;
	}

	@Override
	public List<User> findUserByphone(String phone) {
		// TODO Auto-generated method stub
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andPhoneEqualTo(phone);
		return userMapper.selectByExample(example);
	}

	@Override
	public void updateUser(User user, String uid) {
		// TODO Auto-generated method stub
		UserExample example=new UserExample();
		Criteria criteria=example.createCriteria();
		criteria.andUidEqualTo(user.getUid());
		userMapper.updateByExample(user, example);
	}

	@Override
	public void updateUserState(String uid) throws MyException {
		// TODO Auto-generated method stub
		User user=userMapper.selectByPrimaryKey(uid);
		if(user==null) throw new MyException("该验证码无效！");
		if(user.getState()==true) throw new MyException("已经激活了！该验证码无效！");
		
		user.setState(true);
		userMapper.updateByPrimaryKey(user);
		
	}

	@Override
	public void verifyUserLogin(@Valid User user) throws MyException {
		// TODO Auto-generated method stub
		List<User> users=findUserByEmail(user.getEmail());
		if(users.size()==0) throw new MyException("email,该用户不存在");
		System.err.println(users.get(0).getPassword() == user.getPassword());
		if(! users.get(0).getPassword().equals(user.getPassword()) ) throw new MyException("password,密码错误");
		if(users.get(0).getState()==false) throw new MyException
		("email,该账号还没有激活！（ps:到邮箱中进行激活操作。）");
		
	}

	


	
	
	
	
	
	
	
}
